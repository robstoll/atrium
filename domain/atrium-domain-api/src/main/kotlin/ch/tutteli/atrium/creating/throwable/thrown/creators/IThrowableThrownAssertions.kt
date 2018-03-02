package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import kotlin.reflect.KClass

/**
 * Defines the minimum set of "a [Throwable] was `thrown`"-assertion functions,
 * which an implementation of the domain of Atrium has to provide.
 */
interface IThrowableThrownAssertions {
    fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    )
}
