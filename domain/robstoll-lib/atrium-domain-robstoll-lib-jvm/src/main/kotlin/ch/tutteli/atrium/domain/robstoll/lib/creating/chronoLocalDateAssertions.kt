package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion

fun <T: ChronoLocalDate> _isBeforeOrEquals(assertionContainer: Expect<T>, expected: T):Assertion =
    ExpectImpl
        .builder
        .createDescriptive(assertionContainer, DescriptionDateTimeLikeAssertion.IS_AFTER, expected)
        { it.isBeforeOrEquals(expected) }



