package ch.tutteli.atrium.api.fluent.en_GB.jdk8

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime

class ChronoLocalDateTimeAssertionSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
    fun1(Expect<LocalDateTime>::isBefore),
    fun1(Expect<LocalDateTime>::isBeforeOrEquals),
    fun1(Expect<LocalDateTime>::isAfter)
) {
    @Suppress("unused", "UNUSED_VALUE", /* TODO #289 remove again => */ "UNUSED_VARIABLE")
    private fun ambiguityTest() {
        val chronoLocalDateTime: ChronoLocalDateTime<*> = notImplemented()
        var a1: Expect<ChronoLocalDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoLocalDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoLocalDateTime<*>> = notImplemented()
        var a4: Expect<LocalDateTime> = notImplemented()

        //TODO #289 we need to change the signature, it should not accept T but ChronoLocalDateTime<*>
//        a1 = a1.isBefore(LocalDateTime.now())
//        a1 = a1.isBeforeOrEquals(LocalDateTime.now())
//        a1 = a1.isAfter(LocalDateTime.now())

        a2 = a2.isBefore(LocalDateTime.now())
        a2 = a2.isBeforeOrEquals(LocalDateTime.now())
        a2 = a2.isAfter(LocalDateTime.now())

        //TODO #289 we need to change the signature, it should not accept T but ChronoLocalDateTime<*>
//        a3 = a3.isBefore(LocalDateTime.now())
//        a3 = a3.isBeforeOrEquals(LocalDateTime.now())
//        a3 = a3.isAfter(LocalDateTime.now())

        a4 = a4.isBefore(LocalDateTime.now())
        a4 = a4.isBeforeOrEquals(LocalDateTime.now())
        a4 = a4.isAfter(LocalDateTime.now())


        //TODO #289 this should actually be supported
//        a1 = a1.isBefore(chronoLocalDateTime)
//        a1 = a1.isBeforeOrEquals(chronoLocalDateTime)
//        a1 = a1.isAfter(chronoLocalDateTime)
//
//        a2 = a2.isBefore(chronoLocalDateTime)
//        a2 = a2.isBeforeOrEquals(chronoLocalDateTime)
//        a2 = a2.isAfter(chronoLocalDateTime)
//
//        a3 = a3.isBefore(chronoLocalDateTime)
//        a3 = a3.isBeforeOrEquals(chronoLocalDateTime)
//        a3 = a3.isAfter(chronoLocalDateTime)

//        a4 = a4.isBefore(chronoLocalDateTime)
//        a4 = a4.isBeforeOrEquals(chronoLocalDateTime)
//        a4 = a4.isAfter(chronoLocalDateTime)
    }
}
