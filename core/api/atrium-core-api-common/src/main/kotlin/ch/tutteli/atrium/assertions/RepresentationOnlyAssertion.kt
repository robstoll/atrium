package ch.tutteli.atrium.assertions

/**
 * The base interface for [Assertion]s which only consist of a [representation] (next to [holds]).
 *
 * This assertion is typically used within an [AssertionGroup] where the description of the group gives enough context
 * so that it is enough if the assertion consists only of a representation of an instance.
 *
 * For instance, representing `isNoneOf(1, 2, 3)` can be achieved with an assertion group and inner
 * [RepresentationOnlyAssertion]s.
 */
interface RepresentationOnlyAssertion : Assertion {
    /**
     * The representation of an instance such as `1`, `null` etc.
     *
     * Typically an expected value specified by the test writer, e.g. 1, 2 and 3 in `isNoneOf(1, 2, 3)`
     */
    val representation: Any?
}
