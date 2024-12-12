package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import org.spekframework.spek2.style.specification.Suite

abstract class MapToContainInAnyOrderOnlyKeyValueExpectationsSpec(
    keyWithValueAssertions: MFun2<String, Int, Expect<Int>.() -> Unit>,
    keyWithNullableValueAssertions: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(
    object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLessTest(
            keyValue("a") { toEqual(1) },
            arrayOf(keyValue("a") { toBeLessThanOrEqualTo(2) })
        )
    ) {})

    include(
    object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyWithNullableValueAssertions.forSubjectLessTest(
            keyNullableValue(null) { toEqual(1) },
            arrayOf(keyNullableValue("a", null))
        )
    ) {})

    include(
    object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        ExpectationCreatorTriple(keyWithValueAssertions.name, "$toBeLessThanDescr\\s+: 2",
            {
                keyWithValueAssertions(
                    this,
                    keyValue("a") { toBeLessThan(2) },
                    arrayOf(keyValue("b") { toBeLessThan(3) })
                )
            },
            { keyWithValueAssertions(this, keyValue("a") { }, arrayOf(keyValue("a") { })) }
        )
    ) {})

    include(
    object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        ExpectationCreatorTriple(keyWithNullableValueAssertions.name, "$toBeLessThanDescr\\s+: 2",
            {
                keyWithNullableValueAssertions(
                    this,
                    keyNullableValue("a") { toBeLessThan(2) },
                    arrayOf(keyNullableValue("b", null))
                )
            },
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf()) }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)


    describeFun(keyWithValueAssertions, keyWithValueAssertions)
    {
        val toContainKeyWithValueAssertionsFunctions =
            uncheckedToNonNullable(keyWithValueAssertions, keyWithNullableValueAssertions)
        val emptyMap: Map<out String, Int> = mapOf()

        context("empty map") {
            toContainKeyWithValueAssertionsFunctions.forEach { (name, toContainFun) ->
                it("$name - a to { toEqual(1) } throws AssertionError, reports a") {
                    expect {
                        expect(emptyMap).toContainFun(keyValue("a") { toEqual(1) }, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(0, 1)
                            entryNonExisting("a", "$toEqualDescr: 1")
                            notToContain(additionalEntriesDescr)
                        }
                    }
                }

                it("$name - a to { isLessThan(1) }, b to { toEqual(3) }, c to { isLessThan(4) } } throws AssertionError, reports a, b and c") {
                    expect {
                        expect(emptyMap).toContainFun(
                            keyValue("a") { toBeLessThan(1) },
                            arrayOf(
                                keyValue("b") { toEqual(3) },
                                keyValue("c") { toBeLessThan(4) }
                            ))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(0, 3)
                            entryNonExisting("a", "$toBeLessThanDescr: 1")
                            entryNonExisting("b", "$toEqualDescr: 3")
                            entryNonExisting("c", "$toBeLessThanDescr: 4")
                            notToContain(additionalEntriesDescr)
                        }
                    }
                }
            }
        }

        context("map $map") {
            toContainKeyWithValueAssertionsFunctions.forEach { (name, toContainFun) ->
                listOf(
                    "a to { toEqual(1) }, b to { toEqual(2) }" to listOf(
                        keyValue("a") { toEqual(1) },
                        keyValue("b") { toEqual(2) }),
                    "b to { toEqual(2) }, a to { toEqual(1) }" to listOf(
                        keyValue("b") { toEqual(2) },
                        keyValue("a") { toEqual(1) }),
                    "b to { isGreaterThan(1) }, a to { isLessThan(2) }" to listOf(
                        keyValue("b") { toBeGreaterThan(1) },
                        keyValue("a") { toBeLessThan(2) }),
                    "a to { isGreaterThan(0) }, b to { isLessThan(3) }" to listOf(
                        keyValue("a") { toBeGreaterThan(0) },
                        keyValue("b") { toBeLessThan(3) })
                ).forEach { (description, list) ->
                    it("$name - $description does not throw") {
                        expect(map).toContainFun(list.first(), list.drop(1).toTypedArray())
                    }
                }

                it("$name - a to { isLessThan(2) } throws AssertionError, reports second a and missing b") {
                    expect {
                        expect(map).toContainFun(keyValue("a") { toBeLessThan(2) }, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(2, 1)
                            entrySuccess("a", 1, "$toBeLessThanDescr: 2")
                            additionalEntries("b" to 2)
                        }
                    }
                }

                it("$name - a to { isLessThan(2) }, a to { toEqual(1) } throws AssertionError, reports second a and missing b") {
                    expect {
                        expect(map).toContainFun(keyValue("a") { toBeLessThan(2) }, arrayOf(keyValue("a") { toEqual(1) }))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            entrySuccess("a", 1, "$toBeLessThanDescr: 2")
                            entryNonExisting("a", "$toEqualDescr: 1")
                            additionalEntries("b" to 2)

                            notToContain(sizeDescr)
                        }
                    }
                }

                it("$name - a to { isLessThan(3), b to { isLessThan(1), c to { toEqual(4) } throws AssertionError, reports b and c") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toBeLessThan(3) },
                            arrayOf(
                                keyValue("b") { toBeLessThan(1) },
                                keyValue("c") { toEqual(4) }
                            )
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(2, 3)
                            entrySuccess("a", 1, "$toBeLessThanDescr: 3")
                            entryFailing("b", 2, "$toBeLessThanDescr: 1")
                            entryNonExisting("c", "$toEqualDescr: 4")

                            notToContain(additionalEntriesDescr)
                        }
                    }
                }
            }
        }
    }

    describeFun(keyWithNullableValueAssertions)
    {
        val toContainKeyWithNullableValueAssertionsFun = keyWithNullableValueAssertions.lambda
        context("map: $nullableMap") {
            listOf(
                "null { toEqual(1) }, (a, null), b{ toEqual(2) }" to
                    listOf(
                        keyNullableValue(null) { toEqual(1) },
                        keyNullableValue("a", null),
                        keyNullableValue("b") { toEqual(2) }),
                "b { toEqual(2) }, null{ toEqual(1) }, (a, null)" to
                    listOf(
                        keyNullableValue("b") { toEqual(2) },
                        keyNullableValue(null) { toEqual(1) },
                        keyNullableValue("a", null)
                    )
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
                        keyValues.first(),
                        keyValues.drop(1).toTypedArray()
                    )
                }
            }
            it("a to { toEqual(1) } throws AssertionError, reports failure and missing null and b") {
                expect {
                    expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
                        keyNullableValue("a") { toEqual(1) },
                        arrayOf()
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContainInAnyOrderOnlyDescr()
                        toContainSize(3, 1)
                        entryFailingExplaining("a", null, "$toEqualDescr: 1")
                        additionalEntries(null to 1, "b" to 2)
                    }
                }
            }

            it("a to { toEqual(1) }, c to { isLessThan(3) }, null to null, b to { isLessThan(3) } throws AssertionError, reports all but b") {
                expect {
                    expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
                        keyNullableValue("a") { toEqual(1) },
                        arrayOf(
                            keyNullableValue("c") { toBeLessThan(3) },
                            keyNullableValue(null, null),
                            keyNullableValue("b") { toBeLessThan(3) }
                        )
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContainInAnyOrderOnlyDescr()
                        toContainSize(3, 4)
                        entryFailingExplaining("a", null, "$toEqualDescr: 1")
                        entryNonExisting("c", "$toBeLessThanDescr: 3")
                        entryFailing(null, "1", "$toEqualDescr: null")
                        entrySuccess("b", "2", "$toBeLessThanDescr: 3")

                        notToContain(additionalEntriesDescr)
                    }
                }
            }
        }
    }
})
