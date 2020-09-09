package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
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

abstract class ChronoLocalDateAsStringAssertionSpec(
    isBefore: Fun1<ChronoLocalDate, String>,
    isBeforeOrEqual: Fun1<ChronoLocalDate, String>,
    isAfter: Fun1<ChronoLocalDate, String>,
    isAfterOrEqual: Fun1<ChronoLocalDate, String>,
    isEqual: Fun1<ChronoLocalDate, String>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun isBefore(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(isBefore.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun isBeforeOrEqual(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(isBeforeOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun isAfter(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(isAfter.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun isAfterOrEqual(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(isAfterOrEqual.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    fun isEqual(
        expect: Expect<ChronoLocalDate>,
        expected: ChronoLocalDate
    ): Expect<ChronoLocalDate> =
        expect.(isEqual.lambda)(expected.format(DateTimeFormatter.ISO_LOCAL_DATE))

    include(object : ChronoLocalDateAssertionSpec(
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
})
