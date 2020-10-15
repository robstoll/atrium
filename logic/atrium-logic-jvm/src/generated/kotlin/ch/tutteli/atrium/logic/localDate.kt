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
import java.time.LocalDate
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultLocalDateAssertions

fun AssertionContainer<LocalDate>.year(): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> = impl.year(this)

fun AssertionContainer<LocalDate>.month(): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> = impl.month(this)

fun AssertionContainer<LocalDate>.day(): FeatureExtractorBuilder.ExecutionStep<LocalDate, Int> = impl.day(this)

fun AssertionContainer<LocalDate>.dayOfWeek(): FeatureExtractorBuilder.ExecutionStep<LocalDate, DayOfWeek> = impl.dayOfWeek(this)

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: LocalDateAssertions
    get() = getImpl(LocalDateAssertions::class) { DefaultLocalDateAssertions() }
