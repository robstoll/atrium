package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoLocalDateAsStringExpectationsTest
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import kotlin.test.Test

class ChronoLocalDateAsStringExpectationsTest : AbstractChronoLocalDateAsStringExpectationsTest(
    fun1(Expect<ChronoLocalDate>::toBeBefore),
    fun1(Expect<ChronoLocalDate>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDate>::toBeAfter),
    fun1(Expect<ChronoLocalDate>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDate>::toBeTheSamePointInTimeAs)
) {
    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val december23 = LocalDate.of(2019, 12, 23)

        var a1: Expect<ChronoLocalDate> = expect(december23)
        var a2: Expect<LocalDate> = expect(december23)

        a1 = a1.toBeBefore("2019-12-24")
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs("2019-12-23")
        a1 = a1.toBeAfter("2019-12-22")
        a1 = a1.toBeAfterOrTheSamePointInTimeAs("2019-12-23")
        a1 = a1.toBeTheSamePointInTimeAs("2019-12-23")

        a2 = a2.toBeBefore("2019-12-24")
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs("2019-12-23")
        a2 = a2.toBeAfter("2019-12-22")
        a2 = a2.toBeAfterOrTheSamePointInTimeAs("2019-12-23")
        a2 = a2.toBeTheSamePointInTimeAs("2019-12-23")
    }
}
