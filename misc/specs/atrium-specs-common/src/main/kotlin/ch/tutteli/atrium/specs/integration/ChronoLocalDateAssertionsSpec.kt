package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionDateTimeLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ChronoLocalDateAssertionsSpec(
//    isBefore: Fun1<Int, Int>,
//    isBeforeOrEquals: Fun1<Int, Int>,
//    isAfter: Fun1<Int, Int>,
    isAfterOrEquals: Fun1<Int, Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        isAfterOrEquals.forSubjectLess(1)
    ) {})

    val isAfterOrEqualsDescr = DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUALS

    val fluent = expect(10)
    describe("$describePrefix context subject is 10") {
        describe("${isAfterOrEquals.name} ...") {
            val isAfterOrEqualsFun = isAfterOrEquals.lambda

            it("... 11 throws an AssertionError containing ${DescriptionDateTimeLikeAssertion::class.simpleName}.${DescriptionDateTimeLikeAssertion.IS_AFTER_OR_EQUALS} and `: 11`") {
                expect {
                    fluent.isAfterOrEqualsFun(11)
                }.toThrow<AssertionError> { messageContains("$isAfterOrEqualsDescr: 11") }
            }
            it("... 10 does not throw") {
                fluent.isAfterOrEqualsFun(10)
            }
            it("... 9 does not throw") {
                fluent.isAfterOrEqualsFun(9)
            }
        }
    }

})
