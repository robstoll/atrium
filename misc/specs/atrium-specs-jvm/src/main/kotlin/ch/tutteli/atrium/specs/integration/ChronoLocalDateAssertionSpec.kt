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
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<ChronoLocalDate>(
        describePrefix,
        isAfter.forSubjectLess(LocalDate.of(2019, 12, 23))
    ){})

    val isAfterDescr = DescriptionDateTimeLikeAssertion.IS_AFTER.getDefault()

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
    }
})
