//TODO remove file with 1.0.0
@file:Suppress(
    "DEPRECATION",
    /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.*
import java.time.chrono.ChronoLocalDate

@Deprecated("Will be removed with 1.0.0")
class ChronoLocalDateAssertionsImpl : ChronoLocalDateAssertions {

    override fun <T : ChronoLocalDate> isBefore(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
        _isBefore(expect, expected)

    override fun <T : ChronoLocalDate> isBeforeOrEquals(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
        _isBeforeOrEquals(expect, expected)

    override fun <T : ChronoLocalDate> isAfter(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
        _isAfter(expect, expected)

    override fun <T : ChronoLocalDate> isAfterOrEquals(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
        _isAfterOrEquals(expect, expected)

    override fun <T : ChronoLocalDate> isEqual(expect: Expect<T>, expected: ChronoLocalDate): Assertion =
        _isEqual(expect, expected)
}
