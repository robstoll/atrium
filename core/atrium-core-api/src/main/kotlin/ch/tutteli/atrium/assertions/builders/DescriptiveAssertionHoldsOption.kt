package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion

/**
 * Options to define [DescriptiveAssertion.holds].
 */
interface DescriptiveAssertionHoldsOption {

    /**
     * Defines a constant failing assertion.
     */
    val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
    /**
     * Defines a constant holding assertion.
     */
    val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>

    /**
     * Whether the assertion holds or not is defined by the given [test].
     */
    fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
}
