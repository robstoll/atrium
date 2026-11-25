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
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Suppress("FunctionName")
abstract class AbstractChronoLocalDateTimeAsStringExpectationsTest(
    toBeBefore: Fun1<ChronoLocalDateTime<*>, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>,
    toBeAfter: Fun1<ChronoLocalDateTime<*>, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, String>
) : AbstractChronoLocalDateTimeExpectationsTest(
    toBeBefore.name to { e: Expect<ChronoLocalDateTime<*>>, expected: ChronoLocalDateTime<*> ->
        e.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))
    },
    toBeBeforeOrTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDateTime<*>>, expected: ChronoLocalDateTime<*> ->
        e.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))
    },
    toBeAfter.name to { e: Expect<ChronoLocalDateTime<*>>, expected: ChronoLocalDateTime<*> ->
        e.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))
    },
    toBeAfterOrTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDateTime<*>>, expected: ChronoLocalDateTime<*> ->
        e.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))
    },
    toBeTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDateTime<*>>, expected: ChronoLocalDateTime<*> ->
        e.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_DATE_TIME))
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

    private val subject = LocalDateTime.now() as ChronoLocalDateTime<*>
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
            "2020-01-02T03:04:05" to LocalDateTime.of(2020, 1, 2, 3, 4, 5) as ChronoLocalDateTime<*>,
            "2020-01-02T03:04" to LocalDateTime.of(2020, 1, 2, 3, 4, 0),
            "2020-01-02" to LocalDateTime.of(2020, 1, 2, 0, 0, 0)
        ).forEach { (localDateTimeAsString, chronoLocalDateTime) ->
            val before = chronoLocalDateTime.minus(Duration.ofSeconds(1))
            val after = chronoLocalDateTime.plus(Duration.ofSeconds(1))
            describe("passing $localDateTimeAsString") {

                it("$before ${toBeBeforeSpec.name} $localDateTimeAsString does not throw") {
                    expect(before).toBeBeforeFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${toBeBeforeSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).toBeBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $localDateTimeAsString") }
                }
                it("$after ${toBeBeforeSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $localDateTimeAsString") }
                }

                it("$before ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString does not throw") {
                    expect(before).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$chronoLocalDateTime ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeBeforeOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeBeforeOrTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $localDateTimeAsString")
                    }
                }

                it("$before ${toBeAfterSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeAfterSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(chronoLocalDateTime).toBeAfterFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $localDateTimeAsString") }
                }
                it("$after ${toBeAfterSpec.name} $localDateTimeAsString does not throw") {
                    expect(after).toBeAfterFun(localDateTimeAsString)
                }

                it("$before ${toBeAfterOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeAfterOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeAfterOrTheSamePointInTimeAsSpec.name} $localDateTimeAsString does not throw") {
                    expect(after).toBeAfterOrTheSamePointInTimeAsFun(localDateTimeAsString)
                }

                it("$before ${toBeTheSamePointInTimeAsSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(before).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $localDateTimeAsString") }
                }
                it("$chronoLocalDateTime ${toBeTheSamePointInTimeAsSpec.name} $localDateTimeAsString does not throw") {
                    expect(chronoLocalDateTime).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                }
                it("$after ${toBeTheSamePointInTimeAsSpec.name} $localDateTimeAsString throws an AssertionError") {
                    expect {
                        expect(after).toBeTheSamePointInTimeAsFun(localDateTimeAsString)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $localDateTimeAsString") }
                }
            }
        }
    }
}
