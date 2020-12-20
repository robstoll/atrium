package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

fun keyValue(key: String, assertionCreator: Expect<Int>.() -> Unit): Pair<String, Expect<Int>.() -> Unit> =
    key to assertionCreator

fun keyNullableValue(
    key: String?,
    assertionCreator: (Expect<Int>.() -> Unit)?
): Pair<String?, (Expect<Int>.() -> Unit)?> = key to assertionCreator

abstract class MapContainsInAnyOrderKeyValueAssertionsSpec(
    keyWithValueAssertions: MFun2<String, Int, Expect<Int>.() -> Unit>,
    keyWithNullableValueAssertions: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : MapLikeContainsSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLess(
            keyValue("a") { toBe(1) },
            arrayOf(keyValue("a") { isLessThanOrEqual(2) })
        )
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyWithNullableValueAssertions.forSubjectLess(
            keyNullableValue(null) { toBe(1) },
            arrayOf(keyNullableValue("a", null))
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        assertionCreatorSpecTriple(keyWithValueAssertions.name, "$toBeDescr: 1",
            { keyWithValueAssertions(this, keyValue("a") { toBe(1) }, arrayOf()) },
            { keyWithValueAssertions(this, keyValue("a") { }, arrayOf()) }
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        assertionCreatorSpecTriple(keyWithNullableValueAssertions.name, "$toBeDescr: 1",
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { toBe(1) }, arrayOf()) },
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf()) }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(keyWithValueAssertions, keyWithNullableValueAssertions) {
        val containsKeyWithValueAssertionsFunctions = uncheckedToNonNullable(
            keyWithValueAssertions,
            keyWithNullableValueAssertions
        )
        val fluent = expect(map)

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
        val containsFun = keyWithNullableValueAssertions.lambda
        val nullableFluent = expect(nullableMap)

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
                    nullableFluent.containsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            it("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.containsFun(
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
