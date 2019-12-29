package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.time.LocalDate
import java.time.chrono.ChronoLocalDate

abstract class ChronoLocalDateAssertionSpec(
    isAfter: Fun1<ChronoLocalDate, ChronoLocalDate>,
    isBeforeOrEquals: Fun1<ChronoLocalDate, ChronoLocalDate>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<ChronoLocalDate>(
        describePrefix,
        isAfter.forSubjectLess(LocalDate.of(2019, 12, 23)),
        isBeforeOrEquals.forSubjectLess(LocalDate.of(2019, 12, 23))
    ){})

    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()
    val isBeforeOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_BEFORE_OR_EQUALS.getDefault()

    val fluent = expect(LocalDate.of(2019, 12, 23) as ChronoLocalDate)

    describe("$describePrefix context subject is 2019-12-23") {
        describe("${isAfter.name} ...") {
            val isAfterFun = isAfter.lambda

            it("... 2019/12/24 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(LocalDate.of(2019, 12, 24))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-24") }
            }
            it("... 2019/12/23 throws an AssertionError") {
                expect {
                    fluent.isAfterFun(LocalDate.of(2019, 12, 23))
                }.toThrow<AssertionError> { messageContains("$isAfterDescr: 2019-12-23") }
            }
            it("... 2019/12/22 does not throw") {
                fluent.isAfterFun(LocalDate.of(2019, 12, 22))
            }
        }
        describe("${isBeforeOrEquals.name} ...") {
            val isBeforeOrEqualsFun = isBeforeOrEquals.lambda

            it("... 2019/12/24 does not throw") {
                fluent.isBeforeOrEqualsFun(LocalDate.of(2019, 12, 24))
            }
            it("... 2019/12/23 does not throw") {
                fluent.isBeforeOrEqualsFun(LocalDate.of(2019, 12, 23))
            }
            it("... 2019/12/22 throws an AssertionError") {
                expect {
                    fluent.isBeforeOrEqualsFun(LocalDate.of(2019, 12, 22))
                }.toThrow<AssertionError> {messageContains("$isBeforeOrEqualsDescr: 2019-12-22")}
            }
        }
    }
})
