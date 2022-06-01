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

abstract class MapToContainInAnyOrderKeyValueExpectationsSpec(
    keyWithValueAssertions: MFun2<String, Int, Expect<Int>.() -> Unit>,
    keyWithNullableValueAssertions: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLess(
            keyValue("a") { toEqual(1) },
            arrayOf(keyValue("a") { toBeLessThanOrEqualTo(2) })
        )
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyWithNullableValueAssertions.forSubjectLess(
            keyNullableValue(null) { toEqual(1) },
            arrayOf(keyNullableValue("a", null))
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        assertionCreatorSpecTriple(keyWithValueAssertions.name, "$toEqualDescr: 1",
            { keyWithValueAssertions(this, keyValue("a") { toEqual(1) }, arrayOf()) },
            { keyWithValueAssertions(this, keyValue("a") { }, arrayOf()) }
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        assertionCreatorSpecTriple(keyWithNullableValueAssertions.name, "$toEqualDescr: 1",
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { toEqual(1) }, arrayOf()) },
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf()) }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(keyWithValueAssertions, keyWithNullableValueAssertions) {
        val toContainKeyWithValueAssertionsFunctions = uncheckedToNonNullable(
            keyWithValueAssertions,
            keyWithNullableValueAssertions
        )
        val fluent = expect(map)

        context("map $map") {
            toContainKeyWithValueAssertionsFunctions.forEach { (name, toContainKeyWithValueAssertionsFun) ->
                listOf(
                    "a { toBe(1) }" to listOf(keyValue("a") { toEqual(1) }),
                    "b { toBe(2) }" to listOf(keyValue("b") { toEqual(2) }),
                    "a { toBe(1) }, b { toBe(2) }" to listOf(keyValue("a") { toEqual(1) }, keyValue("b") { toEqual(2) }),
                    "b { toBe(2) }, a { toBe(1) }" to listOf(keyValue("b") { toEqual(2) }, keyValue("a") { toEqual(1) })
                ).forEach { (description, keyValues) ->
                    it("$name - $description does not throw") {
                        fluent.toContainKeyWithValueAssertionsFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                    }
                }

                it("$name - a { isLessThan(2) } and a { isGreaterThan(0) } does not throw (no unique match)") {
                    fluent.toContainKeyWithValueAssertionsFun(
                        keyValue("a") { toBeLessThan(2) },
                        arrayOf(keyValue("a") { toBeGreaterThan(0) })
                    )
                }

                it("$name - a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                    expect {
                        fluent.toContainKeyWithValueAssertionsFun(
                            keyValue("a") { toBeLessThan(3) },
                            arrayOf(keyValue("b") { toBeLessThan(2) }, keyValue("c") { toBeLessThan(1) })
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                entry("b", 2),
                                "$toBeLessThanDescr: 2",
                                entry("c", keyDoesNotExist),
                                "$toBeLessThanDescr: 1"
                            )
                            notToContain(entry("a"))
                        }
                    }
                }
            }
        }
    }

    describeFun(keyWithNullableValueAssertions) {
        val toContainFun = keyWithNullableValueAssertions.lambda
        val nullableFluent = expect(nullableMap)

        context("map $nullableMap") {
            listOf(
                "(a, null)" to
                    listOf(keyNullableValue("a", null)),
                "a { toBe(1) }" to
                    listOf(keyNullableValue(null) { toEqual(1) }),
                "b { toBe(2) }" to
                    listOf(keyNullableValue("b") { toEqual(2) }),
                "(a, null), b{ toBe(2) }" to
                    listOf(keyNullableValue("a", null), keyNullableValue("b") { toEqual(2) }),
                "null { toBe(1) }, b{ toBe(2) }" to
                    listOf(keyNullableValue(null) { toEqual(1) }, keyNullableValue("b") { toEqual(2) }),
                "null { toBe(1) }, (a, null)" to
                    listOf(keyNullableValue(null) { toEqual(1) }, keyNullableValue("a", null)),
                "null { toBe(1) }, (a, null), b{ toBe(2) }" to
                    listOf(
                        keyNullableValue(null) { toEqual(1) },
                        keyNullableValue("a", null),
                        keyNullableValue("b") { toEqual(2) }),
                "b { toBe(2) }, null{ toBe(1) }, (a, null)" to
                    listOf(
                        keyNullableValue("b") { toEqual(2) },
                        keyNullableValue(null) { toEqual(1) },
                        keyNullableValue("a", null)
                    )
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    nullableFluent.toContainFun(keyValues.first(), keyValues.drop(1).toTypedArray())
                }
            }

            it("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.toContainFun(
                        keyNullableValue("a", null), arrayOf(
                            keyNullableValue("b") { toBeLessThan(2) },
                            keyNullableValue("c") { toBeLessThan(1) }
                        )
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            entry("b", 2),
                            "$toBeLessThanDescr: 2",
                            entry("c", keyDoesNotExist),
                            "$toBeLessThanDescr: 1"
                        )
                        notToContain(entry("a"))
                    }
                }
            }
        }
    }
})
