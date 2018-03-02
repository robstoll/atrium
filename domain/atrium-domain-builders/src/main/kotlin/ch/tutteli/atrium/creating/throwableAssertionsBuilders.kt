@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.creating.throwable.thrown.creators.IThrowableThrownAssertions
import ch.tutteli.atrium.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.creating.throwable.thrown.providers.IThrowableThrownAbsentMessageProvider
import ch.tutteli.atrium.creating.throwable.thrown.providers.ThrowableThrownAbsentMessageProvider
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

object ThrowableAssertionsBuilder : IThrowableAssertions {
    override inline fun thrownBuilder(
        assertionVerb: Translatable,
        noinline act: () -> Unit,
        reporter: Reporter
    ): ThrowableThrown.Builder = ThrowableAssertions.thrownBuilder(assertionVerb, act, reporter)

    /**
     * Delegates to [ThrowableThrownAssertions].
     */
    inline val thrown get() = ThrowableThrownAssertionsBuilder
}


object ThrowableThrownAssertionsBuilder : IThrowableThrownAssertions {

    override inline fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) = ThrowableThrownAssertions.toBe(throwableThrownBuilder, expectedType, assertionCreator)

    /**
     * Delegates to [ThrowableThrownAbsentMessageProvider].
     */
    inline val providers get() = ThrowableThrownAbsentMessageProviderBuilder
}

object ThrowableThrownAbsentMessageProviderBuilder : IThrowableThrownAbsentMessageProvider {

    override inline fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = ThrowableThrownAbsentMessageProvider.translatableBased(translatable)
}
