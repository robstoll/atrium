package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeAssertionSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
    fun1(Expect<ChronoLocalDateTime<*>>::isBefore),
    fun1(Expect<ChronoLocalDateTime<*>>::isBeforeOrEquals),
    fun1(Expect<ChronoLocalDateTime<*>>::isAfter),
    fun1(Expect<ChronoLocalDateTime<*>>::isAfterOrEquals)
) {
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoLocalDateTime: ChronoLocalDateTime<*> = notImplemented()
        var a1: Expect<ChronoLocalDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoLocalDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoLocalDateTime<*>> = notImplemented()
        var a4: Expect<LocalDateTime> = notImplemented()

        a1 = a1.isBefore(LocalDateTime.now())
        a1 = a1.isBeforeOrEquals(LocalDateTime.now())
        a1 = a1.isAfter(LocalDateTime.now())
        a1 = a1.isAfterOrEquals(LocalDateTime.now())

        a2 = a2.isBefore(LocalDateTime.now())
        a2 = a2.isBeforeOrEquals(LocalDateTime.now())
        a2 = a2.isAfter(LocalDateTime.now())
        a2 = a2.isAfterOrEquals(LocalDateTime.now())

        a3 = a3.isBefore(LocalDateTime.now())
        a3 = a3.isBeforeOrEquals(LocalDateTime.now())
        a3 = a3.isAfter(LocalDateTime.now())
        a3 = a3.isAfterOrEquals(LocalDateTime.now())

        a4 = a4.isBefore(LocalDateTime.now())
        a4 = a4.isBeforeOrEquals(LocalDateTime.now())
        a4 = a4.isAfter(LocalDateTime.now())
        a4 = a4.isAfterOrEquals(LocalDateTime.now())

        a1 = a1.isBefore(chronoLocalDateTime)
        a1 = a1.isBeforeOrEquals(chronoLocalDateTime)
        a1 = a1.isAfter(chronoLocalDateTime)
        a1 = a1.isAfterOrEquals(chronoLocalDateTime)

        a2 = a2.isBefore(chronoLocalDateTime)
        a2 = a2.isBeforeOrEquals(chronoLocalDateTime)
        a2 = a2.isAfter(chronoLocalDateTime)
        a2 = a2.isAfterOrEquals(chronoLocalDateTime)

        a3 = a3.isBefore(chronoLocalDateTime)
        a3 = a3.isBeforeOrEquals(chronoLocalDateTime)
        a3 = a3.isAfter(chronoLocalDateTime)
        a3 = a3.isAfterOrEquals(chronoLocalDateTime)

        a4 = a4.isBefore(chronoLocalDateTime)
        a4 = a4.isBeforeOrEquals(chronoLocalDateTime)
        a4 = a4.isAfter(chronoLocalDateTime)
        a4 = a4.isAfterOrEquals(chronoLocalDateTime)
    }
}
