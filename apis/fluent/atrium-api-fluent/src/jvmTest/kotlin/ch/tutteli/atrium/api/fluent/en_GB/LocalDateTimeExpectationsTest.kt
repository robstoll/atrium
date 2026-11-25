package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.property
import java.time.DayOfWeek
import java.time.LocalDateTime
import kotlin.test.Test

class LocalDateTimeExpectationsTest : ch.tutteli.atrium.specs.integration.AbstractLocalDateTimeExpectationsTest(
    property<LocalDateTime, Int>(Expect<LocalDateTime>::year),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::year),
    property<LocalDateTime, Int>(Expect<LocalDateTime>::month),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::month),
    property<LocalDateTime, Int>(Expect<LocalDateTime>::day),
    fun1<LocalDateTime, Expect<Int>.() -> Unit>(Expect<LocalDateTime>::day),
    property<LocalDateTime, DayOfWeek>(Expect<LocalDateTime>::dayOfWeek),
    fun1<LocalDateTime, Expect<DayOfWeek>.() -> Unit>(Expect<LocalDateTime>::dayOfWeek)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var a1: Expect<LocalDateTime> = expect(LocalDateTime.of(2009, 5, 15, 10, 5))

        a1.year
        a1 = a1.year { toEqual(2009) }

        a1.month
        a1 = a1.month { toEqual(5) }

        a1.day
        a1 = a1.day { toEqual(15) }

        a1.dayOfWeek
        a1 = a1.dayOfWeek { toEqual(DayOfWeek.FRIDAY) }
    }
}
