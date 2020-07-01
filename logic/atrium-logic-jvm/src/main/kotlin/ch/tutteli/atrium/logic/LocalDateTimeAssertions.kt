package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDateTime

interface LocalDateTimeAssertions {
    fun year(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int>

    fun month(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int>

    fun day(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, DayOfWeek>
}
