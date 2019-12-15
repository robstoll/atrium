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
import java.time.LocalDateTime

abstract class LocalDateTimeFeatureAssertionsSpec(
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
        day.forSubjectLess { isLessOrEquals(20) },
        dayOfWeekFeature.forSubjectLess().adjustName { "$it feature" },
        dayOfWeek.forSubjectLess { isLessOrEquals(DayOfWeek.SUNDAY) }
    ) {})

    include(object : AssertionCreatorSpec<LocalDateTime>(
        describePrefix, LocalDateTime.of(2040, 1, 15, 10, 20, 30),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) },
        month.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) },
        day.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(15) },
        dayOfWeek.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(DayOfWeek.SUNDAY) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(LocalDateTime.of(2009, 5, 15, 10, 5))
    val monthDescr = DescriptionDateTimeLikeAssertion.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeAssertion.YEAR.getDefault()
    val dayDescr = DescriptionDateTimeLikeAssertion.DAY.getDefault()
    val dayOfWeekDescr = DescriptionDateTimeLikeAssertion.DAY_OF_WEEK.getDefault()

    describeFun("val ${yearFeature.name}") {
        val yearVal = yearFeature.lambda

        context("LocalDateTime with year 2009") {
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

        context("LocalDateTime with year 2009") {
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

        context("LocalDateTime with month May(5)") {
            it("toBe(May) holds") {
                fluent.monthVal().toBe(5)
            }
            it("toBe(April) fails") {
                expect {
                    fluent.monthVal().toBe(4)
                }.toThrow<AssertionError> {
                    messageContains("$monthDescr: 5")
                }
            }
        }
    }

    describeFun("fun ${month.name}") {
        val monthFun = month.lambda

        context("LocalDateTime with month May(5)") {
            it("is greater than February(2) holds") {
                fluent.monthFun { isGreaterThan(2) }
            }
            it("is less than 5 fails") {
                expect {
                    fluent.monthFun { isLessThan(5) }
                }.toThrow<AssertionError> {
                    messageContains("$monthDescr: 5")
                }
            }
        }
    }

    describeFun("val ${dayOfWeekFeature.name}") {
        val dayOfWeekVal = dayOfWeekFeature.lambda

        context("LocalDateTime with day of week Friday(5)") {
            it("toBe(Friday) holds") {
                fluent.dayOfWeekVal().toBe(DayOfWeek.FRIDAY)
            }
            it("toBe(Monday) fails") {
                expect {
                    fluent.dayOfWeekVal().toBe(DayOfWeek.MONDAY)
                }.toThrow<AssertionError> {
                    messageContains("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}")
                }
            }
        }
    }

    describeFun("fun ${dayOfWeek.name}") {
        val dayOfWeekFun = dayOfWeek.lambda

        context("LocalDateTime with day of week Friday(5)") {
            it("is greater than Monday(1) holds") {
                fluent.dayOfWeekFun { isGreaterThan(DayOfWeek.MONDAY) }
            }
            it("is less than Friday(5) fails") {
                expect {
                    fluent.dayOfWeekFun { isLessThan(DayOfWeek.FRIDAY) }
                }.toThrow<AssertionError> {
                    messageContains("$dayOfWeekDescr: ${DayOfWeek.FRIDAY}")
                }
            }
        }
    }

    describeFun("val ${dayFeature.name}") {
        val dayVal = dayFeature.lambda

        context("LocalDate with day of month 15") {
            it("toBe(15) holds") {
                fluent.dayVal().toBe(15)
            }
            it("toBe(20) fails") {
                expect {
                    fluent.dayVal().toBe(20)
                }.toThrow<AssertionError> {
                    messageContains("$dayDescr: 15")
                }
            }
        }
    }

    describeFun("fun ${day.name}") {
        val dayOfMonthFun = day.lambda

        context("LocalDate with day of month 15") {
            it("is greater than 5 holds") {
                fluent.dayOfMonthFun { isGreaterThan(5) }
            }
            it("is less than 5 fails") {
                expect {
                    fluent.dayOfMonthFun { isLessThan(5) }
                }.toThrow<AssertionError> {
                    messageContains("$dayDescr: 15")
                }
            }
        }
    }
})
