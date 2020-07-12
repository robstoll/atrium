//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.LocalDateTime

fun AssertionContainer<LocalDateTime>.year(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.year(this)

fun AssertionContainer<LocalDateTime>.month(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.month(this)

fun AssertionContainer<LocalDateTime>.day(): ExtractedFeaturePostStep<LocalDateTime, Int> = _localDateTimeImpl.day(this)

fun AssertionContainer<LocalDateTime>.dayOfWeek(): ExtractedFeaturePostStep<LocalDateTime, DayOfWeek> = _localDateTimeImpl.dayOfWeek(this)
