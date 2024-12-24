package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionDateTimeLikeProof.*
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.DayOfWeek
import java.time.ZonedDateTime

abstract class ZonedDateTimeExpectationsSpec(
    yearFeature: Feature0<ZonedDateTime, Int>,
    year: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    monthFeature: Feature0<ZonedDateTime, Int>,
    month: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    dayFeature: Feature0<ZonedDateTime, Int>,
    day: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    dayOfWeekFeature: Feature0<ZonedDateTime, DayOfWeek>,
    dayOfWeek: Fun1<ZonedDateTime, Expect<DayOfWeek>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<ZonedDateTime>(describePrefix,
        yearFeature.forSubjectLessTest(),
        year.forSubjectLessTest { toBeGreaterThan(2000) },
        monthFeature.forSubjectLessTest(),
        month.forSubjectLessTest { toBeLessThan(12) },
        dayFeature.forSubjectLessTest(),
        day.forSubjectLessTest { toBeLessThanOrEqualTo(20) },
        dayOfWeekFeature.forSubjectLessTest(),
        dayOfWeek.forSubjectLessTest { toBeLessThanOrEqualTo(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<ZonedDateTime>(
        describePrefix, ZonedDateTime.now().withYear(2040).withDayOfYear(1).withDayOfMonth(15),
        year.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(2040) },
        month.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) },
        day.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(15) },
        dayOfWeek.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(DayOfWeek.SUNDAY) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val subject = ZonedDateTime.now().withMonth(5).withYear(2009).withDayOfMonth(15)

    describeFun(yearFeature, year) {
        val yearFunctions = unifySignatures(yearFeature, year)

        context("ZonedDateTime with year 2009") {
            yearFunctions.forEach { (name, yearFun, _) ->
                it("$name - is greater than 2009 holds") {
                    expect(subject).yearFun { toBeGreaterThan(2008) }
                }
                it("$name - is less than 2009 fails") {
                    expect {
                        expect(subject).yearFun { toBeLessThan(2009) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(YEAR, 2009) }
                    }
                }
            }
        }
    }

    describeFun(monthFeature, month) {
        val monthFunctions = unifySignatures(monthFeature, month)

        context("ZonedDateTime with month May(5)") {
            monthFunctions.forEach { (name, monthFun, _) ->
                it("$name - is greater than February(2) holds") {
                    expect(subject).monthFun { toBeGreaterThan(2) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        expect(subject).monthFun { toBeLessThan(5) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(MONTH, 5) }
                    }
                }
            }
        }
    }


    describeFun(dayFeature, day) {
        val dayFunctions = unifySignatures(dayFeature, day)

        context("LocalDate with day of month 15") {
            dayFunctions.forEach { (name, dayFun, _) ->
                it("$name - is greater than 5 holds") {
                    expect(subject).dayFun { toBeGreaterThan(5) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        expect(subject).dayFun { toBeLessThan(5) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DAY, 15) }
                    }
                }
            }
        }
    }

    describeFun(dayOfWeekFeature, dayOfWeek) {
        val dayOfWeekFunctions = unifySignatures(dayOfWeekFeature, dayOfWeek)

        context("ZonedDateTime with day of week Friday(5)") {
            dayOfWeekFunctions.forEach { (name, dayOfWeekFun, _) ->
                it("$name - is greater than Monday(1) holds") {
                    expect(subject).dayOfWeekFun { toBeGreaterThan(DayOfWeek.MONDAY) }
                }
                it("$name - is less than Friday(5) fails") {
                    expect {
                        expect(subject).dayOfWeekFun { toBeLessThan(DayOfWeek.FRIDAY) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr(DAY_OF_WEEK, DayOfWeek.FRIDAY) }
                    }
                }
            }
        }
    }
})
