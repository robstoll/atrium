package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
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
    containsPair: Pair<String, Assert<Map<String, Int>>.(Pair<String, Int>, Array<out Pair<String, Int>>) -> Assert<Map<String, Int>>>,
    containsKeyPair: Pair<String, Assert<Map<String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNullableKeyPair: Pair<String, Assert<Map<String?, *>>.(String?) -> Assert<Map<String?, *>>>,
    hasSizePair: Pair<String, Assert<Map<*, *>>.(Int) -> Assert<Map<*, *>>>,
    isEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    isNotEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, "key" to 1, arrayOf()) },
        containsKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<Map<String?, Int>>("$describePrefix[nullable Key] ",
        containsNullableKeyPair.first to mapToCreateAssertion{ containsNullableKeyPair.second(this, null) }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, "a" to 1, arrayOf("b" to 2)) }, mapOf("a" to 1, "b" to 2), mapOf("a" to 1, "b" to 3)),
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
    val map = mapOf("a" to 1, "b" to 2)
    val fluent = assert(map)

    val (contains, containsFun) = containsPair
    val (containsKey, containsKeyFun) = containsKeyPair
    val (containsNullableKey, containsNullableKeyFun) = containsNullableKeyPair
    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()
    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()
    val entryDescr = DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault()

    fun entry(key: String, value: Any): String {

        return String.format(entryDescr, "\"$key\"") + ": " + value
    }

    describeFun(contains) {
        context("map $map") {
            test("a to 1 does not throw") {
                fluent.containsFun("a" to 1, arrayOf())
            }
            test("b to 2 does not throw") {
                fluent.containsFun("b" to 2, arrayOf())
            }
            test("a to 1 and b to 2 does not throw") {
                fluent.containsFun("a" to 1, arrayOf("b" to 2))
            }
            test("b to 2 and a to 1 does not throw") {
                fluent.containsFun("b" to 2, arrayOf("a" to 1))
            }

            test("a to 1 and a to 1 does not throw (ignores duplicates)") {
                fluent.containsFun("a" to 1, arrayOf("a" to 1))
            }

            test("{a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                }.toThrow<AssertionError>{
                    message {
                        contains(
                            entry("b", 2),
                            "$toBeDescr: 3",
                            entry("c", keyDoesNotExist),
                            "$toBeDescr: 4"
                        )
                        containsNot("$entryDescr a")
                    }
                }
            }
        }
    }

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
                fluent.isEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty) {
        it("does not throw if a map is not empty") {
            fluent.isNotEmptyFun()
        }

        it("throws an AssertionError if a map is empty") {
            expect {
                assert(mapOf()).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
