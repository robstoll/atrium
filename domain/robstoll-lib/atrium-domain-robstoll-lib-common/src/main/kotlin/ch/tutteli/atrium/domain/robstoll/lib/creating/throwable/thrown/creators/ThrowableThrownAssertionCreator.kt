@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.newReportingPlantNullable
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.creators.DownCastAssertionCreator
import ch.tutteli.atrium.domain.robstoll.lib.creating.any.typetransformation.failurehandlers.ThrowableThrownFailureHandler
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_NOT_THROWN_1
import ch.tutteli.atrium.translations.DescriptionThrowableAssertion.IS_NOT_THROWN_2
import kotlin.reflect.KClass

@Deprecated("Switch from Assert to Expect and use throwable.thrown.isA or throwable.thrown.notThrown instead; will be removed with 1.0.0")
class ThrowableThrownAssertionCreator<TExpected : Throwable>(
    private val absentThrowableMessageProvider: ThrowableThrown.AbsentThrowableMessageProvider
) : ThrowableThrown.Creator<TExpected> {

    override fun executeActAssertNothingThrown(throwableThrownBuilder: ThrowableThrown.Builder) {
        val throwable: Throwable? = catchThrowableAndAdjust(throwableThrownBuilder)
        val subjectPlant = createReportingPlantForThrowable(throwableThrownBuilder, throwable)
        if (throwable == null) {
            subjectPlant.addAssertion(
                AssertImpl.builder.createDescriptive(IS_NOT_THROWN_1, RawString.create(IS_NOT_THROWN_2)) { true }
            )
        } else {
            val explainingAssertion =
                AssertImpl.any.typeTransformation.failureHandlers.newExplanatoryWithHint<Any, TExpected>(
                    showHint = trueProvider,
                    failureHintFactory = {
                        ThrowableThrownFailureHandler.propertiesOfException(
                            throwable,
                            maxStackTrace = 15
                        )
                    }
                ).createFailingAssertion(IS_NOT_THROWN_1, RawString.create(IS_NOT_THROWN_2))
            subjectPlant.addAssertion(explainingAssertion)
        }
    }

    override fun executeActAndCreateAssertion(
        throwableThrownBuilder: ThrowableThrown.Builder,
        description: Translatable,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        val throwable: Throwable? = catchThrowableAndAdjust(throwableThrownBuilder)
        val subjectPlant = createReportingPlantForThrowable(throwableThrownBuilder, throwable)

        val failureHandler = ThrowableThrownFailureHandler(throwable, expectedType)
        DownCastAssertionCreator<Throwable, TExpected>()
            .downCast(description, expectedType, subjectPlant, assertionCreator, failureHandler)
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

    @Suppress("DEPRECATION")
    private fun createReportingPlantForThrowable(
        throwableThrownBuilder: ThrowableThrown.Builder,
        throwable: Throwable?
    ): ReportingAssertionPlantNullable<Throwable?> = AssertImpl.coreFactory.newReportingPlantNullable(
        throwableThrownBuilder.assertionVerb,
        { throwable },
        throwableThrownBuilder.reporter,
        absentThrowableMessageProvider.message
    )
}
