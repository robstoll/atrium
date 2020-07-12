//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import kotlin.reflect.KClass

/**
 * The access point to an implementation of [ThrowableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Use _logic from ch.tutteli.atrium.logic instead; will be removed with 1.0.0")
val throwableAssertions by lazy { loadSingleService(ThrowableAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [Throwable],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use ThrowableAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.ThrowableAssertions")
)
interface ThrowableAssertions {

    fun <TExpected : Throwable> cause(
        expect: Expect<out Throwable>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected>

    /**
     * Turns the given [assertionVerb] into an [Untranslatable] and delegates to the other overload.
     */
    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated(
        "Use Expect instead; will be removed with 1.0.0",
        ReplaceWith(
            "this.thrownBuilder(\n" +
                "// !!!! in case you define an assertion verb function, remove it entirely, this is no longer required !!!!\n" +
                ")"
        )
    )
    fun thrownBuilder(
        assertionVerb: String,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder =
        thrownBuilder(Untranslatable(assertionVerb), act, reporter)

    @Suppress("DEPRECATION")
    @Deprecated(
        "Use Expect instead; will be removed with 1.0.0",
        ReplaceWith(
            "this.thrownBuilder(\n" +
                "// !!!! in case you define an assertion verb function, remove it entirely, this is no longer required !!!!\n" +
                ")"
        )
    )
    fun thrownBuilder(
        assertionVerb: Translatable,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder
}

