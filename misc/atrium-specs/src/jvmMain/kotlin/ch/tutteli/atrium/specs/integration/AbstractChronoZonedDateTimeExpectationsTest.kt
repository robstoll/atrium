package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageToContain
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.forSubjectLessTest
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime

@Suppress("FunctionName")
abstract class AbstractChronoZonedDateTimeExpectationsTest(
    private val toBeBeforeSpec: Fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>,
    private val toBeBeforeOrTheSamePointInTimeAsSpec: Fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>,
    private val toBeAfterSpec: Fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>,
    private val toBeAfterOrTheSamePointInTimeAsSpec: Fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>,
    private val toBeTheSamePointInTimeAsSpec: Fun1<ChronoZonedDateTime<*>, ChronoZonedDateTime<*>>
) : ExpectationFunctionBaseTest() {

    private val ten = ZonedDateTime.of(2019, 12, 24, 10, 15, 30, 210, ZoneOffset.UTC)
    private val eleven = ZonedDateTime.of(2019, 12, 24, 11, 15, 30, 210, ZoneOffset.UTC)
    private val twelve = ZonedDateTime.of(2019, 12, 24, 12, 15, 30, 210, ZoneOffset.UTC)

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
        listOf<ChronoZonedDateTime<*>>(
            eleven,
            eleven.withZoneSameInstant(ZoneOffset.UTC)
        ).forEach { subject ->
            describe("subject is $subject") {
                it("$ten throws an AssertionError") {
                    expect {
                        expect(subject).toBeBeforeFun(ten)
                    }.toThrow<AssertionError> { messageToContain("$toBeBeforeDescr: $ten") }
                }
                it("$eleven does not throw") {
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
        listOf<ChronoZonedDateTime<*>>(
            eleven,
            eleven.withZoneSameInstant(ZoneOffset.UTC)
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
        listOf<ChronoZonedDateTime<*>>(
            eleven,
            eleven.withZoneSameInstant(ZoneOffset.UTC)
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
        listOf<ChronoZonedDateTime<*>>(
            eleven,
            eleven.withZoneSameInstant(ZoneOffset.UTC)
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
        listOf<ChronoZonedDateTime<*>>(
            eleven,
            eleven.withZoneSameInstant(ZoneOffset.UTC)
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
