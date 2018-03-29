package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_UK.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.messageContains
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class CollectionAssertionsSpec(
    verbs: AssertionVerbFactory,
    hasSizePair: Pair<String, Assert<List<Int>>.(Int) -> Assert<List<Int>>>,
    isEmptyPair: Pair<String, Assert<List<Int>>.() -> Assert<List<Int>>>,
    isNotEmptyPair: Pair<String, Assert<List<Int>>.() -> Assert<List<Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<List<Int>>(describePrefix,
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})

    include(object : CheckingAssertionSpec<List<Int>>(verbs, describePrefix,
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, listOf(1), listOf(1, 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, listOf(), listOf(1, 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, listOf(2), listOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (List<Int>) -> Assert<List<Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(listOf(1, 2))

    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    describeFun(hasSize) {
        context("collection with two entries") {
            test("expect 2 does not throw") {
                fluent.hasSizeFun(2)
            }
            test("expect 1 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(1)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", DescriptionAnyAssertion.TO_BE.getDefault() + ": 1")
                }
            }
            test("expect 3 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(3)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", DescriptionAnyAssertion.TO_BE.getDefault() + ": 3")
                }
            }
        }
    }

    describeFun(isEmpty) {
        it("does not throw if a collection is empty") {
            assert(listOf()).isEmptyFun()
        }

        it("throws an AssertionError if a collection is not empty") {
            expect {
                assert(listOf(1, 2)).isEmptyFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionBasic.IS, DescriptionCollectionAssertion.EMPTY)
                }
            }
        }
    }

    describeFun(isNotEmpty) {
        it("does not throw if a collection is not empty") {
            assert(listOf(1)).isNotEmptyFun()
        }

        it("throws an AssertionError if a collection is empty") {
            expect {
                assert(listOf()).isNotEmptyFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionBasic.IS_NOT, DescriptionCollectionAssertion.EMPTY)
                }
            }
        }
    }
})
