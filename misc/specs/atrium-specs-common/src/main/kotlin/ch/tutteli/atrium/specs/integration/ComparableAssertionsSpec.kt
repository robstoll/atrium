package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite
import org.spekframework.spek2.style.specification.describe

abstract class ComparableAssertionsSpec(
    isLessThan: Fun1<Int, Int>,
    isLessOrEquals: Fun1<Int, Int>,
    isGreaterThan: Fun1<Int, Int>,
    isGreaterOrEquals: Fun1<Int, Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        isLessThan.forSubjectLess(1),
        isLessOrEquals.forSubjectLess(1),
        isGreaterThan.forSubjectLess(1),
        isGreaterOrEquals.forSubjectLess(1)
    ) {})

    val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
    val isLessOrEqualsDescr = DescriptionComparableAssertion.IS_LESS_OR_EQUALS.getDefault()
    val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()
    val isGreaterOrEqualsDescr = DescriptionComparableAssertion.IS_GREATER_OR_EQUALS.getDefault()

    val fluent = expect(10)
    describe("$describePrefix context subject is 10") {
        context("${isLessThan.name} ...") {
            val isLessThanFun = isLessThan.lambda

            it("... 11 does not throw") {
                fluent.isLessThanFun(11)
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(10)
                }.toThrow<AssertionError> { messageContains("$isLessThanDescr: 10") }
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(9)
                }.toThrow<AssertionError> { messageContains("$isLessThanDescr: 9") }
            }
        }

        describe("${isLessOrEquals.name} ...") {
            val isLessOrEqualsFun = isLessOrEquals.lambda

            it("... 11 does not throw") {
                fluent.isLessOrEqualsFun(11)
            }
            it("... 10 does not throw") {
                fluent.isLessOrEqualsFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_OR_EQUALS} and `: 10`") {
                expect {
                    fluent.isLessOrEqualsFun(9)
                }.toThrow<AssertionError> { messageContains("$isLessOrEqualsDescr: 9") }
            }
        }

        describe("${isGreaterThan.name} ...") {
            val isGreaterThanFun = isGreaterThan.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 11`") {
                expect {
                    fluent.isGreaterThanFun(11)
                }.toThrow<AssertionError> { messageContains("$isGreaterThanDescr: 11") }
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 10`") {
                expect {
                    fluent.isGreaterThanFun(10)
                }.toThrow<AssertionError> { messageContains("$isGreaterThanDescr: 10") }
            }
            it("... 9 does not throw") {
                fluent.isGreaterThanFun(9)
            }
        }

        describe("${isGreaterOrEquals.name} ...") {
            val isGreaterOrEqualsFun = isGreaterOrEquals.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_OR_EQUALS} and `: 11`") {
                expect {
                    fluent.isGreaterOrEqualsFun(11)
                }.toThrow<AssertionError> { messageContains("$isGreaterOrEqualsDescr: 11") }
            }
            it("... 10 does not throw") {
                fluent.isGreaterOrEqualsFun(10)
            }
            it("... 9 does not throw") {
                fluent.isGreaterOrEqualsFun(9)
            }
        }
    }

})
