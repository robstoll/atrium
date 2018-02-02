package ch.tutteli.atrium.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.throwable.thrown.builders.ThrowableThrownBuilder
import kotlin.reflect.KClass

/**
 * Defines the minimum set of "a [Throwable] was `thrown`"-assertion functions,
 * which an implementation of the domain of Atrium has to provide.
 */
interface IThrowableThrownAssertions {
    fun <TExpected : Throwable> toThrow(
        throwableThrownBuilder: ThrowableThrownBuilder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    )
}
