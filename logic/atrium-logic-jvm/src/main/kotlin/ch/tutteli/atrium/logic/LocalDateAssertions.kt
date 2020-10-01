@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [LocalDate] type.
 */
interface LocalDateAssertions {
    fun year(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun month(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun day(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, DayOfWeek>
}
