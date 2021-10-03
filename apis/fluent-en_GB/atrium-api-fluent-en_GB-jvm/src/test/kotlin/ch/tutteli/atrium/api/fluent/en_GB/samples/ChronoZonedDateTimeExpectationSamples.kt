package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.time.ZoneId
import java.time.ZonedDateTime
import kotlin.test.Test

class ChronoZonedDateTimeExpectationSamples {

    @Test
    fun toBeBeforeChronoZonedDateTime() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBefore(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
    }

    @Test
    fun toBeBeforeString() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBefore("2021-06-06T12:10:00.000000000-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBefore("2021-06-06T12:10:00-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBefore("2021-06-06T12:10-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBefore("2021-06-07-03:00")
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsChronoZonedDateTime() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsString() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00.000000000-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 34, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-07-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeTheSamePointInTimeAsChronoZonedDateTime() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeTheSamePointInTimeAs(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
    }

    @Test
    fun toBeTheSamePointInTimeAsString() {
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsChronoZonedDateTime() {
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsString() {
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06-03:00")
        expect(ZonedDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeAfterChronoZonedDateTime() {
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfter(ZonedDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneId.of("America/Sao_Paulo")))
    }

    @Test
    fun toBeAfterString() {
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfter("2021-06-06T10:05:35.103000000-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfter("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfter("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(ZonedDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneId.of("America/Sao_Paulo")))
            .toBeAfter("2021-06-06-03:00")
    }
}
