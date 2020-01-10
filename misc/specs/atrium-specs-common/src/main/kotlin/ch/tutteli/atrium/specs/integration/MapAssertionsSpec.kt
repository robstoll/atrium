package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapAssertionsSpec(
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

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        contains.forSubjectLess("key" to 1, arrayOf()),
        containsKeyWithValueAssertions.forSubjectLess(
            keyValue("a") { toBe(1) },
            arrayOf(keyValue("a") { isLessThanOrEqual(2) })
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

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, mapOf("a" to 1),
        assertionCreatorSpecTriple(containsKeyWithValueAssertions.name, "$toBeDescr: 1",
            { containsKeyWithValueAssertions(this, keyValue("a") { toBe(1) }, arrayOf()) },
            { containsKeyWithValueAssertions(this, keyValue("a") { }, arrayOf()) }
        )
    ) {})
    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1),
        assertionCreatorSpecTriple(containsKeyWithNullableValueAssertions.name, "$toBeDescr: 1",
            { containsKeyWithNullableValueAssertions(this, keyNullableValue("a") { toBe(1) }, arrayOf()) },
            { containsKeyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf()) }
        )
    ) {})


    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)
    val fluent = expect(map)
    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)
    val nullableFluent = expect(nullableMap)

    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapAssertion.CONTAINS_KEY.getDefault()
    val containsNotKeyDescr = DescriptionMapAssertion.CONTAINS_NOT_KEY.getDefault()
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
                            entry("c", keyDoesNotExist),
                            "$toBeDescr: 4"
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
                "a { toBe(1) }" to listOf(keyValue("a") { toBe(1) }),
                "b { toBe(2) }" to listOf(keyValue("b") { toBe(2) }),
                "a { toBe(1) }, b { toBe(2) }" to listOf(keyValue("a") { toBe(1) }, keyValue("b") { toBe(2) }),
                "b { toBe(2) }, a { toBe(1) }" to listOf(keyValue("b") { toBe(2) }, keyValue("a") { toBe(1) })
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    fluent.containsKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            it("a { isLessThan(2) } and a { isGreaterThan(0) } does not throw (no unique match)") {
                fluent.containsKeyWithValueAssertionsFun(
                    keyValue("a") { isLessThan(2) },
                    arrayOf(keyValue("a") { isGreaterThan(0) })
                )
            }

            it("a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
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
                "a { toBe(1) }" to
                    listOf(keyNullableValue(null) { toBe(1) }),
                "b { toBe(2) }" to
                    listOf(keyNullableValue("b") { toBe(2) }),
                "(a, null), b{ toBe(2) }" to
                    listOf(keyNullableValue("a", null), keyNullableValue("b") { toBe(2) }),
                "null { toBe(1) }, b{ toBe(2) }" to
                    listOf(keyNullableValue(null) { toBe(1) }, keyNullableValue("b") { toBe(2) }),
                "null { toBe(1) }, (a, null)" to
                    listOf(keyNullableValue(null) { toBe(1) }, keyNullableValue("a", null)),
                "null { toBe(1) }, (a, null), b{ toBe(2) }" to
                    listOf(
                        keyNullableValue(null) { toBe(1) },
                        keyNullableValue("a", null),
                        keyNullableValue("b") { toBe(2) }),
                "b { toBe(2) }, null{ toBe(1) }, (a, null)" to
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

            it("b { isLessThan(3) } and b { isGreaterThan(0) } does not throw (no unique match)") {
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
                            entry("c", keyDoesNotExist),
                            "$lessThanDescr: 1"
                        )
                        containsNot(entry("a"))
                    }
                }
            }
        }
    }

    describeFun(containsKey.name) {
        val containsKeyFun = containsKey.lambda
        val fluent2 = expect(map as Map<out String, *>)

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
            expect(map2).containsNullableKeyFun(null)
        }

        it("throws an AssertionError if the map does not contain the key") {
            expect {
                val map2: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
                expect(map2).containsNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsKeyDescr: null") }
        }
    }

    describeFun(containsNotKey.name) {
        val containsNotKeyFun = containsNotKey.lambda
        val fluent2 = expect(map as Map<out String, *>)

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
            expect(map2).containsNotNullableKeyFun(null)
        }

        it("throws an AssertionError if the map contains the key") {
            expect {
                val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
                expect(map2).containsNotNullableKeyFun(null)
            }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: null") }
        }
    }


    describeFun(isEmpty.name) {
        val isEmptyFun = isEmpty.lambda

        it("does not throw if a map is empty") {
            val map2: Map<*, *> = mapOf<String, Int>()
            expect(map2).isEmptyFun()
        }

        it("throws an AssertionError if a map is not empty") {
            expect {
                expect(map as Map<*, *>).isEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
        }
    }

    describeFun(isNotEmpty.name) {
        val isNotEmptyFun = isNotEmpty.lambda

        it("does not throw if a map is not empty") {
            expect(map as Map<*, *>).isNotEmptyFun()
        }

        it("throws an AssertionError if a map is empty") {
            expect {
                val map2: Map<*, *> = mapOf<String, Int>()
                expect(map2).isNotEmptyFun()
            }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
        }
    }
})
