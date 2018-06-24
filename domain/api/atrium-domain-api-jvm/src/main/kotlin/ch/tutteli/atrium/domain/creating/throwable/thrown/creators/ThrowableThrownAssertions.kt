package ch.tutteli.atrium.domain.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import java.util.*
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [ThrowableThrownAssertions].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val throwableThrownAssertions by lazy { loadSingleService(ThrowableThrownAssertions::class) }


/**
 * Defines the minimum set of "a [Throwable] was `thrown`"-assertion functions,
 * which an implementation of the domain of Atrium has to provide.
 */
interface ThrowableThrownAssertions {

    fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    )
}
