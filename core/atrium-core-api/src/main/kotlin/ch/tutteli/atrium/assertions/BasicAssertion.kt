package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [IBasicAssertion] which lazily evaluates [holds].
 *
 * @constructor Constructor overload with a lazy [BasicAssertion.holds].
 * @param description The [BasicAssertion.description].
 * @param expected The [BasicAssertion.expected].
 * @param test Lazily determines whether [BasicAssertion.holds].
 */
class BasicAssertion(
    override val description: Translatable,
    override val expected: Any,
    private val test: () -> Boolean
) : IBasicAssertion {

    /**
     * Constructor overload with an eager [BasicAssertion.holds].
     *
     * If the calculation for [holds] is expensive, then you might want to use the other overload with a lazy test.
     *
     * @param description The [BasicAssertion.description].
     * @param representation The [BasicAssertion.expected].
     * @param holds Determines whether [BasicAssertion.holds] or not
     */
    constructor(description: Translatable, representation: Any, holds: Boolean)
        : this(description, representation, { holds })

    override fun holds() = test()

    /**
     * @suppress
     */
    override fun toString() = "$description: $expected (holds=${holds()})"
}
