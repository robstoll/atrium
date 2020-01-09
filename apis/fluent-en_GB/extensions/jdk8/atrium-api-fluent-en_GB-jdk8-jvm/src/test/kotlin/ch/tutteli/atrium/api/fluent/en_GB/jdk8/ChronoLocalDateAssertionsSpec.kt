package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

class ChronoLocalDateAssertionsSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateAssertionSpec(
    fun1(Expect<ChronoLocalDate>::isBefore),
    fun1(Expect<ChronoLocalDate>::isBeforeOrEqual),
    fun1(Expect<ChronoLocalDate>::isAfter),
    fun1(Expect<ChronoLocalDate>::isAfterOrEqual)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoLocalDate: ChronoLocalDate = notImplemented()
        var a1: Expect<ChronoLocalDate> = notImplemented()
        var a2: Expect<LocalDate> = notImplemented()

        a1 = a1.isBefore(LocalDate.now())
        a1 = a1.isBeforeOrEqual(LocalDate.now())
        a1 = a1.isAfter(LocalDate.now())
        a1 = a1.isAfterOrEqual(LocalDate.now())

        a2 = a2.isBefore(LocalDate.now())
        a2 = a2.isBeforeOrEqual(LocalDate.now())
        a2 = a2.isAfter(LocalDate.now())
        a2 = a2.isAfterOrEqual(LocalDate.now())

        a1 = a1.isBefore(JapaneseDate.now())
        a1 = a1.isBeforeOrEqual(JapaneseDate.now())
        a1 = a1.isAfter(JapaneseDate.now())
        a1 = a1.isAfterOrEqual(JapaneseDate.now())

        a2 = a2.isBefore(JapaneseDate.now())
        a2 = a2.isBeforeOrEqual(JapaneseDate.now())
        a2 = a2.isAfter(JapaneseDate.now())
        a2 = a2.isAfterOrEqual(JapaneseDate.now())

        a1 = a1.isBefore(chronoLocalDate)
        a1 = a1.isBeforeOrEqual(chronoLocalDate)
        a1 = a1.isAfter(chronoLocalDate)
        a1 = a1.isAfterOrEqual(chronoLocalDate)

        a2 = a2.isBefore(chronoLocalDate)
        a2 = a2.isBeforeOrEqual(chronoLocalDate)
        a2 = a2.isAfter(chronoLocalDate)
        a2 = a2.isAfterOrEqual(chronoLocalDate)
    }
}
