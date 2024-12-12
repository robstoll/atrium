package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForComposition
import ch.tutteli.atrium.creating.proofs.*
import ch.tutteli.atrium.creating.proofs.builders.*
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.Icon
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable

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

    fun row(icon: Icon? = null, includingBorder: Boolean = true, init: RowBuilder<SubjectT>.() -> Unit): Reportable =
        add(RowBuilder(proofContainer, icon, includingBorder).build(init))

    fun text(string: String): Reportable = add(Text(string))

}
