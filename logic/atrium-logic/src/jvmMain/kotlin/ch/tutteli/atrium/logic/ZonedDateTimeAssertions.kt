@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ZonedDateTime] type.
 */
interface ZonedDateTimeAssertions {
    fun year(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun month(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun day(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek>
}
