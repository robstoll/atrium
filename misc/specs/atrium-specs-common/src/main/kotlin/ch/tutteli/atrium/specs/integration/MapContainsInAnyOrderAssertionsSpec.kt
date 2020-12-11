package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.style.specification.Suite
import kotlin.reflect.KFunction3

typealias MFun2<K, V, T> = Fun2<Map<out K, V>, Pair<K, T>, Array<out Pair<K, T>>>

fun <K, V, T> mfun2(
    f: KFunction3<Expect<Map<out K, V>>, Pair<K, T>, Array<out Pair<K, T>>, Expect<Map<out K, V>>>
) = fun2(f)

fun keyValue(key: String, assertionCreator: Expect<Int>.() -> Unit): Pair<String, Expect<Int>.() -> Unit> =
    key to assertionCreator

fun keyNullableValue(
    key: String?,
    assertionCreator: (Expect<Int>.() -> Unit)?
): Pair<String?, (Expect<Int>.() -> Unit)?> = key to assertionCreator

abstract class MapContainsInAnyOrderAssertionsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    keyWithValueAssertions: MFun2<String, Int, Expect<Int>.() -> Unit>,
    keyWithNullableValueAssertions: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : MapLikeContainsSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyValuePairs.forSubjectLess("key" to 1, arrayOf()),
        keyWithValueAssertions.forSubjectLess(
            keyValue("a") { toBe(1) },
            arrayOf(keyValue("a") { isLessThanOrEqual(2) })
        )
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyValuePairsNullable.forSubjectLess(null to 1, arrayOf("a" to null)),
        keyWithNullableValueAssertions.forSubjectLess(
            keyNullableValue(null) { toBe(1) },
            arrayOf(keyNullableValue("a", null))
        )
    ) {})

    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        assertionCreatorSpecTriple(keyWithValueAssertions.name, "$toBeDescr: 1",
            { keyWithValueAssertions(this, keyValue("a") { toBe(1) }, arrayOf()) },
            { keyWithValueAssertions(this, keyValue("a") { }, arrayOf()) }
        )
    ) {})

    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        assertionCreatorSpecTriple(keyWithNullableValueAssertions.name, "$toBeDescr: 1",
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { toBe(1) }, arrayOf()) },
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf()) }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(map)
    val nullableFluent = expect(nullableMap)

    fun entry(key: String): String = String.format(DescriptionMapAssertion.ENTRY_WITH_KEY.getDefault(), "\"$key\"")

    fun entry(key: String, value: Any): String = entry(key) + ": " + value

    describeFun(keyValuePairs, keyValuePairsNullable) {
        val containsFunctions = uncheckedToNonNullable(keyValuePairs, keyValuePairsNullable)

        context("map $map") {
            containsFunctions.forEach { (name, containsFun) ->
                listOf(
                    listOf("a" to 1),
                    listOf("b" to 2),
                    listOf("a" to 1, "b" to 2),
                    listOf("b" to 2, "a" to 1)
                ).forEach {
                    it("$name - $it does not throw") {
                        fluent.containsFun(it.first(), it.drop(1).toTypedArray())
                    }
                }

                it("$name - a to 1 and a to 1 does not throw (no unique match)") {
                    fluent.containsFun("a" to 1, arrayOf("a" to 1))
                }

                it("$name - {a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
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
    }

    describeFun(keyValuePairsNullable) {
        val containsNullableFun = keyValuePairsNullable.lambda
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

    describeFun(keyWithValueAssertions, keyWithNullableValueAssertions) {
        val containsKeyWithValueAssertionsFunctions = uncheckedToNonNullable(
            keyWithValueAssertions,
            keyWithNullableValueAssertions
        )

        context("map $map") {
            containsKeyWithValueAssertionsFunctions.forEach { (name, containsKeyWithValueAssertionsFun) ->
                listOf(
                    "a { toBe(1) }" to listOf(keyValue("a") { toBe(1) }),
                    "b { toBe(2) }" to listOf(keyValue("b") { toBe(2) }),
                    "a { toBe(1) }, b { toBe(2) }" to listOf(keyValue("a") { toBe(1) }, keyValue("b") { toBe(2) }),
                    "b { toBe(2) }, a { toBe(1) }" to listOf(keyValue("b") { toBe(2) }, keyValue("a") { toBe(1) })
                ).forEach { (description, keyValues) ->
                    it("$name - $description does not throw") {
                        fluent.containsKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                    }
                }

                it("$name - a { isLessThan(2) } and a { isGreaterThan(0) } does not throw (no unique match)") {
                    fluent.containsKeyWithValueAssertionsFun(
                        keyValue("a") { isLessThan(2) },
                        arrayOf(keyValue("a") { isGreaterThan(0) })
                    )
                }

                it("$name - a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
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
    }

    describeFun(keyWithNullableValueAssertions) {
        val containsKeyWithNullableValueAssertionsFun = keyWithNullableValueAssertions.lambda
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
})
