@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include


@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class MapAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Map<out String, Int>>.(Pair<String, Int>, Array<out Pair<String, Int>>) -> Assert<Map<out String, Int>>>,
    containsNullablePair: Pair<String, Assert<Map<out String?, Int?>>.(Pair<String?, Int?>, Array<out Pair<String?, Int?>>) -> Assert<Map<out String?, Int?>>>,
    containsKeyWithValueAssertionsPair: Pair<String, Assert<Map<out String, Int>>.(Pair<String, Assert<Int>.() -> Unit>, Array<out Pair<String, Assert<Int>.() -> Unit>>) -> Assert<Map<out String, Int>>>,
    containsKeyWithNullableValueAssertionsPair: Pair<String, Assert<Map<out String?, Int?>>.(Pair<String?, (Assert<Int>.() -> Unit)?>, Array<out Pair<String?, (Assert<Int>.() -> Unit)?>>) -> Assert<Map<out String?, Int?>>>,
    containsKeyPair: Pair<String, Assert<Map<out String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNullableKeyPair: Pair<String, Assert<Map<out String?, *>>.(String?) -> Assert<Map<out String?, *>>>,
    containsNotKeyPair: Pair<String, Assert<Map<out String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNotNullableKeyPair: Pair<String, Assert<Map<out String?, *>>.(String?) -> Assert<Map<out String?, *>>>,
    hasSizePair: Pair<String, Assert<Map<*, *>>.(Int) -> Assert<Map<*, *>>>,
    isEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    isNotEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun keyValue(key: String, assertionCreator: Assert<Int>.() -> Unit): Pair<String, Assert<Int>.() -> Unit>
        = key to assertionCreator

    fun keyNullableValue(key: String?, assertionCreator: (Assert<Int>.() -> Unit)?): Pair<String?, (Assert<Int>.() -> Unit)?>
        = key to assertionCreator

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Map<out String, Int>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, "key" to 1, arrayOf()) },
        containsKeyWithValueAssertionsPair.first to mapToCreateAssertion { containsKeyWithValueAssertionsPair.second(this, keyValue("a") { toBe(1) }, arrayOf(keyValue("a") { isLessOrEquals(2) })) },
        containsKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        containsNotKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Map<out String?, Int?>>("$describePrefix[nullable Key] ",
        containsNullablePair.first to mapToCreateAssertion{ containsNullablePair.second(this, null to 1, arrayOf("a" to null)) },
        containsKeyWithNullableValueAssertionsPair.first to mapToCreateAssertion { containsKeyWithNullableValueAssertionsPair.second(this, keyNullableValue(null) { toBe(1) }, arrayOf(keyNullableValue("a", null))) },
        containsNullableKeyPair.first to mapToCreateAssertion{ containsNullableKeyPair.second(this, null) },
        containsNotNullableKeyPair.first to mapToCreateAssertion{ containsNotNullableKeyPair.second(this, null) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, "a" to 1, arrayOf("b" to 2)) }, mapOf("a" to 1, "b" to 2), mapOf("a" to 1, "b" to 3)),
        checkingTriple(containsKeyWithValueAssertionsPair.first,
            { containsKeyWithValueAssertionsPair.second(this, keyValue("a"){ isLessThan(2) }, arrayOf(keyValue("b") { isGreaterOrEquals(2) })) },
            mapOf("a" to 1, "b" to 2), mapOf("a" to 2, "b" to 3)
        ),
        checkingTriple(containsKeyPair.first, {containsKeyPair.second(this, "a")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(containsNotKeyPair.first, {containsNotKeyPair.second(this, "b")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, mapOf(), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, mapOf("b" to 2), mapOf())
    ) {})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Map<String?, Int?>>(verbs, "$describePrefix[nullable Key] ",
        checkingTriple(containsNullablePair.first,
            { containsNullablePair.second(this, null to 1, arrayOf("a" to null))},
            mapOf("a" to null, null to 1), mapOf("b" to 1, null to 1)
        ),
        checkingTriple(containsKeyWithNullableValueAssertionsPair.first,
            { containsKeyWithNullableValueAssertionsPair.second(this, keyNullableValue(null){ isLessThan(2) }, arrayOf(keyNullableValue("a", null))) },
            mapOf("a" to null, null to 1), mapOf("a" to null, "b" to 1, null to 3)
        ),
        checkingTriple(containsNullableKeyPair.first,
            { containsNullableKeyPair.second(this, null)},
            mapOf("a" to 1, null to 1), mapOf<String?, Int?>("b" to 1)
        ),
        checkingTriple(containsNotNullableKeyPair.first,
            { containsNotNullableKeyPair.second(this, null)},
            mapOf<String?, Int?>("a" to 1, "b" to 1), mapOf<String?, Int?>(null to 1)
        )
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Map<out String, Int>) -> Assert<Map<out String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val map = mapOf("a" to 1, "b" to 2)
    val fluent = assert(map)
    val nullableMap = mapOf("a" to null, null to 1, "b" to 2)
    val nullableFluent = verbs.checkImmediately(nullableMap)

    val (contains, containsFun) = containsPair
    val (containsNullable, containsNullableFun) = containsNullablePair
    val (containsKeyWithValueAssertions, containsKeyWithValueAssertionsFun) = containsKeyWithValueAssertionsPair
    val (containsKeyWithNullableValueAssertions, containsKeyWithNullableValueAssertionsFun) = containsKeyWithNullableValueAssertionsPair
    val (containsKey, containsKeyFun) = containsKeyPair
    val (containsNullableKey, containsNullableKeyFun) = containsNullableKeyPair
    val (containsNotKey, containsNotKeyFun) = containsNotKeyPair
    val (containsNotNullableKey, containsNotNullableKeyFun) = containsNotNullableKeyPair
    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()
    val containsNotKeyDescr = DescriptionMapAssertion.CONTAINS_NOT_KEY.getDefault()
    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()
    val lessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()

    fun entry(key: String): String
        = String.format(DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault(), "\"$key\"")

    fun entry(key: String, value: Any): String
        = entry(key) + ": " + value

    describeFun(contains) {
        context("map $map") {
            listOf(
                listOf("a" to 1),
                listOf("b" to 2),
                listOf("a" to 1, "b" to 2),
                listOf("b" to 2, "a" to 1)
            ).forEach {
                test("$it does not throw") {
                    fluent.containsFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            test("a to 1 and a to 1 does not throw (no unique match)") {
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
                        containsNot(entry("a"))
                    }
                }
            }
        }
    }

    describeFun("$containsNullable for nullable") {
        context("map $nullableMap") {
            listOf(
                listOf("a" to null),
                listOf(null to 1),
                listOf("b" to 2),
                listOf("a" to null, "b" to 2),
                listOf(null to 1, "b" to 2),
                listOf(null to 1, "a" to null),
                listOf(null to 1, "a" to null, "b" to 2),
                listOf("b" to 2, null to 1, "a" to null)
            ).forEach {
                test("$it does not throw") {
                    nullableFluent.containsNullableFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            test("a to null and a to null does not throw (no unique match)") {
                nullableFluent.containsNullableFun("a" to null, arrayOf("a" to null))
            }

            test("{a to null, null to 2, b to 3, c to 4} throws AssertionError, reports a, null, b and c") {
                expect {
                    nullableFluent.containsNullableFun("a" to null, arrayOf(null to 2, "b" to 3, "c" to 4))
                }.toThrow<AssertionError>{
                    message {
                        contains(
                            entry("b", 2),
                            "$toBeDescr: 3",
                            entry("c", keyDoesNotExist)
                            //TODO seems like notToBeNull is not subjectLess
                            //"$toBeDescr: 4"
                        )
                        containsNot(entry("a"))
                    }
                }
            }
        }
    }

    describeFun(containsKeyWithValueAssertions) {
        context("map $map") {
            listOf(
                "a{ toBe(1) }" to listOf(keyValue("a") { toBe(1) } ),
                "b{ toBe(2) }" to listOf(keyValue("b") { toBe(2) } ),
                "a{ toBe(1) }, b{ toBe(2) }" to listOf(keyValue("a") { toBe(1) }, keyValue("b"){ toBe(2) }) ,
                "b{ toBe(2) }, a{ toBe(1) }" to listOf(keyValue("b") { toBe(2) }, keyValue("a"){ toBe(1) })
            ).forEach { (description, keyValues) ->
                test("$description does not throw") {
                    fluent.containsKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            test("a{ isLessThan(2) } and a{ isGreaterThan(0) } does not throw (no unique match)") {
                fluent.containsKeyWithValueAssertionsFun(keyValue("a") { isLessThan(2) }, arrayOf(keyValue("a"){ isGreaterThan(0) }))
            }

            test("a{ isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsKeyWithValueAssertionsFun(keyValue("a") { isLessThan(3) }, arrayOf(keyValue("b"){ isLessThan(2) }, keyValue("c") { isLessThan(1) }))
                }.toThrow<AssertionError>{
                    message {
                        contains(
                            entry("b", 2),
                            "$lessThanDescr: 2",
                            entry("c", keyDoesNotExist),
                            "$lessThanDescr: 1"
                        )
                        containsNot(entry("a"))
                    }
                }
            }
        }
    }

    describeFun("$containsKeyWithNullableValueAssertions for nullable") {
        context("map $nullableMap") {
            listOf(
                "(a, null)" to
                    listOf(keyNullableValue("a" , null)),
                "a{ toBe(1) }" to
                    listOf(keyNullableValue(null){ toBe(1) }),
                "b{ toBe(2) }" to
                    listOf(keyNullableValue("b"){ toBe(2) }),
                "(a, null), b{ toBe(2) }" to
                    listOf(keyNullableValue("a", null), keyNullableValue("b"){ toBe(2) }),
                "null{ toBe(1) }, b{ toBe(2) }" to
                    listOf(keyNullableValue(null){ toBe(1) }, keyNullableValue("b"){ toBe(2)}),
                "null{ toBe(1) }, (a, null)" to
                    listOf(keyNullableValue(null){ toBe(1) }, keyNullableValue("a", null)),
                "null{ toBe(1) }, (a, null), b{ toBe(2) }" to
                    listOf(keyNullableValue(null ){ toBe(1) }, keyNullableValue("a", null), keyNullableValue("b") { toBe(2) }),
                "b{ toBe(2) }, null{ toBe(1) }, (a, null)" to
                    listOf(keyNullableValue("b"){ toBe(2) }, keyNullableValue(null){ toBe(1)}, keyNullableValue("a", null))
            ).forEach { (description, keyValues) ->
                test("$description does not throw") {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            test("b{ isLessThan(3) } and b{ isGreaterThan(0) } does not throw (no unique match)") {
                nullableFluent.containsKeyWithNullableValueAssertionsFun(
                    keyNullableValue("b") { isLessThan(3) },
                    arrayOf(keyNullableValue("b"){ isGreaterThan(0) })
                )
            }

            test("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(
                        keyNullableValue("a", null), arrayOf(
                            keyNullableValue("b"){ isLessThan(2) },
                            keyNullableValue("c") { isLessThan(1) }
                        )
                    )
                }.toThrow<AssertionError>{
                    message {
                        contains(
                            entry("b", 2),
                            "$lessThanDescr: 2",
                            entry("c", keyDoesNotExist)
                            //TODO seems like notToBeNull is not subjectLess
                            //"$lessThanDescr: 1"
                        )
                        containsNot(entry("a"))
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

    describeFun("$containsNullableKey for nullable key type") {
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

    describeFun(containsNotKey) {
        it("does not throw if the map does not contain the key") {
            fluent.containsNotKeyFun("c")
        }

        it("throws an AssertionError if the map contains the key") {
            expect {
                fluent.containsNotKeyFun("a")
            }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: \"a\"")}
        }
    }

    describeFun("$containsNotNullableKey for nullable key type") {
        it("does not throw if the map does not contain the key") {
            verbs.checkImmediately(mapOf<String?, Int>("a" to 1, "b" to 2)).containsNotNullableKeyFun(null)
        }

        it("throws an AssertionError if the map contains the key") {
            expect {
                verbs.checkImmediately(mapOf("a" to 1, null to 2)).containsNotNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: null")}
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
