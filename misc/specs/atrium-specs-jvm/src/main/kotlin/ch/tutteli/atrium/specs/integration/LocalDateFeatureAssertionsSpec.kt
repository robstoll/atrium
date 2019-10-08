package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.LocalDate
import java.time.Month

abstract class LocalDateFeatureAssertionsSpec(
    yearFeature: Feature0<LocalDate, Int>,
    year: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    monthFeature: Feature0<LocalDate, Int>,
    month: Fun1<LocalDate, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<LocalDate>(describePrefix,
        yearFeature.forSubjectLess().adjustName { "$it feature" },
        year.forSubjectLess { isGreaterThan(2000) },
        monthFeature.forSubjectLess().adjustName { "$it feature" },
        month.forSubjectLess { isLessThan(12) }
    ) {})

    include(object : AssertionCreatorSpec<LocalDate>(
        describePrefix, LocalDate.ofYearDay(2040, 1),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) },
        month.forAssertionCreatorSpec("$toBeDescr: 1") {toBe(1)}
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(LocalDate.of(2009,Month.MARCH,13))
    val monthDescr = DescriptionDateTimeLikeAssertion.MONTH.getDefault()
    val yearDescr = DescriptionDateTimeLikeAssertion.YEAR.getDefault()

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
})
