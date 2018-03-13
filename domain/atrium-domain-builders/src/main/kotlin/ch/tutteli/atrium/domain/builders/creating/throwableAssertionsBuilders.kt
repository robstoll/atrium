@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.ThrowableAssertions
import ch.tutteli.atrium.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.creating.throwable.thrown.creators.throwableThrownAssertions
import ch.tutteli.atrium.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory
import ch.tutteli.atrium.creating.throwable.thrown.providers.absentThrowableMessageProviderFactory
import ch.tutteli.atrium.creating.throwableAssertions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

object ThrowableAssertionsBuilder : ThrowableAssertions {
    override inline fun thrownBuilder(
        assertionVerb: Translatable,
        noinline act: () -> Unit,
        reporter: Reporter
    ): ThrowableThrown.Builder = throwableAssertions.thrownBuilder(assertionVerb, act, reporter)

    /**
     * Delegates to [throwableThrownAssertions].
     */
    inline val thrown get() = ThrowableThrownAssertionsBuilder
}


object ThrowableThrownAssertionsBuilder : ThrowableThrownAssertions {

    override inline fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) = throwableThrownAssertions.toBe(throwableThrownBuilder, expectedType, assertionCreator)

    /**
     * Delegates to [absentThrowableMessageProviderFactory].
     */
    inline val providers get() = AbsentThrowableMessageProviderFactoryBuilder
}

object AbsentThrowableMessageProviderFactoryBuilder : AbsentThrowableMessageProviderFactory {

    override inline fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider
        = absentThrowableMessageProviderFactory.translatableBased(translatable)
}
