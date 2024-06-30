//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [LocalDateTime] type.
 */
//TODO 1.3.0 deprecate
interface LocalDateTimeAssertions {
    fun year(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int>

    fun month(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int>

    fun day(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, DayOfWeek>
}
