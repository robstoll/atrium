package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

abstract class ComparableAssertionsSpec(
    isLessThan: Fun1<Int, Int>,
    isLessOrEquals: Fun1<Int, Int>,
    isGreaterThan: Fun1<Int, Int>,
    isGreaterOrEquals: Fun1<Int, Int>,
    isEqualComparingTo: Fun1<Int, Int>,
    isEqualComparingTo2: Fun1<DiffEqualsCompareTo, DiffEqualsCompareTo>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Int>(
        describePrefix,
        isLessThan.forSubjectLess(1),
        isLessOrEquals.forSubjectLess(1),
        isGreaterThan.forSubjectLess(1),
        isGreaterOrEquals.forSubjectLess(1),
        isEqualComparingTo.forSubjectLess(1)
    ) {})

    val isLessThanDescr = IS_LESS_THAN.getDefault()
    val isLessOrEqualsDescr = IS_LESS_THAN_OR_EQUAL.getDefault()
    val isGreaterThanDescr = IS_GREATER_THAN.getDefault()
    val isGreaterOrEqualsDescr = IS_GREATER_THAN_OR_EQUAL.getDefault()
    val isEqualComparingToDescr = IS_EQUAL.getDefault()

    val fluent = expect(10)
    describe("$describePrefix context subject is 10") {
        context("${isLessThan.name} ...") {
            val isLessThanFun = isLessThan.lambda

            it("... 11 does not throw") {
                fluent.isLessThanFun(11)
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_LESS_THAN and `: 10`") {
                expect {
                    fluent.isLessThanFun(10)
                }.toThrow<AssertionError> { messageContains("$isLessThanDescr: 10") }
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_LESS_THAN and `: 10`") {
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
            it("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_LESS_THAN_OR_EQUAL and `: 10`") {
                expect {
                    fluent.isLessOrEqualsFun(9)
                }.toThrow<AssertionError> { messageContains("$isLessOrEqualsDescr: 9") }
            }
        }

        describe("${isGreaterThan.name} ...") {
            val isGreaterThanFun = isGreaterThan.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_GREATER_THAN and `: 11`") {
                expect {
                    fluent.isGreaterThanFun(11)
                }.toThrow<AssertionError> { messageContains("$isGreaterThanDescr: 11") }
            }
            it("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_GREATER_THAN and `: 10`") {
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

            it("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_GREATER_THAN_OR_EQUAL and `: 11`") {
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

        describe("${isEqualComparingTo.name} ...") {
            val isEqualComparingToFun = isEqualComparingTo.lambda

            it("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_EQUAL and `: 11`") {
                expect {
                    fluent.isEqualComparingToFun(11)
                }.toThrow<AssertionError> { messageContains("$isEqualComparingToDescr: 11") }
            }
            it("... 10 does not throw") {
                fluent.isEqualComparingToFun(10)
            }
            it("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.$IS_EQUAL and `: 9`") {
                expect {
                    fluent.isEqualComparingToFun(9)
                }.toThrow<AssertionError> { messageContains("$isEqualComparingToDescr: 9") }
            }
        }
    }
    describe("$describePrefix context subject is a class where equals is different from compareTo") {
        describe("${isEqualComparingTo2.name} ...") {
            val isEqualComparingToFun = isEqualComparingTo2.lambda
            it("expected is same instance but compareTo returns false - throws AssertionError") {
                expect {
                    val subject = DiffEqualsCompareTo("welcome")
                    expect(subject).isEqualComparingToFun(subject)
                }.toThrow<AssertionError> { messageContains("$isEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }

            it("expected equals but compareTo returns false - throws AssertionError") {
                expect {
                    expect(DiffEqualsCompareTo("welcome")).isEqualComparingToFun(DiffEqualsCompareTo("welcome"))
                }.toThrow<AssertionError> { messageContains("$isEqualComparingToDescr: DiffEqualsCompareTo(s=welcome)") }
            }
            it("expected does not equal but compareTo = 0 - does not throw") {
                expect(DiffEqualsCompareTo("welcome")).isEqualComparingToFun(DiffEqualsCompareTo("hello"))
            }
        }
    }
})

data class DiffEqualsCompareTo(val s: String) : Comparable<DiffEqualsCompareTo> {
    override fun compareTo(other: DiffEqualsCompareTo): Int = other.s.compareTo("hello")
}

