package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.Year

abstract class LocalDateExpectationsSpec(
    yearFeature: Feature0<LocalDate, Int>,
    year: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    monthFeature: Feature0<LocalDate, Int>,
    month: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    dayFeature: Feature0<LocalDate, Int>,
    day: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    dayOfWeekFeature: Feature0<LocalDate, DayOfWeek>,
    dayOfWeek: Fun1<LocalDate, Expect<DayOfWeek>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<LocalDate>(
        describePrefix,
        yearFeature.forSubjectLessTest(),
        year.forSubjectLessTest { toBeGreaterThan(2000) },
        monthFeature.forSubjectLessTest(),
        month.forSubjectLessTest { toBeLessThan(12) },
        dayFeature.forSubjectLessTest(),
        day.forSubjectLessTest { toBeLessThanOrEqualTo(20) },
        dayOfWeekFeature.forSubjectLessTest(),
        dayOfWeek.forSubjectLessTest { toBeLessThanOrEqualTo(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<LocalDate>(
        describePrefix, LocalDate.of(2040, 1, 13),
        year.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(2040) },
        month.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        day.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(13) },
        dayOfWeek.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(DayOfWeek.FRIDAY) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val subject = LocalDate.of(2009, Month.MARCH, 13)

    describeFun(yearFeature, year) {
        val yearFunctions = unifySignatures(yearFeature, year)

        context("LocalDate with year 2009") {
            yearFunctions.forEach { (name, yearFun, _) ->
                it("$name - is greater than 2009 holds") {
                    expect(subject).yearFun { toBeGreaterThan(2008) }
                }
                it("$name - is less than 2009 fails") {
                    expect {
                        expect(subject).yearFun { toBeLessThan(2009) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DescriptionDateTimeLikeProof.YEAR, 2009) }
                    }
                }
            }
        }
    }

    describeFun(monthFeature, month) {
        val monthFunctions = unifySignatures(monthFeature, month)

        context("LocalDate with month March(3)") {
            monthFunctions.forEach { (name, monthFun, _) ->
                it("$name - is greater than February(2) holds") {
                    expect(subject).monthFun { toBeGreaterThan(2) }
                }
                it("$name - is less than March(3) fails") {
                    expect {
                        expect(subject).monthFun { toBeLessThan(3) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DescriptionDateTimeLikeProof.MONTH, 3) }
                    }
                }
            }
        }
    }

    describeFun(dayFeature, day) {
        val dayFunctions = unifySignatures(dayFeature, day)

        context("LocalDate with day of month 13") {
            dayFunctions.forEach { (name, dayFun, _) ->
                it("$name - is greater than 5 holds") {
                    expect(subject).dayFun { toBeGreaterThan(5) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        expect(subject).dayFun { toBeLessThan(5) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DescriptionDateTimeLikeProof.DAY, 13) }
                    }
                }
            }
        }
    }

    describeFun(dayOfWeekFeature, dayOfWeek) {
        val dayOfWeekFunctions = unifySignatures(dayOfWeekFeature, dayOfWeek)

        context("LocalDate with day of week Friday(5)") {
            dayOfWeekFunctions.forEach { (name, dayOfWeekFun, _) ->
                it("$name - is greater than Monday(1) holds") {
                    expect(subject).dayOfWeekFun { toBeGreaterThan(DayOfWeek.MONDAY) }
                }
                it("$name - is less than Friday(5) fails") {
                    expect {
                        expect(subject).dayOfWeekFun { toBeLessThan(DayOfWeek.FRIDAY) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DescriptionDateTimeLikeProof.DAY_OF_WEEK, DayOfWeek.FRIDAY) }
                    }
                }
            }
        }
    }
})
