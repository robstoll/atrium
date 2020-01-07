@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

/**
 * The access point to an implementation of [ChronoZonedDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoZonedDateTimeAssertions by lazy { loadSingleService(ChronoZonedDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoZonedDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoZonedDateTimeAssertions {
    fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBefore(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion

    fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion

    fun <T : ChronoZonedDateTime<out ChronoLocalDate>> isAfter(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion
}
