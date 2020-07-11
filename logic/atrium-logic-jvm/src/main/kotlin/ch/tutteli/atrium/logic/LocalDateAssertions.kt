@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.chrono.ChronoLocalDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [LocalDate] type.
 */
interface LocalDateAssertions {
    fun year(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun month(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun day(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, Int>

    fun dayOfWeek(container: AssertionContainer<LocalDate>): ExtractedFeaturePostStep<LocalDate, DayOfWeek>
}
