@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import java.time.chrono.ChronoLocalDate

/**
 * The access point to an implementation of [ChronoLocalDateAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val chronoLocalDateAssertions by lazy { loadSingleService(ChronoLocalDateAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [ChronoLocalDate],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ChronoLocalDateAssertions {
    fun <T : ChronoLocalDate> isBefore(expect: Expect<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isAfter(expect: Expect<T>, expected: ChronoLocalDate): Assertion
    fun <T : ChronoLocalDate> isBeforeOrEquals(expect: Expect<T>, expected: ChronoLocalDate): Assertion
}
