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
import java.nio.file.Path
import java.time.DayOfWeek
import java.time.ZonedDateTime

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultZonedDateTimeAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ZonedDateTimeAssertions
    get() = getImpl(ZonedDateTimeAssertions::class) { DefaultZonedDateTimeAssertions() }

fun AssertionContainer<ZonedDateTime>.year(): ExtractedFeaturePostStep<ZonedDateTime, Int> = impl.year(this)

fun AssertionContainer<ZonedDateTime>.month(): ExtractedFeaturePostStep<ZonedDateTime, Int> = impl.month(this)

fun AssertionContainer<ZonedDateTime>.day(): ExtractedFeaturePostStep<ZonedDateTime, Int> = impl.day(this)

fun AssertionContainer<ZonedDateTime>.dayOfWeek(): ExtractedFeaturePostStep<ZonedDateTime, DayOfWeek> = impl.dayOfWeek(this)
