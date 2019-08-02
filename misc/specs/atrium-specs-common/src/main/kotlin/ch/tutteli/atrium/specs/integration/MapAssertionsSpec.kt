package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapAssertionsSpec(
    verbs: AssertionVerbFactory,
    contains: Fun2<Map<out String, Int>, Pair<String, Int>, Array<out Pair<String, Int>>>,
    containsNullable: Fun2<Map<out String?, Int?>, Pair<String?, Int?>, Array<out Pair<String?, Int?>>>,
    containsKeyWithValueAssertions: Fun2<Map<out String, Int>, Pair<String, Expect<Int>.() -> Unit>, Array<out Pair<String, Expect<Int>.() -> Unit>>>,
    containsKeyWithNullableValueAssertions: Fun2<Map<out String?, Int?>, Pair<String?, (Expect<Int>.() -> Unit)?>, Array<out Pair<String?, (Expect<Int>.() -> Unit)?>>>,
    containsKey: Fun1<Map<out String, *>, String>,
    containsNullableKey: Fun1<Map<out String?, *>, String?>,
    containsNotKey: Fun1<Map<out String, *>, String>,
    containsNotNullableKey: Fun1<Map<out String?, *>, String?>,
    isEmpty: Fun0<Map<*, *>>,
    isNotEmpty: Fun0<Map<*, *>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun keyValue(key: String, assertionCreator: Expect<Int>.() -> Unit): Pair<String, Expect<Int>.() -> Unit> =
        key to assertionCreator

    fun keyNullableValue(
        key: String?,
        assertionCreator: (Expect<Int>.() -> Unit)?
    ): Pair<String?, (Expect<Int>.() -> Unit)?> = key to assertionCreator

    @Suppress("UNCHECKED_CAST")
    fun <K, V, T> T.unchecked1(): Pair<String, Expect<Map<out K, V>>.() -> Unit> =
        this as Pair<String, Expect<Map<out K, V>>.() -> Unit>

    @Suppress("UNCHECKED_CAST")
    fun <T, R> R.unchecked2(): Triple<String, Expect<T>.() -> Unit, Pair<T, T>> =
        this as Triple<String, Expect<T>.() -> Unit, Pair<T, T>>

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        contains.forSubjectLess("key" to 1, arrayOf()),
        containsKeyWithValueAssertions.forSubjectLess(
            keyValue("a") { toBe(1) },
            arrayOf(keyValue("a") { isLessOrEquals(2) })
        ),
        containsKey.forSubjectLess("a").unchecked1(),
        containsNotKey.forSubjectLess("a").unchecked1(),
        isEmpty.forSubjectLess().unchecked1(),
        isNotEmpty.forSubjectLess().unchecked1()
    ) {})
    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        containsNullable.forSubjectLess(null to 1, arrayOf("a" to null)),
        containsKeyWithNullableValueAssertions.forSubjectLess(
            keyNullableValue(null) { toBe(1) },
            arrayOf(keyNullableValue("a", null))
        ),
        containsNullableKey.forSubjectLess(null).unchecked1(),
        containsNotNullableKey.forSubjectLess(null).unchecked1()
    ) {})

    include(object : CheckingAssertionSpec<Map<out String, Int>>(verbs, describePrefix,
        contains.forChecking("a" to 1, arrayOf("b" to 2), mapOf("a" to 1, "b" to 2), mapOf("a" to 1, "b" to 3)),
        containsKeyWithValueAssertions.forChecking(
            keyValue("a") { isLessThan(2) }, arrayOf(keyValue("b") { isGreaterOrEquals(2) }),
            mapOf("a" to 1, "b" to 2), mapOf("a" to 2, "b" to 3)
        ),
        containsKey.forChecking("a", mapOf("a" to 1), mapOf("b" to 1)).unchecked2(),
        containsNotKey.forChecking("b", mapOf("a" to 1), mapOf("b" to 1)).unchecked2(),
        isEmpty.forChecking(mapOf<String, Int>(), mapOf("a" to 1, "b" to 2)).unchecked2(),
        isNotEmpty.forChecking(mapOf("b" to 2), mapOf<String, Int>()).unchecked2()
    ) {})
    include(object : CheckingAssertionSpec<Map<out String?, Int?>>(
        verbs, "$describePrefix[nullable Key] ",
        containsNullable.forChecking(
            null to 1, arrayOf("a" to null),
            mapOf("a" to null, null to 1), mapOf("b" to 1, null to 1)
        ),
        containsKeyWithNullableValueAssertions.forChecking(
            keyNullableValue(null) { isLessThan(2) }, arrayOf(keyNullableValue("a", null)),
            mapOf("a" to null, null to 1), mapOf("a" to null, "b" to 1, null to 3)
        ).unchecked2(),
        containsNullableKey.forChecking(
            null,
            mapOf("a" to 1, null to 1), mapOf<String?, Int?>("b" to 1)
        ).unchecked2(),
        containsNotNullableKey.forChecking(
            null,
            mapOf<String?, Int?>("a" to 1, "b" to 1), mapOf<String?, Int?>(null to 1)
        ).unchecked2()
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)
    val fluent = verbs.check(map)
    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)
    val nullableFluent = verbs.check(nullableMap)


    val isDescr = DescriptionBasic.IS.getDefault()
    val isNotDescr = DescriptionBasic.IS_NOT.getDefault()
    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()
    val containsNotKeyDescr = DescriptionMapAssertion.CONTAINS_NOT_KEY.getDefault()
    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()
    val lessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()

    fun entry(key: String): String = String.format(DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault(), "\"$key\"")

    fun entry(key: String, value: Any): String = entry(key) + ": " + value

    describeFun(contains.name) {
        val containsFun = contains.lambda
        context("map $map") {
            listOf(
                listOf("a" to 1),
                listOf("b" to 2),
                listOf("a" to 1, "b" to 2),
                listOf("b" to 2, "a" to 1)
            ).forEach {
                it("$it does not throw") {
                    fluent.containsFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            it("a to 1 and a to 1 does not throw (no unique match)") {
                fluent.containsFun("a" to 1, arrayOf("a" to 1))
            }

            it("{a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                }.toThrow<AssertionError> {
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

    describeFun("${containsNullable.name} for nullable") {
        val containsNullableFun = containsNullable.lambda
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
                it("$it does not throw") {
                    nullableFluent.containsNullableFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            it("a to null and a to null does not throw (no unique match)") {
                nullableFluent.containsNullableFun("a" to null, arrayOf("a" to null))
            }

            it("{a to null, null to 2, b to 3, c to 4} throws AssertionError, reports a, null, b and c") {
                expect {
                    nullableFluent.containsNullableFun("a" to null, arrayOf(null to 2, "b" to 3, "c" to 4))
                }.toThrow<AssertionError> {
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

    describeFun(containsKeyWithValueAssertions.name) {
        val containsKeyWithValueAssertionsFun = containsKeyWithValueAssertions.lambda
        context("map $map") {
            listOf(
                "a{ toBe(1) }" to listOf(keyValue("a") { toBe(1) }),
                "b{ toBe(2) }" to listOf(keyValue("b") { toBe(2) }),
                "a{ toBe(1) }, b{ toBe(2) }" to listOf(keyValue("a") { toBe(1) }, keyValue("b") { toBe(2) }),
                "b{ toBe(2) }, a{ toBe(1) }" to listOf(keyValue("b") { toBe(2) }, keyValue("a") { toBe(1) })
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    fluent.containsKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            it("a{ isLessThan(2) } and a{ isGreaterThan(0) } does not throw (no unique match)") {
                fluent.containsKeyWithValueAssertionsFun(
                    keyValue("a") { isLessThan(2) },
                    arrayOf(keyValue("a") { isGreaterThan(0) })
                )
            }

            it("a{ isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    fluent.containsKeyWithValueAssertionsFun(
                        keyValue("a") { isLessThan(3) },
                        arrayOf(keyValue("b") { isLessThan(2) }, keyValue("c") { isLessThan(1) })
                    )
                }.toThrow<AssertionError> {
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

    describeFun("${containsKeyWithNullableValueAssertions.name} for nullable") {
        val containsKeyWithNullableValueAssertionsFun = containsKeyWithNullableValueAssertions.lambda
        context("map $nullableMap") {
            listOf(
                "(a, null)" to
                    listOf(keyNullableValue("a", null)),
                "a{ toBe(1) }" to
                    listOf(keyNullableValue(null) { toBe(1) }),
                "b{ toBe(2) }" to
                    listOf(keyNullableValue("b") { toBe(2) }),
                "(a, null), b{ toBe(2) }" to
                    listOf(keyNullableValue("a", null), keyNullableValue("b") { toBe(2) }),
                "null{ toBe(1) }, b{ toBe(2) }" to
                    listOf(keyNullableValue(null) { toBe(1) }, keyNullableValue("b") { toBe(2) }),
                "null{ toBe(1) }, (a, null)" to
                    listOf(keyNullableValue(null) { toBe(1) }, keyNullableValue("a", null)),
                "null{ toBe(1) }, (a, null), b{ toBe(2) }" to
                    listOf(
                        keyNullableValue(null) { toBe(1) },
                        keyNullableValue("a", null),
                        keyNullableValue("b") { toBe(2) }),
                "b{ toBe(2) }, null{ toBe(1) }, (a, null)" to
                    listOf(
                        keyNullableValue("b") { toBe(2) },
                        keyNullableValue(null) { toBe(1) },
                        keyNullableValue("a", null)
                    )
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(
                        keyValues.first(),
                        keyValues.drop(1).toTypedArray()
                    )
                }
            }

            it("b{ isLessThan(3) } and b{ isGreaterThan(0) } does not throw (no unique match)") {
                nullableFluent.containsKeyWithNullableValueAssertionsFun(
                    keyNullableValue("b") { isLessThan(3) },
                    arrayOf(keyNullableValue("b") { isGreaterThan(0) })
                )
            }

            it("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.containsKeyWithNullableValueAssertionsFun(
                        keyNullableValue("a", null), arrayOf(
                            keyNullableValue("b") { isLessThan(2) },
                            keyNullableValue("c") { isLessThan(1) }
                        )
                    )
                }.toThrow<AssertionError> {
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

    describeFun(containsKey.name) {
        val containsKeyFun = containsKey.lambda
        val fluent2 = verbs.check(map as Map<out String, *>)

        it("does not throw if the map contains the key") {
            fluent2.containsKeyFun("a")
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                fluent2.containsKeyFun("c")
            }.toThrow<AssertionError> { messageContains("$containsKeyDescr: \"c\"") }
        }

        it("does not throw if null is passed and the map contains null as key") {
            fluent2.containsKeyFun("a")
        }
    }

    describeFun("$containsNullableKey for nullable key type") {
        val containsNullableKeyFun = containsNullableKey.lambda
        it("does not throw if the map contains the key") {
            val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
            verbs.check(map2).containsNullableKeyFun(null)
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                val map2: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
                verbs.check(map2).containsNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsKeyDescr: null") }
        }
    }

    describeFun(containsNotKey.name) {
        val containsNotKeyFun = containsNotKey.lambda
        val fluent2 = verbs.check(map as Map<out String, *>)

        it("does not throw if the map does not contain the key") {
            fluent2.containsNotKeyFun("c")
        }

        it("throws an AssertionError if the map contains the key") {
            expect {
                fluent2.containsNotKeyFun("a")
            }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: \"a\"") }
        }
    }

    describeFun("${containsNotNullableKey.name} for nullable key type") {
        val containsNotNullableKeyFun = containsNotNullableKey.lambda

        it("does not throw if the map does not contain the key") {
            val map2: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
            verbs.check(map2).containsNotNullableKeyFun(null)
        }

        it("throws an AssertionError if the map contains the key") {
            expect {
                val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
                verbs.check(map2).containsNotNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: null") }
        }
    }


    describeFun(isEmpty.name) {
        val isEmptyFun = isEmpty.lambda

        it("does not throw if a map is empty") {
            val map2: Map<*, *> = mapOf<String, Int>()
            verbs.check(map2).isEmptyFun()
        }

        it("throws an AssertionError if a map is not empty") {
            expect {
                verbs.check(map as Map<*, *>).isEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty.name) {
        val isNotEmptyFun = isNotEmpty.lambda

        it("does not throw if a map is not empty") {
            verbs.check(map as Map<*, *>).isNotEmptyFun()
        }

        it("throws an AssertionError if a map is empty") {
            expect {
                val map2: Map<*, *> = mapOf<String, Int>()
                verbs.check(map2).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
