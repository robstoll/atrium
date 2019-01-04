package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class MapAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsKeyPair: Pair<String, Assert<Map<String, Int>>.(String) -> Assert<Map<String, Int>>>,
    hasSizePair: Pair<String, Assert<Map<String, Int>>.(Int) -> Assert<Map<String, Int>>>,
    isEmptyPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Map<String, Int>>>,
    isNotEmptyPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Map<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        containsKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(containsKeyPair.first, {containsKeyPair.second(this, "a")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, mapOf(), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, mapOf("b" to 2), mapOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(mapOf("a" to 1, "b" to 2))

    val (containsKey, containsKeyFun) = containsKeyPair
    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()

    describeFun(containsKey) {
        it("does not throw if the map contains the key") {
            assert(mapOf("a" to 1, "b" to 2)).containsKeyFun("a")
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                assert(mapOf( "b" to 2)).containsKeyFun("a")
            }.toThrow<AssertionError> { messageContains(containsKeyDescr, "a")}
        }
    }

    describeFun(hasSize) {
        context("map with two entries") {
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
        it("does not throw if a map is empty") {
            assert(mapOf()).isEmptyFun()
        }

        it("throws an AssertionError if a map is not empty") {
            expect {
                assert(mapOf("a" to 1, "b" to 2)).isEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty) {
        it("does not throw if a map is not empty") {
            assert(mapOf("a" to 1)).isNotEmptyFun()
        }

        it("throws an AssertionError if a map is empty") {
            expect {
                assert(mapOf()).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
