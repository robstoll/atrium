package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withRepresentation
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import kotlin.reflect.KClass

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalWithOptions::class)
fun <T : Throwable, TExpected : Throwable> _cause(
    expect: Expect<T>,
    expectedType: KClass<TExpected>
): ChangedSubjectPostStep<Throwable?, TExpected> =
    ExpectImpl.feature.manualFeature(expect, DescriptionThrowableAssertion.OCCURRED_EXCEPTION_CAUSE) { cause }
        .getExpectOfFeature()
        .withRepresentation { it ?: DescriptionThrowableAssertion.NOT_CAUSED }
        .let {
            ExpectImpl.changeSubject(it).reportBuilder()
                .downCastTo(expectedType)
                .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 7))
                .build()
        }
