package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
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

abstract class ChronoLocalDateTimeExpectationsSpec(
    toBeBefore: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    toBeBeforeOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    toBeAfter: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    toBeAfterOrTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    toBeTheSamePointInTimeAs: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val ten = LocalDateTime.of(2019, 12, 24, 10, 15, 30)
    val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)
    val twelve = LocalDateTime.of(2019, 12, 24, 12, 15, 30)

    include(object : SubjectLessSpec<ChronoLocalDateTime<*>>(
        describePrefix,
        toBeBefore.forSubjectLess(eleven),
        toBeBeforeOrTheSamePointInTimeAs.forSubjectLess(eleven),
        toBeAfter.forSubjectLess(eleven),
        toBeAfterOrTheSamePointInTimeAs.forSubjectLess(eleven),
        toBeTheSamePointInTimeAs.forSubjectLess(eleven)
    ) {})

    val toBeBeforeDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE.getDefault()
    val toBeBeforeOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUAL.getDefault()
    val toBeAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val toBeAfterOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUAL.getDefault()
    val toBeTheSamePointInTimeAsDescr = DescriptionDateTimeLikeAssertion.IS_EQUAL_TO.getDefault()

    listOf<ChronoLocalDateTime<*>>(
        eleven,
        JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
    ).forEach { subject ->
        val fluent = expect(subject)

        describe("$describePrefix subject is $subject") {
            describe("${toBeBefore.name} ...") {
                val toBeBeforeFun = toBeBefore.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $ten") }
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeFun(eleven)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $eleven") }
                }
                it("$twelve does not throw") {
                    fluent.toBeBeforeFun(twelve)
                }
            }
            describe("${toBeBeforeOrTheSamePointInTimeAs.name} ...") {
                val toBeBeforeOrTheSamePointInTimeAsFun = toBeBeforeOrTheSamePointInTimeAs.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.toBeBeforeOrTheSamePointInTimeAsFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $ten") }
                }
                it("$eleven does not throw") {
                    fluent.toBeBeforeOrTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve does not throw") {
                    fluent.toBeBeforeOrTheSamePointInTimeAsFun(twelve)
                }
            }

            describe("${toBeAfter.name} ...") {
                val toBeAfterFun = toBeAfter.lambda

                it("$ten does not throw") {
                    fluent.toBeAfterFun(ten)
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        fluent.toBeAfterFun(eleven)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $eleven") }
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.toBeAfterFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $twelve") }
                }
            }

            describe("${toBeAfterOrTheSamePointInTimeAs.name} ...") {
                val toBeAfterOrTheSamePointInTimeAsFun = toBeAfterOrTheSamePointInTimeAs.lambda

                it("$ten does not throw") {
                    fluent.toBeAfterOrTheSamePointInTimeAsFun(ten)
                }
                it("$eleven does not throw") {
                    fluent.toBeAfterOrTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.toBeAfterOrTheSamePointInTimeAsFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $twelve") }
                }
            }

            describe("${toBeTheSamePointInTimeAs.name} ...") {
                val toBeTheSamePointInTimeAsFun = toBeTheSamePointInTimeAs.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        fluent.toBeTheSamePointInTimeAsFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $ten") }
                }
                it("$eleven does not throw") {
                    fluent.toBeTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        fluent.toBeTheSamePointInTimeAsFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $twelve") }
                }
            }
        }
    }
})
