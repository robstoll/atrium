// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  buildSrc/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import java.time.DayOfWeek
import java.time.ZonedDateTime

/**
 * Collection of assertion functions and builders which are applicable to subjects with a [ZonedDateTime] type.
 */
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.logic.impl.DefaultZonedDateTimeAssertions

fun AssertionContainer<ZonedDateTime>.year(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = impl.year(this)

fun AssertionContainer<ZonedDateTime>.month(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = impl.month(this)

fun AssertionContainer<ZonedDateTime>.day(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, Int> = impl.day(this)

fun AssertionContainer<ZonedDateTime>.dayOfWeek(): FeatureExtractorBuilder.ExecutionStep<ZonedDateTime, DayOfWeek> = impl.dayOfWeek(this)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> AssertionContainer<T>.impl: ZonedDateTimeAssertions
    get() = getImpl(ZonedDateTimeAssertions::class) { DefaultZonedDateTimeAssertions() }
