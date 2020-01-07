@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isAfter
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBefore
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBeforeOrEquals
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeAssertionsImpl : ChronoZonedDateTimeAssertions {

    override fun <T : ChronoZonedDateTime<*>> isBefore(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isBefore(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isBeforeOrEquals(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isBeforeOrEquals(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isAfter(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isAfter(expect, expected)
}
