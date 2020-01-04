@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ChronoLocalDateAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._isAfter
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBefore
import ch.tutteli.atrium.domain.robstoll.lib.creating._isBeforeOrEquals
import java.time.chrono.ChronoLocalDate

class ChronoLocalDateAssertionsImpl : ChronoLocalDateAssertions {

    override fun <T : ChronoLocalDate> isBefore(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBefore(assertionContainer, expected)

    override fun <T : ChronoLocalDate> isBeforeOrEquals(assertionContainer: Expect<T>, expected: T): Assertion =
        _isBeforeOrEquals(assertionContainer, expected)

    override fun <T : ChronoLocalDate> isAfter(assertionContainer: Expect<T>, expected: T): Assertion =
        _isAfter(assertionContainer, expected)

}
