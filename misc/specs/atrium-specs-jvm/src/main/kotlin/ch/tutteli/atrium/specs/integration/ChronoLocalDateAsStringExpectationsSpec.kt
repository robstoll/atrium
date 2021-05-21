package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

abstract class ChronoLocalDateAsStringExpectationsSpec(
    toBeBefore: Fun1<ChronoLocalDate, String>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>,
    toBeAfter: Fun1<ChronoLocalDate, String>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDate, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun toBeBefore(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(toBeBefore.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun toBeBeforeOrTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(toBeBeforeOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun toBeAfter(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(toBeAfter.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun toBeAfterOrTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(toBeAfterOrTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun toBeTheSamePointInTimeAs(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(toBeTheSamePointInTimeAs.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    include(object : ChronoLocalDateExpectationsSpec(
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

    val subject = LocalDate.now() as ChronoLocalDate
    val now = expect(subject)

    describe("wrong format") {
        listOf(
            "20200903",
            "2020-9-03",
            "2020-09-3",
            "20-09-3",
            "2020-09-03T07:14"
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
})
