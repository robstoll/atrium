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

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultLocalDateTimeAssertions

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: LocalDateTimeAssertions
    get() = getImpl(LocalDateTimeAssertions::class) { DefaultLocalDateTimeAssertions() }

fun AssertionContainer<LocalDateTime>.year(): ExtractedFeaturePostStep<LocalDateTime, Int> = impl.year(this)

fun AssertionContainer<LocalDateTime>.month(): ExtractedFeaturePostStep<LocalDateTime, Int> = impl.month(this)

fun AssertionContainer<LocalDateTime>.day(): ExtractedFeaturePostStep<LocalDateTime, Int> = impl.day(this)

fun AssertionContainer<LocalDateTime>.dayOfWeek(): ExtractedFeaturePostStep<LocalDateTime, DayOfWeek> = impl.dayOfWeek(this)
