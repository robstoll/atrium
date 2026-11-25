package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractChronoLocalDateExpectationsTest
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate
import java.time.format.DateTimeParseException
import kotlin.test.Test

class ChronoLocalDateExpectationsTest : AbstractChronoLocalDateExpectationsTest(
    fun1(Expect<ChronoLocalDate>::toBeBefore),
    fun1(Expect<ChronoLocalDate>::toBeBeforeOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDate>::toBeAfter),
    fun1(Expect<ChronoLocalDate>::toBeAfterOrTheSamePointInTimeAs),
    fun1(Expect<ChronoLocalDate>::toBeTheSamePointInTimeAs)
) {

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        val december22 = LocalDate.of(2019, 12, 22)
        val december23 = LocalDate.of(2019, 12, 23)
        val december24 = LocalDate.of(2019, 12, 24)

        var a1: Expect<ChronoLocalDate> = expect(december23)
        var a2: Expect<LocalDate> = expect(december23)

        a1 = a1.toBeBefore(december24)
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(december23)
        a1 = a1.toBeAfter(december22)
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(december23)
        a1 = a1.toBeTheSamePointInTimeAs(december23)

        a2 = a2.toBeBefore(december24)
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(december23)
        a2 = a2.toBeAfter(december22)
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(december23)
        a2 = a2.toBeTheSamePointInTimeAs(december23)

        a1 = a1.toBeBefore(JapaneseDate.of(2019, 12, 24))
        a1 = a1.toBeBeforeOrTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))
        a1 = a1.toBeAfter(JapaneseDate.of(2019, 12, 22))
        a1 = a1.toBeAfterOrTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))
        a1 = a1.toBeTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))

        a2 = a2.toBeBefore(JapaneseDate.of(2019, 12, 24))
        a2 = a2.toBeBeforeOrTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))
        a2 = a2.toBeAfter(JapaneseDate.of(2019, 12, 22))
        a2 = a2.toBeAfterOrTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))
        a2 = a2.toBeTheSamePointInTimeAs(JapaneseDate.of(2019, 12, 23))

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

    /**
     * Restores the "wrong format" cases of the previously Spek-based
     * ChronoLocalDateAsStringExpectationsSpec, which stopped being executed when the
     * include of the renamed spec was removed; full String coverage comes back with
     * the migration of that spec (#2271).
     */
    @Test
    fun stringArgumentInWrongFormatThrowsDateTimeParseException() {
        val now = expect(LocalDate.now() as ChronoLocalDate)
        listOf(
            "20200903",
            "2020-9-03",
            "2020-09-3",
            "20-09-3",
            "2020-09-03T07:14"
        ).forEach { value ->
            expect {
                now.toBeBefore(value)
            }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
            expect {
                now.toBeBeforeOrTheSamePointInTimeAs(value)
            }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
            expect {
                now.toBeAfter(value)
            }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
            expect {
                now.toBeAfterOrTheSamePointInTimeAs(value)
            }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
            expect {
                now.toBeTheSamePointInTimeAs(value)
            }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
        }
    }
}
