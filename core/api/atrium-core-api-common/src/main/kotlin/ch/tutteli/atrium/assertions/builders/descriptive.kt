package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.descriptive.DescriptionOptionImpl
import ch.tutteli.atrium.assertions.builders.impl.descriptive.FinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.descriptive.HoldsOptionImpl
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

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

        /**
         * Uses the given [test] as [DescriptiveAssertion.holds] based on the subject provided by [subjectProvider].
         *
         * Notice, this function might change its signature with 1.0.0 to something like
         * ```
         * fun <T> withTest(expect: Expect, test: (T) -> Boolean): DescriptionOption<FinalStep>
         * ```
         */
        fun <T> withTest(subjectProvider: SubjectProvider<T>, test: (T) -> Boolean): DescriptionOption<FinalStep>

        companion object {
            /**
             * Factory method to create the [HoldsOption] step in the process of building a descriptive assertion.
             */
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
         * Wraps the given [description] into an [Untranslatable] and uses it as [DescriptiveAssertion.description]
         * next to [representation] which is used as [DescriptiveAssertion.representation] unless
         * [representation] is null in which case a representation for null is used (e.g. [RawString.NULL]).
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withDescriptionAndRepresentation(description: String, representation: Any?): R =
            withDescriptionAndRepresentation(Untranslatable(description), representation)

        /**
         * Uses the given [description] as [DescriptiveAssertion.description] and [representation]
         * as [DescriptiveAssertion.representation] unless [representation] is null in which case a representation for
         * null is used (e.g. [RawString.NULL]).
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R

        companion object {
            /**
             * Factory method to create the [DescriptionOption] step based on the given [test] and
             * another [factory] method which creates the next step in the building process of a descriptive assertion.
             */
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
            /**
             * Factory method to create the [FinalStep] in the building process of a [DescriptiveAssertion] based on the
             * given [test] which indicates whether the assertion holds or not, a [description] as well as a
             * [representation].
             */
            fun create(
                test: () -> Boolean,
                description: Translatable,
                representation: Any
            ): FinalStep = FinalStepImpl(test, description, representation)
        }
    }
}


