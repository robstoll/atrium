package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractZonedDateTimeExpectationsTest
import ch.tutteli.atrium.specs.property
import java.time.DayOfWeek
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.Test

class ZonedDateTimeExpectationsTest : AbstractZonedDateTimeExpectationsTest(
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::year),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::year),
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::month),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::month),
    property<ZonedDateTime, Int>(Expect<ZonedDateTime>::day),
    fun1<ZonedDateTime, Expect<Int>.() -> Unit>(Expect<ZonedDateTime>::day),
    property<ZonedDateTime, DayOfWeek>(Expect<ZonedDateTime>::dayOfWeek),
    fun1<ZonedDateTime, Expect<DayOfWeek>.() -> Unit>(Expect<ZonedDateTime>::dayOfWeek)
) {

    @Suppress("UNUSED_VARIABLE", "UNUSED_VALUE", "unused")
    @Test
    fun ambiguityTest() {
        var a1: Expect<ZonedDateTime> = expect(ZonedDateTime.of(2009, 5, 15, 10, 30, 0, 0, ZoneId.of("UTC")))

        val year: Expect<Int> = a1.year
        a1 = a1.year { toEqual(2009) }

        val month: Expect<Int> = a1.month
        a1 = a1.month { toEqual(5) }

        val day: Expect<Int> = a1.day
        a1 = a1.day { toEqual(15) }

        val dayOfWeek: Expect<DayOfWeek> = a1.dayOfWeek
        a1 = a1.dayOfWeek { toEqual(DayOfWeek.FRIDAY) }
    }
}
