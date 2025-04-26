package ch.tutteli.atrium.creating.proofs.impl

import ch.tutteli.atrium.creating.proofs.RepresentationOnlyProof

internal class RepresentationOnlyProofImpl(
    override val representation: Any,
    test: () -> Boolean,
) : TestBasedProof(test), RepresentationOnlyProof {

    /**
     * @suppress
     */
    override fun toString() = "ROP($representation (holds=${holds()}))"
}
