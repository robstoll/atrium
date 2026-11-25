package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoLocalDateTimeExpectationsTest
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.chrono.ChronoLocalDateTime
import java.time.chrono.JapaneseDate
import kotlin.test.Test

class ChronoLocalDateTimeExpectationsTest : AbstractChronoLocalDateTimeExpectationsTest(
    fun1(Expect<ChronoLocalDateTime<*>>::toBeBefore),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeAfter),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeTheSamePointInTimeAs)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val ten = LocalDateTime.of(2019, 12, 24, 10, 15, 30)
        val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)
        val twelve = LocalDateTime.of(2019, 12, 24, 12, 15, 30)

        var a1: Expect<ChronoLocalDateTime<*>> = expect(eleven)
        var a2: Expect<LocalDateTime> = expect(eleven)

        a1 = a1.toBeBefore(twelve)
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(eleven)
        a1 = a1.toBeAfter(ten)
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(eleven)
        a1 = a1.toBeTheSamePointInTimeAs(eleven)

        a2 = a2.toBeBefore(twelve)
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(eleven)
        a2 = a2.toBeAfter(ten)
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(eleven)
        a2 = a2.toBeTheSamePointInTimeAs(eleven)

        val japaneseTen = JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(10, 15, 30))
        val japaneseTwelve = JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(12, 15, 30))

        a1 = a1.toBeBefore(japaneseTwelve)
        a1 = a1.toBeAfter(japaneseTen)
        a1 = a1.toBeTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30)))

        a1 = a1.toBeBefore("2019-12-24T12:15:30")
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs("2019-12-24T11:15:30")
        a1 = a1.toBeAfter("2019-12-24T10:15:30")
        a1 = a1.toBeAfterOrTheSamePointInTimeAs("2019-12-24T11:15:30")
        a1 = a1.toBeTheSamePointInTimeAs("2019-12-24T11:15:30")

        a2 = a2.toBeBefore("2019-12-24T12:15:30")
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs("2019-12-24T11:15:30")
        a2 = a2.toBeAfter("2019-12-24T10:15:30")
        a2 = a2.toBeAfterOrTheSamePointInTimeAs("2019-12-24T11:15:30")
        a2 = a2.toBeTheSamePointInTimeAs("2019-12-24T11:15:30")
    }
}
