package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * The access point to an implementation of [ThrowableAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val throwableAssertions by lazy { loadSingleService(ThrowableAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [Throwable],
 * which an implementation of the domain of Atrium has to provide.
 */
//TODO remove with 1.0.0 if there aren't any non-deprecated functions added
interface ThrowableAssertions {

    /**
     * Turns the given [assertionVerb] into an [Untranslatable] and delegates to the other overload.
     */
    @Suppress("DEPRECATION", "DeprecatedCallableAddReplaceWith")
    @Deprecated("Use Expect instead; will be removed with 1.0.0")
    fun thrownBuilder(
        assertionVerb: String,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder =
        thrownBuilder(Untranslatable(assertionVerb), act, reporter)

    @Suppress("DEPRECATION")
    @Deprecated("Use Expect instead; will be removed with 1.0.0")
    fun thrownBuilder(
        assertionVerb: Translatable,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder
}

