package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForCompositionBasedOnGivenSubject
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
abstract class BaseBuilder<
    SubjectT,
    ReportableT : Reportable,
    ReportableGroupT,
    SELF : BaseBuilder<SubjectT, ReportableT, ReportableGroupT, SELF>
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

    fun invisibleGroup(init: InvisibleProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(InvisibleProofGroupBuilder(proofContainer).build(init))

    fun invisibleFixedClaimGroup(holds: Boolean, init: InvisibleFixedClaimGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(InvisibleFixedClaimGroupBuilder(proofContainer, holds).build(init))

    fun fixedClaimGroup(
        description: InlineElement,
        representation: Any?,
        holds: Boolean,
        init: FixedClaimGroupBuilder<SubjectT>.() -> Unit
    ): Proof = add(FixedClaimGroupBuilder(proofContainer, description, representation, holds).build(init))

    fun reportableGroup(
        description: InlineElement,
        representation: Any?,
        init: ReportableGroupBuilder<SubjectT>.() -> Unit
    ): Reportable =
        add(ReportableGroupBuilder(proofContainer, description, representation).build(init))

    fun explanatoryGroup(init: ExplanatoryGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(ExplanatoryGroupBuilder(proofContainer).build(init))

    fun debugGroup(description: InlineElement, init: DebugGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(DebugGroupBuilder(proofContainer, description).build(init))

    fun inlineGroup(vararg inlineElements: InlineElement): Reportable = Reportable.inlineGroup(inlineElements.toList())


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

typealias AnyBuilder = BaseBuilder<*, *, *, *>
typealias AnyProofBuilder = BaseBuilder<*, Proof, ProofGroup, *>
typealias AnyReportableBuilder = BaseBuilder<*, Reportable, ReportableGroup, *>


abstract class BaseReportableBuilder<SubjectT, SELF : BaseReportableBuilder<SubjectT, SELF>>(
    proofContainer: ProofContainer<SubjectT>
) : BaseBuilder<SubjectT, Reportable, ReportableGroup, SELF>(proofContainer) {
    override fun buildSingle(singleChild: Reportable): Reportable = singleChild
}

abstract class BaseProofBuilder<SubjectT, SELF : BaseProofBuilder<SubjectT, SELF>>(
    proofContainer: ProofContainer<SubjectT>
) : BaseBuilder<SubjectT, Proof, ProofGroup, SELF>(proofContainer) {
    override fun buildSingle(singleChild: Reportable): Proof = when (singleChild) {
        is Proof -> singleChild
        else -> TODO("1.3.0 create group explaining that only one reportable was created and not a proof, without proof we cannot build a ProofGroup")
    }
}

class ProofBuilder<T> internal constructor(
    proofContainer: ProofContainer<T>
) : BaseProofBuilder<T, ProofBuilder<T>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.invisibleGroup(children)
}

class ProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : BaseProofBuilder<SubjectT, ProofGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.group(description, representation, children)
}

class InvisibleProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseProofBuilder<SubjectT, InvisibleProofGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.invisibleGroup(children)
}

class InvisibleFixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val holds: Boolean
) : BaseProofBuilder<SubjectT, InvisibleFixedClaimGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup = Proof.invisibleFixedClaimGroup(children, holds)
}


class FixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?,
    private val holds: Boolean
) : BaseProofBuilder<SubjectT, FixedClaimGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ProofGroup =
        Proof.fixedClaimGroup(description, representation, children, holds)
}

class ReportableGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : BaseReportableBuilder<SubjectT, ReportableGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ReportableGroup =
        Reportable.group(description, representation, children)
}

class ExplanatoryGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseReportableBuilder<SubjectT, ExplanatoryGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ReportableGroup =
        Reportable.proofExplanation(Proof.invisibleGroup(children))

    //TODO 1.3.0 add KDoc
    fun <SomeSubjectT> collect(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
    ): Boolean = proofContainer.collectForCompositionBasedOnGivenSubject(None, expectationCreatorWithUsageHints)
        .let { (proofs, oneCollected) ->
            addAll(proofs)
            oneCollected
        }

}

class DebugGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseReportableBuilder<SubjectT, DebugGroupBuilder<SubjectT>>(proofContainer) {

    override fun buildGroup(children: List<Reportable>): ReportableGroup =
        Reportable.debugGroup(description, children)
}
