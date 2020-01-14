package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.impl.explanatory.ExplanationOptionImpl
import ch.tutteli.atrium.assertions.builders.impl.explanatory.FinalStepImpl
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.kbox.glue

/**
 * Defines the contract to build an [ExplanatoryAssertion].
 */
interface Explanatory {

    /**
     * Option step which allows to specify an [ExplanatoryAssertion.explanation].
     */
    interface ExplanationOption {

        /**
         * Uses the given [translatable] together with the [arg] and optionally [otherArgs] to create an
         * [TranslatableWithArgs] which is then used as [ExplanatoryAssertion.explanation].
         *
         * It delegates to the overload which expects a single [Translatable]; see there for more details about
         * how the [Translatable] is used as [ExplanatoryAssertion.explanation].
         */
        fun withExplanation(translatable: Translatable, arg: Any, vararg otherArgs: Any): FinalStep =
            withExplanation(TranslatableWithArgs(translatable, arg glue otherArgs))

        /**
         * Uses the given [description] as explanation.
         */
        fun withExplanation(description: String): FinalStep = withExplanation(Untranslatable(description))

        /**
         * Uses the given [translatable] as explanation.
         *
         * In detail, the given [translatable] is turned into a [RawString] so that an [ObjectFormatter] translates the
         * given [translatable] and treats the result as raw string.
         */
        fun withExplanation(translatable: Translatable): FinalStep = withExplanation(RawString.create(translatable))

        /**
         * Uses the given [explanation] as [ExplanatoryAssertion.explanation].
         *
         * Notice, if you want to use a text (e.g. a [String]) as explanation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withExplanation(explanation: Any?): FinalStep


        @Deprecated(
            "use withExplanation instead; will be removed with 0.10.0",
            ReplaceWith("this.withExplanation(translatable, arg, *otherArgs)")
        )
        fun withDescription(translatable: Translatable, arg: Any, vararg otherArgs: Any): FinalStep =
            withExplanation(translatable, arg, *otherArgs)


        @Deprecated(
            "use withExplanation instead; will be removed with 0.10.0",
            ReplaceWith("this.withExplanation(translatable)")
        )
        fun withDescription(translatable: Translatable): FinalStep = withExplanation(translatable)

        @Deprecated(
            "use withExplanation instead; will be removed with 0.10.0",
            ReplaceWith("this.withExplanation(explanation)")
        )
        fun withDescription(explanation: Any?): FinalStep = withExplanation(explanation)


        companion object {
            /**
             * Factory method to create the [ExplanationOption] step in the building process
             * of an [ExplanatoryAssertion].
             */
            fun create(): ExplanationOption = ExplanationOptionImpl
        }
    }

    /**
     * Final step which creates an [ExplanatoryAssertion] based on the previously defined [explanation].
     */
    interface FinalStep : AssertionBuilderFinalStep<ExplanatoryAssertion> {
        /**
         * The previously defined [ExplanatoryAssertion.explanation].
         */
        val explanation: Any?

        companion object {
            /**
             * Factory method to create the [FinalStep] in the building process of a [ExplanatoryAssertion] based on the
             * given [explanation].
             */
            fun create(explanation: Any?): FinalStep = FinalStepImpl(explanation)
        }
    }
}
