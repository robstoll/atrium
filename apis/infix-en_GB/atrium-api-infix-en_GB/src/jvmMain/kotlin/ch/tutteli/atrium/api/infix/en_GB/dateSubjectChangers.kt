@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.changeSubject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

/**
 * Turns `Expect<Date>` into `Expect<LocalDate>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DateSubjectChangerSamples.asLocalDateFeature
 *
 * @since 0.19.0
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
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DateSubjectChangerSamples.asLocalDate
 *
 * @since 0.19.0
 */
infix fun <T : Date> Expect<T>.asLocalDate(assertionCreator : Expect<LocalDate>.() -> Unit) : Expect<T> =
    apply { asLocalDate(o)._logic.appendAsGroup(assertionCreator) }

/**
 * Turns `Expect<Date>` into `Expect<LocalDateTime>`.
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return The newly created [Expect] for the transformed subject.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DateSubjectChangerSamples.asLocalDateTimeFeature
 *
 * @since 1.0.0
 */
infix fun <T : Date> Expect<T>.asLocalDateTime(@Suppress("UNUSED_PARAMETER") o : o) : Expect<LocalDateTime> =
    _logic.changeSubject.unreported { it.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() }

/**
 * Expects that the subject of `this` expectation holds all assertions the given [assertionCreator] creates for
 * the subject as [LocalDateTime].
 *
 * The transformation as such is not reflected in reporting.
 *
 * @return an [Expect] for the subject of `this` expectation.
 *
 * @sample ch.tutteli.atrium.api.infix.en_GB.samples.DateSubjectChangerSamples.asLocalDateTime
 *
 * @since 1.0.0
 */
infix fun <T : Date> Expect<T>.asLocalDateTime(assertionCreator : Expect<LocalDateTime>.() -> Unit) : Expect<T> =
    apply { asLocalDateTime(o)._logic.appendAsGroup(assertionCreator) }
