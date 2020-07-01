package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.ZonedDateTime

interface ZonedDateTimeAssertions {
    fun year(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int>

    fun month(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int>

    fun day(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int>

    fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, DayOfWeek>
}
