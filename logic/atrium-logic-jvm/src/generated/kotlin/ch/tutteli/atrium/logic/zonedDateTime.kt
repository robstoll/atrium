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
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.ZonedDateTime

fun AssertionContainer<ZonedDateTime>.year(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = _zonedDateTimeImpl.year(this)

fun AssertionContainer<ZonedDateTime>.month(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = _zonedDateTimeImpl.month(this)

fun AssertionContainer<ZonedDateTime>.day(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = _zonedDateTimeImpl.day(this)

fun AssertionContainer<ZonedDateTime>.dayOfWeek(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek> = _zonedDateTimeImpl.dayOfWeek(this)
