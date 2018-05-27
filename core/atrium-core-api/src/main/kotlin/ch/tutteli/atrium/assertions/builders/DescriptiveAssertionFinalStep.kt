package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.DescriptiveAssertionFinalStepImpl
import ch.tutteli.atrium.reporting.translating.Translatable

interface DescriptiveAssertionFinalStep : AssertionBuilderFinalStep<DescriptiveAssertion> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     * The [DescriptiveAssertion.description].
     */
    val description: Translatable

    /**
     * The [DescriptiveAssertion.representation].
     */
    val representation: Any

    companion object {
        fun create(
            test: () -> Boolean,
            description: Translatable,
            representation: Any
        ): DescriptiveAssertionFinalStep = DescriptiveAssertionFinalStepImpl(test, description, representation)
    }
}

