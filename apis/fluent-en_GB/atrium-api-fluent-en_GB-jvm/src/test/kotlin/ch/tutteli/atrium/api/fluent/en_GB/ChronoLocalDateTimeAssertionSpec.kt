package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.AtriumError
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.chrono.ChronoLocalDate
import java.time.chrono.ChronoLocalDateTime
import java.time.format.DateTimeFormatter.ISO_DATE_TIME
import java.time.format.DateTimeParseException

class ChronoLocalDateTimeAssertionSpec : Spek({
    include(ChronoLocalDateTimeSpec)
    include(StringSpec)
    mapOf(
        "20200101010101111" to "wrong string format",
        "20200101010101" to "wrong string format",
        "202001010101" to "wrong string format",
        "2020010101" to "wrong string format",
        "2020-1-01T01:01" to "wrong string format",
        "2020-01-1T01:01" to "wrong string format",
        "20-01-01T01:01" to "wrong string format",
        "2020-01-01T1:01" to "wrong string format",
        "2020-01-01T01:1" to "wrong string format",
        "2020-01-01T01:01:1" to "wrong string format",
        "2020-01-01t01:01:01" to "wrong string format"
    ).forEach{(value, description) ->
        run {
            val now = expect(LocalDateTime.now())
            describe(description) {
                it("throws an DateTimeParseException") {
                    expect {
                        now.isBefore(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("throws an DateTimeParseException") {
                    expect {
                        now.isBeforeOrEqual(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("throws an DateTimeParseException") {
                    expect {
                        now.isAfter(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("throws an DateTimeParseException") {
                    expect {
                        now.isAfterOrEqual(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
                it("throws an DateTimeParseException") {
                    expect {
                        now.isEqual(value)
                    }.toThrow<DateTimeParseException> { messageContains("could not be parsed") }
                }
            }
        }
    }

    mapOf(
        "2020-01-01T01:01:01" to "allowed shortcuts",
        "2020-01-01T01:01" to "allowed shortcuts",
        "2020-01-01" to "allowed shortcuts"
    ).forEach{(value, description) ->
        run {
            val nowLocalDateTime = LocalDateTime.now()
            val now = expect(nowLocalDateTime)
            val before = LocalDateTime.now()
            describe(description) {
                it("throws an AtriumError") {
                    expect {
                        now.isBefore(value)
                    }.toThrow<AtriumError> { messageContains("is before: $value") }
                }
                it("doesn't throw any Error") {
                    expect {
                        now.isBefore(before.format(ISO_DATE_TIME))
                    }
                }
                it("doesn't throw any Error") {
                    expect {
                        now.isBeforeOrEqual(nowLocalDateTime.format(ISO_DATE_TIME))
                    }
                }
                it("throws an AtriumError") {
                    expect {
                        now.isBeforeOrEqual(value)
                    }.toThrow<AtriumError> { messageContains("is before or equal: $value") }
                }
                it("doesn't throw any Error") {
                    expect {
                        now.isAfter(value)
                    }
                }
                it("throws an AtriumError") {
                    expect {
                        now.isAfter(before.format(ISO_DATE_TIME))
                    }.toThrow<AtriumError> { messageContains("is after: ${before.format(ISO_DATE_TIME)}") }
                }
                it("doesn't throw any Error") {
                    expect {
                        now.isAfterOrEqual(nowLocalDateTime.format(ISO_DATE_TIME))
                    }
                }
                it("doesn't throw any Error") {
                    expect {
                        now.isAfterOrEqual(value)
                    }
                }
                it("throws an AtriumError") {
                    expect {
                        now.isAfterOrEqual(before.format(ISO_DATE_TIME))
                    }.toThrow<AtriumError> { messageContains("is after or equal: ${before.format(ISO_DATE_TIME)}") }
                }
                it("throws an AtriumError") {
                    expect {
                        now.isEqual(value)
                    }.toThrow<AtriumError> { messageContains("is equal to: $value") }
                }
                it("doesn't throw any error") {
                    expect {
                        now.isEqual(nowLocalDateTime.format(ISO_DATE_TIME))
                    }
                }
            }
        }
    }
}) {
    object ChronoLocalDateTimeSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::isBefore),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::isBeforeOrEqual),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::isAfter),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::isAfterOrEqual),
        fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>(Expect<ChronoLocalDateTime<*>>::isEqual)
    )

    object StringSpec : ch.tutteli.atrium.specs.integration.ChronoLocalDateTimeAssertionSpec(
        fun1(Companion::isBefore),
        fun1(Companion::isBeforeOrEqual),
        fun1(Companion::isAfter),
        fun1(Companion::isAfterOrEqual),
        fun1(Companion::isEqual)
    )

    companion object {
        fun isBefore(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            expect.isBefore(expected.format(ISO_DATE_TIME))

        fun isBeforeOrEqual(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            expect.isBeforeOrEqual(expected.format(ISO_DATE_TIME))

        fun isAfter(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            expect.isAfter(expected.format(ISO_DATE_TIME))

        fun isAfterOrEqual(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            expect.isAfterOrEqual(expected.format(ISO_DATE_TIME))

        fun isEqual(
            expect: Expect<ChronoLocalDateTime<*>>,
            expected: ChronoLocalDateTime<*>
        ): Expect<ChronoLocalDateTime<*>> =
            expect.isEqual(expected.format(ISO_DATE_TIME))
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        val chronoLocalDateTime: ChronoLocalDateTime<*> = notImplemented()
        var a1: Expect<ChronoLocalDateTime<ChronoLocalDate>> = notImplemented()
        var a2: Expect<ChronoLocalDateTime<LocalDate>> = notImplemented()
        var a3: Expect<ChronoLocalDateTime<*>> = notImplemented()
        var a4: Expect<LocalDateTime> = notImplemented()

        a1 = a1.isBefore(LocalDateTime.now())
        a1 = a1.isBeforeOrEqual(LocalDateTime.now())
        a1 = a1.isAfter(LocalDateTime.now())
        a1 = a1.isAfterOrEqual(LocalDateTime.now())
        a1 = a1.isEqual(LocalDateTime.now())

        a2 = a2.isBefore(LocalDateTime.now())
        a2 = a2.isBeforeOrEqual(LocalDateTime.now())
        a2 = a2.isAfter(LocalDateTime.now())
        a2 = a2.isAfterOrEqual(LocalDateTime.now())
        a2 = a2.isEqual(LocalDateTime.now())

        a3 = a3.isBefore(LocalDateTime.now())
        a3 = a3.isBeforeOrEqual(LocalDateTime.now())
        a3 = a3.isAfter(LocalDateTime.now())
        a3 = a3.isAfterOrEqual(LocalDateTime.now())
        a3 = a3.isEqual(LocalDateTime.now())

        a4 = a4.isBefore(LocalDateTime.now())
        a4 = a4.isBeforeOrEqual(LocalDateTime.now())
        a4 = a4.isAfter(LocalDateTime.now())
        a4 = a4.isAfterOrEqual(LocalDateTime.now())
        a4 = a4.isEqual(LocalDateTime.now())

        a1 = a1.isBefore(chronoLocalDateTime)
        a1 = a1.isBeforeOrEqual(chronoLocalDateTime)
        a1 = a1.isAfter(chronoLocalDateTime)
        a1 = a1.isAfterOrEqual(chronoLocalDateTime)
        a1 = a1.isEqual(chronoLocalDateTime)

        a2 = a2.isBefore(chronoLocalDateTime)
        a2 = a2.isBeforeOrEqual(chronoLocalDateTime)
        a2 = a2.isAfter(chronoLocalDateTime)
        a2 = a2.isAfterOrEqual(chronoLocalDateTime)
        a2 = a2.isEqual(chronoLocalDateTime)

        a3 = a3.isBefore(chronoLocalDateTime)
        a3 = a3.isBeforeOrEqual(chronoLocalDateTime)
        a3 = a3.isAfter(chronoLocalDateTime)
        a3 = a3.isAfterOrEqual(chronoLocalDateTime)
        a3 = a3.isEqual(chronoLocalDateTime)

        a4 = a4.isBefore(chronoLocalDateTime)
        a4 = a4.isBeforeOrEqual(chronoLocalDateTime)
        a4 = a4.isAfter(chronoLocalDateTime)
        a4 = a4.isAfterOrEqual(chronoLocalDateTime)
        a4 = a4.isEqual(chronoLocalDateTime)
    }
}
