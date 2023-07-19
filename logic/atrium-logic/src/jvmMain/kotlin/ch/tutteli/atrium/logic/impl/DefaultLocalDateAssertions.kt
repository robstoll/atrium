package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.LocalDateAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.DayOfWeek
import java.time.LocalDate

class DefaultLocalDateAssertions : LocalDateAssertions {

    override fun year(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<LocalDate>): FeatureExtractorBuilder.ExecutionStep<LocalDate, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
