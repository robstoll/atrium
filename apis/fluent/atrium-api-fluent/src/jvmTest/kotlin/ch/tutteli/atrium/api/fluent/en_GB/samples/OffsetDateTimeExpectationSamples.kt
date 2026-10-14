package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.time.OffsetDateTime
import java.time.ZoneOffset
import kotlin.test.Test

class OffsetDateTimeExpectationSamples {

    @Test
    fun toBeBeforeOffsetDateTime() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(1)))
            .toBeBefore(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(1)))
    }

    @Test
    fun toBeBeforeString() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBefore("2021-06-06T12:10:00.000000000-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBefore("2021-06-06T12:10:00-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBefore("2021-06-06T12:10-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBefore("2021-06-07-03:00")
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsOffsetDateTime() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
    }

    @Test
    fun toBeBeforeOrTheSamePointInTimeAsString() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00.000000000-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 34, 0, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10:00-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T12:10-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-07-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeBeforeOrTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeTheSamePointInTimeAsOffsetDateTime() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeTheSamePointInTimeAs(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
    }

    @Test
    fun toBeTheSamePointInTimeAsString() {
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneOffset.ofHours(-3)))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsOffsetDateTime() {
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
    }

    @Test
    fun toBeAfterOrTheSamePointInTimeAsString() {
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35.000000103-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 10, 5, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06-03:00")
        expect(OffsetDateTime.of(2021, 6, 6, 0, 0, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfterOrTheSamePointInTimeAs("2021-06-06-03:00")
    }

    @Test
    fun toBeAfterOffsetDateTime() {
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfter(OffsetDateTime.of(2021, 6, 6, 10, 5, 35, 103, ZoneOffset.ofHours(-3)))
    }

    @Test
    fun toBeAfterString() {
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfter("2021-06-06T10:05:35.103000000-03:00")

        // format yyyy-MM-ddThh:mm:SS can be used, in which case .000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfter("2021-06-06T10:05:35-03:00")

        // format yyyy-MM-ddThh:mm can be used, in which case 00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfter("2021-06-06T10:05-03:00")

        // format yyyy-MM-dd can be used, in which case 00:00:00.000000000 is used for the omitted part
        expect(OffsetDateTime.of(2021, 6, 6, 12, 10, 0, 0, ZoneOffset.ofHours(-3)))
            .toBeAfter("2021-06-06-03:00")
    }
}
