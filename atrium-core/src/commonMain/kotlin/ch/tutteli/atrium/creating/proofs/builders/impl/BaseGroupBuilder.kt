package ch.tutteli.atrium.creating.proofs.builders.impl

import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.collectForComposition
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.*
import ch.tutteli.atrium.reporting.reportables.*

abstract class BaseGroupBuilder<
    SubjectT,
    ReportableT : Reportable,
    SelfT : BaseGroupBuilder<SubjectT, ReportableT, SelfT>
    >(
    proofContainer: ProofContainer<SubjectT>,
    diagnosticBuilderDelegate: DiagnosticBuilderDelegate<SubjectT>
) : BaseBuilder<SubjectT, ReportableT, Reportable, SelfT>(proofContainer),
    DiagnosticBuilder<SubjectT> by diagnosticBuilderDelegate {
    init {
        @Suppress(
            // we are aware of it, but we know that diagnosticBuilder will not use it upon construction finished
            "LeakingThis"
        )
        diagnosticBuilderDelegate.reportableBuilder = this
    }


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

    fun proofGroup(description: Diagnostic, representation: Any?, init: ProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(ProofGroupBuilder(proofContainer, description, representation).build(init))

    fun feature(
        description: Diagnostic,
        representation: Any?,
        init: FeatureProofGroupBuilder<SubjectT>.() -> Unit
    ): Proof = add(FeatureProofGroupBuilder(proofContainer, description, representation).build(init))

    fun invisibleGroup(init: InvisibleProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(InvisibleProofGroupBuilder(proofContainer).build(init))

    fun invisibleFailingProofGroup(init: InvisibleFailingProofGroupBuilder<SubjectT>.() -> Unit): Proof =
        add(InvisibleFailingProofGroupBuilder(proofContainer, DiagnosticBuilderDelegate()).build(init))

    fun fixedClaimGroup(
        description: InlineElement,
        representation: Any?,
        holds: Boolean,
        init: FixedClaimGroupBuilder<SubjectT>.() -> Unit
    ): Proof = add(FixedClaimGroupBuilder(proofContainer, description, representation, holds).build(init))
}
