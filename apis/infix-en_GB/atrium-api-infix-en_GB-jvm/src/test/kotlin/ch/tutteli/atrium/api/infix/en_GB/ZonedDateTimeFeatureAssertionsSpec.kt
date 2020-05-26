package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import ch.tutteli.atrium.specs.testutils.WithAsciiReporter
import java.time.DayOfWeek
import java.time.ZonedDateTime

class ZonedDateTimeFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.ZonedDateTimeFeatureAssertionsSpec(
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::year),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::year),
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::month),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::month),
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::day),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::day),
    property<ZonedDateTime, DayOfWeek>(Expect<ZonedDateTime>::dayOfWeek),
    fun1<ZonedDateTime, Expect<DayOfWeek>.() -> Unit>(Expect<ZonedDateTime>::dayOfWeek)
) {
    companion object : WithAsciiReporter()

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<ZonedDateTime> = notImplemented()

        a1.year
        a1 = a1 year { }

        a1.month
        a1 = a1 month { }

        a1.dayOfWeek
        a1 = a1 dayOfWeek { }

        a1.day
        a1 = a1 day { }
    }
}
