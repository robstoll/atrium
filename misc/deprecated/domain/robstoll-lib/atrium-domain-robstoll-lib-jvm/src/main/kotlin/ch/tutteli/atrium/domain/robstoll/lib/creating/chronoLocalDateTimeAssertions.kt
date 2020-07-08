@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : ChronoLocalDateTime<*>> _isBefore(expect: Expect<T>, expected: ChronoLocalDateTime<*>): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_BEFORE, expected) { it.isBefore(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : ChronoLocalDateTime<out ChronoLocalDate>> _isBeforeOrEquals(
    expect: Expect<T>,
    expected: ChronoLocalDateTime<*>
): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : ChronoLocalDateTime<out ChronoLocalDate>> _isAfter(
    expect: Expect<T>,
    expected: ChronoLocalDateTime<*>
): Assertion = ExpectImpl.builder.createDescriptive(expect, IS_AFTER, expected) { it.isAfter(expected) }

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : ChronoLocalDateTime<out ChronoLocalDate>> _isAfterOrEquals(
    expect: Expect<T>,
    expected: ChronoLocalDateTime<*>
): Assertion = ExpectImpl.builder.createDescriptive(expect, IS_AFTER_OR_EQUAL, expected) {
    it.isAfter(expected) || it.isEqual(expected)
}

@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("use the function from atrium-logic instead, will be removed with 1.0.0")
fun <T : ChronoLocalDateTime<out ChronoLocalDate>> _isEqual(
    expect: Expect<T>,
    expected: ChronoLocalDateTime<*>
): Assertion = ExpectImpl.builder.createDescriptive(expect, IS_EQUAL_TO, expected) {
    it.isEqual(expected)
}
