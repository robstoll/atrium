package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoZonedDateTimeExpectationsTest
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import kotlin.test.Test

class ChronoZonedDateTimeExpectationsTest : AbstractChronoZonedDateTimeExpectationsTest(
    fun1(Expect<ChronoZonedDateTime<*>>::toBeBefore),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeAfter),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoZonedDateTime<*>>::toBeTheSamePointInTimeAs)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val ten = ZonedDateTime.of(2019, 12, 24, 10, 15, 30, 0, ZoneOffset.UTC)
        val eleven = ZonedDateTime.of(2019, 12, 24, 11, 15, 30, 0, ZoneOffset.UTC)
        val twelve = ZonedDateTime.of(2019, 12, 24, 12, 15, 30, 0, ZoneOffset.UTC)

        var a1: Expect<ChronoZonedDateTime<*>> = expect(eleven)
        var a2: Expect<ZonedDateTime> = expect(eleven)

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

        a1 = a1.toBeTheSamePointInTimeAs(eleven.withZoneSameInstant(ZoneOffset.of("+01:30")))

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
