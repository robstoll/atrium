package ch.tutteli.atrium.domain.builders.reporting.impl.verb

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.translating.Translatable

class AssertionVerbStepImpl<T>(override val maybeSubject: Option<T>) : ExpectBuilder.AssertionVerbStep<T> {
    override fun withVerb(verb: Translatable): ExpectBuilder.OptionsStep<T> =
        ExpectBuilder.OptionsStep.create(maybeSubject, verb)
}

class OptionsStepImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable
) : ExpectBuilder.OptionsStep<T> {

    override fun withOptions(expectOptions: ExpectOptions<T>): ExpectBuilder.FinalStep<T> = toFinalStep(expectOptions)
    override fun withoutOptions(): ExpectBuilder.FinalStep<T> = toFinalStep(null)

    private fun toFinalStep(expectOptions: ExpectOptions<T>?) =
        ExpectBuilder.FinalStep.create(maybeSubject, assertionVerb, expectOptions)
}

class FinalStepImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable,
    override val options: ExpectOptions<T>?
) : ExpectBuilder.FinalStep<T> {

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override fun build(): RootExpect<T> = RootExpect(maybeSubject, assertionVerb, options?.toRootExpectOptions())
}
