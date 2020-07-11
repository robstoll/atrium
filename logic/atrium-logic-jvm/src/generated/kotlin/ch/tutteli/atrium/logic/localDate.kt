//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.chrono.ChronoLocalDateTime

fun AssertionContainer<LocalDate>.year(): ExtractedFeaturePostStep<LocalDate, Int> = _localDateImpl.year(this)

fun AssertionContainer<LocalDate>.month(): ExtractedFeaturePostStep<LocalDate, Int> = _localDateImpl.month(this)

fun AssertionContainer<LocalDate>.day(): ExtractedFeaturePostStep<LocalDate, Int> = _localDateImpl.day(this)

fun AssertionContainer<LocalDate>.dayOfWeek(): ExtractedFeaturePostStep<LocalDate, DayOfWeek> = _localDateImpl.dayOfWeek(this)
