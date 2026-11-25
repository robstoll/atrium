package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.DayOfWeek
import java.time.ZonedDateTime

@Suppress("FunctionName")
abstract class AbstractZonedDateTimeExpectationsTest(
    private val yearFeatureSpec: Feature0<ZonedDateTime, Int>,
    private val yearSpec: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    private val monthFeatureSpec: Feature0<ZonedDateTime, Int>,
    private val monthSpec: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    private val dayFeatureSpec: Feature0<ZonedDateTime, Int>,
    private val daySpec: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    private val dayOfWeekFeatureSpec: Feature0<ZonedDateTime, DayOfWeek>,
    private val dayOfWeekSpec: Fun1<ZonedDateTime, Expect<DayOfWeek>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        yearFeatureSpec.forSubjectLessTest(),
        yearSpec.forSubjectLessTest { toBeGreaterThan(2000) },
        monthFeatureSpec.forSubjectLessTest(),
        monthSpec.forSubjectLessTest { toBeLessThan(12) },
        dayFeatureSpec.forSubjectLessTest(),
        daySpec.forSubjectLessTest { toBeLessThanOrEqualTo(20) },
        dayOfWeekFeatureSpec.forSubjectLessTest(),
        dayOfWeekSpec.forSubjectLessTest { toBeLessThanOrEqualTo(DayOfWeek.SUNDAY) }
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ZonedDateTime.now().withYear(2040).withDayOfYear(1).withDayOfMonth(15),
        yearSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(2040) },
        monthSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        daySpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(15) },
        dayOfWeekSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(DayOfWeek.SUNDAY) }
    )

    private val subject: ZonedDateTime = ZonedDateTime.now().withMonth(5).withYear(2009).withDayOfMonth(15)
    private val yearDescr = DescriptionDateTimeLikeExpectation.YEAR.getDefault()
    private val monthDescr = DescriptionDateTimeLikeExpectation.MONTH.getDefault()
    private val dayDescr = DescriptionDateTimeLikeExpectation.DAY.getDefault()
    private val dayOfWeekDescr = DescriptionDateTimeLikeExpectation.DAY_OF_WEEK.getDefault()

    @TestFactory
    fun year__feature_and_function() = testFactoryForFeatureNonFeature(
        yearFeatureSpec, yearSpec
    ) { name, yearFun, _ ->
        describe("ZonedDateTime with year 2009") {
            it("$name - is greater than 2008 holds") {
                expect(subject).yearFun { toBeGreaterThan(2008) }
            }
            it("$name - is less than 2009 fails") {
                expect {
                    expect(subject).yearFun { toBeLessThan(2009) }
                }.toThrow<AssertionError> {
                    messageToContain("$yearDescr: 2009")
                }
            }
        }
    }

    @TestFactory
    fun month__feature_and_function() = testFactoryForFeatureNonFeature(
        monthFeatureSpec, monthSpec
    ) { name, monthFun, _ ->
        describe("ZonedDateTime with month May(5)") {
            it("$name - is greater than February(2) holds") {
                expect(subject).monthFun { toBeGreaterThan(2) }
            }
            it("$name - is less than 5 fails") {
                expect {
                    expect(subject).monthFun { toBeLessThan(5) }
                }.toThrow<AssertionError> {
                    messageToContain("$monthDescr: 5")
                }
            }
        }
    }

    @TestFactory
    fun day__feature_and_function() = testFactoryForFeatureNonFeature(
        dayFeatureSpec, daySpec
    ) { name, dayFun, _ ->
        describe("ZonedDateTime with day of month 15") {
            it("$name - is greater than 5 holds") {
                expect(subject).dayFun { toBeGreaterThan(5) }
            }
            it("$name - is less than 5 fails") {
                expect {
                    expect(subject).dayFun { toBeLessThan(5) }
                }.toThrow<AssertionError> {
                    messageToContain("$dayDescr: 15")
                }
            }
        }
    }

    @TestFactory
    fun dayOfWeek__feature_and_function() = testFactoryForFeatureNonFeature(
        dayOfWeekFeatureSpec, dayOfWeekSpec
    ) { name, dayOfWeekFun, _ ->
        describe("ZonedDateTime with day of week Friday(5)") {
            it("$name - is greater than Monday(1) holds") {
                expect(subject).dayOfWeekFun { toBeGreaterThan(DayOfWeek.MONDAY) }
            }
            it("$name - is less than Friday(5) fails") {
                expect {
                    expect(subject).dayOfWeekFun { toBeLessThan(DayOfWeek.FRIDAY) }
                }.toThrow<AssertionError> {
                    messageToContain("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}")
                }
            }
        }
    }
}
