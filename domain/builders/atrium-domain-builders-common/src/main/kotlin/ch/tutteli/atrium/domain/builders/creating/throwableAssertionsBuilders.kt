@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.ThrowableAssertions
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.throwableThrownAssertions
import ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
import ch.tutteli.atrium.domain.creating.throwable.thrown.providers.absentThrowableMessageProviderFactory
import ch.tutteli.atrium.domain.creating.throwableAssertions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

/**
 * Delegates inter alia to the implementation of [ThrowableAssertions].
 * In detail, it implements [ThrowableAssertions] by delegating to [throwableAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ThrowableAssertionsBuilder : ThrowableAssertions {

    override inline fun thrownBuilder(
        assertionVerb: Translatable,
        noinline act: () -> Unit,
        reporter: Reporter
    ): ThrowableThrown.Builder = throwableAssertions.thrownBuilder(assertionVerb, act, reporter)

    /**
     * Returns [ThrowableThrownAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableThrownAssertions].
     */
    inline val thrown get() = ThrowableThrownAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [ThrowableThrownAssertions].
 * In detail, it implements [ThrowableThrownAssertions] by delegating to [throwableThrownAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object ThrowableThrownAssertionsBuilder : ThrowableThrownAssertions {

    override inline fun <TExpected : Throwable> isA(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>
    ) = throwableThrownAssertions.isA(throwableThrownBuilder, expectedType)

    override inline fun notThrown(
        throwableThrownBuilder: ThrowableThrown.Builder
    ): ChangedSubjectPostStep<Throwable?, Nothing?> = throwableThrownAssertions.notThrown(throwableThrownBuilder)

    override inline fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) = throwableThrownAssertions.toBe(throwableThrownBuilder, expectedType, assertionCreator)

    override inline fun nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder)
        = throwableThrownAssertions.nothingThrown(throwableThrownBuilder)

    /**
     * Returns [AbsentThrowableMessageProviderFactoryBuilder]
     * which inter alia delegates to the implementation of [AbsentThrowableMessageProviderFactory].
     */
    inline val providers get() = AbsentThrowableMessageProviderFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [AbsentThrowableMessageProviderFactory].
 * In detail, it implements [AbsentThrowableMessageProviderFactory] by delegating to [absentThrowableMessageProviderFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
object AbsentThrowableMessageProviderFactoryBuilder : AbsentThrowableMessageProviderFactory {

    override inline fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider =
        absentThrowableMessageProviderFactory.translatableBased(translatable)
}
