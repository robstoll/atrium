@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import java.time.chrono.ChronoLocalDate

fun <T : ChronoLocalDate> _isBefore(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_BEFORE, expected) { it.isBefore(expected) }

fun <T : ChronoLocalDate> _isBeforeOrEquals(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_BEFORE_OR_EQUAL, expected) {
        it.isBefore(expected) || it.isEqual(expected)
    }

fun <T : ChronoLocalDate> _isAfter(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_AFTER, expected) { it.isAfter(expected) }

fun <T : ChronoLocalDate> _isAfterOrEquals(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
    ExpectImpl.builder.createDescriptive(expect, IS_AFTER_OR_EQUAL, expected) {
        it.isAfter(expected) || it.isEqual(expected)
    }

fun <T : ChronoLocalDate> _isEqual(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
    ExpectImpl.builder.createDescriptive(expect, SAME_DAY, expected) {
        it.isEqual(expected)
    }

