package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.*
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.Duration
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

abstract class ChronoZonedDateTimeAsStringExpectationsSpec(
    toBeBefore: Fun1<ChronoZonedDateTime<*>, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>,
    toBeAfter: Fun1<ChronoZonedDateTime<*>, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun toBeBefore(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun toBeBeforeOrTheSamePointInTimeAs(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun toBeAfter(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun toBeAfterOrTheSamePointInTimeAs(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun toBeTheSamePointInTimeAs(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    include(object : ChronoZonedDateTimeExpectationsSpec(
        fun1(::toBeBefore),
        fun1(::toBeBeforeOrTheSamePointInTimeAs),
        fun1(::toBeAfter),
        fun1(::toBeAfterOrTheSamePointInTimeAs),
        fun1(::toBeTheSamePointInTimeAs),
        describePrefix
    ) {})

    val toBeBeforeFun = toBeBefore.lambda
    val toBeBeforeOrTheSamePointInTimeAsFun = toBeBeforeOrTheSamePointInTimeAs.lambda
    val toBeAfterFun = toBeAfter.lambda
    val toBeAfterOrTheSamePointInTimeAsFun = toBeAfterOrTheSamePointInTimeAs.lambda
    val toBeTheSamePointInTimeAsFun = toBeTheSamePointInTimeAs.lambda

    val subject = ZonedDateTime.now() as ChronoZonedDateTime<*>
    val now = expect(subject)

    describe("wrong format") {
        listOf(
            "20200102030405666Z",
            "20200102030405Z",
            "202001020304Z",
            "20200102Z",
            "2020-1-02T03:04Z",
            "2020-01-2T03:04Z",
            "20-01-02T03:04Z",
            "2020-01-02T3:04Z",
            "2020-01-02T03:4Z",
            "2020-01-02T03:04:5Z",
            "2020-01-02t03:04Z",
            "2020-01-02T03:04Z+5",
            "2020-01-02T03:04Z+5:0",
            "2020-01-02T03:04Z+05:6",
            "2020-01-02T03:04Z-5",
            "2020-01-02T03:04Z-5:06",
            "2020-01-02T03:04Z-05:6"
        ).forEach { value ->
            context(value) {
                it("${toBeBefore.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeBeforeFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeBeforeOrTheSamePointInTimeAs.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeBeforeOrTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeAfter.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeAfterFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("toBeAfterOrTheSamePointInTimeAs throws a DateTimeParseException") {
                    expect {
                        now.toBeAfterOrTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeTheSamePointInTimeAs.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
            }
        }
    }

    describe("allowed shortcuts") {
        mapOf(
            Pair("2020-01-02T03:04:05Z", "2020-01-02T03:04:05Z") to ZonedDateTime.of(2020, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC) as ChronoZonedDateTime<*>,
            Pair("2020-01-02T03:04Z", "2020-01-02T03:04Z") to ZonedDateTime.of(2020, 1, 2, 3, 4, 0, 0, ZoneOffset.UTC),
            Pair("2020-01-02Z", "2020-01-02T00:00Z") to ZonedDateTime.of(2020, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC),
            Pair("2020-01-02+01:30", "2020-01-02T00:00+01:30") to ZonedDateTime.of(2020, 1, 2, 0, 0, 0, 0, ZoneOffset.of("+01:30"))
        ).forEach { (timePair, chronoZonedDateTime) ->
            val before = chronoZonedDateTime.minus(Duration.ofSeconds(1))
            val after = chronoZonedDateTime.plus(Duration.ofSeconds(1))

            /**
             * The first value of the pair is used as the function input
             */
            val zonedDateTimeAsString = timePair.first
            /**
             * The second value of the pair is used as comparison value.
             * This construct is needed because some test cases miss the time specification like "2020-01-02Z".
             * "2020-01-02Z" gets parsed to "2020-01-02T00:00Z" where the time is set to 00:00.
             * In this case we need a special reference value for comparing the time within the exception message.
             */
            val zonedDateTimeReferenceValue = timePair.second

            context("passing $zonedDateTimeAsString") {

                it("$before ${toBeBefore.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(before).toBeBeforeFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${toBeBefore.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).toBeBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_BEFORE, zonedDateTimeReferenceValue) }
                    }
                }
                it("$after ${toBeBefore.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_BEFORE, zonedDateTimeReferenceValue) }
                    }
                }

                it("$before ${toBeBeforeOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(before).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${toBeBeforeOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeBeforeOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS, zonedDateTimeReferenceValue) }
                    }
                }

                it("$before ${toBeAfter.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_AFTER, zonedDateTimeReferenceValue) }
                    }
                }
                it("$chronoZonedDateTime ${toBeAfter.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).toBeAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_AFTER, zonedDateTimeReferenceValue) }
                    }
                }
                it("$after ${toBeAfter.name} $zonedDateTimeAsString does not throw") {
                    expect(after).toBeAfterFun(zonedDateTimeAsString)
                }

                it("$before ${toBeAfterOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS, zonedDateTimeReferenceValue) }
                    }
                }
                it("$chronoZonedDateTime ${toBeAfterOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeAfterOrTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(after).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }

                it("$before ${toBeTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_THE_SAME_POINT_IN_TIME_AS, zonedDateTimeReferenceValue) }
                    }
                }
                it("$chronoZonedDateTime ${toBeTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeTheSamePointInTimeAs.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        message { toContainDescr(TO_BE_THE_SAME_POINT_IN_TIME_AS, zonedDateTimeReferenceValue) }
                    }
                }
            }
        }
    }
})
