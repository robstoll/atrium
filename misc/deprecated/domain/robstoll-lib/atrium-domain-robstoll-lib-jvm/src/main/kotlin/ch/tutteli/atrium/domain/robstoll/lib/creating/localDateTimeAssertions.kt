//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE",
    /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */
    "FINAL_UPPER_BOUND"
)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import java.time.DayOfWeek
import java.time.LocalDateTime

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : LocalDateTime> _year(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, DescriptionDateTimeLikeAssertion.YEAR) { year }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : LocalDateTime> _month(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, DescriptionDateTimeLikeAssertion.MONTH) { monthValue }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : LocalDateTime> _day(expect: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(expect, DescriptionDateTimeLikeAssertion.DAY) { dayOfMonth }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : LocalDateTime> _dayOfWeek(expect: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek> =
    ExpectImpl.feature.manualFeature(expect, DescriptionDateTimeLikeAssertion.DAY_OF_WEEK) { dayOfWeek }

