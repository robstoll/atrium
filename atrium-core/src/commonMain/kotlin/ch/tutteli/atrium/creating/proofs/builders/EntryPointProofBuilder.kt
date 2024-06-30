package ch.tutteli.atrium.creating.proofs.builders

import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.creating.proofs.builders.impl.BaseGroupBuilder
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

//TODO 1.4.0 also introduce a DslMarker for Atrium as such to prevent scenarios as the following:
// expect{ .. }.toThrow<...> {
//   message { message { } }
// }
// makes it look like `message` has again a `message` feature where in reality its the same,
// a DslMarker should prevent this

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

            else -> Proof.invisibleGroup(children)
        }
}


