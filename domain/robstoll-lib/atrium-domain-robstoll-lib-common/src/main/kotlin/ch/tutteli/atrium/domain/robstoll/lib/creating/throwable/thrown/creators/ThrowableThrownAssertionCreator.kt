package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.AssertionPlantWithCommonFields
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators.DownCastAssertionCreator
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.translating.Translatable
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

    private fun createReportingPlantForThrowable(
        throwableThrownBuilder: ThrowableThrown.Builder,
        throwable: Throwable?
    ): ReportingAssertionPlantNullable<Throwable?> {
        //TODO use the default method once it is moved to ThrowableThrownCommon in 1.0.0
        val evalOnceSubjectProvider = { throwable }.evalOnce()
        return coreFactory.newReportingPlantNullable(
            AssertionPlantWithCommonFields.CommonFields(
                throwableThrownBuilder.assertionVerb,
                evalOnceSubjectProvider,
                evalOnceSubjectProvider,
                coreFactory.newThrowingAssertionChecker(throwableThrownBuilder.reporter),
                absentThrowableMessageProvider.message
            )
        )
    }
}
