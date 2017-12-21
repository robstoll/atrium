package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [DescriptiveAssertion] which lazily evaluates [holds].
 *
 * @constructor Constructor overload with a lazy [BasicDescriptiveAssertion.holds].
 * @param description The [BasicDescriptiveAssertion.description].
 * @param expected The [BasicDescriptiveAssertion.expected].
 * @param test Lazily determines whether [BasicDescriptiveAssertion.holds].
 */
class BasicDescriptiveAssertion(
    override val description: Translatable,
    override val expected: Any,
    private val test: () -> Boolean
) : DescriptiveAssertion {

    /**
     * Constructor overload with an eager [BasicDescriptiveAssertion.holds].
     *
     * If the calculation for [holds] is expensive, then you might want to use the other overload with a lazy test.
     *
     * @param description The [BasicDescriptiveAssertion.description].
     * @param representation The [BasicDescriptiveAssertion.expected].
     * @param holds Determines whether [BasicDescriptiveAssertion.holds] or not
     */
    constructor(description: Translatable, representation: Any, holds: Boolean)
        : this(description, representation, { holds })

    override fun holds() = test()

    /**
     * @suppress
     */
    override fun toString() = "$description: $expected (holds=${holds()})"
}
