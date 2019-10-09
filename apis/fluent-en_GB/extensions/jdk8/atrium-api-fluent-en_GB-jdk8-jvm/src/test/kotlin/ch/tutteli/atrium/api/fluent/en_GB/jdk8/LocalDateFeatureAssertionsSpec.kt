package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.property
import java.time.LocalDate

class LocalDateFeatureAssertionsSpec : ch.tutteli.atrium.specs.integration.LocalDateFeatureAssertionsSpec(
    property<LocalDate, Int>(Expect<LocalDate>::year),
    fun1<LocalDate, Expect<Int>.() -> Unit>(Expect<LocalDate>::year),
    property<LocalDate, Int>(Expect<LocalDate>::month),
    fun1<LocalDate, Expect<Int>.() -> Unit>(Expect<LocalDate>::month)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<LocalDate> = notImplemented()
        var a2: Expect<out LocalDate> = notImplemented()

        a1.year
        a1 = a1.year { }
        a2.year
        a2 = a2.year { }

        a1.month
        a1 = a1.month {  }
        a2.month
        a2 = a2.month {  }
    }
}

