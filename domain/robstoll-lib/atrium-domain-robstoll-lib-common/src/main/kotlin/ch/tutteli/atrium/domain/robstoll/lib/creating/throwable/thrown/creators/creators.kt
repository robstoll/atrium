package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.ReportingAssertionContainer
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import kotlin.reflect.KClass

fun <TExpected : Throwable> _isThrown(
    throwableThrownBuilder: ThrowableThrown.Builder,
    expectedType: KClass<TExpected>
): ChangedSubjectPostStep<Throwable?, TExpected> {
    val assertionContainer = createReportingAssertionContainerForThrowable(throwableThrownBuilder)
    return ExpectImpl.changeSubject(assertionContainer).reportBuilder()
        .downCastTo(expectedType)
        .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 7))
        .build()
}

private fun createReportingAssertionContainerForThrowable(
    throwableThrownBuilder: ThrowableThrown.Builder
): ReportingAssertionContainer<Throwable?> {
    val throwable: Throwable? = catchThrowableAndAdjust(throwableThrownBuilder)
    return coreFactory.newReportingAssertionContainer(
        ReportingAssertionContainer.AssertionCheckerDecorator.create(
            throwableThrownBuilder.assertionVerb,
            Some(throwable),
            throwable,
            coreFactory.newThrowingAssertionChecker(throwableThrownBuilder.reporter),
            RawString.create(NO_EXCEPTION_OCCURRED)
        )
    )
}

private fun catchThrowableAndAdjust(throwableThrownBuilder: ThrowableThrown.Builder): Throwable? {
    return try {
        throwableThrownBuilder.act()
        null
    } catch (throwable: Throwable) {
        throwableThrownBuilder.reporter.atriumErrorAdjuster.adjust(throwable)
        throwable
    }
}


fun _notThrown(throwableThrownBuilder: ThrowableThrown.Builder): ChangedSubjectPostStep<Throwable?, Nothing?> {
    val assertionContainer = createReportingAssertionContainerForThrowable(throwableThrownBuilder)
    return ExpectImpl.changeSubject(assertionContainer).reportBuilder()
        .withDescriptionAndRepresentation(
            DescriptionThrowableAssertion.IS_NOT_THROWN_1,
            RawString.create(DescriptionThrowableAssertion.IS_NOT_THROWN_2)
        )
        .withCheck { it == null }
        .withTransformation { it as Nothing? }
        .withFailureHandler(ThrowableThrownFailureHandler(maxStackTrace = 15))
        .build()
}
