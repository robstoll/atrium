package ch.tutteli.atrium.specs.integration


import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.specs.CheckingAssertionSpec
import ch.tutteli.atrium.specs.SubjectLessSpec
import ch.tutteli.atrium.specs.include
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ComparableAssertionsSpec(
    verbs: AssertionVerbFactory,
    isLessThanPair: Fun1<Int, Int>,
    isLessOrEqualsPair: Fun1<Int, Int>,
    isGreaterThanPair: Fun1<Int, Int>,
    isGreaterOrEqualsPair: Fun1<Int, Int>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(describePrefix,
        isLessThanPair.forSubjectLess(1),
        isLessOrEqualsPair.forSubjectLess(1),
        isGreaterThanPair.forSubjectLess(1),
        isGreaterOrEqualsPair.forSubjectLess(1)
    ) {})

    include(object : CheckingAssertionSpec<Int>(verbs, describePrefix,
        isLessThanPair.forChecking(1, 0, 1),
        isLessOrEqualsPair.forChecking(1, 1, 2),
        isGreaterThanPair.forChecking(1, 2, 1),
        isGreaterOrEqualsPair.forChecking(1,  1, 0)
    ) {})

    val expect = verbs::checkException
    val (isLessThan, isLessThanFun) = isLessThanPair
    val (isLessOrEquals, isLessOrEqualsFun) = isLessOrEqualsPair
    val (isGreaterThan, isGreaterThanFun) = isGreaterThanPair
    val (isGreaterOrEquals, isGreaterOrEqualsFun) = isGreaterOrEqualsPair

    val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
    val isLessOrEqualsDescr = DescriptionComparableAssertion.IS_LESS_OR_EQUALS.getDefault()
    val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()
    val isGreaterOrEqualsDescr = DescriptionComparableAssertion.IS_GREATER_OR_EQUALS.getDefault()

    val fluent = verbs.check(10)
    describe("$describePrefix context subject is 10") {
        context("$isLessThan ...") {
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

        describe("$isLessOrEquals ...") {
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

        describe("$isGreaterThan ...") {
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

        describe("$isGreaterOrEquals ...") {
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
