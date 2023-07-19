package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.LocalDateTimeAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.DayOfWeek
import java.time.LocalDateTime

class DefaultLocalDateTimeAssertions : LocalDateTimeAssertions {
    override fun year(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<LocalDateTime>): FeatureExtractorBuilder.ExecutionStep<LocalDateTime, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
