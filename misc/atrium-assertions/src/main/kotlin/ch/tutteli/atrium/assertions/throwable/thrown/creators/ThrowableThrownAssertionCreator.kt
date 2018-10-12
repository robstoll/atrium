@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.throwable.thrown.creators

import ch.tutteli.atrium.assertions.any.typetransformation.AnyTypeTransformation
import ch.tutteli.atrium.assertions.any.typetransformation.DownCaster
import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.ReportingAssertionPlantNullable
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Represents a creator of a sophisticated a [Throwable] (of type [TExpected]) was thrown assertion.
 *
 * It uses the given [absentThrowableMessageProvider] in case no [Throwable] was thrown at all and uses a [DownCaster]
 * to perform the down-cast to the desired type [TExpected] (for this it passes on the given [failureHandler] to the
 * [DownCaster])
 *
 * @param TExpected The type of the [Throwable] which we expect was thrown.
 *
 * @constructor Represents a creator of a sophisticated a [Throwable] (of type [TExpected]) was thrown assertion.
 * @param absentThrowableMessageProvider Provides a description for the case no [Throwable] was thrown at all.
 * @param failureHandler A handler which decides how the assertion creator lambda (see [executeActAndCreateAssertion])
 *   is used in reporting.
 */
@Deprecated("Please open an issue if you used this class, will be removed with 1.0.0")
class ThrowableThrownAssertionCreator<TExpected : Throwable>(
    private val absentThrowableMessageProvider: ThrowableThrown.AbsentThrowableMessageProvider,
    private val failureHandler: AnyTypeTransformation.TypeTransformationFailureHandler<Throwable, TExpected>
) : ThrowableThrown.Creator<TExpected> {

    override fun executeActAndCreateAssertion(
        throwableThrownBuilder: ThrowableThrownBuilder,
        description: Translatable,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        val throwable: Throwable? = catchThrowable(throwableThrownBuilder)
        val subjectPlant = createReportingPlantForThrowable(throwableThrownBuilder, throwable)

        DownCaster<Throwable, TExpected>(failureHandler)
            .downCast(description, expectedType, subjectPlant, assertionCreator)
    }

    private fun catchThrowable(throwableThrownBuilder: ThrowableThrownBuilder): Throwable? {
        return try {
            throwableThrownBuilder.act()
            null
        } catch (t: Throwable) {
            t
        }
    }

    private fun createReportingPlantForThrowable(throwableThrownBuilder: ThrowableThrownBuilder, throwable: Throwable?): ReportingAssertionPlantNullable<Throwable?> {
        return coreFactory.newReportingPlantNullable(
            throwableThrownBuilder.assertionVerb,
            { throwable },
            throwableThrownBuilder.reporter,
            absentThrowableMessageProvider.message
        )
    }
}
