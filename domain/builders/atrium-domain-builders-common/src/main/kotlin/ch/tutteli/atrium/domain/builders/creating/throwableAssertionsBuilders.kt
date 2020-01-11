@file:Suppress("OVERRIDE_BY_INLINE", "NOTHING_TO_INLINE", "DEPRECATION" /* will be removed with 0.10.0 */)

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.ThrowableAssertions
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.throwableThrownAssertions
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

    @Deprecated(
        "Use Expect instead; will be removed with 1.0.0",
        ReplaceWith(
            "this.thrownBuilder(\n" +
                "// !!!! in case you define an assertion verb, remove it entirely, this is no longer required !!!!\n" +
                ")"
        )
    )
    override inline fun thrownBuilder(
        assertionVerb: Translatable,
        noinline act: () -> Unit,
        reporter: Reporter
    ): ThrowableThrown.Builder = throwableAssertions.thrownBuilder(assertionVerb, act, reporter)

    /**
     * Returns [ThrowableThrownAssertionsBuilder]
     * which inter alia delegates to the implementation of [ThrowableThrownAssertions].
     */
    @Deprecated("Will be removed with 0.10.0")
    inline val thrown
        get() = ThrowableThrownAssertionsBuilder
}

/**
 * Delegates inter alia to the implementation of [ThrowableThrownAssertions].
 * In detail, it implements [ThrowableThrownAssertions] by delegating to [throwableThrownAssertions]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Deprecated("Will be removed with 0.10.0")
object ThrowableThrownAssertionsBuilder : ThrowableThrownAssertions {

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 0.10.0")
    override inline fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        noinline assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) = throwableThrownAssertions.toBe(throwableThrownBuilder, expectedType, assertionCreator)

    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Switch from Assert to Expect; will be removed with 0.10.0")
    override inline fun nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder) =
        throwableThrownAssertions.nothingThrown(throwableThrownBuilder)

    /**
     * Returns [AbsentThrowableMessageProviderFactoryBuilder]
     * which inter alia delegates to the implementation of [ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory].
     */
    @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
    @Deprecated("Will be removed with 0.10.0")
    inline val providers
        get() = AbsentThrowableMessageProviderFactoryBuilder
}

/**
 * Delegates inter alia to the implementation of [ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory].
 * In detail, it implements [ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory] by delegating to [absentThrowableMessageProviderFactory]
 * which in turn delegates to the implementation via [loadSingleService].
 */
@Suppress("DEPRECATION")
@Deprecated("Will be removed with 0.10.0")
object AbsentThrowableMessageProviderFactoryBuilder :
    ch.tutteli.atrium.domain.creating.throwable.thrown.providers.AbsentThrowableMessageProviderFactory {

    override inline fun translatableBased(translatable: Translatable): ThrowableThrown.AbsentThrowableMessageProvider =
        absentThrowableMessageProviderFactory.translatableBased(translatable)
}
