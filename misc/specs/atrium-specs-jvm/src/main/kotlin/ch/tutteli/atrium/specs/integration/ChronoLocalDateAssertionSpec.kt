package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

abstract class ChronoLocalDateAssertionSpec(
    isBefore: Fun1<ChronoLocalDate, ChronoLocalDate>,
    isBeforeOrEqual: Fun1<ChronoLocalDate, ChronoLocalDate>,
    isAfter: Fun1<ChronoLocalDate, ChronoLocalDate>,
    isAfterOrEqual: Fun1<ChronoLocalDate, ChronoLocalDate>,
    isEqual: Fun1<ChronoLocalDate, ChronoLocalDate>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val december22 = LocalDate.of(2019, 12, 22)
    val december23 = LocalDate.of(2019, 12, 23)
    val december24 = LocalDate.of(2019, 12, 24)

    include(object : SubjectLessSpec<ChronoLocalDate>(
        describePrefix,
        isBefore.forSubjectLess(december23),
        isBeforeOrEqual.forSubjectLess(december23),
        isAfter.forSubjectLess(december23),
        isAfterOrEqual.forSubjectLess(december23),
        isEqual.forSubjectLess(december23)
    ) {})

    val isBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()
    val isBeforeOrEqualDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUAL.getDefault()
    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val isAfterOrEqualDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUAL.getDefault()
    val isEqualDescr = DescriptionDateTimeLikeAssertion.SAME_DAY.getDefault()


    listOf<ChronoLocalDate>(
        december23,
        JapaneseDate.of(2019, 12, 23)
    ).forEach { subject ->
        val fluent = expect(subject)

        describe("$describePrefix subject is $subject") {
            describe("${isBefore.name} ...") {
                val isBeforeFun = isBefore.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(december22)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $december22") }
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        fluent.isBeforeFun(december23)
                    }.toThrow<AssertionError> { messageContains("$isBeforeDescr: $december23") }
                }
                it("... $december24 does not throw") {
                    fluent.isBeforeFun(december24)
                }
            }
            describe("${isBeforeOrEqual.name} ...") {
                val isBeforeOrEqualFun = isBeforeOrEqual.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.isBeforeOrEqualFun(december22)
                    }.toThrow<AssertionError> { messageContains("$isBeforeOrEqualDescr: $december22") }
                }
                it("... $december23 does not throw") {
                    fluent.isBeforeOrEqualFun(december23)
                }
                it("... $december24 does not throw") {
                    fluent.isBeforeOrEqualFun(december24)
                }
            }
            describe("${isAfter.name} ...") {
                val isAfterFun = isAfter.lambda

                it("... $december22 does not throw") {
                    fluent.isAfterFun(december22)
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(december23)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $december23") }
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.isAfterFun(december24)
                    }.toThrow<AssertionError> { messageContains("$isAfterDescr: $december24") }
                }
            }
            describe("${isAfterOrEqual.name} ...") {
                val isAfterOrEqualFun = isAfterOrEqual.lambda

                it("... $december22 does not throw") {
                    fluent.isAfterOrEqualFun(december22)
                }
                it("... $december23 does not throw") {
                    fluent.isAfterOrEqualFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.isAfterOrEqualFun(december24)
                    }.toThrow<AssertionError> { messageContains("$isAfterOrEqualDescr: $december24") }
                }
            }
            describe("${isEqual.name} ...") {
                val isEqualFun = isEqual.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.isEqualFun(december22)
                    }.toThrow<AssertionError> { messageContains("$isEqualDescr: $december22") }
                }
                it("... $december23 does not throw") {
                    fluent.isEqualFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.isEqualFun(december24)
                    }.toThrow<AssertionError> { messageContains("$isEqualDescr: $december24") }
                }
            }
        }
    }
})
