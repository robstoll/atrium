package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import java.time.LocalDateTime

fun <T: LocalDateTime>_year(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(assertionContainer, DescriptionDateTimeLikeAssertion.YEAR) { year }

fun <T: LocalDateTime>_month(assertionContainer: Expect<T>): ExtractedFeaturePostStep<T, Int> =
    ExpectImpl.feature.manualFeature(assertionContainer, DescriptionDateTimeLikeAssertion.MONTH) { monthValue }

