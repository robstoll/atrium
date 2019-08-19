@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class ComparableAssertionsSpec(
    verbs: AssertionVerbFactory,
    isLessThanPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isLessOrEqualsPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isGreaterThanPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isGreaterOrEqualsPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Int>(describePrefix,
        isLessThanPair.first to mapToCreateAssertion { isLessThanPair.second(this, 1) },
        isLessOrEqualsPair.first to mapToCreateAssertion { isLessOrEqualsPair.second(this, 1) },
        isGreaterThanPair.first to mapToCreateAssertion { isGreaterThanPair.second(this, 1) },
        isGreaterOrEqualsPair.first to mapToCreateAssertion { isGreaterOrEqualsPair.second(this, 1) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Int>(verbs, describePrefix,
        checkingTriple(isLessThanPair.first, { isLessThanPair.second(this, 1) }, 0, 1),
        checkingTriple(isLessOrEqualsPair.first, { isLessOrEqualsPair.second(this, 1) }, 1, 2),
        checkingTriple(isGreaterThanPair.first, { isGreaterThanPair.second(this, 1) }, 2, 1),
        checkingTriple(isGreaterOrEqualsPair.first, { isGreaterOrEqualsPair.second(this, 1) }, 1, 0)
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

    val fluent = verbs.checkImmediately(10)
    group("$describePrefix context subject is 10") {
        describe("$isLessThan ...") {
            test("... 11 does not throw") {
                fluent.isLessThanFun(11)
            }
            test("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(10)
                }.toThrow<AssertionError> { messageContains("$isLessThanDescr: 10") }
            }
            test("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(9)
                }.toThrow<AssertionError> { messageContains("$isLessThanDescr: 9") }
            }
        }

        describe("$isLessOrEquals ...") {
            test("... 11 does not throw") {
                fluent.isLessOrEqualsFun(11)
            }
            test("... 10 does not throw") {
                fluent.isLessOrEqualsFun(10)
            }
            test("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_OR_EQUALS} and `: 10`") {
                expect {
                    fluent.isLessOrEqualsFun(9)
                }.toThrow<AssertionError> { messageContains("$isLessOrEqualsDescr: 9") }
            }
        }

        describe("$isGreaterThan ...") {
            test("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 11`") {
                expect {
                    fluent.isGreaterThanFun(11)
                }.toThrow<AssertionError> { messageContains("$isGreaterThanDescr: 11") }
            }
            test("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 10`") {
                expect {
                    fluent.isGreaterThanFun(10)
                }.toThrow<AssertionError> { messageContains("$isGreaterThanDescr: 10") }
            }
            test("... 9 does not throw") {
                fluent.isGreaterThanFun(9)
            }
        }

        describe("$isGreaterOrEquals ...") {
            test("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_OR_EQUALS} and `: 11`") {
                expect {
                    fluent.isGreaterOrEqualsFun(11)
                }.toThrow<AssertionError> { messageContains("$isGreaterOrEqualsDescr: 11") }
            }
            test("... 10 does not throw") {
                fluent.isGreaterOrEqualsFun(10)
            }
            test("... 9 does not throw") {
                fluent.isGreaterOrEqualsFun(9)
            }
        }
    }

})
