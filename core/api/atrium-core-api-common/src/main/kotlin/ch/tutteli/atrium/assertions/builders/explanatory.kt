package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.builders.impl.ExplanatoryAssertionFinalStepImpl
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.kbox.glue

/**
 * Option step which allows to specify [ExplanatoryAssertion.explanation].
 */
interface ExplanatoryAssertionExplanationOption {

    /**
     * Uses the given [translatable] together with the [arg] and optionally [otherArgs] to create an
     * [TranslatableWithArgs] which is then used as [ExplanatoryAssertion.explanation].
     *
     * It delegates to the overload which expects a single [Translatable]; see there for more details about
     * how the [Translatable] is used as [ExplanatoryAssertion.explanation].
     */
    fun withDescription(translatable: Translatable, arg: Any, vararg otherArgs: Any): ExplanatoryAssertionFinalStep
        = withDescription(TranslatableWithArgs(translatable, arg glue otherArgs))

    /**
     * Uses the given [translatable] as explanation.
     *
     * In detail, the given [translatable] is turned into a [RawString] so that an [ObjectFormatter] translates the
     * given [translatable] and treats the result as raw string.
     */
    fun withDescription(translatable: Translatable): ExplanatoryAssertionFinalStep
        = withDescription(RawString.create(translatable))

    /**
     * Uses the given [explanation] as [ExplanatoryAssertion.explanation].
     *
     * In case you want to pass a [String] which should be treated as [RawString] in reporting, then please wrap it
     * into a [RawString] (`RawString.create("Your text..")`.
     */
    fun withDescription(explanation: Any?) : ExplanatoryAssertionFinalStep
}

/**
 * Final step which creates an [ExplanatoryAssertion] based on the previously defined [explanation].
 */
interface ExplanatoryAssertionFinalStep : AssertionBuilderFinalStep<ExplanatoryAssertion>{
    /**
     * The previously defined [ExplanatoryAssertion.explanation].
     */
    val explanation: Any?

    companion object {
        fun create(explanation: Any?): ExplanatoryAssertionFinalStep = ExplanatoryAssertionFinalStepImpl(explanation)
    }
}
