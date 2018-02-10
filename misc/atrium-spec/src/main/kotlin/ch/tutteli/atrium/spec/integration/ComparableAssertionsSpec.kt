package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.include

abstract class ComparableAssertionsSpec(
    verbs: AssertionVerbFactory,
    isLessThanPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isLessOrEqualPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isGreaterThanPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    isGreaterOrEqualPair: Pair<String, Assert<Int>.(Int) -> Assert<Int>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Int>(describePrefix,
        isLessThanPair.first to mapToCreateAssertion { isLessThanPair.second(this, 1) },
        isLessOrEqualPair.first to mapToCreateAssertion { isLessOrEqualPair.second(this, 1) },
        isGreaterThanPair.first to mapToCreateAssertion { isGreaterThanPair.second(this, 1) },
        isGreaterOrEqualPair.first to mapToCreateAssertion { isGreaterOrEqualPair.second(this, 1) }
    ) {})

    include(object : CheckingAssertionSpec<Int>(verbs, describePrefix,
        checkingTriple(isLessThanPair.first, { isLessThanPair.second(this, 1) }, 0, 1),
        checkingTriple(isLessOrEqualPair.first, { isLessOrEqualPair.second(this, 1) }, 1, 2),
        checkingTriple(isGreaterThanPair.first, { isGreaterThanPair.second(this, 1) }, 2, 1),
        checkingTriple(isGreaterOrEqualPair.first, { isGreaterOrEqualPair.second(this, 1) }, 1, 0)
    ) {})

    val expect = verbs::checkException
    val (isLessThan, isLessThanFun) = isLessThanPair
    val (isLessOrEqual, isLessOrEqualFun) = isLessOrEqualPair
    val (isGreaterThan, isGreaterThanFun) = isGreaterThanPair
    val (isGreaterOrEqual, isGreaterOrEqualFun) = isGreaterOrEqualPair

    val fluent = verbs.checkImmediately(10)
    group("$describePrefix context subject is 10") {
        describe("$isLessThan ...") {
            test("... 11 does not throw") {
                fluent.isLessThanFun(11)
            }
            test("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(10)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_LESS_THAN)
                        contains(": 10")
                    }
                }
            }
            test("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_THAN} and `: 10`") {
                expect {
                    fluent.isLessThanFun(9)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_LESS_THAN)
                        contains(": 9")
                    }
                }
            }
        }

        describe("$isLessOrEqual ...") {
            test("... 11 does not throw") {
                fluent.isLessOrEqualFun(11)
            }
            test("... 10 does not throw") {
                fluent.isLessOrEqualFun(10)
            }
            test("... 9 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_LESS_OR_EQUALS} and `: 10`") {
                expect {
                    fluent.isLessOrEqualFun(9)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_LESS_OR_EQUALS)
                        contains(": 9")
                    }
                }
            }
        }

        describe("$isGreaterThan ...") {
            test("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 11`") {
                expect {
                    fluent.isGreaterThanFun(11)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_GREATER_THAN)
                        contains(": 11")
                    }
                }
            }
            test("... 10 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_THAN} and `: 10`") {
                expect {
                    fluent.isGreaterThanFun(10)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_GREATER_THAN)
                        contains(": 10")
                    }
                }
            }
            test("... 9 does not throw") {
                fluent.isGreaterThanFun(9)
            }
        }

        describe("$isGreaterOrEqual ...") {
            test("... 11 throws an AssertionError containing ${DescriptionComparableAssertion::class.simpleName}.${DescriptionComparableAssertion.IS_GREATER_OR_EQUALS} and `: 11`") {
                expect {
                    fluent.isGreaterOrEqualFun(11)
                }.toThrow<AssertionError> {
                    message {
                        containsDefaultTranslationOf(DescriptionComparableAssertion.IS_GREATER_OR_EQUALS)
                        contains(": 11")
                    }
                }
            }
            test("... 10 does not throw") {
                fluent.isGreaterOrEqualFun(10)
            }
            test("... 9 does not throw") {
                fluent.isGreaterOrEqualFun(9)
            }
        }
    }

})
