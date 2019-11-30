package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
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

private inline fun <R> catchAndAdjustThrowable(act: () -> R): Either<R> =
    try {
        Right(act())
    } catch (throwable: Throwable) {
        //TODO should be taken from current assertionContainer once it is configured this way
        reporter.atriumErrorAdjuster.adjust(throwable)
        Left(throwable)
    }

//TODO consider to move to core of Atrium
private sealed class Either<out R> {

    inline fun <T> map(f: (R) -> T): Either<T> = flatMap { Right(f(it)) }

    inline fun <T> flatMap(f: (R) -> Either<T>): Either<T> = fold({ Left(it) }, f)

    inline fun <T> fold(default: (Throwable) -> T, f: (R) -> T): T = when (this) {
        is Right -> f(this.r)
        is Left -> default(this.l)
    }
}

private data class Left(val l: Throwable) : Either<Nothing>()
private data class Right<R>(val r: R) : Either<R>()

fun <R, T : () -> R> _isNotThrowing(assertionContainer: Expect<T>): ChangedSubjectPostStep<*, R> {
    return ExpectImpl.changeSubject(assertionContainer)
        .unreported {
            catchAndAdjustThrowable(it)
        }
        .let { eitherContainer ->
            ExpectImpl.changeSubject(eitherContainer).reportBuilder()
                .withDescriptionAndRepresentation(IS_NOT_THROWING_1, RawString.create(IS_NOT_THROWING_2))
                .withTransformation {
                    if (it is Right) Some(it.r) else None
                }
                //TODO could be extracted into an own pattern/function FailureHandlerAdapter
                .withFailureHandler(object : SubjectChanger.FailureHandler<Either<R>, R> {
                    override fun createAssertion(
                        originalAssertionContainer: Expect<Either<R>>,
                        descriptiveAssertion: Assertion,
                        maybeAssertionCreator: Option<Expect<R>.() -> Unit>
                    ): Assertion {
                        return ExpectImpl.changeSubject(originalAssertionContainer).unreported { (it as Left).l }
                            .let {
                                val handler = ThrowableThrownFailureHandler<Throwable, R>(maxStackTrace = 15)
                                handler.createAssertion(it, descriptiveAssertion, maybeAssertionCreator)
                            }
                    }
                })
                .build()
        }
}


