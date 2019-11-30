package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.core.Either
import ch.tutteli.atrium.core.Left
import ch.tutteli.atrium.core.Right
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.translations.DescriptionFunLikeAssertion.*
import kotlin.reflect.KClass

fun <TExpected : Throwable> _isThrowing(
    assertionContainer: Expect<out () -> Any?>,
    expectedType: KClass<TExpected>
): ChangedSubjectPostStep<*, TExpected> =
    //TODO allow to pass an ExpectOptions which allows to change the nullRepresentation.
    ExpectImpl.feature
        .manualFeature(assertionContainer, THROWN_EXCEPTION_WHEN_CALLED) {
            catchAndAdjustThrowable(this).fold(
                { it },
                {
                    // use null as subject in case no exception occurred
                    null
                }
            )
        }
        .getExpectOfFeature()
        .let {
            ExpectImpl.changeSubject(it).reportBuilder()
                .downCastTo(expectedType)
                .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 7))
                .build()
        }

private inline fun <R> catchAndAdjustThrowable(act: () -> R): Either<Throwable, R> =
    try {
        Right(act())
    } catch (throwable: Throwable) {
        //TODO should be taken from current assertionContainer once it is configured this way
        reporter.atriumErrorAdjuster.adjust(throwable)
        Left(throwable)
    }

fun <R, T : () -> R> _isNotThrowing(assertionContainer: Expect<T>): ChangedSubjectPostStep<*, R> {
    return ExpectImpl.changeSubject(assertionContainer)
        .unreported {
            catchAndAdjustThrowable(it)
        }
        .let { eitherContainer ->
            ExpectImpl.changeSubject(eitherContainer).reportBuilder()
                .withDescriptionAndRepresentation(IS_NOT_THROWING_1, RawString.create(IS_NOT_THROWING_2))
                .withTransformation { either -> either.toOption() }
                .withFailureHandlerAdapter(ThrowableThrownFailureHandler(maxStackTrace = 15)) {
                    // must be left as otherwise the failure handler would not kick in.
                    (it as Left).l
                }
                .build()
        }
}


