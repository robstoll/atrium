package ch.tutteli.atrium.assertions.throwable.thrown.builders

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the *deprecated* entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 *
 * It contains the [assertionVerb] and [reporter] which will be used to create an [AssertionPlantNullable] as well as
 * the [act] function which is expected to throw a [Throwable].
 *
 * @property assertionVerb The assertion verb which will be used inter alia in reporting.
 * @property act The function which is expected to throw a [Throwable].
 * @property reporter The reporter which will be used for a [CoreFactory.newThrowingAssertionChecker].
 *
 * @constructor Represents the entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 * @param assertionVerb The assertion verb which will be used inter alia in reporting.
 * @param act The function which is expected to throw a [Throwable].
 * @param reporter The reporter which will be used for a [CoreFactory.newThrowingAssertionChecker].
 */
@Suppress("DEPRECATION")
@Deprecated(
    "Use AssertImpl; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.throwable.thrownBuilder(assertionVerb, act, reporter)",
        "ch.tutteli.atrium.domain.builders.AssertImpl"
    )
)
class ThrowableThrownBuilder(
    override val assertionVerb: Translatable,
    override val act: () -> Unit,
    override val reporter: Reporter
) : ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder
