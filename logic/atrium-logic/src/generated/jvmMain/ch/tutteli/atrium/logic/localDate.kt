// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
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

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: LocalDateAssertions
    get() = getImpl(LocalDateAssertions::class) { DefaultLocalDateAssertions() }
