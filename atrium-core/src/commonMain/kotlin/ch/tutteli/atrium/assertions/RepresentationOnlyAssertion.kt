//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.assertions

/**
 * The base interface for [Assertion]s which only consist of a [representation] (next to [holds]).
 *
 * This assertion is typically used within an [AssertionGroup] where the description of the group gives enough context
 * so that it is enough if the assertion consists only of a representation of an instance.
 *
 * For instance, representing `notToEqualOneOf(1, 2, 3)` can be achieved with an expectation-group and inner
 * [RepresentationOnlyAssertion]s.
 */
@Deprecated(
    "Switch to Proof, will be removed with 2.0.0 at the latest",
    ReplaceWith("Proof.representationOnlyProof(representation, test)", "ch.tutteli.atrium.creating.proofs.Proof")
)
interface RepresentationOnlyAssertion : Assertion {
    /**
     * The representation of an instance such as `1`, `null` etc.
     *
     * Typically, an expected value specified by the test writer, e.g. 1, 2 and 3 in `toBeNoneOf(1, 2, 3)`
     */
    val representation: Any?
}
