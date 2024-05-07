//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * A default implementation for [DescriptiveAssertion] which lazily evaluates [holds].
 *
 * @constructor Constructor overload with a lazy [BasicDescriptiveAssertion.holds].
 * @param description The [BasicDescriptiveAssertion.description].
 * @param representation The [BasicDescriptiveAssertion.representation].
 * @param test Lazily determines whether [BasicDescriptiveAssertion.holds].
 */

internal class BasicDescriptiveAssertion(
    override val description: Translatable,
    override val representation: Any,
    private val test: () -> Boolean
) : DescriptiveAssertion {

    override fun holds() = test()

    /**
     * @suppress
     */
    override fun toString() = "$description: $representation (holds=${holds().toString()})"
}
