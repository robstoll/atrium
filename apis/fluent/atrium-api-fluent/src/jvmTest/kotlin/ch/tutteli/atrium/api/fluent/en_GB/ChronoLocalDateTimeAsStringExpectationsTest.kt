package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoLocalDateTimeAsStringExpectationsTest
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime
import kotlin.test.Test

class ChronoLocalDateTimeAsStringExpectationsTest : AbstractChronoLocalDateTimeAsStringExpectationsTest(
    fun1(Expect<ChronoLocalDateTime<*>>::toBeBefore),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeAfter),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDateTime<*>>::toBeTheSamePointInTimeAs)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)

        var a1: Expect<ChronoLocalDateTime<*>> = expect(eleven)
        var a2: Expect<LocalDateTime> = expect(eleven)

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
