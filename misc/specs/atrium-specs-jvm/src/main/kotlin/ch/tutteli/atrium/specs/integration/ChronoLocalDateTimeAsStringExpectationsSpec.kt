package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
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

abstract class ChronoLocalDateTimeAsStringExpectationsSpec(
    toBeBefore: Fun1<ChronoLocalDateTime<*>, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>,
    toBeAfter: Fun1<ChronoLocalDateTime<*>, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun toBeBefore(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun toBeBeforeOrTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun toBeAfter(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun toBeAfterOrTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    fun toBeTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDateTime<*>>,
        expected: ChronoLocalDateTime<*>
    ): Expect<ChronoLocalDateTime<*>> =
        expect.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))

    include(object : ChronoLocalDateTimeExpectationsSpec(
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
            "2020-01-02T03:04:05" to LocalDateTime.of(2020, 1, 2, 3, 4, 5) as ChronoLocalDateTime<*>,
            "2020-01-02T03:04" to LocalDateTime.of(2020, 1, 2, 3, 4, 0),
            "2020-01-02" to LocalDateTime.of(2020, 1, 2, 0, 0, 0)
        ).forEach { (localDateTimeAsString, chronoLocalDateTime) ->
            val before = chronoLocalDateTime.minus(Duration.ofSeconds(1))
            val after = chronoLocalDateTime.plus(Duration.ofSeconds(1))
            context("passing $localDateTimeAsString") {

                it("$before ${toBeBefore.name} $localDateTimeAsString does not throw") {
                    expect(before).toBeBeforeFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${toBeBefore.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).toBeBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_BEFORE.getDefault()}: $localDateTimeAsString") }
                }
                it("$after ${toBeBefore.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_BEFORE.getDefault()}: $localDateTimeAsString") }
                }

                it("$before ${toBeBeforeOrTheSamePointInTimeAs.name} $localDateTimeAsString does not throw") {
                    expect(before).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${toBeBeforeOrTheSamePointInTimeAs.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeBeforeOrTheSamePointInTimeAs.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> {
                        messageToContain("${IS_BEFORE_OR_EQUAL.getDefault()}: $localDateTimeAsString")
                    }
                }

                it("$before ${toBeAfter.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_AFTER.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeAfter.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).toBeAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_AFTER.getDefault()}: $localDateTimeAsString") }
                }
                it("$after ${toBeAfter.name} $localDateTimeAsString does not throw") {
                    expect(after).toBeAfterFun(localDateTimeAsString)
                }

                it("$before ${toBeAfterOrTheSamePointInTimeAs.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_AFTER_OR_EQUAL.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeAfterOrTheSamePointInTimeAs.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeAfterOrTheSamePointInTimeAs.name} $localDateTimeAsString does not throw") {
                    expect(after).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }

                it("$before ${toBeTheSamePointInTimeAs.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_EQUAL_TO.getDefault()}: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeTheSamePointInTimeAs.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeTheSamePointInTimeAs.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("${IS_EQUAL_TO.getDefault()}: $localDateTimeAsString") }
                }
            }
        }
    }
})
