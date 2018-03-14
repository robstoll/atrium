package ch.tutteli.atrium.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.creators.DownCastAssertionCreator
import ch.tutteli.atrium.robstoll.lib.creating.any.typetransformation.failurehandlers.ThrowableThrownFailureHandler
import kotlin.reflect.KClass

class ThrowableThrownAssertionCreator<TExpected : Throwable>(
    private val absentThrowableMessageProvider: ThrowableThrown.AbsentThrowableMessageProvider
) : ThrowableThrown.Creator<TExpected> {

    override fun executeActAndCreateAssertion(
        throwableThrownBuilder: ThrowableThrown.Builder,
        description: Translatable,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        val throwable: Throwable? = catchThrowable(throwableThrownBuilder)
        val subjectPlant = createReportingPlantForThrowable(throwableThrownBuilder, throwable)

        val failureHandler = ThrowableThrownFailureHandler(throwable, expectedType)
        DownCastAssertionCreator<Throwable, TExpected>()
            .downCast(description, expectedType, subjectPlant, assertionCreator, failureHandler)
    }

    private fun catchThrowable(throwableThrownBuilder: ThrowableThrown.Builder): Throwable? {
        return try {
            throwableThrownBuilder.act()
            null
        } catch (t: Throwable) {
            t
        }
    }

    private fun createReportingPlantForThrowable(throwableThrownBuilder: ThrowableThrown.Builder, throwable: Throwable?): ReportingAssertionPlantNullable<Throwable?> {
        return coreFactory.newReportingPlantNullable(
            throwableThrownBuilder.assertionVerb,
            throwable,
            throwableThrownBuilder.reporter,
            absentThrowableMessageProvider.message)
    }
}
