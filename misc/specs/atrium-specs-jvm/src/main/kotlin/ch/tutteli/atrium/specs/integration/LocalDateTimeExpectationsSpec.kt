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
import java.time.LocalDateTime

abstract class LocalDateTimeAssertionsSpec(
    yearFeature: Feature0<LocalDateTime, Int>,
    year: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    monthFeature: Feature0<LocalDateTime, Int>,
    month: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    dayFeature: Feature0<LocalDateTime, Int>,
    day: Fun1<LocalDateTime, Expect<Int>.() -> Unit>,
    dayOfWeekFeature: Feature0<LocalDateTime, DayOfWeek>,
    dayOfWeek: Fun1<LocalDateTime, Expect<DayOfWeek>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<LocalDateTime>(describePrefix,
        yearFeature.forSubjectLess().adjustName { "$it feature" },
        year.forSubjectLess { isGreaterThan(2000) },
        monthFeature.forSubjectLess().adjustName { "$it feature" },
        month.forSubjectLess { isLessThan(12) },
        dayFeature.forSubjectLess().adjustName { "$it feature" },
        day.forSubjectLess { isLessThanOrEqual(20) },
        dayOfWeekFeature.forSubjectLess().adjustName { "$it feature" },
        dayOfWeek.forSubjectLess { isLessThanOrEqual(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<LocalDateTime>(
        describePrefix, LocalDateTime.of(2040, 1, 15, 10, 20, 30),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) },
        month.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) },
        day.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(15) },
        dayOfWeek.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(DayOfWeek.SUNDAY) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(LocalDateTime.of(2009, 5, 15, 10, 5))
    val monthDescr = DescriptionDateTimeLikeAssertion.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeAssertion.YEAR.getDefault()
    val dayDescr = DescriptionDateTimeLikeAssertion.DAY.getDefault()
    val dayOfWeekDescr = DescriptionDateTimeLikeAssertion.DAY_OF_WEEK.getDefault()

    describeFun(yearFeature, year) {
        val yearFunctions = unifySignatures(yearFeature, year)

        context("LocalDateTime with year 2009") {
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

        context("LocalDateTime with month May(5)") {
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

        context("LocalDateTime with day of month 15") {
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

        context("LocalDateTime with day of week Friday(5)") {
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
