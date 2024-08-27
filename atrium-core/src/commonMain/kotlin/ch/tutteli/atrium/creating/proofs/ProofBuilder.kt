package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForComposition
import ch.tutteli.atrium.creating.collectForCompositionBasedOnGivenSubject
import ch.tutteli.atrium.reporting.reportables.*

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
    SelfT : BaseBuilder<SubjectT, ReportableT, SelfT>
    >(
    protected val proofContainer: ProofContainer<SubjectT>
) {
    @Suppress("UNCHECKED_CAST")
    private inline val self: SelfT get() = this as SelfT

    private val reportables = mutableListOf<Reportable>()

    fun <R : Reportable> add(r: R): R = r.also { reportables.add(it) }
    fun addAll(reportables: List<Reportable>): Unit = reportables.forEach(this::add)

    fun <R : Reportable> _core(proofCreator: ProofContainer<SubjectT>.() -> R): R =
        add(proofContainer.proofCreator())

    //TODO 1.3.0 add KDoc
    fun collect(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SubjectT>
    ): Boolean = proofContainer.collectForComposition(expectationCreatorWithUsageHints)
        .let { (proofs, oneCollected) ->
            addAll(proofs)
            oneCollected
        }

    fun simpleProof(description: InlineElement, representation: Any?, test: (SubjectT) -> Boolean): Proof =
        add(proofContainer.buildSimpleProof(description, representation, test))

    fun representationOnlyProof(representation: Any?, test: (SubjectT) -> Boolean): Proof =
        add(Proof.representationOnlyProof(representation, proofContainer.toTestFunction(test)))

    fun group(description: Reportable, representation: Any?, init: ProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(ProofGroupBuilder(proofContainer, description, representation).build(init))

    fun feature(
        description: Reportable,
        representation: Any?,
        init: FeatureProofGroupBuilder<SubjectT>.() -> Unit
    ): Proof = add(FeatureProofGroupBuilder(proofContainer, description, representation).build(init))

    fun invisibleGroup(init: InvisibleProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(InvisibleProofGroupBuilder(proofContainer).build(init))

    //TODO 1.3.0 this is smelly again, looks a bit like the same hack as we hade with ExplanatoryAssertionGroup which should not have been an Assertion which can fail
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
    ): Reportable = add(ReportableGroupBuilder(proofContainer, description, representation).build(init))

    fun proofExplanation(init: ProofExplanationGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(ProofExplanationGroupBuilder(proofContainer).build(init))

    fun errorExplanationGroup(
        description: InlineElement,
        init: ErrorExplanationGroupBuilder<SubjectT>.() -> Unit
    ): Reportable = add(ErrorExplanationGroupBuilder(proofContainer, description).build(init))

    fun informationGroup(
        description: InlineElement,
        init: InformationGroupBuilder<SubjectT>.() -> Unit
    ): Reportable = add(InformationGroupBuilder(proofContainer, description).build(init))


    fun debugGroup(description: InlineElement, init: DebugGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(DebugGroupBuilder(proofContainer, description).build(init))

    fun inlineGroup(vararg inlineElements: InlineElement): Reportable = Reportable.inlineGroup(inlineElements.toList())

    fun build(init: SelfT.() -> Unit): ReportableT {
        //TODO 1.3.0 transform an unexpected exception into a failing proof
        self.also(init)
        return build(reportables)
    }

    protected abstract fun build(children: List<Reportable>): ReportableT
}
//
typealias AnyBuilder = BaseBuilder<*, *,  *>
typealias AnyProofBuilder = BaseBuilder<*, Proof, *>
typealias AnyReportableBuilder = BaseBuilder<*, Reportable, *>

//abstract class BaseReportableBuilder<SubjectT, SelfT : BaseReportableBuilder<SubjectT, SelfT>>(
//    proofContainer: ProofContainer<SubjectT>
//) : BaseBuilder<SubjectT, Reportable, ReportableGroup, SelfT>(proofContainer) {
////    override fun buildSingle(singleChild: Reportable): Reportable = singleChild
//}
//
//abstract class BaseProofGroupBuilder<SubjectT, SelfT : BaseProofGroupBuilder<SubjectT, SelfT>>(
//    proofContainer: ProofContainer<SubjectT>
//) : BaseBuilder<SubjectT, Proof, SelfT>(proofContainer) {
//    override fun build(children: List<Reportable>): ProofGroup =
//        when (chil)
//    when (singleChild)
//    {
//        is Proof -> singleChild
//        else -> TODO("1.3.0 create group explaining that only one reportable was created and not a proof, without proof we cannot build a ProofGroup")
//    }
//}

class ProofBuilder<T> internal constructor(
    proofContainer: ProofContainer<T>
) : BaseBuilder<T, Proof, ProofBuilder<T>>(proofContainer) {

    override fun build(children: List<Reportable>): Proof =
        when (children.size) {
            1 -> when (val first = children.first()) {
                is Proof -> first
                else -> throw IllegalArgumentException("You need to add at least one Proof when building a Proof. Given: $first")
            }

            else -> Proof.invisibleGroup(children)
        }
}

abstract class BaseGroupBuilder<SubjectT,
    ReportableT : Reportable,
    SelfT : BaseBuilder<SubjectT, ReportableT, SelfT>
    >(
    proofContainer: ProofContainer<SubjectT>,
    private val factory: (children: List<Reportable>) -> ReportableT,
) : BaseBuilder<SubjectT, ReportableT, SelfT>(proofContainer) {
    override fun build(children: List<Reportable>): ReportableT = factory(children)
}

class ProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseGroupBuilder<SubjectT, ProofGroup, ProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.group(description, representation, children) }
)

class FeatureProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseGroupBuilder<SubjectT, FeatureProofGroup, FeatureProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.featureGroup(description, representation, children) }
)

class InvisibleProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseGroupBuilder<SubjectT, InvisibleProofGroup, InvisibleProofGroupBuilder<SubjectT>>(
    proofContainer,
    Proof::invisibleGroup
)

class InvisibleFixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val holds: Boolean
) : BaseGroupBuilder<SubjectT, InvisibleFixedClaimGroup, InvisibleFixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.invisibleFixedClaimGroup(children, holds) }
)

class FixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?,
    private val holds: Boolean
) : BaseGroupBuilder<SubjectT, FixedClaimGroup, FixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.fixedClaimGroup(description, representation, children, holds) }
)

class ReportableGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : BaseGroupBuilder<SubjectT, ReportableGroup, ReportableGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.group(description, representation, children) }
)

class ProofExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseGroupBuilder<SubjectT, ProofExplanation, ProofExplanationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.proofExplanation(Proof.invisibleGroup(children)) }
) {

    //TODO 1.3.0 add KDoc
    fun <SomeSubjectT> collectWithoutSubject(
        expectationCreatorWithUsageHints: ExpectationCreatorWithUsageHints<SomeSubjectT>
    ): Boolean = proofContainer.collectForCompositionBasedOnGivenSubject(None, expectationCreatorWithUsageHints)
        .let { (proofs, oneCollected) ->
            addAll(proofs)
            oneCollected
        }
}

class ErrorExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseGroupBuilder<SubjectT, ErrorExplanationGroup, ErrorExplanationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.errorExplanationGroup(description, children) }
)

class InformationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseGroupBuilder<SubjectT, InformationGroup, InformationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.informationGroup(description, children) }
)

class DebugGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseGroupBuilder<SubjectT, DebugGroup, DebugGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.debugGroup(description, children) }
)
