package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.time.LocalDateTime
import java.time.Month
import kotlin.test.Test

class ChronoLocalDateTimeExpectationSamples {

    @Test
    fun toBeBeforeChronoLocalDateTime() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0))
    }

    @Test
    fun toBeBeforeString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore("2021-06-06T12:10:00.000000000")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore("2021-06-06T12:10:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore("2021-06-06T12:10")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBefore("2021-06-07")
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsChronoLocalDateTime() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0))
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00.000000000")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 34, 0)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 0)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 0, 0)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeBeforeOrTheSamePointInTimeAs("2021-06-07")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 0, 0, 0, 0)).toBeBeforeOrTheSamePointInTimeAs("2021-06-06")
    }

    @Test
    fun toBeTheSamePointInTimeAsChronoLocalDateTime() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 0)).toBeTheSamePointInTimeAs("2021-06-06T10:05:35")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 0, 0)).toBeTheSamePointInTimeAs("2021-06-06T10:05")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 0, 0, 0, 0)).toBeTheSamePointInTimeAs("2021-06-06")
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsChronoLocalDateTime() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeAfterOrTheSamePointInTimeAs(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06")
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 0, 0, 0, 0)).toBeAfterOrTheSamePointInTimeAs("2021-06-06")
    }

    @Test
    fun toBeAfterChronoLocalDateTime() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter(LocalDateTime.of(2021, Month.JUNE, 6, 10, 5, 35, 103))
    }

    @Test
    fun toBeAfterString() {
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter("2021-06-06T10:05:35.103000000")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter("2021-06-06T10:05:35")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter("2021-06-06T10:05")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(LocalDateTime.of(2021, Month.JUNE, 6, 12, 10, 0, 0)).toBeAfter("2021-06-06")
    }
}
