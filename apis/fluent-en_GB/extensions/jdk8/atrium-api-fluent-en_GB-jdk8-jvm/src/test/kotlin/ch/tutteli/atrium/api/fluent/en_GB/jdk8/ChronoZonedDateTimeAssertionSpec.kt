package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import java.time.ZonedDateTime

object ChronoZonedDateTimeAssertionSpec : ch.tutteli.atrium.specs.integration.ChronoZonedDateTimeAssertionSpec(
    fun1(Expect<ZonedDateTime>::isAfter),
    fun1(Expect<ZonedDateTime>::isBeforeOrEquals),
    fun1(Expect<ZonedDateTime>::isBefore)
)
