//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [LocalDate] type.
 */
//TODO 1.3.0 deprecate
interface LocalDateAssertions {
    fun year(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun month(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun day(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, DayOfWeek>
}
