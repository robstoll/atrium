package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.*
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

abstract class ChronoZonedDateTimeAsStringAssertionSpec(
    isBefore: Fun1<ChronoZonedDateTime<*>, String>,
    isBeforeOrEqual: Fun1<ChronoZonedDateTime<*>, String>,
    isAfter: Fun1<ChronoZonedDateTime<*>, String>,
    isAfterOrEqual: Fun1<ChronoZonedDateTime<*>, String>,
    isEqual: Fun1<ChronoZonedDateTime<*>, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun isBefore(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(isBefore.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun isBeforeOrEqual(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(isBeforeOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun isAfter(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(isAfter.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun isAfterOrEqual(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(isAfterOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    fun isEqual(
        expect: Expect<ChronoZonedDateTime<*>>,
        expected: ChronoZonedDateTime<*>
    ): Expect<ChronoZonedDateTime<*>> =
        expect.(isEqual.lambda)(expected.format(DateTimeFormatter.ISO_ZONED_DATE_TIME))

    include(object : ChronoZonedDateTimeAssertionSpec(
        fun1(::isBefore),
        fun1(::isBeforeOrEqual),
        fun1(::isAfter),
        fun1(::isAfterOrEqual),
        fun1(::isEqual),
        describePrefix
    ) {})

    val isBeforeFun = isBefore.lambda
    val isBeforeOrEqualFun = isBeforeOrEqual.lambda
    val isAfterFun = isAfter.lambda
    val isAfterOrEqualFun = isAfterOrEqual.lambda
    val isEqualFun = isEqual.lambda

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
                it("${isBefore.name} throws a DateTimeParseException") {
                    expect {
                        now.isBeforeFun(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("${isBeforeOrEqual.name} throws a DateTimeParseException") {
                    expect {
                        now.isBeforeOrEqualFun(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("${isAfter.name} throws a DateTimeParseException") {
                    expect {
                        now.isAfterFun(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("isAfterOrEqual throws a DateTimeParseException") {
                    expect {
                        now.isAfterOrEqualFun(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("${isEqual.name} throws a DateTimeParseException") {
                    expect {
                        now.isEqualFun(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
            }
        }
    }

    describe("allowed shortcuts") {
        mapOf(
            "2020-01-02T03:04:05Z" to ZonedDateTime.of(2020, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC) as ChronoZonedDateTime<*>,
            "2020-01-02T03:04Z" to ZonedDateTime.of(2020, 1, 2, 3, 4, 0, 0, ZoneOffset.UTC),
            "2020-01-02Z" to ZonedDateTime.of(2020, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC)
        ).forEach { (zonedDateTimeAsString, chronoZonedDateTime) ->
            val before = chronoZonedDateTime.minus(Duration.ofSeconds(1))
            val after = chronoZonedDateTime.plus(Duration.ofSeconds(1))
            context("passing $zonedDateTimeAsString") {

                it("$before ${isBefore.name} $zonedDateTimeAsString does not throw") {
                    expect(before).isBeforeFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${isBefore.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).isBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_BEFORE.getDefault()}: $zonedDateTimeAsString") }
                }
                it("$after ${isBefore.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isBeforeFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_BEFORE.getDefault()}: $zonedDateTimeAsString") }
                }

                it("$before ${isBeforeOrEqual.name} $zonedDateTimeAsString does not throw") {
                    expect(before).isBeforeOrEqualFun(zonedDateTimeAsString)
                }
                it("$chronoZonedDateTime ${isBeforeOrEqual.name} $zonedDateTimeAsString does not throw") {
                    expect(chronoZonedDateTime).isBeforeOrEqualFun(zonedDateTimeAsString)
                }
                it("$after ${isBeforeOrEqual.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isBeforeOrEqualFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> {
                        messageContains("${IS_BEFORE_OR_EQUAL.getDefault()}: $zonedDateTimeAsString")
                    }
                }

                it("$before ${isAfter.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER.getDefault()}: $zonedDateTimeAsString") }
                }
                it("$chronoZonedDateTime ${isAfter.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoZonedDateTime).isAfterFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER.getDefault()}: $zonedDateTimeAsString") }
                }
                it("$after ${isAfter.name} $zonedDateTimeAsString does not throw") {
                    expect(after).isAfterFun(zonedDateTimeAsString)
                }

                it("$before ${isAfterOrEqual.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isAfterOrEqualFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER_OR_EQUAL.getDefault()}: $zonedDateTimeAsString") }
                }
                it("$chronoZonedDateTime ${isAfterOrEqual.name} $zonedDateTimeAsString does not throw") {
                    expect(chronoZonedDateTime).isAfterOrEqualFun(zonedDateTimeAsString)
                }
                it("$after ${isAfterOrEqual.name} $zonedDateTimeAsString does not throw") {
                    expect(after).isAfterOrEqualFun(zonedDateTimeAsString)
                }

                it("$before ${isEqual.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isEqualFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_EQUAL_TO.getDefault()}: $zonedDateTimeAsString") }
                }
                it("$chronoZonedDateTime ${isEqual.name} $zonedDateTimeAsString does not throw") {
                    expect(chronoZonedDateTime).isEqualFun(zonedDateTimeAsString)
                }
                it("$after ${isEqual.name} $zonedDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isEqualFun(zonedDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_EQUAL_TO.getDefault()}: $zonedDateTimeAsString") }
                }
            }
        }
    }
})
