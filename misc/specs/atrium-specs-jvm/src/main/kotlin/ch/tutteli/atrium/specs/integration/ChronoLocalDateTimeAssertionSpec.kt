package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.chrono.ChronoLocalDateTime
import java.time.chrono.JapaneseDate

abstract class ChronoLocalDateTimeAssertionSpec(
    isBefore: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    isBeforeOrEqual: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    isAfter: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    isAfterOrEqual: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val ten = LocalDateTime.of(2019, 12, 24, 10, 15, 30)
    val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)
    val twelve = LocalDateTime.of(2019, 12, 24, 12, 15, 30)

    include(object : SubjectLessSpec<ChronoLocalDateTime<*>>(
        describePrefix,
        isBefore.forSubjectLess(eleven),
        isBeforeOrEqual.forSubjectLess(eleven),
        isAfter.forSubjectLess(eleven),
        isAfterOrEqual.forSubjectLess(eleven)
    ) {})

    val isBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()
    val isBeforeOrEqualDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUAL.getDefault()
    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val isAfterOrEqualDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUAL.getDefault()

    listOf<ChronoLocalDateTime<*>>(
        eleven,
        JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
    ).forEach { subject ->
        val fluent = expect(subject)

        describe("$describePrefix subject is $subject") {
            describe("${isBefore.name} ...") {
                val isBeforeFun = isBefore.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(ten)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $ten") }
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(eleven)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $eleven") }
                }
                it("$twelve does not throw") {
                    fluent.isBeforeFun(twelve)
                }
            }
            describe("${isBeforeOrEqual.name} ...") {
                val isBeforeOrEqualFun = isBeforeOrEqual.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.isBeforeOrEqualFun(ten)
                    }.toThrow<AssertionError> { messageContains("$isBeforeOrEqualDescr: $ten") }
                }
                it("$eleven does not throw") {
                    fluent.isBeforeOrEqualFun(eleven)
                }
                it("$twelve does not throw") {
                    fluent.isBeforeOrEqualFun(twelve)
                }
            }

            describe("${isAfter.name} ...") {
                val isAfterFun = isAfter.lambda

                it("$ten does not throw") {
                    fluent.isAfterFun(ten)
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(eleven)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $eleven") }
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(twelve)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $twelve") }
                }
            }

            describe("${isAfterOrEqual.name} ...") {
                val isAfterOrEqualFun = isAfterOrEqual.lambda

                it("$ten does not throw") {
                    fluent.isAfterOrEqualFun(ten)
                }
                it("$eleven does not throw") {
                    fluent.isAfterOrEqualFun(eleven)
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.isAfterOrEqualFun(twelve)
                    }.toThrow<AssertionError> { messageContains("$isAfterOrEqualDescr: $twelve") }
                }
            }
        }
    }
})
