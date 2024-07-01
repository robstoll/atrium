//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ZonedDateTime] type.
 */
//TODO 1.3.0 deprecate
interface ZonedDateTimeAssertions {
    fun year(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun month(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun day(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int>

    fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek>
}
