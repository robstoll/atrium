package ch.tutteli.atrium.domain.builders.reporting.impl.verb

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.AssertionVerbBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

class AssertionVerbOptionImpl<T>(override val maybeSubject: Option<T>) : AssertionVerbBuilder.AssertionVerbOption<T> {
    override fun withVerb(verb: Translatable): AssertionVerbBuilder.ReporterOption<T> =
        AssertionVerbBuilder.ReporterOption.create(maybeSubject, verb)
}

class ReporterOptionImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable
) : AssertionVerbBuilder.ReporterOption<T> {

    override fun withCustomReporter(reporter: Reporter): AssertionVerbBuilder.FinalStep<T> =
        AssertionVerbBuilder.FinalStep.create(maybeSubject, assertionVerb, reporter)
}

class FinalStepImpl<T>(
    override val maybeSubject: Option<T>,
    override val assertionVerb: Translatable,
    override val reporter: Reporter
) : AssertionVerbBuilder.FinalStep<T> {

    override fun build(): Expect<T> =
        ExpectImpl.coreFactory.newReportingAssertionContainer(assertionVerb, maybeSubject, reporter)
}
