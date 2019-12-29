package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
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
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<ZonedDateTime> = notImplemented()
        var a2: Expect<out ZonedDateTime> = notImplemented()

        a1.year
        a2.year
        a1 = a1.year { }
        a2 = a2.year { }

        a1.month
        a2.month
        a1 = a1.month { }
        a2 = a2.month { }

        a1.dayOfWeek
        a2.dayOfWeek
        a1 = a1.dayOfWeek { }
        a2 = a2.dayOfWeek { }

        a1.day
        a2.day
        a1 = a1.day { }
        a2 = a2.day { }
    }
}
