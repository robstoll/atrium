@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.DayOfWeek
import java.time.ZonedDateTime

fun <T : ZonedDateTime> _year(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, YEAR) { year }

fun <T : ZonedDateTime> _month(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, MONTH) { monthValue }

fun <T : ZonedDateTime> _day(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, DAY) { dayOfMonth }

fun <T : ZonedDateTime> _dayOfWeek(expect: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek> =
    ExpectImpl.feature.manualFeature(expect, DAY_OF_WEEK) { dayOfWeek }
