package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.time.DayOfWeek
import java.time.LocalDate

class LocalDateFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.LocalDateFeatureAssertionsSpec(
    property<LocalDate, Int>(Expect<LocalDate>::year),
    fun1<LocalDate, Expect<Int>.() -> Unit>(Expect<LocalDate>::year),
    property<LocalDate, Int>(Expect<LocalDate>::month),
    fun1<LocalDate, Expect<Int>.() -> Unit>(Expect<LocalDate>::month),
    property<LocalDate, Int>(Expect<LocalDate>::day),
    fun1<LocalDate, Expect<Int>.() -> Unit>(Expect<LocalDate>::day),
    property<LocalDate, DayOfWeek>(Expect<LocalDate>::dayOfWeek),
    fun1<LocalDate, Expect<DayOfWeek>.() -> Unit>(Expect<LocalDate>::dayOfWeek)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<LocalDate> = notImplemented()

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

