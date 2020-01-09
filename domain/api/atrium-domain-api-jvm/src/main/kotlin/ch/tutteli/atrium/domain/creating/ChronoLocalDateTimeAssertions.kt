@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * The access point to an implementation of [ChronoLocalDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoLocalDateTimeAssertions by lazy { loadSingleService(ChronoLocalDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoLocalDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoLocalDateTimeAssertions {
    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBefore(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isBeforeOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfter(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion

    fun <T : ChronoLocalDateTime<out ChronoLocalDate>> isAfterOrEquals(
        expect: Expect<T>,
        expected: ChronoLocalDateTime<*>
    ): Assertion
}
