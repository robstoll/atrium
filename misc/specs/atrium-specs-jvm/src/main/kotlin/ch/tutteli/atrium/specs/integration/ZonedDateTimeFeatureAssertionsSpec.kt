package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThanOrEqual
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.DayOfWeek
import java.time.ZonedDateTime

abstract class ZonedDateTimeFeatureAssertionsSpec(
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
        yearFeature.forSubjectLess().adjustName { "$it feature" },
        year.forSubjectLess { isGreaterThan(2000) },
        monthFeature.forSubjectLess().adjustName { "$it feature" },
        month.forSubjectLess { isLessThan(12) },
        dayFeature.forSubjectLess().adjustName { "$it feature" },
        day.forSubjectLess { isLessThanOrEqual(20) },
        dayOfWeekFeature.forSubjectLess().adjustName { "$it feature" },
        dayOfWeek.forSubjectLess { isLessThanOrEqual(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<ZonedDateTime>(
        describePrefix, ZonedDateTime.now().withYear(2040).withDayOfYear(1).withDayOfMonth(15),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) },
        month.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) },
        day.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(15) },
        dayOfWeek.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(DayOfWeek.SUNDAY) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(ZonedDateTime.now().withMonth(5).withYear(2009).withDayOfMonth(15))
    val monthDescr = DescriptionDateTimeLikeAssertion.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeAssertion.YEAR.getDefault()
    val dayOfWeekDescr = DescriptionDateTimeLikeAssertion.DAY_OF_WEEK.getDefault()
    val dayDescr = DescriptionDateTimeLikeAssertion.DAY.getDefault()

    describeFun(yearFeature, year) {
        val yearFunctions = unifySignatures(yearFeature, year)

        context("ZonedDateTime with year 2009") {
            yearFunctions.forEach { (name, yearFun, _) ->
                it("$name - is greater than 2009 holds") {
                    fluent.yearFun { isGreaterThan(2008) }
                }
                it("$name - is less than 2009 fails") {
                    expect {
                        fluent.yearFun { isLessThan(2009) }
                    }.toThrow<AssertionError> {
                        messageContains("$yearDescr: 2009")
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
                    fluent.monthFun { isGreaterThan(2) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        fluent.monthFun { isLessThan(5) }
                    }.toThrow<AssertionError> {
                        messageContains("$monthDescr: 5")
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
                    fluent.dayFun { isGreaterThan(5) }
                }
                it("$name - is less than 5 fails") {
                    expect {
                        fluent.dayFun { isLessThan(5) }
                    }.toThrow<AssertionError> {
                        messageContains("$dayDescr: 15")
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
                    fluent.dayOfWeekFun { isGreaterThan(DayOfWeek.MONDAY) }
                }
                it("$name - is less than Friday(5) fails") {
                    expect {
                        fluent.dayOfWeekFun { isLessThan(DayOfWeek.FRIDAY) }
                    }.toThrow<AssertionError> {
                        messageContains("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}")
                    }
                }
            }
        }
    }
})
