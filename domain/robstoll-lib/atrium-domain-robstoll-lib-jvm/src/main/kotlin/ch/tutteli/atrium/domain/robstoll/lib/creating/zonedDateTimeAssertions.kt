@file:Suppress(
    "FINAL_UPPER_BOUND" /* remove once https://youtrack.jetbrains.com/issue/KT-34257 is fixed */,
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating._domain
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import java.time.DayOfWeek
import java.time.ZonedDateTime

fun <T : ZonedDateTime> _year(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    assertionContainer._domain.manualFeature(DescriptionDateTimeLikeAssertion.YEAR) { year }

fun <T : ZonedDateTime> _month(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    assertionContainer._domain.manualFeature(DescriptionDateTimeLikeAssertion.MONTH) { monthValue }

fun <T : ZonedDateTime> _day(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    assertionContainer._domain.manualFeature(DescriptionDateTimeLikeAssertion.DAY) { dayOfMonth }

fun <T : ZonedDateTime> _dayOfWeek(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek> =
    assertionContainer._domain.manualFeature(DescriptionDateTimeLikeAssertion.DAY_OF_WEEK) { dayOfWeek }


