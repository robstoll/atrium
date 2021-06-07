package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.LocalDate
import java.time.Month
import kotlin.test.Test

class ChronoLocalDateExpectationSamples {

    @Test
    fun toBeBeforeChronoLocalDate() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBefore(LocalDate.of(2021, Month.JULY, 1))
    }

    @Test
    fun toBeBeforeString() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBefore("2021-07-01")
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBeforeOrTheSamePointInTimeAs(LocalDate.of(2021, Month.JULY, 1))
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBeforeOrTheSamePointInTimeAs(LocalDate.of(2021, Month.JUNE, 6))
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsString() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBeforeOrTheSamePointInTimeAs("2021-07-01")
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06")
    }

    @Test
    fun toBeTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeTheSamePointInTimeAs(LocalDate.of(2021, Month.JUNE, 6))
    }

    @Test
    fun toBeTheSamePointInTimeAsString() {
        expect(LocalDate.of(2021, Month.JUNE, 6)).toBeTheSamePointInTimeAs("2021-06-06")
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDate.of(2021, Month.AUGUST, 6)).toBeAfterOrTheSamePointInTimeAs(LocalDate.of(2021, Month.JULY, 1))
        expect(LocalDate.of(2021, Month.JULY, 1)).toBeAfterOrTheSamePointInTimeAs(LocalDate.of(2021, Month.JULY, 1))
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsString() {
        expect(LocalDate.of(2021, Month.AUGUST, 6)).toBeAfterOrTheSamePointInTimeAs("2021-07-01")
        expect(LocalDate.of(2021, Month.JULY, 1)).toBeAfterOrTheSamePointInTimeAs("2021-07-01")
    }

    @Test
    fun toBeAfterChronoLocalDate() {
        expect(LocalDate.of(2021, Month.AUGUST, 6)).toBeAfter(LocalDate.of(2021, Month.JULY, 1))
    }

    @Test
    fun toBeAfterString() {
        expect(LocalDate.of(2021, Month.AUGUST, 6)).toBeAfter("2021-07-01")
    }
}
