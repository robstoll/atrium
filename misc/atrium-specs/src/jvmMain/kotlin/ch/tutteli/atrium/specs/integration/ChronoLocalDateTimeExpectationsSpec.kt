package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
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

    val toBeBeforeDescr = DescriptionDateTimeLikeExpectation.TO_BE_BEFORE.getDefault()
    val toBeBeforeOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeExpectation.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    val toBeAfterDescr = DescriptionDateTimeLikeExpectation.TO_BE_AFTER.getDefault()
    val toBeAfterOrTheSamePointInTimeAsDescr = DescriptionDateTimeLikeExpectation.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    val toBeTheSamePointInTimeAsDescr = DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_POINT_IN_TIME_AS.getDefault()

    listOf<ChronoLocalDateTime<*>>(
        eleven,
        JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
    ).forEach { subject ->

        describe("$describePrefix subject is $subject") {
            describe("${toBeBefore.name} ...") {
                val toBeBeforeFun = toBeBefore.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $ten") }
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeFun(eleven)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $eleven") }
                }
                it("$twelve does not throw") {
                    expect(subject).toBeBeforeFun(twelve)
                }
            }
            describe("${toBeBeforeOrTheSamePointInTimeAs.name} ...") {
                val toBeBeforeOrTheSamePointInTimeAsFun = toBeBeforeOrTheSamePointInTimeAs.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $ten") }
                }
                it("$eleven does not throw") {
                    expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve does not throw") {
                    expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(twelve)
                }
            }

            describe("${toBeAfter.name} ...") {
                val toBeAfterFun = toBeAfter.lambda

                it("$ten does not throw") {
                    expect(subject).toBeAfterFun(ten)
                }
                it("$eleven throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterFun(eleven)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $eleven") }
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $twelve") }
                }
            }

            describe("${toBeAfterOrTheSamePointInTimeAs.name} ...") {
                val toBeAfterOrTheSamePointInTimeAsFun = toBeAfterOrTheSamePointInTimeAs.lambda

                it("$ten does not throw") {
                    expect(subject).toBeAfterOrTheSamePointInTimeAsFun(ten)
                }
                it("$eleven does not throw") {
                    expect(subject).toBeAfterOrTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterOrTheSamePointInTimeAsFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $twelve") }
                }
            }

            describe("${toBeTheSamePointInTimeAs.name} ...") {
                val toBeTheSamePointInTimeAsFun = toBeTheSamePointInTimeAs.lambda

                it("$ten throws an AssertionError") {
                    expect {
                        expect(subject).toBeTheSamePointInTimeAsFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $ten") }
                }
                it("$eleven does not throw") {
                    expect(subject).toBeTheSamePointInTimeAsFun(eleven)
                }
                it("$twelve throws an AssertionError") {
                    expect {
                        expect(subject).toBeTheSamePointInTimeAsFun(twelve)
                    }.toThrow<AssertionError> { messageToContain("$toBeTheSamePointInTimeAsDescr: $twelve") }
                }
            }
        }
    }
})
