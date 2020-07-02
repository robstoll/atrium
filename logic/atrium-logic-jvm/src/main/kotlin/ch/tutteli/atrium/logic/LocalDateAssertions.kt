package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDate

interface LocalDateAssertions {
    fun year(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun month(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun day(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, DayOfWeek>
}
