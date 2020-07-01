//---------------------------------------------------
//  Generated content, modify:
//  logic/generateLogic.gradle
//  if necessary - enjoy the day 🙂
//---------------------------------------------------

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import java.time.DayOfWeek
import java.time.ZonedDateTime

fun AssertionContainer<ZonedDateTime>.year(): ExtractedFeaturePostStep<ZonedDateTime, Int> = _zonedDateTimeImpl.year(this)

fun AssertionContainer<ZonedDateTime>.month(): ExtractedFeaturePostStep<ZonedDateTime, Int> = _zonedDateTimeImpl.month(this)

fun AssertionContainer<ZonedDateTime>.day(): ExtractedFeaturePostStep<ZonedDateTime, Int> = _zonedDateTimeImpl.day(this)

fun AssertionContainer<ZonedDateTime>.dayOfWeek(): ExtractedFeaturePostStep<ZonedDateTime, DayOfWeek> = _zonedDateTimeImpl.dayOfWeek(this)
