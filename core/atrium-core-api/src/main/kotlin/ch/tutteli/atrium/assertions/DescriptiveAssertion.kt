package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion]s which only consist of a [representation] of an expected result
 * with a complementary [description].
 *
 * E.g., the assertion `is not: null` could be represented with this type where `null` is the [representation] and
 * `is not` the complementary description.
 */
interface DescriptiveAssertion : Assertion {
    /**
     * The representation of the expected result such as `1`, `null` etc.
     */
    val representation: Any

    @Deprecated("Use representation, will be removed with 1.0.0", ReplaceWith("representation"))
    val expected get() = representation

    /**
     * The complementary description to the [representation] result such as `contains`, `is not` etc.
     */
    val description: Translatable
}
