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
import java.time.LocalDateTime

fun AssertionContainer<LocalDateTime>.year(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.year(this)

fun AssertionContainer<LocalDateTime>.month(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.month(this)

fun AssertionContainer<LocalDateTime>.day(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.day(this)

fun AssertionContainer<LocalDateTime>.dayOfWeek(): ExtractedFeaturePostStep<LocalDateTime, DayOfWeek> = _localDateTimeImpl.dayOfWeek(this)
