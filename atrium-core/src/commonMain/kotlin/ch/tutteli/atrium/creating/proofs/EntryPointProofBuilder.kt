package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForComposition
import ch.tutteli.atrium.creating.collectForCompositionBasedOnGivenSubject
import ch.tutteli.atrium.reporting.HorizontalAlignment
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.*

fun <T> ProofContainer<T>.buildSimpleProof(
    description: InlineElement,
    representation: Any?,
    test: (T) -> Boolean
): Proof = Proof.simple(description, representation, this.toTestFunction(test))

fun <T> ProofContainer<T>.toTestFunction(test: (T) -> Boolean): () -> Boolean = {
    this.maybeSubject.fold(falseProvider, test)
}


fun <T> ProofContainer<T>.buildProof(init: EntryPointProofBuilder<T>.() -> Unit): Proof =
    EntryPointProofBuilder(this).build(init)

/**
 * Used to prevent that one can use e.g. [ProofExplanationGroupBuilder.collectWithoutSubject] in a nested [ProofExplanationGroupBuilder.group].
 *
 * @since 1.3.0
 */
@DslMarker
annotation class ProofBuilderMarker

@ProofBuilderMarker
abstract class BaseBuilder<
    SubjectT,
    ReportableT : Reportable,
    ReportableElementT : Reportable,
    SelfT : BaseBuilder<SubjectT, ReportableT, ReportableElementT, SelfT>
    >(
    protected val proofContainer: ProofContainer<SubjectT>
) {
    @Suppress("UNCHECKED_CAST")
    private inline val self: SelfT get() = this as SelfT

    private val reportables = mutableListOf<ReportableElementT>()

    fun <R : ReportableElementT> add(r: R): R = r.also { reportables.add(it) }
    fun addAll(reportables: List<ReportableElementT>): Unit = reportables.forEach(this::add)
    fun addAll(vararg reportables: ReportableElementT): Unit = reportables.forEach(this::add)

    fun build(init: SelfT.() -> Unit): ReportableT {
        //TODO 1.3.0 transform an unexpected exception into a failing proof
        self.also(init)
        return build(reportables)
    }

    protected abstract fun build(children: List<ReportableElementT>): ReportableT
}


abstract class BaseGroupBuilder<
    SubjectT,
    ReportableT : Reportable,
    SelfT : BaseGroupBuilder<SubjectT, ReportableT, SelfT>
    >(
    proofContainer: ProofContainer<SubjectT>
) : BaseBuilder<SubjectT, ReportableT, Reportable, SelfT>(proofContainer) {

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

    fun failureExplanationGroup(
        description: InlineElement,
        init: FailureExplanationGroupBuilder<SubjectT>.() -> Unit
    ): Reportable = add(FailureExplanationGroupBuilder(proofContainer, description).build(init))

    fun informationGroup(
        description: InlineElement,
        init: InformationGroupBuilder<SubjectT>.() -> Unit
    ): Reportable = add(InformationGroupBuilder(proofContainer, description).build(init))

    fun debugGroup(description: InlineElement, init: DebugGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(DebugGroupBuilder(proofContainer, description).build(init))


    fun usageHintGroup(init: UsageHintGroupBuilder<SubjectT>.() -> Unit): Reportable =
        add(UsageHintGroupBuilder(proofContainer).build(init))


    fun inlineGroup(vararg inlineElements: InlineElement): InlineElement =
        Reportable.inlineGroup(inlineElements.toList())

    fun row(init: RowBuilder<SubjectT>.() -> Unit): Reportable =
        add(RowBuilder(proofContainer).build(init))

    fun text(string: String): Reportable = add(Text(string))

}
//TODO 1.3.0 remove again?
typealias AnyBuilder = BaseGroupBuilder<*, *, *>
typealias AnyProofBuilder = BaseGroupBuilder<*, Proof, *>
typealias AnyReportableBuilder = BaseGroupBuilder<*, Reportable, *>

class EntryPointProofBuilder<T> internal constructor(
    proofContainer: ProofContainer<T>
) : BaseGroupBuilder<T, Proof, EntryPointProofBuilder<T>>(proofContainer) {

    override fun build(children: List<Reportable>): Proof =
        when (children.size) {
            1 -> when (val first = children.first()) {
                is Proof -> first
                else -> throw IllegalArgumentException("You need to add at least one Proof when building a Proof. Given: $first")
            }

            else -> Proof.invisibleGroupOrSingleChildIfProof(children)
        }
}

abstract class BaseSubGroupBuilder<SubjectT,
    ReportableT : Reportable,
    SelfT : BaseGroupBuilder<SubjectT, ReportableT, SelfT>
    >(
    proofContainer: ProofContainer<SubjectT>,
    private val factory: (children: List<Reportable>) -> ReportableT,
) : BaseGroupBuilder<SubjectT, ReportableT, SelfT>(proofContainer) {
    override fun build(children: List<Reportable>): ReportableT = factory(children)
}

class ProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, ProofGroup, ProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.group(description, representation, children) }
)

class FeatureProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: Reportable,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, FeatureProofGroup, FeatureProofGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.featureGroup(description, representation, children) }
)

class InvisibleProofGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, Proof, InvisibleProofGroupBuilder<SubjectT>>(
    proofContainer,
    Proof::invisibleGroupOrSingleChildIfProof
)

class InvisibleFixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val holds: Boolean
) : BaseSubGroupBuilder<SubjectT, InvisibleFixedClaimGroup, InvisibleFixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.invisibleFixedClaimGroup(children, holds) }
)

class FixedClaimGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?,
    private val holds: Boolean
) : BaseSubGroupBuilder<SubjectT, FixedClaimGroup, FixedClaimGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Proof.fixedClaimGroup(description, representation, children, holds) }
)

class ReportableGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
    private val representation: Any?
) : BaseSubGroupBuilder<SubjectT, ReportableGroup, ReportableGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.group(description, representation, children) }
)

class ProofExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, ProofExplanation, ProofExplanationGroupBuilder<SubjectT>>(
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

class FailureExplanationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, FailureExplanationGroup, FailureExplanationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.failureExplanationGroup(description, children) }
)

class InformationGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, InformationGroup, InformationGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.informationGroup(description, children) }
)

class DebugGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    private val description: InlineElement,
) : BaseSubGroupBuilder<SubjectT, DebugGroup, DebugGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.debugGroup(description, children) }
)

class UsageHintGroupBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
) : BaseSubGroupBuilder<SubjectT, UsageHintGroup, UsageHintGroupBuilder<SubjectT>>(
    proofContainer,
    { children -> Reportable.usageHintGroup(children) }
)

class RowBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>
) : BaseBuilder<SubjectT, Row, Column, RowBuilder<SubjectT>>(proofContainer) {

    fun column(inlineElement: InlineElement, alignment: HorizontalAlignment = HorizontalAlignment.DEFAULT) =
        add(Reportable.column(inlineElement, alignment))

    override fun build(children: List<Column>): Row =
        Reportable.row(children)
}


