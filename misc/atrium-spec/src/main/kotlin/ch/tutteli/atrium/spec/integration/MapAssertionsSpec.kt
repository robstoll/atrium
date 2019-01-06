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
    containsKeyPair: Pair<String, Assert<Map<String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNullableKeyPair: Pair<String, Assert<Map<String?, *>>.(String?) -> Assert<Map<String?, *>>>,
    hasSizePair: Pair<String, Assert<Map<*, *>>.(Int) -> Assert<Map<*, *>>>,
    isEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    isNotEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        containsKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<Map<String?, Int>>("$describePrefix[nullable Key] ",
        containsNullableKeyPair.first to mapToCreateAssertion{ containsNullableKeyPair.second(this, null) }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(containsKeyPair.first, {containsKeyPair.second(this, "a")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, mapOf(), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, mapOf("b" to 2), mapOf())
    ) {})
    include(object : CheckingAssertionSpec<Map<String?, Int>>(verbs, "$describePrefix[nullable Key] ",
        checkingTriple(containsNullableKeyPair.first, {containsNullableKeyPair.second(this, null)}, mapOf("a" to 1, null to 1), mapOf<String?, Int>("b" to 1))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(mapOf("a" to 1, "b" to 2))

    val (containsKey, containsKeyFun) = containsKeyPair
    val (containsNullableKey, containsNullableKeyFun) = containsNullableKeyPair
    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()

    describeFun(containsKey) {
        it("does not throw if the map contains the key") {
            fluent.containsKeyFun("a")
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                fluent.containsKeyFun("c")
            }.toThrow<AssertionError> { messageContains("$containsKeyDescr: \"c\"")}
        }
    }

    describeFun(containsNullableKey) {
        it("does not throw if the map contains the key") {
            verbs.checkImmediately(mapOf("a" to 1, null to 2)).containsNullableKeyFun(null)
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                verbs.checkImmediately(mapOf<String?, Int>("a" to 1, "b" to 2)).containsNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsKeyDescr: null")}
        }
    }

    it("does not throw if null is passed and the map contains null as key") {
        fluent.containsKeyFun("a")
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
