package ch.tutteli.atrium.creating.throwable.thrown.builders

import ch.tutteli.atrium.ICoreFactory
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 *
 * It contains the [assertionVerb] and [reporter] which will be used to create an [AssertionPlantNullable] as well as
 * the [act] function which is expected to throw a [Throwable].
 *
 * @property assertionVerb The assertion verb which will be used inter alia in error reporting.
 * @property act The function which is expected to throw a [Throwable].
 * @property reporter The reporter which will be use for a [ICoreFactory.newThrowingAssertionChecker].
 *
 * @constructor Represents the entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 * @param assertionVerb The assertion verb which will be used inter alia in error reporting.
 * @param act The function which is expected to throw a [Throwable].
 * @param reporter The reporter which will be use for a [ICoreFactory.newThrowingAssertionChecker].
 */
class ThrowableThrownBuilder(
    val assertionVerb: Translatable,
    val act: () -> Unit,
    val reporter: Reporter
)
