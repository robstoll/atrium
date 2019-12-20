package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.chronoZonedDateTime
import java.time.chrono.ChronoZonedDateTime

fun <T : ChronoZonedDateTime> Expect<T>.isAfter(expected: T): Expect<T> =
    addAssertion(ExpectImpl.chronoZonedDateTime.isAfter(this, expected))
