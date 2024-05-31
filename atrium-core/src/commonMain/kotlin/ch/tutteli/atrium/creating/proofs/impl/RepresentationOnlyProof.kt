package ch.tutteli.atrium.creating.proofs.impl

internal class RepresentationOnlyProofImpl(
    val representation: Any,
    test: () -> Boolean,
) : TestBasedProof(test) {

    /**
     * @suppress
     */
    override fun toString() = "ROP($representation (holds=${holds()}))"
}
