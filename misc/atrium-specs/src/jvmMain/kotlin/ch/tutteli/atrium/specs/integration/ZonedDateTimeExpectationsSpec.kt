package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeExpectation
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
        yearFeature.forSubjectLess(),
        year.forSubjectLess { toBeGreaterThan(2000) },
        monthFeature.forSubjectLess(),
        month.forSubjectLess { toBeLessThan(12) },
        dayFeature.forSubjectLess(),
        day.forSubjectLess { toBeLessThanOrEqualTo(20) },
        dayOfWeekFeature.forSubjectLess(),
        dayOfWeek.forSubjectLess { toBeLessThanOrEqualTo(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<ZonedDateTime>(
        describePrefix, ZonedDateTime.now().withYear(2040).withDayOfYear(1).withDayOfMonth(15),
        year.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(2040) },
        month.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(1) },
        day.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(15) },
        dayOfWeek.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(DayOfWeek.SUNDAY) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(ZonedDateTime.now().withMonth(5).withYear(2009).withDayOfMonth(15))
    val monthDescr = DescriptionDateTimeLikeExpectation.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeExpectation.YEAR.getDefault()
    val dayDescr = DescriptionDateTimeLikeExpectation.DAY.getDefault()
    val dayOfWeekDescr = DescriptionDateTimeLikeExpectation.DAY_OF_WEEK.getDefault()

    describeFun(yearFeature, year) {
        val yearFunctions = unifySignatures(yearFeature, year)

        context("ZonedDateTime with year 2009") {
            yearFunctions.forEach { (name, yearFun, _) ->
                it("$name - is greater than 2009 holds") {
                    fluent.yearFun { toBeGreaterThan(2008) }
                }
                it("$name - is less than 2009 fails") {
                    expect {
                        fluent.yearFun { toBeLessThan(2009) }
                    }.toThrow<AssertionError> {
                        messageToContain("$yearDescr: 2009")
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
                    fluent.monthFun { toBeGreaterThan(2) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        fluent.monthFun { toBeLessThan(5) }
                    }.toThrow<AssertionError> {
                        messageToContain("$monthDescr: 5")
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
                    fluent.dayFun { toBeGreaterThan(5) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        fluent.dayFun { toBeLessThan(5) }
                    }.toThrow<AssertionError> {
                        messageToContain("$dayDescr: 15")
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
                    fluent.dayOfWeekFun { toBeGreaterThan(DayOfWeek.MONDAY) }
                }
                it("$name - is less than Friday(5) fails") {
                    expect {
                        fluent.dayOfWeekFun { toBeLessThan(DayOfWeek.FRIDAY) }
                    }.toThrow<AssertionError> {
                        messageToContain("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}")
                    }
                }
            }
        }
    }
})
