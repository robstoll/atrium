@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class CollectionAssertionsSpec(
    verbs: AssertionVerbFactory,
    hasSizePair: Pair<String, Assert<Collection<Int>>.(Int) -> Assert<Collection<Int>>>,
    isEmptyPair: Pair<String, Assert<Collection<Int>>.() -> Assert<Collection<Int>>>,
    isNotEmptyPair: Pair<String, Assert<Collection<Int>>.() -> Assert<Collection<Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Collection<Int>>(describePrefix,
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Collection<Int>>(verbs, describePrefix,
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, listOf(1) as Collection<Int>, listOf(1, 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, listOf<Int>() as Collection<Int>, listOf(1, 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, listOf(2) as Collection<Int>, listOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) = describeFun(describePrefix, funName, body = body)

    val assert: (List<Int>) -> Assert<List<Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(listOf(1, 2))

    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    val isDescr = IS.getDefault()
    val isNotDescr = IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()

    describeFun(hasSize) {
        context("collection with two entries") {
            test("expect 2 does not throw") {
                fluent.hasSizeFun(2)
            }
            test("expect 1 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(1)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", TO_BE.getDefault() + ": 1")
                }
            }
            test("expect 3 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(3)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", TO_BE.getDefault() + ": 3")
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
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty) {
        it("does not throw if a collection is not empty") {
            assert(listOf(1)).isNotEmptyFun()
        }

        it("throws an AssertionError if a collection is empty") {
            expect {
                assert(listOf()).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
