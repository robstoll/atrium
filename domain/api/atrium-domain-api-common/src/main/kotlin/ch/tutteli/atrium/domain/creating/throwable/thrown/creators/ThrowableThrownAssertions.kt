package ch.tutteli.atrium.domain.creating.throwable.thrown.creators

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [ThrowableThrownAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val throwableThrownAssertions by lazy { loadSingleService(ThrowableThrownAssertions::class) }


/**
 * Defines the minimum set of "a [Throwable] was `thrown`"-assertion functions,
 * which an implementation of the domain of Atrium has to provide.
 */
interface ThrowableThrownAssertions {

    fun <TExpected : Throwable> isA(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected>

    fun notThrown(
        throwableThrownBuilder: ThrowableThrown.Builder
    ): ChangedSubjectPostStep<Throwable?, Nothing?>


    @Deprecated("Switch from Assert to Expect and use isA instead; will be removed with 1.0.0")
    fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    )

    @Deprecated("Switch from Assert to Expect and use notThrown instead; will be removed with 1.0.0")
    fun nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder)
}
