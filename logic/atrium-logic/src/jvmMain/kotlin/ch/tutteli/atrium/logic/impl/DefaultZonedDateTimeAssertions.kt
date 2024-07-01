//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ZonedDateTimeAssertions
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation.*
import java.time.DayOfWeek
import java.time.ZonedDateTime


class DefaultZonedDateTimeAssertions : ZonedDateTimeAssertions {
    override fun year(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
