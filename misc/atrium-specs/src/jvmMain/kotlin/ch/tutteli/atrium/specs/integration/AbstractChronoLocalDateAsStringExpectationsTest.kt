package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.testfactories.TestFactory
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Suppress("FunctionName")
abstract class AbstractChronoLocalDateAsStringExpectationsTest(
    toBeBefore: Fun1<ChronoLocalDate, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>,
    toBeAfter: Fun1<ChronoLocalDate, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>
) : AbstractChronoLocalDateExpectationsTest(
    toBeBefore.name to { e: Expect<ChronoLocalDate>, expected: ChronoLocalDate ->
        e.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))
    },
    toBeBeforeOrTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDate>, expected: ChronoLocalDate ->
        e.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))
    },
    toBeAfter.name to { e: Expect<ChronoLocalDate>, expected: ChronoLocalDate ->
        e.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))
    },
    toBeAfterOrTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDate>, expected: ChronoLocalDate ->
        e.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))
    },
    toBeTheSamePointInTimeAs.name to { e: Expect<ChronoLocalDate>, expected: ChronoLocalDate ->
        e.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))
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

    private val subject = LocalDate.now() as ChronoLocalDate
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
            "20200903",
            "2020-9-03",
            "2020-09-3",
            "20-09-3",
            "2020-09-03T07:14"
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
}
