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
import java.time.Duration
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

abstract class ChronoLocalDateTimeAsStringAssertionSpec(
    isBefore: Fun1<ChronoLocalDateTime<*>, String>,
    isBeforeOrEqual: Fun1<ChronoLocalDateTime<*>, String>,
    isAfter: Fun1<ChronoLocalDateTime<*>, String>,
    isAfterOrEqual: Fun1<ChronoLocalDateTime<*>, String>,
    isEqual: Fun1<ChronoLocalDateTime<*>, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun isBefore(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(isBefore.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun isBeforeOrEqual(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(isBeforeOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun isAfter(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(isAfter.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun isAfterOrEqual(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(isAfterOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun isEqual(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(isEqual.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    include(object : ChronoLocalDateTimeAssertionSpec(
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

    val subject = LocalDateTime.now() as ChronoLocalDateTime<*>
    val now = expect(subject)

    describe("wrong format") {
        listOf(
            "20200101010101111",
            "20200101010101",
            "202001010101",
            "2020010101",
            "20-01-01T01:01",
            "2020-1-01T01:01",
            "2020-01-1T01:01",
            "2020-01-01T01",
            "2020-01-01T1:01",
            "2020-01-01T01:1",
            "2020-01-01T01:01:1",
            "2020-01-01t01:01:01"
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
            "2020-01-02T03:04:05" to LocalDateTime.of(2020, 1, 2, 3, 4, 5) as ChronoLocalDateTime<*>,
            "2020-01-02T03:04" to LocalDateTime.of(2020, 1, 2, 3, 4, 0),
            "2020-01-02" to LocalDateTime.of(2020, 1, 2, 0, 0, 0)
        ).forEach { (localDateTimeAsString, chronoLocalDateTime) ->
            val before = chronoLocalDateTime.minus(Duration.ofSeconds(1))
            val after = chronoLocalDateTime.plus(Duration.ofSeconds(1))
            context("passing $localDateTimeAsString") {

                it("$before ${isBefore.name} $localDateTimeAsString does not throw") {
                    expect(before).isBeforeFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${isBefore.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).isBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_BEFORE.getDefault()}: $localDateTimeAsString") }
                }
                it("$after ${isBefore.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_BEFORE.getDefault()}: $localDateTimeAsString") }
                }

                it("$before ${isBeforeOrEqual.name} $localDateTimeAsString does not throw") {
                    expect(before).isBeforeOrEqualFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${isBeforeOrEqual.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).isBeforeOrEqualFun(localDateTimeAsString)
                }
                it("$after ${isBeforeOrEqual.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isBeforeOrEqualFun(localDateTimeAsString)
                    }.toThrow<AssertionError> {
                        messageContains("${IS_BEFORE_OR_EQUAL.getDefault()}: $localDateTimeAsString")
                    }
                }

                it("$before ${isAfter.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${isAfter.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).isAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER.getDefault()}: $localDateTimeAsString") }
                }
                it("$after ${isAfter.name} $localDateTimeAsString does not throw") {
                    expect(after).isAfterFun(localDateTimeAsString)
                }

                it("$before ${isAfterOrEqual.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isAfterOrEqualFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_AFTER_OR_EQUAL.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${isAfterOrEqual.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).isAfterOrEqualFun(localDateTimeAsString)
                }
                it("$after ${isAfterOrEqual.name} $localDateTimeAsString does not throw") {
                    expect(after).isAfterOrEqualFun(localDateTimeAsString)
                }

                it("$before ${isEqual.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).isEqualFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_EQUAL_TO.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${isEqual.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).isEqualFun(localDateTimeAsString)
                }
                it("$after ${isEqual.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).isEqualFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageContains("${IS_EQUAL_TO.getDefault()}: $localDateTimeAsString") }
                }
            }
        }
    }
})
