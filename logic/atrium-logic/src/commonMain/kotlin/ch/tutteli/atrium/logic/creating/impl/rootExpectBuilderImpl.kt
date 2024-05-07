package ch.tutteli.atrium.logic.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.logic.creating.RootExpectBuilder

class ExpectationVerbStepImpl<T>(override val subject: T) : RootExpectBuilder.ExpectationVerbStep<T> {
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun withVerb(verb: ch.tutteli.atrium.reporting.translating.Translatable): RootExpectBuilder.OptionsStep<T> =
        RootExpectBuilder.OptionsStep(subject, verb)
}

class OptionsStepImpl<T>(
    override val subject: T,
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable
) : RootExpectBuilder.OptionsStep<T> {

    @ExperimentalNewExpectTypes
    override fun withOptions(rootExpectOptions: RootExpectOptions<T>): RootExpectBuilder.FinalStep<T> =
        toFinalStep(rootExpectOptions)

    @ExperimentalNewExpectTypes
    override fun withoutOptions(): RootExpectBuilder.FinalStep<T> = toFinalStep(null)

    @ExperimentalNewExpectTypes
    private fun toFinalStep(rootExpectOptions: RootExpectOptions<T>?) =
        RootExpectBuilder.FinalStep(subject, expectationVerb, rootExpectOptions)
}

@ExperimentalNewExpectTypes
class FinalStepImpl<T>(
    override val subject: T,
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable,
    override val options: RootExpectOptions<T>?
) : RootExpectBuilder.FinalStep<T> {

    override fun build(): RootExpect<T> = RootExpect(Some(subject), expectationVerb, options)
}
