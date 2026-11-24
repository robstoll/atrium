package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import java.time.DayOfWeek
import java.time.LocalDateTime

@Suppress("FunctionName")
abstract class AbstractLocalDateTimeExpectationsTest(
    private val yearFeatureSpec: Feature0<LocalDateTime, Int>,
    private val yearSpec: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    private val monthFeatureSpec: Feature0<LocalDateTime, Int>,
    private val monthSpec: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    private val dayFeatureSpec: Feature0<LocalDateTime, Int>,
    private val daySpec: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    private val dayOfWeekFeatureSpec: Feature0<LocalDateTime, DayOfWeek>,
    private val dayOfWeekSpec: Fun1<LocalDateTime, Expect<DayOfWeek>.() -> Unit>,
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
        LocalDateTime.of(2040, 1, 15, 10, 20, 30),
        yearSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(2040) },
        monthSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        daySpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(15) },
        dayOfWeekSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(DayOfWeek.SUNDAY) }
    )

    private val subject = LocalDateTime.of(2009, 5, 15, 10, 5)
    private val monthDescr = DescriptionDateTimeLikeExpectation.MONTH.getDefault()
    private val yearDescr = DescriptionDateTimeLikeExpectation.YEAR.getDefault()
    private val dayDescr = DescriptionDateTimeLikeExpectation.DAY.getDefault()
    private val dayOfWeekDescr = DescriptionDateTimeLikeExpectation.DAY_OF_WEEK.getDefault()

    @TestFactory
    fun year() = testFactoryForFeatureNonFeature(yearFeatureSpec, yearSpec) { name, yearFun, _ ->
        describe("LocalDateTime with year 2009") {
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
        describe("LocalDateTime with month May(5)") {
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
    fun day() = testFactoryForFeatureNonFeature(dayFeatureSpec, daySpec) { name, dayFun, _ ->
        describe("LocalDateTime with day of month 15") {
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
    fun dayOfWeek() = testFactoryForFeatureNonFeature(dayOfWeekFeatureSpec, dayOfWeekSpec) { name, dayOfWeekFun, _ ->
        describe("LocalDateTime with day of week Friday(5)") {
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
