package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.DayOfWeek
import java.time.LocalDate

@Suppress("FunctionName")
abstract class AbstractLocalDateExpectationsTest(
    private val yearFeatureSpec: Feature0<LocalDate, Int>,
    private val yearSpec: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    private val monthFeatureSpec: Feature0<LocalDate, Int>,
    private val monthSpec: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    private val dayFeatureSpec: Feature0<LocalDate, Int>,
    private val daySpec: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    private val dayOfWeekFeatureSpec: Feature0<LocalDate, DayOfWeek>,
    private val dayOfWeekSpec: Fun1<LocalDate, Expect<DayOfWeek>.() -> Unit>,
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
        LocalDate.of(2040, 1, 13),
        yearSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(2040) },
        monthSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        daySpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(13) },
        dayOfWeekSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(DayOfWeek.FRIDAY) }
    )

    private val subject = LocalDate.of(2009, 3, 13)
    private val monthDescr = DescriptionDateTimeLikeExpectation.MONTH.getDefault()
    private val yearDescr = DescriptionDateTimeLikeExpectation.YEAR.getDefault()
    private val dayDescr = DescriptionDateTimeLikeExpectation.DAY.getDefault()
    private val dayOfWeekDescr = DescriptionDateTimeLikeExpectation.DAY_OF_WEEK.getDefault()

    @TestFactory
    fun year() = testFactoryForFeatureNonFeature(yearFeatureSpec, yearSpec) { name, yearFun, _ ->
        describe("LocalDate with year 2009") {
            it("$name - is greater than 2009 holds") {
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
    fun month() = testFactoryForFeatureNonFeature(monthFeatureSpec, monthSpec) { name, monthFun, _ ->
        describe("LocalDate with month March(3)") {
            it("$name - is greater than February(2) holds") {
                expect(subject).monthFun { toBeGreaterThan(2) }
            }
            it("$name - is less than March(3) fails") {
                expect {
                    expect(subject).monthFun { toBeLessThan(3) }
                }.toThrow<AssertionError> {
                    messageToContain("$monthDescr: 3")
                }
            }
        }
    }

    @TestFactory
    fun day() = testFactoryForFeatureNonFeature(dayFeatureSpec, daySpec) { name, dayFun, _ ->
        describe("LocalDate with day of month 13") {
            it("$name - is greater than 5 holds") {
                expect(subject).dayFun { toBeGreaterThan(5) }
            }
            it("$name - is less than 5 fails") {
                expect {
                    expect(subject).dayFun { toBeLessThan(5) }
                }.toThrow<AssertionError> {
                    messageToContain("$dayDescr: 13")
                }
            }
        }
    }

    @TestFactory
    fun dayOfWeek() = testFactoryForFeatureNonFeature(dayOfWeekFeatureSpec, dayOfWeekSpec) { name, dayOfWeekFun, _ ->
        describe("LocalDate with day of week Friday(5)") {
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
