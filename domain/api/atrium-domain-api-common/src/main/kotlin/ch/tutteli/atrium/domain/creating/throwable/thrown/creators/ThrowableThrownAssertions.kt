@file:Suppress("DEPRECATION" /* will be removed with 0.10.0 */)

package ch.tutteli.atrium.domain.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [ThrowableThrownAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Use fun0Assertions instead; will be removed with 0.10.0")
val throwableThrownAssertions by lazy { loadSingleService(ThrowableThrownAssertions::class) }


/**
 * Defines the minimum set of "a [Throwable] was `thrown`"-assertion functions,
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated("Use Fun0Assertions instead; will be removed with 0.10.0")
interface ThrowableThrownAssertions {

    @Deprecated("Switch from Assert to Expect and use Fun0Assertions  instead; will be removed with 0.10.0")
    fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    )

    @Deprecated("Switch from Assert to Expect and use Fun0Assertions instead; will be removed with 0.10.0")
    fun nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder)
}
