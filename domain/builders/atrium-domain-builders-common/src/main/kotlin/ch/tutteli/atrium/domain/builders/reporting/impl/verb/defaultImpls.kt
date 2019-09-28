package ch.tutteli.atrium.domain.builders.reporting.impl.verb

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.Translatable

class AssertionVerbStepImpl<T>(override val maybeSubject: Option<T>) : ExpectBuilder.AssertionVerbStep<T> {
    override fun withVerb(verb: Translatable): ExpectBuilder.OptionsStep<T> =
        ExpectBuilder.OptionsStep.create(maybeSubject, verb)
}

class OptionsStepImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable
) : ExpectBuilder.OptionsStep<T> {

    override fun withOptions(expectOptions: ExpectOptions): ExpectBuilder.FinalStep<T> =
        ExpectBuilder.FinalStep.create(maybeSubject, assertionVerb, expectOptions)
}

class FinalStepImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable,
    override val options: ExpectOptions
) : ExpectBuilder.FinalStep<T> {

    override fun build(): Expect<T> =
        coreFactory.newReportingAssertionContainer(
            ReportingAssertionContainer.AssertionCheckerDecorator.create(
                options.assertionVerb ?: assertionVerb,
                maybeSubject,
                options.representation ?: maybeSubject.getOrElse {
                    RawString.create(SHOULD_NOT_BE_SHOWN_TO_THE_USER_BUG)
                },
                coreFactory.newThrowingAssertionChecker(options.reporter ?: reporter),
                options.nullRepresentation ?: RawString.NULL
            )
        )
}
