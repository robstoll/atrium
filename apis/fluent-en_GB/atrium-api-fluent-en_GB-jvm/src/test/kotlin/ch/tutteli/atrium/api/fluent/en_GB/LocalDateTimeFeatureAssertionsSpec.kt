package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.time.DayOfWeek
import java.time.LocalDateTime

class LocalDateTimeFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.LocalDateTimeFeatureAssertionsSpec(
    property<LocalDateTime, Int>(Expect<LocalDateTime>::year),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::year),
    property<LocalDateTime, Int>(Expect<LocalDateTime>::month),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::month),
    property<LocalDateTime, Int>(Expect<LocalDateTime>::day),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::day),
    property<LocalDateTime, DayOfWeek>(Expect<LocalDateTime>::dayOfWeek),
    fun1<LocalDateTime, Expect<DayOfWeek>.() -> Unit>(Expect<LocalDateTime>::dayOfWeek)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<LocalDateTime> = notImplemented()

        a1.year
        a1 = a1.year { }

        a1.month
        a1 = a1.month { }

        a1.day
        a1 = a1.day { }

        a1.dayOfWeek
        a1 = a1.dayOfWeek { }
    }
}
