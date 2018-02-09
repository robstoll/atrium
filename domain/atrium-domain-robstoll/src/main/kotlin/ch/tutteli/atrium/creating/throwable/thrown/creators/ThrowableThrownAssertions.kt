package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import kotlin.reflect.KClass

/**
 * Robstoll's implementation of [IThrowableThrownAssertions].
 */
object ThrowableThrownAssertions : IThrowableThrownAssertions {
    override fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrownBuilder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        _toBe(throwableThrownBuilder, expectedType, assertionCreator)
    }
}
