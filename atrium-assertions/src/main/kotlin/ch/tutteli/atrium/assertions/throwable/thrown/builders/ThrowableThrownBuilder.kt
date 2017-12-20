package ch.tutteli.atrium.assertions.throwable.thrown.builders

import ch.tutteli.atrium.IAtriumFactory
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 *
 * It contains the [assertionVerb] and [reporter] which will be used to create an [IAssertionPlantNullable] as well as
 * the [act] function which is expected to throw a [Throwable].
 *
 * @property assertionVerb The assertion verb which will be used inter alia in error reporting.
 * @property act The function which is expected to throw a [Throwable].
 * @property reporter The reporter which will be use for a [IAtriumFactory.newThrowingAssertionChecker].
 *
 * @constructor Represents the entry point of the fluent API of sophisticated a [Throwable] `was thrown` assertions.
 * @param assertionVerb The assertion verb which will be used inter alia in error reporting.
 * @param act The function which is expected to throw a [Throwable].
 * @param reporter The reporter which will be use for a [IAtriumFactory.newThrowingAssertionChecker].
 */
class ThrowableThrownBuilder(
    val assertionVerb: ITranslatable,
    val act: () -> Unit,
    val reporter: Reporter
)
