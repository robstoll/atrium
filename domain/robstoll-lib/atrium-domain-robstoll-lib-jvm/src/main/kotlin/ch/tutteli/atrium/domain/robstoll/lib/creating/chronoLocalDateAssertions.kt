package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.IS_BEFORE
import java.time.chrono.ChronoLocalDate

fun <T : ChronoLocalDate> _isBefore(assertionContainer: Expect<T>, expected: T): Assertion =
    ExpectImpl.builder.createDescriptive(assertionContainer, IS_BEFORE, expected) { it.isBefore(expected) }
