package ch.tutteli.atrium.domain.reporting

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.ProofGroup
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable
import ch.tutteli.atrium.reporting.reportables.ReportableGroup

fun <T> ProofContainer<T>.buildSimpleProof(
    description: InlineElement,
    representation: Any?,
    test: (T) -> Boolean
): Proof = Proof.simple(description, representation, this.toTestFunction(test))

fun <T> ProofContainer<T>.toTestFunction(test: (T) -> Boolean): () -> Boolean = {
    this.maybeSubject.fold(falseProvider, test)
}

fun <T> ProofContainer<T>.buildProof(init: ProofBuilder<T>.() -> Unit): Proof =
    ProofBuilder(this).build(init)

@DslMarker
annotation class ReportableBuilderMarker

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
@ReportableBuilderMarker
abstract class GroupBasedReportableBuilder<
    SubjectT,
    ReportableT : Reportable,
    ReportableGroupT,
    SELF : GroupBasedReportableBuilder<SubjectT, ReportableT, ReportableGroupT, SELF>
    >(
    protected val proofContainer: ProofContainer<SubjectT>
) where ReportableGroupT : ReportableGroup, ReportableGroupT : ReportableT {
    @Suppress("UNCHECKED_CAST")
    private inline val self: SELF get() = this as SELF

    private val reportables = mutableListOf<Reportable>()

    fun <R : Reportable> add(r: R): R = r.also { reportables.add(it) }
    fun addAll(reportables: List<Reportable>): Unit = reportables.forEach(this::add)

    fun <R : Reportable> _domain(proofCreator: ProofContainer<SubjectT>.() -> R): R =
        add(proofContainer.proofCreator())

    fun simpleProof(description: InlineElement, representation: Any?, test: (SubjectT) -> Boolean): Proof =
        add(proofContainer.buildSimpleProof(description, representation, test))

    fun representationOnlyProof(representation: Any?, test: (SubjectT) -> Boolean): Proof =
        add(Proof.representationOnlyProof(representation, proofContainer.toTestFunction(test)))

    fun group(description: InlineElement, representation: Any?, init: ProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(ProofGroupBuilder(proofContainer, description, representation).build(init))

    fun invisibleGroup(init: InvisibleProofGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(InvisibleProofGroupBuilder(proofContainer).build(init))

    fun explanatoryGroup(init: ExplanatoryGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(ExplanatoryGroupBuilder(proofContainer).build(init))

    fun build(init: SELF.() -> Unit): ReportableT {
        //TODO 1.3.0 transform an unexpected exception into a failing proof
        self.also(init)
        return when (reportables.size) {
            0 -> TODO("1.3.0 create proof which tells wrong usage of buildProof -- no proof created")
            1 -> buildSingle(reportables.first())
            else -> buildGroup(reportables)
        }
    }

    protected abstract fun buildSingle(singleChild: Reportable): ReportableT
    protected abstract fun buildGroup(children: List<Reportable>): ReportableGroupT
}

abstract class GroupBasedProofBuilder<SubjectT, SELF : GroupBasedProofBuilder<SubjectT, SELF>>(
    proofContainer: ProofContainer<SubjectT>
) : GroupBasedReportableBuilder<SubjectT, Proof, ProofGroup, SELF>(proofContainer) {
    override fun buildSingle(singleChild: Reportable): Proof = when (singleChild) {
        is Proof -> singleChild
        else -> TODO("1.3.0 create group explaining that only one reportable was created and not a proof, without proof we cannot build a ProofGroup")
    }
}

class ProofBuilder<T> internal constructor(
    proofContainer: ProofContainer<T>
) : GroupBasedProofBuilder<T, ProofBuilder<T>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.invisibleGroup(children)
}

class ProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : GroupBasedProofBuilder<SubjectT, ProofGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.group(description, representation, children)
}

class InvisibleProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : GroupBasedProofBuilder<SubjectT, InvisibleProofGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.invisibleGroup(children)
}

class ExplanatoryGroupBuilder<SubjectT : Any>(
    proofContainer: ProofContainer<SubjectT>,
) : GroupBasedReportableBuilder<SubjectT, Reportable, ReportableGroup, ExplanatoryGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildSingle(singleChild: Reportable): Reportable = singleChild

    override fun buildGroup(children: List<Reportable>): ReportableGroup =
        Reportable.proofExplanation(Proof.invisibleGroup(children))

    @OptIn(ExperimentalComponentFactoryContainer::class)
    fun <T> collect(expectationCreator: Expect<T>.() -> Unit) : Unit = addAll(
        //TODO 1.3.0 CollectingExpect does not handle unexpected exceptions yet. Also, we have an open TODO to add a
        // flag to collectForComposition which should indicate whether appendAsGroup created a Reportable explaining
        // that no proofs where appended
        CollectingExpect<T>(maybeSubject = None, proofContainer.components).appendAsGroup(expectationCreator).getAssertions()
    )
}
