package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.Duration
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Suppress("FunctionName")
abstract class AbstractChronoZonedDateTimeAsStringExpectationsTest(
    toBeBefore: Fun1<ChronoZonedDateTime<*>, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>,
    toBeAfter: Fun1<ChronoZonedDateTime<*>, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoZonedDateTime<*>, String>
) : AbstractChronoZonedDateTimeExpectationsTest(
    toBeBefore.name to { e: Expect<ChronoZonedDateTime<*>>, expected: ChronoZonedDateTime<*> ->
        e.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    },
    toBeBeforeOrTheSamePointInTimeAs.name to { e: Expect<ChronoZonedDateTime<*>>, expected: ChronoZonedDateTime<*> ->
        e.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    },
    toBeAfter.name to { e: Expect<ChronoZonedDateTime<*>>, expected: ChronoZonedDateTime<*> ->
        e.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    },
    toBeAfterOrTheSamePointInTimeAs.name to { e: Expect<ChronoZonedDateTime<*>>, expected: ChronoZonedDateTime<*> ->
        e.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    },
    toBeTheSamePointInTimeAs.name to { e: Expect<ChronoZonedDateTime<*>>, expected: ChronoZonedDateTime<*> ->
        e.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
    }
) {

    private val toBeBeforeSpec = toBeBefore
    private val toBeBeforeOrTheSamePointInTimeAsSpec = toBeBeforeOrTheSamePointInTimeAs
    private val toBeAfterSpec = toBeAfter
    private val toBeAfterOrTheSamePointInTimeAsSpec = toBeAfterOrTheSamePointInTimeAs
    private val toBeTheSamePointInTimeAsSpec = toBeTheSamePointInTimeAs

    private val toBeBeforeFun = toBeBefore.lambda
    private val toBeBeforeOrTheSamePointInTimeAsFun = toBeBeforeOrTheSamePointInTimeAs.lambda
    private val toBeAfterFun = toBeAfter.lambda
    private val toBeAfterOrTheSamePointInTimeAsFun = toBeAfterOrTheSamePointInTimeAs.lambda
    private val toBeTheSamePointInTimeAsFun = toBeTheSamePointInTimeAs.lambda

    private val toBeBeforeDescr = DescriptionDateTimeLikeExpectation.TO_BE_BEFORE.getDefault()
    private val toBeBeforeOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeAfterDescr = DescriptionDateTimeLikeExpectation.TO_BE_AFTER.getDefault()
    private val toBeAfterOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_POINT_IN_TIME_AS.getDefault()

    private val subject = ZonedDateTime.now() as ChronoZonedDateTime<*>
    private val now = expect(subject)

    @TestFactory
    fun stringArgumentsInWrongFormat() = testFactory(
        toBeBeforeSpec,
        toBeBeforeOrTheSamePointInTimeAsSpec,
        toBeAfterSpec,
        toBeAfterOrTheSamePointInTimeAsSpec,
        toBeTheSamePointInTimeAsSpec
    ) {
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
            describe(value) {
                it("${toBeBeforeSpec.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeBeforeFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeBeforeOrTheSamePointInTimeAsSpec.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeBeforeOrTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeAfterSpec.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeAfterFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("toBeAfterOrTheSamePointInTimeAs throws a DateTimeParseException") {
                    expect {
                        now.toBeAfterOrTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
                it("${toBeTheSamePointInTimeAsSpec.name} throws a DateTimeParseException") {
                    expect {
                        now.toBeTheSamePointInTimeAsFun(value)
                    }.toThrow<DateTimeParseException> { messageToContain("could not be parsed") }
                }
            }
        }
    }

    @TestFactory
    fun allowedShortcuts() = testFactory(
        toBeBeforeSpec,
        toBeBeforeOrTheSamePointInTimeAsSpec,
        toBeAfterSpec,
        toBeAfterOrTheSamePointInTimeAsSpec,
        toBeTheSamePointInTimeAsSpec
    ) {
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

            describe("passing $zonedDateTimeAsString") {

                it("$before ${toBeBeforeSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(before).toBeBeforeFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${toBeBeforeSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).toBeBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $zonedDateTimeReferenceValue") }
                }
                it("$after ${toBeBeforeSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $zonedDateTimeReferenceValue") }
                }

                it("$before ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(before).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $zonedDateTimeReferenceValue")
                    }
                }

                it("$before ${toBeAfterSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $zonedDateTimeReferenceValue") }
                }
                it("$chronoZonedDateTime ${toBeAfterSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).toBeAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $zonedDateTimeReferenceValue") }
                }
                it("$after ${toBeAfterSpec.name} $zonedDateTimeAsString does not throw") {
                    expect(after).toBeAfterFun(zonedDateTimeAsString)
                }

                it("$before ${toBeAfterOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $zonedDateTimeReferenceValue") }
                }
                it("$chronoZonedDateTime ${toBeAfterOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeAfterOrTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(after).toBeAfterOrTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }

                it("$before ${toBeTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(before).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $zonedDateTimeReferenceValue") }
                }
                it("$chronoZonedDateTime ${toBeTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue does not throw") {
                    expect(chronoZonedDateTime).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                }
                it("$after ${toBeTheSamePointInTimeAsSpec.name} $zonedDateTimeReferenceValue throws an AssertionError") {
                    expect {
                        expect(after).toBeTheSamePointInTimeAsFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $zonedDateTimeReferenceValue") }
                }
            }
        }
    }
}
