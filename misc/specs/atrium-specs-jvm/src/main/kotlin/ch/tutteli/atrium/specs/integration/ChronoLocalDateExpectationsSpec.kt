package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

abstract class ChronoLocalDateExpectationsSpec(
    toBeBefore: Fun1<ChronoLocalDate, ChronoLocalDate>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, ChronoLocalDate>,
    toBeAfter: Fun1<ChronoLocalDate, ChronoLocalDate>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDate, ChronoLocalDate>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDate, ChronoLocalDate>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val december22 = LocalDate.of(2019, 12, 22)
    val december23 = LocalDate.of(2019, 12, 23)
    val december24 = LocalDate.of(2019, 12, 24)

    include(object : SubjectLessSpec<ChronoLocalDate>(
        describePrefix,
        toBeBefore.forSubjectLess(december23),
        toBeBeforeOrTheSamePointInTimeAs.forSubjectLess(december23),
        toBeAfter.forSubjectLess(december23),
        toBeAfterOrTheSamePointInTimeAs.forSubjectLess(december23),
        toBeTheSamePointInTimeAs.forSubjectLess(december23)
    ) {})

    val toBeBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()
    val toBeBeforeOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUAL.getDefault()
    val toBeAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val toBeAfterOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUAL.getDefault()
    val toBeTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.SAME_DAY.getDefault()


    listOf<ChronoLocalDate>(
        december23,
        JapaneseDate.of(2019, 12, 23)
    ).forEach { subject ->
        val fluent = expect(subject)

        describe("$describePrefix subject is $subject") {
            describe("${toBeBefore.name} ...") {
                val toBeBeforeFun = toBeBefore.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeFun(december22)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $december22") }
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeFun(december23)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $december23") }
                }
                it("... $december24 does not throw") {
                    fluent.toBeBeforeFun(december24)
                }
            }
            describe("${toBeBeforeOrTheSamePointInTimeAs.name} ...") {
                val toBeBeforeOrTheSamePointInTimeAsFun = toBeBeforeOrTheSamePointInTimeAs.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeOrTheSamePointInTimeAsFun(december22)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $december22") }
                }
                it("... $december23 does not throw") {
                    fluent.toBeBeforeOrTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 does not throw") {
                    fluent.toBeBeforeOrTheSamePointInTimeAsFun(december24)
                }
            }
            describe("${toBeAfter.name} ...") {
                val toBeAfterFun = toBeAfter.lambda

                it("... $december22 does not throw") {
                    fluent.toBeAfterFun(december22)
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        fluent.toBeAfterFun(december23)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $december23") }
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.toBeAfterFun(december24)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $december24") }
                }
            }
            describe("${toBeAfterOrTheSamePointInTimeAs.name} ...") {
                val toBeAfterOrTheSamePointInTimeAsFun = toBeAfterOrTheSamePointInTimeAs.lambda

                it("... $december22 does not throw") {
                    fluent.toBeAfterOrTheSamePointInTimeAsFun(december22)
                }
                it("... $december23 does not throw") {
                    fluent.toBeAfterOrTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.toBeAfterOrTheSamePointInTimeAsFun(december24)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $december24") }
                }
            }
            describe("${toBeTheSamePointInTimeAs.name} ...") {
                val toBeTheSamePointInTimeAsFun = toBeTheSamePointInTimeAs.lambda

                it("... $december22 throws an AssertionError") {
                    expect {
                        fluent.toBeTheSamePointInTimeAsFun(december22)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $december22") }
                }
                it("... $december23 does not throw") {
                    fluent.toBeTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        fluent.toBeTheSamePointInTimeAsFun(december24)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $december24") }
                }
            }
        }
    }
})
