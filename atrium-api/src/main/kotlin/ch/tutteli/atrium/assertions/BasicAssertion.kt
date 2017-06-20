package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * A default implementation for [IBasicAssertion].
 *
 * @constructor Constructor overload with a lazy [BasicAssertion.holds].
 * @param description The [BasicAssertion.description].
 * @param expected The [BasicAssertion.expected].
 * @param test Lazily determines whether [BasicAssertion.holds].
 */
class BasicAssertion(
    override val description: ITranslatable,
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
    constructor(description: ITranslatable, representation: Any, holds: Boolean)
        : this(description, representation, { holds })

    override fun holds() = test()

    /**
     * @suppress
     */
    override fun toString() = "$description: $expected (holds=${holds()})"
}
