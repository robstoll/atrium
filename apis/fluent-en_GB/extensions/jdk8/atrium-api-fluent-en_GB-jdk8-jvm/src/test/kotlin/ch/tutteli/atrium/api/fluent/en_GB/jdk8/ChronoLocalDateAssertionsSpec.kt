package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import java.time.chrono.ChronoLocalDate

object ChronoLocalDateAssertionsSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAssertionSpec(
    fun1(Expect<ChronoLocalDate>::isAfter),
    fun1(Expect<ChronoLocalDate>::isBeforeOrEquals)
)
