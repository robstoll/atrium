@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * The access point to an implementation of [LocalDateAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val localDateAssertions by lazy { loadSingleService(LocalDateAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [LocalDate],
 * which an implementation of the domain of Atrium has to provide.
 */
interface LocalDateAssertions {
    fun <T : LocalDate> year(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDate> month(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDate> day(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDate> dayOfWeek(expect: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek>
}
