package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import java.time.chrono.ChronoLocalDateTime

/**
 * Expects that the subject of the assertion is before [expected] [ChronoLocalDateTime].
 *
 * @return This assertion container to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 *
 * @since 0.9.0
 */
fun <T : ChronoLocalDateTime> Expect<T>.isAfter(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoLocalDateTime.isAfter(this, expected))
