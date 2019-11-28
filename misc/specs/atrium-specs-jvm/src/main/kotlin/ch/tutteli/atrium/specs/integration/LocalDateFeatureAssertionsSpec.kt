package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessOrEquals
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toBe
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.AssertionCreatorSpec
import ch.tutteli.atrium.specs.Feature0
import ch.tutteli.atrium.specs.Fun1
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.adjustName
import ch.tutteli.atrium.specs.describeFunTemplate
import ch.tutteli.atrium.specs.forAssertionCreatorSpec
import ch.tutteli.atrium.specs.forSubjectLess
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.name
import ch.tutteli.atrium.specs.toBeDescr
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.MonthDay

abstract class LocalDateFeatureAssertionsSpec(
    yearFeature: Feature0<LocalDate, Int>,
    year: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    monthFeature: Feature0<LocalDate, Int>,
    month: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    dayOfWeekFeature: Feature0<LocalDate, DayOfWeek>,
    dayOfWeek: Fun1<LocalDate, Expect<DayOfWeek>.() -> Unit>,
    dayFeature: Feature0<LocalDate, Int>,
    day: Fun1<LocalDate,  Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<LocalDate>(describePrefix,
        yearFeature.forSubjectLess().adjustName { "$it feature" },
        year.forSubjectLess { isGreaterThan(2000) },
        monthFeature.forSubjectLess().adjustName { "$it feature" },
        month.forSubjectLess { isLessThan(12) },
        dayOfWeekFeature.forSubjectLess().adjustName { "$it feature" },
        dayOfWeek.forSubjectLess { isLessOrEquals(DayOfWeek.SUNDAY) },
        dayFeature.forSubjectLess().adjustName { "$it feature" },
        day.forSubjectLess { isLessOrEquals(20) }
    ) {})

    include(object : AssertionCreatorSpec<LocalDate>(
        describePrefix, LocalDate.ofYearDay(2040, 1).withDayOfMonth(13),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) },
        month.forAssertionCreatorSpec("$toBeDescr: 1") {toBe(1)},
        dayOfWeek.forAssertionCreatorSpec("$toBeDescr: 1") {toBe(DayOfWeek.FRIDAY)},
        day.forAssertionCreatorSpec("$toBeDescr: 1") {toBe(13)}
        ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(LocalDate.of(2009,Month.MARCH,13))
    val monthDescr = DescriptionDateTimeLikeAssertion.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeAssertion.YEAR.getDefault()
    val dayOfWeekDescr = DescriptionDateTimeLikeAssertion.DAY_OF_WEEK.getDefault()
    val dayDescr = DescriptionDateTimeLikeAssertion.DAY.getDefault()

    describeFun("val ${yearFeature.name}") {
        val yearVal = yearFeature.lambda

        context("LocalDate with year 2009") {
            it("toBe(2009) holds") {
                fluent.yearVal().toBe(2009)
            }
            it("toBe(2019) fails") {
                expect {
                    fluent.yearVal().toBe(2019)
                }.toThrow<AssertionError> {
                    messageContains("$yearDescr: 2009")
                }
            }
        }
    }

    describeFun("fun ${year.name}") {
        val yearFun = year.lambda

        context("LocalDate with year 2009") {
            it("is greater than 2009 holds") {
                fluent.yearFun { isGreaterThan(2008) }
            }
            it("is less than 2009 fails") {
                expect {
                    fluent.yearFun { isLessThan(2009) }
                }.toThrow<AssertionError> {
                    messageContains("$yearDescr: 2009")
                }
            }
        }
    }

    describeFun("val ${monthFeature.name}") {
        val monthVal = monthFeature.lambda

        context("LocalDate with month March(3)") {
            it("toBe(March) holds") {
                fluent.monthVal().toBe(3)
            }
            it("toBe(April) fails") {
                expect {
                    fluent.monthVal().toBe(4)
                }.toThrow<AssertionError> {
                    messageContains("$monthDescr: 3" )
                }
            }
        }
    }

    describeFun("fun ${month.name}") {
        val monthFun = month.lambda

        context("LocalDate with month March(3)") {
            it("is greater than February(2) holds") {
                fluent.monthFun { isGreaterThan(2) }
            }
            it("is less than March(3) fails") {
                expect {
                    fluent.monthFun { isLessThan(3) }
                }.toThrow<AssertionError> {
                    messageContains("$monthDescr: 3")
                }
            }
        }
    }

    describeFun("val ${dayOfWeekFeature.name}") {
        val dayOfWeekVal = dayOfWeekFeature.lambda

        context("LocalDate with day of week Friday(5)") {
            it("toBe(Friday) holds") {
                fluent.dayOfWeekVal().toBe(DayOfWeek.FRIDAY)
            }
            it("toBe(Monday) fails") {
                expect {
                    fluent.dayOfWeekVal().toBe(DayOfWeek.MONDAY)
                }.toThrow<AssertionError> {
                    messageContains("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}" )
                }
            }
        }
    }

    describeFun("fun ${dayOfWeek.name}") {
        val dayOfWeekFun = dayOfWeek.lambda

        context("LocalDate with day of week Friday(5)") {
            it("is greater than Monday(1) holds") {
                fluent.dayOfWeekFun { isGreaterThan(DayOfWeek.MONDAY) }
            }
            it("is less than Friday(5) fails") {
                expect {
                    fluent.dayOfWeekFun { isLessThan(DayOfWeek.FRIDAY) }
                }.toThrow<AssertionError> {
                    messageContains("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}" )
                }
            }
        }
    }

    describeFun("val ${dayFeature.name}") {
        val dayVal = dayFeature.lambda

        context("LocalDate with day of month 13") {
            it("toBe(13) holds") {
                fluent.dayVal().toBe(13)
            }
            it("toBe(20) fails") {
                expect {
                    fluent.dayVal().toBe(20)
                }.toThrow<AssertionError> {
                    messageContains("expect: 2009-03-13" )
                }
            }
        }
    }

    describeFun("fun ${day.name}") {
        val dayOfMonthFun = day.lambda

        context("LocalDate with day of month 13") {
            it("is greater than 5 holds") {
                fluent.dayOfMonthFun { isGreaterThan(5) }
            }
            it("is less than 5 fails") {
                expect {
                    fluent.dayOfMonthFun { isLessThan(5) }
                }.toThrow<AssertionError> {
                    messageContains("expect: 2009-03-13" )
                }
            }
        }
    }
})
