//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoZonedDateTimeAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.time.chrono.ChronoZonedDateTime

class ChronoZonedDateTimeAssertionsImpl : ChronoZonedDateTimeAssertions {

    override fun <T : ChronoZonedDateTime<*>> isBefore(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isBefore(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isBeforeOrEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isBeforeOrEquals(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isAfter(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isAfter(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isAfterOrEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isAfterOrEquals(expect, expected)

    override fun <T : ChronoZonedDateTime<*>> isEqual(
        expect: Expect<T>,
        expected: ChronoZonedDateTime<*>
    ): Assertion = _isEqual(expect, expected)
}
