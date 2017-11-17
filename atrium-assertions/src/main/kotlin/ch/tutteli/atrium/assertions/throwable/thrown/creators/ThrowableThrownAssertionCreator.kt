package ch.tutteli.atrium.assertions.throwable.thrown.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.any.narrow.DownCaster
import ch.tutteli.atrium.assertions.any.narrow.IAnyNarrow
import ch.tutteli.atrium.assertions.throwable.thrown.IThrowableThrown
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

class ThrowableThrownAssertionCreator<TExpected : Throwable>(
    private val absentThrowableMessageProvider: IThrowableThrown.IAbsentThrowableMessageProvider,
    private val failureHandler: IAnyNarrow.IDownCastFailureHandler<Throwable, TExpected>
) {

    fun createAndAddAssertionToPlant(throwableThrownBuilder: ThrowableThrownBuilder, description: ITranslatable, expectedType: KClass<TExpected>, assertionCreator: IAssertionPlant<TExpected>.() -> Unit) {
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
