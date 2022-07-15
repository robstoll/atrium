package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nonNullableCases
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nullableCases

abstract class MapToContainInOrderOnlyKeyValueExpectationsSpec(
    keyWithValueAssertions: MFun3<String, Int, Expect<Int>.() -> Unit, InOrderOnlyReportingOptions.() -> Unit>,
    keyWithNullableValueAssertions: MFun3<String?, Int?, (Expect<Int>.() -> Unit)?, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLess(
            keyValue("a") { toEqual(1) },
            arrayOf(keyValue("a") { toBeLessThanOrEqualTo(2) }),
            emptyInOrderOnlyReportOptions
        )
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyWithNullableValueAssertions.forSubjectLess(
            keyNullableValue(null) { toEqual(1) },
            arrayOf(keyNullableValue("a", null)),
            emptyInOrderOnlyReportOptions
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        assertionCreatorSpecTriple(keyWithValueAssertions.name, "$toBeLessThanDescr: 2",
            {
                keyWithValueAssertions(
                    this,
                    keyValue("a") { toBeLessThan(2) },
                    arrayOf(keyValue("b") { toBeLessThan(3) }),
                    emptyInOrderOnlyReportOptions
                )
            },
            {
                keyWithValueAssertions(
                    this,
                    keyValue("a") { },
                    arrayOf(keyValue("b") { }),
                    emptyInOrderOnlyReportOptions
                )
            }
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        assertionCreatorSpecTriple(keyWithNullableValueAssertions.name, "$toBeLessThanDescr: 2",
            {
                keyWithNullableValueAssertions(
                    this,
                    keyNullableValue("a") { toBeLessThan(2) },
                    arrayOf(keyNullableValue("b", null)),
                    emptyInOrderOnlyReportOptions
                )
            },
            {
                keyWithNullableValueAssertions(
                    this,
                    keyNullableValue("a") { },
                    arrayOf(keyValue("b") { }),
                    emptyInOrderOnlyReportOptions
                )
            }
        )
    ) {})

    fun entry(index: Int) = IterableToContainSpecBase.elementWithIndex(index)

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

        return this.toContain.exactly(1).regex(
            "\\Q$successFailureBulletPoint$featureArrow${entry(index)}: $actual\\E.*$separator" +
                "$indentToKeyValue$keyValueBulletPoint${featureArrow}key:.*$separator" +
                "$indentToKeyValue$indentKeyValueBulletPoint$indentFeatureArrow$featureBulletPoint$toEqualDescr: ${if (expectedKey == null) "null" else "\"$expectedKey\""}.*$separator" +
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
        IterableToContainSpecBase.sizeExceeded,
        expectedKey,
        expectedValue,
        explaining = true
    )

    fun Expect<String>.additionalEntries(vararg pairs: Pair<Int, String>): Expect<String> =
        and {
            val additionalEntries =
                "\\Q${warningBulletPoint}${IterableToContainSpecBase.additionalElements}\\E: $separator"
            toContain.exactly(1).regex(additionalEntries)
            pairs.forEach { (index, entry) ->
                toContain.exactly(1).regex(
                    additionalEntries + "(.|$separator)+${listBulletPoint}${
                        IterableToContainSpecBase.elementWithIndex(index) + ": " + entry
                    }"
                )
            }
        }

    nonNullableCases(
        describePrefix,
        keyWithValueAssertions,
        keyWithNullableValueAssertions
    ) { keyWithValueAssertionsFunArr ->

        fun Expect<Map<out String, Int>>.toContainFun(
            t: Pair<String, Expect<Int>.() -> Unit>,
            vararg tX: Pair<String, Expect<Int>.() -> Unit>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = keyWithValueAssertionsFunArr(t, tX, report)

        context("empty map") {
            it("a to { toBe(1) } throws AssertionError, reports a") {
                expect {
                    expect(emptyMap).toContainFun(keyValue("a") { toEqual(1) })
                }.toThrow<AssertionError> {
                    message {
                        toContainInOrderOnlyDescr()
                        toContainSize(0, 1)
                        elementOutOfBound(0, "a", "$toEqualDescr: 1")
                    }
                }
            }

            it("a to { isLessThan(1) }, b to { toBe(3) }, c to { isLessThan(4) } } throws AssertionError, reports a, b and c") {
                expect {
                    expect(emptyMap).toContainFun(
                        keyValue("a") { toBeLessThan(1) },
                        keyValue("b") { toEqual(3) },
                        keyValue("c") { toBeLessThan(4) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        toContainInOrderOnlyDescr()
                        toContainSize(0, 3)
                        elementOutOfBound(0, "a", "$toBeLessThanDescr: 1")
                        elementOutOfBound(1, "b", "$toEqualDescr: 3")
                        elementOutOfBound(2, "c", "$toBeLessThanDescr: 4")
                    }
                }
            }
        }


        context("map $map") {
            val fluent = expect(map)
            listOf(
                "a to { toBe(1) }, b to { toBe(2) }" to listOf(
                    keyValue("a") { toEqual(1) },
                    keyValue("b") { toEqual(2) }),
                "a to { isGreaterThan(0) }, b to { isLessThan(3) }" to listOf(
                    keyValue("a") { toBeGreaterThan(0) },
                    keyValue("b") { toBeLessThan(3) })
            ).forEach { (description, list) ->
                it("$description does not throw") {
                    expect(map).toContainFun(
                        list.first(),
                        *list.drop(1).toTypedArray()
                    )
                }
            }

            it("a to { toBe(1) } throws AssertionError, missing b") {
                expect {
                    fluent.toContainFun(keyValue("a") { toEqual(1) })
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", "$toEqualDescr: 1")
                        additionalEntries(1 to "b=2")
                        toContainSize(2, 1)
                    }
                }
            }

            it("b to { toBe(2) }, a to { toBe(1) } throws AssertionError, wrong order") {
                expect {
                    fluent.toContainFun(
                        keyValue("b") { toEqual(2) },
                        keyValue("a") { toEqual(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=1", "b", "$toEqualDescr: 2")
                        elementFailing(1, "b=2", "a", "$toEqualDescr: 1")

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }

            it("a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    fluent.toContainFun(
                        keyValue("a") { toBeLessThan(3) },
                        keyValue("b") { toBeLessThan(2) },
                        keyValue("c") { toBeLessThan(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", "$toBeLessThanDescr: 3")
                        elementFailing(1, "b=2", "b", "$toBeLessThanDescr: 2")
                        elementOutOfBound(2, "c", "$toBeLessThanDescr: 1")
                        notToContain(additionalEntriesDescr)
                    }
                }
            }
        }

        context("report options") {
            //TODO #1129 add tests, see IterableToContainInOrderOnlyValuesExpectationsSpec -> report options
            context("") {
                it("shows only failing with report option `showOnlyFailing`") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(2) },
                            keyValue("c") { toEqual(3) },
                            report = { showOnlyFailing() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 3)
                            elementSuccess(0, "1", "a", "1")
                            elementFailing(0, "2", "a", "1")
                            elementOutOfBound(3, "d", "4")
                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(2)` because there are 3") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(2) },
                            keyValue("c") { toEqual(3) },
                            report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "1", "a", "1")
                            elementFailing(0, "2", "a", "1")
                            elementOutOfBound(3, "d", "4")
                            toContainSize(2, 3)
                        }
                    }
                }
                it("shows summary with report option `showOnlyFailingIfMoreExpectedElementsThan(2)` because there are 2") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            report = { showOnlyFailingIfMoreExpectedElementsThan(1) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 1)
                            elementSuccess(0, "1", "a", "1")
                        }
                    }
                }
                it("shows summary without report option if there are 2 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 2") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(2) },
                            keyValue("c") { toEqual(3) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "1", "a", "1")
                            elementFailing(0, "2", "a", "1")
                            elementOutOfBound(3, "d", "4")
                            toContainSize(2, 3)
                        }
                    }
                }
                it("shows only failing without report option if there are 3 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 2") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(2) },
                            keyValue("c") { toEqual(3) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "1", "a", "1")
                            elementFailing(0, "2", "a", "1")
                            elementOutOfBound(3, "d", "4")
                            toContainSize(2, 3)
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {

        fun Expect<Map<out String?, Int?>>.toContainFun(
            t: Pair<String?, (Expect<Int>.() -> Unit)?>,
            vararg tX: Pair<String?, (Expect<Int>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = keyWithNullableValueAssertions(this, t, tX, report)

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
                        keyNullableValue(null) { toBeLessThan(2) },
                        keyNullableValue("b") { toBeGreaterThan(1) }
                    )
            ).forEach { (description, keyValues) ->
                it("$description does not throw") {
                    nullableFluent.toContainFun(
                        keyValues.first(),
                        *keyValues.drop(1).toTypedArray()
                    )
                }
            }

            it("(a, null) throws AssertionError, missing b") {
                expect {
                    nullableFluent.toContainFun(keyNullableValue("a", null))
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toEqualDescr: null")
                        additionalEntries(1 to "null=1", 2 to "b=2")
                        toContainSize(3, 1)
                    }
                }
            }

            it("b to { toBe(2) }, (a, null), null to { toBe(1) } throws AssertionError, wrong order") {
                expect {
                    nullableFluent.toContainFun(
                        keyNullableValue("b") { toEqual(2) },
                        keyNullableValue("a", null),
                        keyNullableValue(null) { toEqual(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=null", "b", "$toEqualDescr: 2", explainingValue = true)
                        elementFailing(1, "null=1", "a", "$toEqualDescr: null")
                        elementFailing(2, "b=2", null, "$toEqualDescr: 1")

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }


            it("(a, null), c { isLessThan(1) }, b { isLessThan(2) } throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.toContainFun(
                        keyNullableValue("a", null),
                        keyNullableValue("c") { toBeLessThan(1) },
                        keyNullableValue("b") { toBeLessThan(2) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toEqualDescr: null")
                        elementFailing(1, "null=1", "c", "$toBeLessThanDescr: 1")
                        elementFailing(2, "b=2", "b", "$toBeLessThanDescr: 2")

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }
        }
    }
})
