@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

/**
 * Turns `Expect<Date>` into `Expect<LocalDate>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @since 0.20.0
 */
infix fun <T : Date> Expect<T>.asLocalDate(@Suppress("UNUSED_PARAMETER") o : o) : Expect<LocalDate> =
    _logic.changeSubject.unreported { it.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [LocalDate].
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @since 0.20.0
 */
infix fun <T : Date> Expect<T>.asLocalDate(assertionCreator : Expect<LocalDate>.() -> Unit) : Expect<T> =
    apply { asLocalDate(o)._logic.appendAsGroup(assertionCreator) }
