package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import java.time.ZonedDateTime

abstract class ZonedDateTimeFeatureAssertionsSpec(
    yearFeature: Feature0<ZonedDateTime, Int>,
    year: Fun1<ZonedDateTime, Expect<Int>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<ZonedDateTime>(describePrefix,
        yearFeature.forSubjectLess().adjustName { "$it feature" },
        year.forSubjectLess { isGreaterThan(2000) }
    ) {})

    include(object : AssertionCreatorSpec<ZonedDateTime>(
        describePrefix, ZonedDateTime.now().withYear(2040),
        year.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(2040) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val fluent = expect(ZonedDateTime.now().withYear(2009))

    describeFun("val ${yearFeature.name}") {
        val yearVal = yearFeature.lambda

        context("ZonedDateTime with year 2009") {
            it("toBe(2009) holds") {
                fluent.yearVal().toBe(2009)
            }
            it("toBe(2019) fails") {
                expect {
                    fluent.yearVal().toBe(2019)
                }.toThrow<AssertionError> {
                    messageContains("year: 2009")
                }
            }
        }
    }

    describeFun("fun ${year.name}") {
        val yearFun = year.lambda

        context("ZonedDateTime with year 2009") {
            it("is greater than 2009 holds") {
                fluent.yearFun { isGreaterThan(2008) }
            }
            it("is less than 2009 fails") {
                expect {
                    fluent.yearFun { isLessThan(2009) }
                }.toThrow<AssertionError> {
                    messageContains("year: 2009")
                }
            }
        }
    }
})
