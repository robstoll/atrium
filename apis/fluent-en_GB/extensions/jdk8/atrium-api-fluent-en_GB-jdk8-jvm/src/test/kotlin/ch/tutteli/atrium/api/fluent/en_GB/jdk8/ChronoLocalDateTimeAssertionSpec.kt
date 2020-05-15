// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeAssertionSpec : Spek({
    include(ChronoLocalDateTimeSpec)
}) {
    object ChronoLocalDateTimeSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
        fun1(Expect<ChronoLocalDateTime<*>>::isBefore),
        fun1(Expect<ChronoLocalDateTime<*>>::isBeforeOrEqual),
        fun1(Expect<ChronoLocalDateTime<*>>::isAfter),
        fun1(Expect<ChronoLocalDateTime<*>>::isAfterOrEqual),
        fun1(Expect<ChronoLocalDateTime<*>>::isEqual)
    )

    companion object {
        fun isBefore(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            //TODO #481 turn into string in ISO format
            expect.isBefore(expected)
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoLocalDateTime: ChronoLocalDateTime<*> = notImplemented()
        var a1: Expect<ChronoLocalDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoLocalDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoLocalDateTime<*>> = notImplemented()
        var a4: Expect<LocalDateTime> = notImplemented()

        a1 = a1.isBefore(LocalDateTime.now())
        a1 = a1.isBeforeOrEqual(LocalDateTime.now())
        a1 = a1.isAfter(LocalDateTime.now())
        a1 = a1.isAfterOrEqual(LocalDateTime.now())
        a1 = a1.isEqual(LocalDateTime.now())

        a2 = a2.isBefore(LocalDateTime.now())
        a2 = a2.isBeforeOrEqual(LocalDateTime.now())
        a2 = a2.isAfter(LocalDateTime.now())
        a2 = a2.isAfterOrEqual(LocalDateTime.now())
        a2 = a2.isEqual(LocalDateTime.now())

        a3 = a3.isBefore(LocalDateTime.now())
        a3 = a3.isBeforeOrEqual(LocalDateTime.now())
        a3 = a3.isAfter(LocalDateTime.now())
        a3 = a3.isAfterOrEqual(LocalDateTime.now())
        a3 = a3.isEqual(LocalDateTime.now())

        a4 = a4.isBefore(LocalDateTime.now())
        a4 = a4.isBeforeOrEqual(LocalDateTime.now())
        a4 = a4.isAfter(LocalDateTime.now())
        a4 = a4.isAfterOrEqual(LocalDateTime.now())
        a4 = a4.isEqual(LocalDateTime.now())

        a1 = a1.isBefore(chronoLocalDateTime)
        a1 = a1.isBeforeOrEqual(chronoLocalDateTime)
        a1 = a1.isAfter(chronoLocalDateTime)
        a1 = a1.isAfterOrEqual(chronoLocalDateTime)
        a1 = a1.isEqual(chronoLocalDateTime)

        a2 = a2.isBefore(chronoLocalDateTime)
        a2 = a2.isBeforeOrEqual(chronoLocalDateTime)
        a2 = a2.isAfter(chronoLocalDateTime)
        a2 = a2.isAfterOrEqual(chronoLocalDateTime)
        a2 = a2.isEqual(chronoLocalDateTime)

        a3 = a3.isBefore(chronoLocalDateTime)
        a3 = a3.isBeforeOrEqual(chronoLocalDateTime)
        a3 = a3.isAfter(chronoLocalDateTime)
        a3 = a3.isAfterOrEqual(chronoLocalDateTime)
        a3 = a3.isEqual(chronoLocalDateTime)

        a4 = a4.isBefore(chronoLocalDateTime)
        a4 = a4.isBeforeOrEqual(chronoLocalDateTime)
        a4 = a4.isAfter(chronoLocalDateTime)
        a4 = a4.isAfterOrEqual(chronoLocalDateTime)
        a4 = a4.isEqual(chronoLocalDateTime)
    }
}
