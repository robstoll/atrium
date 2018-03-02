package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwUnsupportedOperationException
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import kotlin.reflect.KClass

/**
 * A dummy implementation of [IThrowableThrownAssertions] which should be replaced by an actual implementation.
 */
object ThrowableThrownAssertions : IThrowableThrownAssertions {
    override fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        throwUnsupportedOperationException()
    }
}
