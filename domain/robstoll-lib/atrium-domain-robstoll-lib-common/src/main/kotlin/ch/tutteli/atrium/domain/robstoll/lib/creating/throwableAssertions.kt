package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withRepresentation
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import kotlin.reflect.KClass

@UseExperimental(ExperimentalWithOptions::class)
inline fun <TExpected : Throwable> _cause(
    expect: Expect<Throwable>,
    expectedType: KClass<TExpected>
): ChangedSubjectPostStep<*, TExpected> =
    ExpectImpl.feature.manualFeature(expect, DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE) { cause }
        .getExpectOfFeature()
        .withRepresentation { it ?: RawString.create(DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED) }
        .let {
            ExpectImpl.changeSubject(it).reportBuilder()
                .downCastTo(expectedType)
                .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 7))
                .build()
        }
