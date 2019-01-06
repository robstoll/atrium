package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.descriptive.DescriptionOptionImpl
import ch.tutteli.atrium.assertions.builders.impl.descriptive.FinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.descriptive.HoldsOptionImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract to build a [DescriptiveAssertion].
 */
interface Descriptive {

    /**
     * Option step which allows to specify [DescriptiveAssertion.holds].
     */
    interface HoldsOption {
        /**
         * Defines a constant failing assertion.
         */
        val failing: DescriptionOption<FinalStep>

        /**
         * Defines a constant holding assertion.
         */
        val holding: DescriptionOption<FinalStep>

        /**
         * Uses the given [test] as [DescriptiveAssertion.holds].
         */
        fun withTest(test: () -> Boolean): DescriptionOption<FinalStep>

        companion object {
            fun create(): HoldsOption = HoldsOptionImpl
        }
    }

    /**
     * Option step which allows to specify the description of a descriptive like assertion (such as [DescriptiveAssertion]).
     */
    interface DescriptionOption<R> {
        /**
         * The previously defined test which is used to determine [Assertion.holds].
         */
        val test: () -> Boolean

        /**
         * Uses the given [description] as [DescriptiveAssertion.description] and [representation]
         * as [DescriptiveAssertion.representation] unless [representation] is null in which case [RawString.NULL] is used.
         */
        fun withDescriptionAndNullableRepresentation(description: Translatable, representation: Any?): R
            = withDescriptionAndRepresentation(description, representation ?: RawString.NULL)

        /**
         * Uses the given [description] as [AssertionGroup.description] and [representation] as [AssertionGroup.representation].
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R

        companion object {
            fun <R> create(
                test: () -> Boolean,
                factory: (() -> Boolean, Translatable, Any) -> R
            ): DescriptionOption<R> = DescriptionOptionImpl(test, factory)
        }
    }


    /**
     * Final step which creates a [DescriptiveAssertion] based on the previously defined [test], [description]
     * and [representation].
     */
    interface FinalStep : AssertionBuilderFinalStep<DescriptiveAssertion> {
        /**
         * The previously defined test which is used to determine [DescriptiveAssertion.holds].
         */
        val test: () -> Boolean

        /**
         * The previously defined [DescriptiveAssertion.description].
         */
        val description: Translatable

        /**
         * The previously defined [DescriptiveAssertion.representation].
         */
        val representation: Any

        companion object {
            fun create(
                test: () -> Boolean,
                description: Translatable,
                representation: Any
            ): FinalStep = FinalStepImpl(test, description, representation)
        }
    }
}


