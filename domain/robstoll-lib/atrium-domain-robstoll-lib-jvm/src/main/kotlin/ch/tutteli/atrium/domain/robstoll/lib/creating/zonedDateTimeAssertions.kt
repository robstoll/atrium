package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import java.time.DayOfWeek
import java.time.ZonedDateTime

fun <T: ZonedDateTime> _year(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(assertionContainer, DescriptionDateTimeLikeAssertion.YEAR) { year }

fun <T: ZonedDateTime> _month(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(assertionContainer, DescriptionDateTimeLikeAssertion.MONTH) { monthValue }

fun <T: ZonedDateTime> _dayOfWeek(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, DayOfWeek> =
    ExpectImpl.feature.manualFeature(assertionContainer, DescriptionDateTimeLikeAssertion.DAY_OF_WEEK) { dayOfWeek }


