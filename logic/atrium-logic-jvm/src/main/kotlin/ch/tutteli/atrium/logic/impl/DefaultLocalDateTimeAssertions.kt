@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.LocalDateTimeAssertions
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.DayOfWeek
import java.time.LocalDateTime

class DefaultLocalDateTimeAssertions : LocalDateTimeAssertions {
    override fun year(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int> =
        container.manualFeature(YEAR) { year }

    override fun month(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int> =
        container.manualFeature(MONTH) { monthValue }

    override fun day(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, Int> =
        container.manualFeature(DAY) { dayOfMonth }

    override fun dayOfWeek(container: AssertionContainer<LocalDateTime>): ExtractedFeaturePostStep<LocalDateTime, DayOfWeek> =
        container.manualFeature(DAY_OF_WEEK) { dayOfWeek }
}
