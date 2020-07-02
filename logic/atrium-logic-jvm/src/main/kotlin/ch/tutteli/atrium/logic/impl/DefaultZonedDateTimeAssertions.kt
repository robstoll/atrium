package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.ZonedDateTimeAssertions
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import java.time.DayOfWeek
import java.time.ZonedDateTime


class DefaultZonedDateTimeAssertions : ZonedDateTimeAssertions {
    override fun year(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int> =
        container.manualFeature(DescriptionDateTimeLikeAssertion.YEAR) { year }

    override fun month(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int> =
        container.manualFeature(DescriptionDateTimeLikeAssertion.MONTH) { monthValue }

    override fun day(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, Int> =
        container.manualFeature(DescriptionDateTimeLikeAssertion.DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<ZonedDateTime>): ExtractedFeaturePostStep<ZonedDateTime, DayOfWeek> =
        container.manualFeature(DescriptionDateTimeLikeAssertion.DAY_OF_WEEK) { dayOfWeek }
}
