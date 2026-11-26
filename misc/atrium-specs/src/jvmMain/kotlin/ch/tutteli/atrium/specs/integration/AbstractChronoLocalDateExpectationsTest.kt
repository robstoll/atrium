package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.forSubjectLessTest
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate
import java.time.chrono.JapaneseDate

@Suppress("FunctionName")
abstract class AbstractChronoLocalDateExpectationsTest(
    private val toBeBeforeSpec: Fun1<ChronoLocalDate, ChronoLocalDate>,
    private val toBeBeforeOrTheSamePointInTimeAsSpec: Fun1<ChronoLocalDate, ChronoLocalDate>,
    private val toBeAfterSpec: Fun1<ChronoLocalDate, ChronoLocalDate>,
    private val toBeAfterOrTheSamePointInTimeAsSpec: Fun1<ChronoLocalDate, ChronoLocalDate>,
    private val toBeTheSamePointInTimeAsSpec: Fun1<ChronoLocalDate, ChronoLocalDate>
) : ExpectationFunctionBaseTest() {

    private val december22 = LocalDate.of(2019, 12, 22)
    private val december23 = LocalDate.of(2019, 12, 23)
    private val december24 = LocalDate.of(2019, 12, 24)

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        toBeBeforeSpec.forSubjectLessTest(december23),
        toBeBeforeOrTheSamePointInTimeAsSpec.forSubjectLessTest(december23),
        toBeAfterSpec.forSubjectLessTest(december23),
        toBeAfterOrTheSamePointInTimeAsSpec.forSubjectLessTest(december23),
        toBeTheSamePointInTimeAsSpec.forSubjectLessTest(december23)
    )

    private val toBeBeforeDescr = DescriptionDateTimeLikeExpectation.TO_BE_BEFORE.getDefault()
    private val toBeBeforeOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_BEFORE_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeAfterDescr = DescriptionDateTimeLikeExpectation.TO_BE_AFTER.getDefault()
    private val toBeAfterOrTheSamePointInTimeAsDescr =
        DescriptionDateTimeLikeExpectation.TO_BE_AFTER_OR_THE_SAME_POINT_IN_TIME_AS.getDefault()
    private val toBeTheSamePointInTimeAsDescr = DescriptionDateTimeLikeExpectation.TO_BE_THE_SAME_DAY_AS.getDefault()

    @TestFactory
    fun toBeBefore() = testFactory(toBeBeforeSpec) { toBeBeforeFun ->
        listOf<ChronoLocalDate>(december23, JapaneseDate.of(2019, 12, 23)).forEach { subject ->
            describe("subject is $subject") {
                it("... $december22 throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeFun(december22)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $december22") }
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeFun(december23)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $december23") }
                }
                it("... $december24 does not throw") {
                    expect(subject).toBeBeforeFun(december24)
                }
            }
        }
    }

    @TestFactory
    fun toBeBeforeOrTheSamePointInTimeAs() = testFactory(toBeBeforeOrTheSamePointInTimeAsSpec) { toBeBeforeOrTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDate>(december23, JapaneseDate.of(2019, 12, 23)).forEach { subject ->
            describe("subject is $subject") {
                it("... $december22 throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(december22)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeBeforeOrTheSamePointInTimeAsDescr: $december22")
                    }
                }
                it("... $december23 does not throw") {
                    expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 does not throw") {
                    expect(subject).toBeBeforeOrTheSamePointInTimeAsFun(december24)
                }
            }
        }
    }

    @TestFactory
    fun toBeAfter() = testFactory(toBeAfterSpec) { toBeAfterFun ->
        listOf<ChronoLocalDate>(december23, JapaneseDate.of(2019, 12, 23)).forEach { subject ->
            describe("subject is $subject") {
                it("... $december22 does not throw") {
                    expect(subject).toBeAfterFun(december22)
                }
                it("... $december23 throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterFun(december23)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $december23") }
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterFun(december24)
                    }.toThrow<AssertionError> { messageToContain("$toBeAfterDescr: $december24") }
                }
            }
        }
    }

    @TestFactory
    fun toBeAfterOrTheSamePointInTimeAs() = testFactory(toBeAfterOrTheSamePointInTimeAsSpec) { toBeAfterOrTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDate>(december23, JapaneseDate.of(2019, 12, 23)).forEach { subject ->
            describe("subject is $subject") {
                it("... $december22 does not throw") {
                    expect(subject).toBeAfterOrTheSamePointInTimeAsFun(december22)
                }
                it("... $december23 does not throw") {
                    expect(subject).toBeAfterOrTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        expect(subject).toBeAfterOrTheSamePointInTimeAsFun(december24)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeAfterOrTheSamePointInTimeAsDescr: $december24")
                    }
                }
            }
        }
    }

    @TestFactory
    fun toBeTheSamePointInTimeAs() = testFactory(toBeTheSamePointInTimeAsSpec) { toBeTheSamePointInTimeAsFun ->
        listOf<ChronoLocalDate>(december23, JapaneseDate.of(2019, 12, 23)).forEach { subject ->
            describe("subject is $subject") {
                it("... $december22 throws an AssertionError") {
                    expect {
                        expect(subject).toBeTheSamePointInTimeAsFun(december22)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeTheSamePointInTimeAsDescr: $december22")
                    }
                }
                it("... $december23 does not throw") {
                    expect(subject).toBeTheSamePointInTimeAsFun(december23)
                }
                it("... $december24 throws an AssertionError") {
                    expect {
                        expect(subject).toBeTheSamePointInTimeAsFun(december24)
                    }.toThrow<AssertionError> {
                        messageToContain("$toBeTheSamePointInTimeAsDescr: $december24")
                    }
                }
            }
        }
    }
}
