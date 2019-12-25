package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

object ChronoLocalDateTimeAssertionSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
    fun1(Expect<LocalDateTime>::isAfter)
)
