package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class MapContainsInOrderOnlyKeyValueExpectationsSpec(
    keyWithValueAssertions: MFun2<String, Int, Expect<Int>.() -> Unit>,
    keyWithNullableValueAssertions: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>,
    describePrefix: String = "[Atrium] "
) : MapLikeContainsFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLess(
            keyValue("a") { toEqual(1) },
            arrayOf(keyValue("a") { isLessThanOrEqual(2) })
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
        assertionCreatorSpecTriple(keyWithValueAssertions.name, "$lessThanDescr: 2",
            {
                keyWithValueAssertions(
                    this,
                    keyValue("a") { isLessThan(2) },
                    arrayOf(keyValue("b") { isLessThan(3) })
                )
            },
            { keyWithValueAssertions(this, keyValue("a") { }, arrayOf(keyValue("b") { })) }
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        assertionCreatorSpecTriple(keyWithNullableValueAssertions.name, "$lessThanDescr: 2",
            {
                keyWithNullableValueAssertions(
                    this,
                    keyNullableValue("a") { isLessThan(2) },
                    arrayOf(keyNullableValue("b", null))
                )
            },
            { keyWithNullableValueAssertions(this, keyNullableValue("a") { }, arrayOf(keyValue("b") { })) }
        )
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun entry(index: Int) = IterableContainsSpecBase.elementWithIndex(index)

    fun Expect<String>.element(
        successFailureBulletPoint: String,
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: String,
        explaining: Boolean = false,
        explainingValue: Boolean = false
    ): Expect<String> {
        val indent = " ".repeat(successFailureBulletPoint.length)
        val keyValueBulletPoint = if (explaining) explanatoryBulletPoint else featureBulletPoint
        val indentKeyValueBulletPoint = " ".repeat(keyValueBulletPoint.length)
        val indentToKeyValue =
            "$indentRootBulletPoint$indent$indentFeatureArrow" + (if (explaining) indentFeatureBulletPoint else "")

        return this.contains.exactly(1).regex(
            "\\Q$successFailureBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentToKeyValue$keyValueBulletPoint${featureArrow}key:.*$separator" +
                "$indentToKeyValue$indentKeyValueBulletPoint$indentFeatureArrow$featureBulletPoint$toBeDescr: ${if (expectedKey == null) "null" else "\"$expectedKey\""}.*$separator" +
                "$indentToKeyValue$keyValueBulletPoint${featureArrow}value:.*$separator" +
                "$indentToKeyValue$indentKeyValueBulletPoint$indentFeatureArrow${if (explainingValue) "$indentFeatureBulletPoint$explanatoryBulletPoint" else featureBulletPoint}$expectedValue"
        )
    }

    fun Expect<String>.elementSuccess(
        index: Int,
        actual: Any,
        expectedKey: String,
        expectedValue: String
    ): Expect<String> = element(successfulBulletPoint, index, actual, expectedKey, expectedValue)

    fun Expect<String>.elementFailing(
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: String,
        explainingValue: Boolean = false
    ): Expect<String> =
        element(failingBulletPoint, index, actual, expectedKey, expectedValue, explainingValue = explainingValue)

    fun Expect<String>.elementOutOfBound(
        index: Int,
        expectedKey: String,
        expectedValue: String
    ): Expect<String> = element(
        failingBulletPoint,
        index,
        IterableContainsSpecBase.sizeExceeded,
        expectedKey,
        expectedValue,
        explaining = true
    )

    fun Expect<String>.additionalEntries(vararg pairs: Pair<Int, String>): Expect<String> =
        and {
            val additionalEntries =
                "\\Q${warningBulletPoint}${IterableContainsSpecBase.additionalElements}\\E: $separator"
            contains.exactly(1).regex(additionalEntries)
            pairs.forEach { (index, entry) ->
                contains.exactly(1).regex(
                    additionalEntries + "(.|$separator)+${listBulletPoint}${
                        IterableContainsSpecBase.elementWithIndex(index) + ": " + entry
                    }"
                )
            }
        }

    describeFun(keyWithValueAssertions, keyWithNullableValueAssertions) {
        val containsKeyWithValueAssertionsFunctions = uncheckedToNonNullable(
            keyWithValueAssertions,
            keyWithNullableValueAssertions
        )

        context("empty map") {
            containsKeyWithValueAssertionsFunctions.forEach { (name, containsFun) ->
                it("$name - a to { toBe(1) } throws AssertionError, reports a") {
                    expect {
                        expect(emptyMap).containsFun(keyValue("a") { toEqual(1) }, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            containsInOrderOnlyDescr()
                            // TODO 0.18.0 wait for size to be moved out of Iterable.contains
                            //containsSize(0, 1)
                            elementOutOfBound(0, "a", "$toBeDescr: 1")
                        }
                    }
                }

                it("$name - a to { isLessThan(1) }, b to { toBe(3) }, c to { isLessThan(4) } } throws AssertionError, reports a, b and c") {
                    expect {
                        expect(emptyMap).containsFun(
                            keyValue("a") { isLessThan(1) },
                            arrayOf(
                                keyValue("b") { toEqual(3) },
                                keyValue("c") { isLessThan(4) }
                            ))
                    }.toThrow<AssertionError> {
                        message {
                            containsInOrderOnlyDescr()
                            // TODO 0.18.0 wait for size to be moved out of Iterable.contains
                            //containsSize(0, 3)
                            elementOutOfBound(0, "a", "$lessThanDescr: 1")
                            elementOutOfBound(1, "b", "$toBeDescr: 3")
                            elementOutOfBound(2, "c", "$lessThanDescr: 4")
                        }
                    }
                }
            }
        }

        context("map $map") {
            val fluent = expect(map)

            containsKeyWithValueAssertionsFunctions.forEach { (name, containsFun) ->
                listOf(
                    "a to { toBe(1) }, b to { toBe(2) }" to listOf(
                        keyValue("a") { toEqual(1) },
                        keyValue("b") { toEqual(2) }),
                    "a to { isGreaterThan(0) }, b to { isLessThan(3) }" to listOf(
                        keyValue("a") { isGreaterThan(0) },
                        keyValue("b") { isLessThan(3) })
                ).forEach { (description, list) ->
                    it("$name - $description does not throw") {
                        expect(map).containsFun(list.first(), list.drop(1).toTypedArray())
                    }
                }

                it("$name - a to { toBe(1) } throws AssertionError, missing b") {
                    expect {
                        fluent.containsFun(
                            keyValue("a") { toEqual(1) },
                            arrayOf()
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "a=1", "a", "$toBeDescr: 1")
                            additionalEntries(1 to "b=2")
                            // TODO 0.18.0 wait for size to be moved out of Iterable.contains
//                                containsSize(2, 1)
                        }
                    }
                }

                it("$name - b to { toBe(2) }, a to { toBe(1) } throws AssertionError, wrong order") {
                    expect {
                        fluent.containsFun(
                            keyValue("b") { toEqual(2) },
                            arrayOf(keyValue("a") { toEqual(1) })
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementFailing(0, "a=1", "b", "$toBeDescr: 2")
                            elementFailing(1, "b=2", "a", "$toBeDescr: 1")

                            // TODO 0.18.0 wait for size to be moved out of Iterable.contains
//                                containsNot(sizeDescr)
                            containsNot(additionalEntriesDescr)
                        }
                    }
                }

                it("$name - a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                    expect {
                        fluent.containsFun(
                            keyValue("a") { isLessThan(3) },
                            arrayOf(keyValue("b") { isLessThan(2) }, keyValue("c") { isLessThan(1) })
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "a=1", "a", "$lessThanDescr: 3")
                            elementFailing(1, "b=2", "b", "$lessThanDescr: 2")
                            elementOutOfBound(2, "c", "$lessThanDescr: 1")
                            containsNot(additionalEntriesDescr)
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
                "(a, null), null { toBe(1) }, b { toBe(2) }" to
                    listOf(
                        keyNullableValue("a", null),
                        keyNullableValue(null) { toEqual(1) },
                        keyNullableValue("b") { toEqual(2) }
                    ),
                "(a, null), null { isLessThan(2) }, b { isGreaterThan(1) }" to
                    listOf(
                        keyNullableValue("a", null),
                        keyNullableValue(null) { isLessThan(2) },
                        keyNullableValue("b") { isGreaterThan(1) }
                    )
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    nullableFluent.containsFun(
                        keyValues.first(),
                        keyValues.drop(1).toTypedArray()
                    )
                }
            }

            it("(a, null) throws AssertionError, missing b") {
                expect {
                    nullableFluent.containsFun(keyNullableValue("a", null), arrayOf())
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toBeDescr: null")
                        additionalEntries(1 to "null=1", 2 to "b=2")
                        // TODO 0.18.0 wait for size to be moved out of Iterable.contains
//                            containsSize(3, 1)
                    }
                }
            }

            it("b to { toBe(2) }, (a, null), null to { toBe(1) } throws AssertionError, wrong order") {
                expect {
                    nullableFluent.containsFun(
                        keyNullableValue("b") { toEqual(2) },
                        arrayOf(
                            keyNullableValue("a", null),
                            keyNullableValue(null) { toEqual(1) }
                        )
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=null", "b", "$toBeDescr: 2", explainingValue = true)
                        elementFailing(1, "null=1", "a", "$toBeDescr: null")
                        elementFailing(2, "b=2", null, "$toBeDescr: 1")

                        // TODO 0.18.0 wait for size to be moved out of Iterable.contains
//                                containsNot(sizeDescr)
                        containsNot(additionalEntriesDescr)

                    }
                }
            }


            it("(a, null), c { isLessThan(1) }, b { isLessThan(2) } throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.containsFun(
                        keyNullableValue("a", null), arrayOf(
                            keyNullableValue("c") { isLessThan(1) },
                            keyNullableValue("b") { isLessThan(2) }
                        )
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toBeDescr: null")
                        elementFailing(1, "null=1", "c", "$lessThanDescr: 1")
                        elementFailing(2, "b=2", "b", "$lessThanDescr: 2")

                        // TODO 0.18.0 wait for size to be moved out of Iterable.contains
//                                containsNot(sizeDescr)
                        containsNot(additionalEntriesDescr)
                    }
                }
            }
        }
    }
})
