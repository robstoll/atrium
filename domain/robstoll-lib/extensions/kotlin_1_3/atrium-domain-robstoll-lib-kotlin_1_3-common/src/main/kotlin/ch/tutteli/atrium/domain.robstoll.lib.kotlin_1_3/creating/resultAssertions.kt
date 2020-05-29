package ch.tutteli.atrium.domain.robstoll.lib.kotlin_1_3.creating

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withRepresentation
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.translations.DescriptionResultAssertion
import ch.tutteli.atrium.translations.DescriptionResultAssertion.EXCEPTION
import ch.tutteli.atrium.translations.DescriptionResultAssertion.IS_NOT_FAILURE
import kotlin.reflect.KClass

fun <E, T : Result<E>> _isSuccess(expect: Expect<T>): ExtractedFeaturePostStep<T, E> =
    ExpectImpl.feature.extractor(expect)
        .withDescription(DescriptionResultAssertion.VALUE)
        .withRepresentationForFailure(DescriptionResultAssertion.IS_NOT_SUCCESS)
        .withFeatureExtraction {
            Option.someIf(it.isSuccess) { it.getOrThrow() }
        }
        .withoutOptions()
        .build()

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalWithOptions::class)
fun <TExpected : Throwable> _isFailure(
    expect: Expect<out Result<*>>,
    expectedType: KClass<TExpected>
): ChangedSubjectPostStep<Throwable?, TExpected>
{
    val throwableExpect = ExpectImpl.feature
        .manualFeature(expect, EXCEPTION) { exceptionOrNull() }
        .getExpectOfFeature()
        .withRepresentation { it ?: IS_NOT_FAILURE }

    return ExpectImpl.changeSubject(throwableExpect).reportBuilder()
        .downCastTo(expectedType)
        .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 7))
        .build()
}
