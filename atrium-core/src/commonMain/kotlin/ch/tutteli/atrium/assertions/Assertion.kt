package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.proofs.Proof

/**
 * The base interface of all assertions, providing the method [holds].
 */
@Deprecated(
    "switch to Proof, will be removed with 2.0.0 at the latest",
    ReplaceWith("Proof", "ch.tutteli.atrium.creating.proofs.Proof")
)
interface Assertion : Proof
