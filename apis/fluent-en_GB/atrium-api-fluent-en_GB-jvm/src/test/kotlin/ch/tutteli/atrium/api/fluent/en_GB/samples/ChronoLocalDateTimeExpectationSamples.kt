package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class ChronoLocalDateTimeExpectationSamples {

    @Test
    fun toBeBeforeChronoLocalDate() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0))
    }

    @Test
    fun toBeBeforeString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore("2021-06-06T12:10:00.000000000")
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0))
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00.000000000")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")
    }

    @Test
    fun toBeTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsChronoLocalDate() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeAfterOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")
    }

    @Test
    fun toBeAfterChronoLocalDate() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeAfterString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter("2021-06-06T10:05:35.103000000")
    }
}
