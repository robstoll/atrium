package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseBuilder
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseGroupBuilder
import ch.tutteli.atrium.creating.proofs.builders.impl.DiagnosticBuilder
import ch.tutteli.atrium.creating.proofs.builders.impl.DiagnosticBuilderDelegate
import ch.tutteli.atrium.reporting.reportables.Diagnostic
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.Reportable

/** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.buildSimpleProof(
    description: InlineElement,
    representation: Any?,
    test: (SubjectT) -> Boolean
): Proof = Proof.simple(description, representation, this.toTestFunction(test))

/** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.toTestFunction(test: (SubjectT) -> Boolean): () -> Boolean = {
    this.maybeSubject.fold(falseProvider, test)
}

/** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.buildProof(init: EntryPointProofBuilder<SubjectT>.() -> Unit): Proof =
    EntryPointProofBuilder(this).build(init)

/** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.buildDiagnostic(init: EntryPointDiagnosticBuilder<SubjectT>.() -> Unit): Diagnostic =
    EntryPointDiagnosticBuilder(this, DiagnosticBuilderDelegate()).build(init)

/**
 * Used to prevent that one can use e.g. [ProofExplanationGroupBuilder.collectWithoutSubject] in a nested [ProofExplanationGroupBuilder.proofGroup].
 *
 * @since 1.3.0
 */
@DslMarker
annotation class ProofBuilderMarker

//TODO 1.4.0 also introduce a DslMarker for Atrium as such to prevent scenarios as the following:
// expect{ .. }.toThrow<...> {
//   message { message { } }
// }
// makes it look like `message` has again a `message` feature where in reality its the same,
// a DslMarker should prevent this (but then suddenly everything is purple,
// as far as I remember the reason why I didn't do it back then :D

//TODO 1.3.0 remove again?
typealias AnyBuilder = BaseGroupBuilder<*, *, *>
typealias AnyProofBuilder = BaseGroupBuilder<*, Proof, *>
typealias AnyReportableBuilder = BaseGroupBuilder<*, Reportable, *>

/** @since 1.3.0 */
class EntryPointProofBuilder<SubjectT> internal constructor(
    proofContainer: ProofContainer<SubjectT>
) : BaseGroupBuilder<SubjectT, Proof, EntryPointProofBuilder<SubjectT>>(proofContainer, DiagnosticBuilderDelegate()) {

    override fun build(children: List<Reportable>): Proof =
        when (children.size) {
            1 -> when (val first = children.first()) {
                is Proof -> first
                else -> throw IllegalArgumentException("You need to add at least one Proof when building a Proof. Given: $first")
            }

            else -> Proof.invisibleGroup(children)
        }
}

/** @since 1.3.0 */
class EntryPointDiagnosticBuilder<SubjectT>(
    proofContainer: ProofContainer<SubjectT>,
    diagnosticBuilderDelegate: DiagnosticBuilderDelegate<SubjectT>
) : BaseBuilder<SubjectT, Diagnostic, Diagnostic, EntryPointDiagnosticBuilder<SubjectT>>(proofContainer),
    DiagnosticBuilder<SubjectT> by diagnosticBuilderDelegate {
    init {
        diagnosticBuilderDelegate.reportableBuilder = this
    }

    override fun build(children: List<Diagnostic>): Diagnostic =
        when (children.size) {
            1 -> children.first()

            else -> Diagnostic.invisibleGroup(children)
        }
}
