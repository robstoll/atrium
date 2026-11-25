package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.forSubjectLessTest
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.chrono.ChronoLocalDateTime
import java.time.chrono.JapaneseDate

@Suppress("FunctionName")
abstract class AbstractChronoLocalDateTimeExpectationsTest(
    private val toBeBeforeSpec: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    private val toBeBeforeOrTheSamePointInTimeAsSpec: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    private val toBeAfterSpec: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    private val toBeAfterOrTheSamePointInTimeAsSpec: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>,
    private val toBeTheSamePointInTimeAsSpec: Fun1<ChronoLocalDateTime<*>, ChronoLocalDateTime<*>>
) : ExpectationFunctionBaseTest() {

    private val ten = LocalDateTime.of(2019, 12, 24, 10, 15, 30)
    private val eleven = LocalDateTime.of(2019, 12, 24, 11, 15, 30)
    private val twelve = LocalDateTime.of(2019, 12, 24, 12, 15, 30)

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toBeBeforeSpec.forSubjectLessTest(eleven),
        toBeBeforeOrTheSamePointInTimeAsSpec.forSubjectLessTest(eleven),
        toBeAfterSpec.forSubjectLessTest(eleven),
        toBeAfterOrTheSamePointInTimeAsSpec.forSubjectLessTest(eleven),
        toBeTheSamePointInTimeAsSpec.forSubjectLessTest(eleven)
    )

    private val toBeBeforeDescr = DescriptionDateTimeLikeExpectation.TO_BE_BEFORE.getDefault()
    private val toBeBeforeOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeAfterDescr = DescriptionDateTimeLikeExpectation.TO_BE_AFTER.getDefault()
    private val toBeAfterOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_POINT_IN_TIME_AS.getDefault()

    @TestFactory
    fun toBeBefore() = testFactory(toBeBeforeSpec) { toBeBeforeFun ->
        listOf<ChronoLocalDateTime<*>>(
            eleven,
            JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
        ).forEach { subject ->
            describe("subject is $subject") {
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
        }
    }

    @TestFactory
    fun toBeBeforeOrTheSamePointInTimeAs() = testFactory(toBeBeforeOrTheSamePointInTimeAsSpec) { toBeBeforeOrTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDateTime<*>>(
            eleven,
            JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
        ).forEach { subject ->
            describe("subject is $subject") {
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
        }
    }

    @TestFactory
    fun toBeAfter() = testFactory(toBeAfterSpec) { toBeAfterFun ->
        listOf<ChronoLocalDateTime<*>>(
            eleven,
            JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
        ).forEach { subject ->
            describe("subject is $subject") {
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
        }
    }

    @TestFactory
    fun toBeAfterOrTheSamePointInTimeAs() = testFactory(toBeAfterOrTheSamePointInTimeAsSpec) { toBeAfterOrTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDateTime<*>>(
            eleven,
            JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
        ).forEach { subject ->
            describe("subject is $subject") {
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
        }
    }

    @TestFactory
    fun toBeTheSamePointInTimeAs() = testFactory(toBeTheSamePointInTimeAsSpec) { toBeTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDateTime<*>>(
            eleven,
            JapaneseDate.of(2019, 12, 24).atTime(LocalTime.of(11, 15, 30))
        ).forEach { subject ->
            describe("subject is $subject") {
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
}
