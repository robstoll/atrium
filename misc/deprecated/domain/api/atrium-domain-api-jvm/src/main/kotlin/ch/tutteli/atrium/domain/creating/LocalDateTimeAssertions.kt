//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */
    "FINAL_UPPER_BOUND",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * The access point to an implementation of [LocalDateTimeAssertions].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val localDateTimeAssertions by lazy { loadSingleService(LocalDateTimeAssertions::class) }

/**
 * Defines the minimum set of assertion functions and builders applicable to [LocalDateTime],
 * which an implementation of the domain of Atrium has to provide.
 */
@Deprecated(
    "Use LocalDateTimeAssertions from atrium-logic; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.logic.LocalDateTimeAssertions")
)
interface LocalDateTimeAssertions {
    fun <T : LocalDateTime> year(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDateTime> month(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDateTime> day(expect: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T : LocalDateTime> dayOfWeek(expect: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek>
}
