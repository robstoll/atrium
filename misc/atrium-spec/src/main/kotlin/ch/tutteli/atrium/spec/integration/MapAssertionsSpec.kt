package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.creating.map.KeyNullableValue
import ch.tutteli.atrium.domain.creating.map.KeyValue
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class MapAssertionsSpec(
    verbs: AssertionVerbFactory,
    containsPair: Pair<String, Assert<Map<String, Int>>.(Pair<String, Int>, Array<out Pair<String, Int>>) -> Assert<Map<String, Int>>>,
    containsNullablePair: Pair<String, Assert<Map<String?, Int?>>.(Pair<String?, Int?>, Array<out Pair<String?, Int?>>) -> Assert<Map<String?, Int?>>>,
    containsInAnyOrderOnlyPair: Pair<String, Assert<Map<String, Int>>.(Pair<String, Int>, Array<out Pair<String, Int>>) -> Assert<Map<String, Int>>>,
    containsInAnyOrderOnlyNullablePair: Pair<String, Assert<Map<String?, Int?>>.(Pair<String?, Int?>, Array<out Pair<String?, Int?>>) -> Assert<Map<String?, Int?>>>,
    containsKeyWithValueAssertionsPair: Pair<String, Assert<Map<String, Int>>.(KeyValue<String, Int>, Array<out KeyValue<String, Int>>) -> Assert<Map<String, Int>>>,
    containsKeyWithNullableValueAssertionsPair: Pair<String, Assert<Map<String?, Int?>>.(KeyNullableValue<String?, Int>, Array<out KeyNullableValue<String?, Int>>) -> Assert<Map<String?, Int?>>>,
    containsKeyPair: Pair<String, Assert<Map<String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNullableKeyPair: Pair<String, Assert<Map<String?, *>>.(String?) -> Assert<Map<String?, *>>>,
    containsNotKeyPair: Pair<String, Assert<Map<String, *>>.(String) -> Assert<Map<*, *>>>,
    containsNotNullableKeyPair: Pair<String, Assert<Map<String?, *>>.(String?) -> Assert<Map<String?, *>>>,
    hasSizePair: Pair<String, Assert<Map<*, *>>.(Int) -> Assert<Map<*, *>>>,
    isEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    isNotEmptyPair: Pair<String, Assert<Map<*, *>>.() -> Assert<Map<*, *>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        containsPair.first to mapToCreateAssertion { containsPair.second(this, "key" to 1, arrayOf()) },
        containsInAnyOrderOnlyPair.first to mapToCreateAssertion { containsInAnyOrderOnlyPair.second(this, "key" to 1, arrayOf()) },
        containsKeyWithValueAssertionsPair.first to mapToCreateAssertion { containsKeyWithValueAssertionsPair.second(this, KeyValue("a") { toBe(1) }, arrayOf(KeyValue("a") { isLessOrEquals(2) })) },
        containsKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        containsNotKeyPair.first to mapToCreateAssertion{ containsKeyPair.second(this, "a") },
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})
    include(object : SubjectLessAssertionSpec<Map<String?, Int?>>("$describePrefix[nullable Key] ",
        containsNullablePair.first to mapToCreateAssertion{ containsNullablePair.second(this, null to 1, arrayOf("a" to null)) },
        containsInAnyOrderOnlyNullablePair.first to mapToCreateAssertion{ containsInAnyOrderOnlyNullablePair.second(this, null to 1, arrayOf("a" to null)) },
        containsKeyWithNullableValueAssertionsPair.first to mapToCreateAssertion { containsKeyWithNullableValueAssertionsPair.second(this, KeyNullableValue(null) { toBe(1) }, arrayOf(KeyNullableValue("a", null))) },
        containsNullableKeyPair.first to mapToCreateAssertion{ containsNullableKeyPair.second(this, null) },
        containsNotNullableKeyPair.first to mapToCreateAssertion{ containsNotNullableKeyPair.second(this, null) }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(containsPair.first, { containsPair.second(this, "a" to 1, arrayOf("b" to 2)) }, mapOf("a" to 1, "b" to 2), mapOf("a" to 1, "b" to 3)),
        checkingTriple(containsInAnyOrderOnlyPair.first, { containsInAnyOrderOnlyPair.second(this, "a" to 1, arrayOf("b" to 2)) }, mapOf("a" to 1, "b" to 2), mapOf("a" to 1, "b" to 3, "c" to 4)),
        checkingTriple(containsKeyWithValueAssertionsPair.first,
            { containsKeyWithValueAssertionsPair.second(this, KeyValue("a"){ isLessThan(2) }, arrayOf(KeyValue("b") { isGreaterOrEquals(2) })) },
            mapOf("a" to 1, "b" to 2), mapOf("a" to 2, "b" to 3)
        ),
        checkingTriple(containsKeyPair.first, {containsKeyPair.second(this, "a")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(containsNotKeyPair.first, {containsNotKeyPair.second(this, "b")}, mapOf("a" to 1), mapOf("b" to 1)),
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, mapOf(), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, mapOf("b" to 2), mapOf())
    ) {})
    include(object : CheckingAssertionSpec<Map<String?, Int?>>(verbs, "$describePrefix[nullable Key] ",
        checkingTriple(containsNullablePair.first,
            { containsNullablePair.second(this, null to 1, arrayOf("a" to null))},
            mapOf("a" to null, null to 1), mapOf("b" to 1, null to 1)
        ),
        checkingTriple(containsInAnyOrderOnlyNullablePair.first,
            { containsInAnyOrderOnlyNullablePair.second(this, null to 1, arrayOf("a" to null))},
            mapOf("a" to null, null to 1), mapOf("b" to 1, null to 2, "c" to 3)
        ),
        checkingTriple(containsKeyWithNullableValueAssertionsPair.first,
            { containsKeyWithNullableValueAssertionsPair.second(this, KeyNullableValue(null){ isLessThan(2) }, arrayOf(KeyNullableValue("a", null))) },
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

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val map = mapOf("a" to 1, "b" to 2)
    val fluent = assert(map)
    val nullableMap = mapOf("a" to null, null to 1, "b" to 2)
    val nullableFluent = verbs.checkImmediately(nullableMap)

    val (contains, containsFun) = containsPair
    val (containsNullable, containsNullableFun) = containsNullablePair
    val (containsInAnyOrderOnly, containsInAnyOrderOnlyFun) = containsInAnyOrderOnlyPair
    val (containsInAnyOrderOnlyNullable, containsInAnyOrderOnlyNullableFun) = containsInAnyOrderOnlyNullablePair
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
    val onlyDescr = DescriptionMapAssertion.MAP_CONTAINS_ONLY.getDefault()
    val keyMismatchDescr = DescriptionMapAssertion.MAP_KEYS_MISMATCH.getDefault()

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

    describeFun(containsNullable) {
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

    describeFun(containsInAnyOrderOnly) {
        context("map $map") {
            listOf(
                listOf("a" to 1, "b" to 2),
                listOf("b" to 2, "a" to 1)
            ).forEach {
                test("$it does not throw") {
                    fluent.containsInAnyOrderOnlyFun(it.first(), it.drop(1).toTypedArray())
                }
            }
            test("{a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsInAnyOrderOnlyFun("a" to 1, arrayOf("b" to 3, "c" to 4))
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
            test("{a to 1} throws AssertionError, reports keys mismatch") {
                expect {
                    fluent.containsInAnyOrderOnlyFun("a" to 1, arrayOf())
                }.toThrow<AssertionError>{
                    messageContains(onlyDescr)
                    messageContains("$keyMismatchDescr: \"b\"")
                }
            }
        }
    }

    describeFun(containsInAnyOrderOnlyNullable) {
        context("map $nullableMap") {
            listOf(
                listOf(null to 1, "a" to null, "b" to 2),
                listOf("b" to 2, null to 1, "a" to null)
            ).forEach {
                test("$it does not throw") {
                    nullableFluent.containsInAnyOrderOnlyNullableFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            test("{a to null, null to 2, b to 3, c to 4} throws AssertionError, reports a, null, b and c") {
                expect {
                    nullableFluent.containsInAnyOrderOnlyNullableFun("a" to null, arrayOf(null to 2, "b" to 3, "c" to 4))
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
            test("{a to null, null to 2} throws AssertionError,  reports keys mismatch") {
                expect {
                    nullableFluent.containsInAnyOrderOnlyNullableFun("a" to null, arrayOf(null to 1))
                }.toThrow<AssertionError>{
                    messageContains(onlyDescr)
                    messageContains("$keyMismatchDescr: \"b\"")
                }
            }

            test("{a to null, null to 2} throws AssertionError,  reports keys mismatch") {
                expect {
                    nullableFluent.containsInAnyOrderOnlyNullableFun("a" to null, arrayOf("b" to 2))
                }.toThrow<AssertionError>{
                    messageContains(onlyDescr)
                    messageContains("$keyMismatchDescr: \"null\"")
                }
            }
        }
    }

    describeFun(containsKeyWithValueAssertions) {
        context("map $map") {
            listOf<Pair<String, List<KeyValue<String, Int>>>>(
                "a{ toBe(1) }" to listOf(KeyValue("a") { toBe(1) } ),
                "b{ toBe(2) }" to listOf(KeyValue("b") { toBe(2) } ),
                "a{ toBe(1) }, b{ toBe(2) }" to listOf(KeyValue("a") { toBe(1) }, KeyValue("b"){ toBe(2) }) ,
                "b{ toBe(2) }, a{ toBe(1) }" to listOf(KeyValue("b") { toBe(2) }, KeyValue("a"){ toBe(1) })
            ).forEach { (description, keyValues) ->
                test("$description does not throw") {
                    fluent.containsKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            test("a{ isLessThan(2) } and a{ isGreaterThan(0) } does not throw (no unique match)") {
                fluent.containsKeyWithValueAssertionsFun(KeyValue("a") { isLessThan(2) }, arrayOf(KeyValue("a"){ isGreaterThan(0) }))
            }

            test("a{ isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsKeyWithValueAssertionsFun(KeyValue("a") { isLessThan(3) }, arrayOf(KeyValue("b"){ isLessThan(2) }, KeyValue("c") { isLessThan(1) }))
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

    describeFun(containsKeyWithNullableValueAssertions) {
        context("map $nullableMap") {
            listOf<Pair<String, List<KeyNullableValue<String?, Int>>>>(
                "(a, null)" to
                    listOf(KeyNullableValue("a" , null)),
                "a{ toBe(1) }" to
                    listOf(KeyNullableValue(null){ toBe(1) }),
                "b{ toBe(2) }" to
                    listOf(KeyNullableValue("b"){ toBe(2) }),
                "(a, null), b{ toBe(2) }" to
                    listOf(KeyNullableValue("a", null), KeyNullableValue("b"){ toBe(2) }),
                "null{ toBe(1) }, b{ toBe(2) }" to
                    listOf(KeyNullableValue(null){ toBe(1) }, KeyNullableValue("b"){ toBe(2)}),
                "null{ toBe(1) }, (a, null)" to
                    listOf(KeyNullableValue(null){ toBe(1) }, KeyNullableValue("a", null)),
                "null{ toBe(1) }, (a, null), b{ toBe(2) }" to
                    listOf(KeyNullableValue(null ){ toBe(1) }, KeyNullableValue("a", null), KeyNullableValue("b") { toBe(2) }),
                "b{ toBe(2) }, null{ toBe(1) }, (a, null)" to
                    listOf(KeyNullableValue("b"){ toBe(2) }, KeyNullableValue(null){ toBe(1)}, KeyNullableValue("a", null))
            ).forEach { (description, keyValues) ->
                test("$description does not throw") {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            test("b{ isLessThan(3) } and b{ isGreaterThan(0) } does not throw (no unique match)") {
                nullableFluent.containsKeyWithNullableValueAssertionsFun(
                    KeyNullableValue("b") { isLessThan(3) },
                    arrayOf(KeyNullableValue("b"){ isGreaterThan(0) })
                )
            }

            test("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(
                        KeyNullableValue("a", null), arrayOf(
                            KeyNullableValue("b"){ isLessThan(2) },
                            KeyNullableValue("c") { isLessThan(1) }
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

    describeFun(containsNotNullableKey) {
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
