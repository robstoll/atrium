package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
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
interface LocalDateTimeAssertions {
    fun <T: LocalDateTime> year(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T: LocalDateTime> month(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int>

    fun <T: LocalDateTime> dayOfWeek(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int>
}
