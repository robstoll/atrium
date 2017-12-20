package ch.tutteli.atrium.assertions.throwable.thrown.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.any.narrow.DownCaster
import ch.tutteli.atrium.assertions.any.narrow.AnyNarrow
import ch.tutteli.atrium.assertions.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
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
 *        is used in reporting.
 */
class ThrowableThrownAssertionCreator<TExpected : Throwable>(
    private val absentThrowableMessageProvider: ThrowableThrown.AbsentThrowableMessageProvider,
    private val failureHandler: AnyNarrow.DownCastFailureHandler<Throwable, TExpected>
) : ThrowableThrown.Creator<TExpected> {

    override fun executeActAndCreateAssertion(throwableThrownBuilder: ThrowableThrownBuilder, description: Translatable, expectedType: KClass<TExpected>, assertionCreator: IAssertionPlant<TExpected>.() -> Unit) {
        var throwable: Throwable? = null
        try {
            throwableThrownBuilder.act()
        } catch (t: Throwable) {
            throwable = t
        }
        val subjectPlant = AtriumFactory.newReportingPlantNullable(
            throwableThrownBuilder.assertionVerb,
            throwable,
            throwableThrownBuilder.reporter,
            absentThrowableMessageProvider.message)
        DownCaster(failureHandler).downCast(description, expectedType, subjectPlant, assertionCreator)
    }
}
