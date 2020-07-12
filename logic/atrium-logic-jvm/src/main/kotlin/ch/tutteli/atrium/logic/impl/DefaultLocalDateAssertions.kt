package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.LocalDateAssertions
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.DayOfWeek
import java.time.LocalDate

class DefaultLocalDateAssertions : LocalDateAssertions {

    override fun year(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
