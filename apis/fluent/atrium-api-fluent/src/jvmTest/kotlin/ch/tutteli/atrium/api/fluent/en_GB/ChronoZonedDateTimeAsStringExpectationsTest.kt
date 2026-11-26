package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoZonedDateTimeAsStringExpectationsTest
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import kotlin.test.Test

class ChronoZonedDateTimeAsStringExpectationsTest : AbstractChronoZonedDateTimeAsStringExpectationsTest(
    fun1(Expect<ChronoZonedDateTime<*>>::toBeBefore),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeAfter),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val eleven = ZonedDateTime.of(2019, 12, 24, 11, 15, 30, 0, ZoneOffset.UTC)

        var a1: Expect<ChronoZonedDateTime<*>> = expect(eleven)
        var a2: Expect<ZonedDateTime> = expect(eleven)

        a1 = a1.toBeBefore("2019-12-24T12:15:30Z")
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs("2019-12-24T11:15:30Z")
        a1 = a1.toBeAfter("2019-12-24T10:15:30Z")
        a1 = a1.toBeAfterOrTheSamePointInTimeAs("2019-12-24T11:15:30Z")
        a1 = a1.toBeTheSamePointInTimeAs("2019-12-24T11:15:30Z")

        a2 = a2.toBeBefore("2019-12-24T12:15:30Z")
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs("2019-12-24T11:15:30Z")
        a2 = a2.toBeAfter("2019-12-24T10:15:30Z")
        a2 = a2.toBeAfterOrTheSamePointInTimeAs("2019-12-24T11:15:30Z")
        a2 = a2.toBeTheSamePointInTimeAs("2019-12-24T11:15:30Z")
    }
}
