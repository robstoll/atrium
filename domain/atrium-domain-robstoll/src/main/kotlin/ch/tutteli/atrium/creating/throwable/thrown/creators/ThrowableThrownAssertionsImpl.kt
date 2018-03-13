package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import kotlin.reflect.KClass

/**
 * Robstoll's implementation of [ThrowableThrownAssertions].
 */
class ThrowableThrownAssertionsImpl : ThrowableThrownAssertions {

    override fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        _toBe(throwableThrownBuilder, expectedType, assertionCreator)
    }
}
