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

abstract class ChronoLocalDateAssertionsSpec(
    //isBefore, isBeforeEquals, isAfter
    isAfterOrEquals: Fun1<ChronoLocalDate, ChronoLocalDate>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val date: ChronoLocalDate = LocalDate.of(2019, 12, 15)

    include(object : SubjectLessSpec<ChronoLocalDate>(
        describePrefix,
        isAfterOrEquals.forSubjectLess(date)
    ) {})

    val isAfterOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUALS.getDefault()

    val fluent = expect(date)

    describe("$describePrefix context subject is 2019/12/15") {
        describe("${isAfterOrEquals.name} ...") {
            val isAfterOrEqualsFun = isAfterOrEquals.lambda

            it("... 2019/12/16 throws an AssertionError") {
                expect {
                    fluent.isAfterOrEqualsFun(LocalDate.of(2019, 12, 16))
                }.toThrow<AssertionError> { messageContains("$isAfterOrEqualsDescr: 2019/12/16") }
            }
            it("... 2019/12/15 does not throw") {
                fluent.isAfterOrEqualsFun(LocalDate.of(2019, 12, 15))
            }
            it("... 2019/12/14 does not throw") {
                fluent.isAfterOrEqualsFun(LocalDate.of(2019, 12, 14))
            }
        }
    }

})
