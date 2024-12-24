package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nonNullableCases
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.s
import org.spekframework.spek2.style.specification.Suite

abstract class MapToContainInOrderOnlyKeyValueExpectationsSpec(
    keyWithValueAssertions: MFun3<String, Int, Expect<Int>.() -> Unit, InOrderOnlyReportingOptions.() -> Unit>,
    keyWithNullableValueAssertions: MFun3<String?, Int?, (Expect<Int>.() -> Unit)?, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyWithValueAssertions.forSubjectLessTest(
            keyValue("a") { toEqual(1) },
            arrayOf(keyValue("a") { toBeLessThanOrEqualTo(2) }),
            emptyInOrderOnlyReportOptions
        )
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyWithNullableValueAssertions.forSubjectLessTest(
            keyNullableValue(null) { toEqual(1) },
            arrayOf(keyNullableValue("a", null)),
            emptyInOrderOnlyReportOptions
        )
    ) {})

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        ExpectationCreatorTriple(
            keyWithValueAssertions.name, "$toBeLessThanDescr\\s+: 2",
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
        ExpectationCreatorTriple(
            keyWithNullableValueAssertions.name, "$toBeLessThanDescr\\s+: 2",
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

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<String>.elementSuccess(
        index: Int,
        actual: Any,
        expectedKey: String,
        expectedValue: String
    ): Expect<String> = element(s, indentS, s, indentS, s, s, indentS, s, index, actual, expectedKey, expectedValue)

    fun Expect<String>.elementFailing(
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: String,
        keyBulletPoint: String = g,
        indentKey: String = indentG,
        keySubBulletPoint: String = x,
        valueBulletPoint: String = g,
        indentValue: String = indentG,
        valueSubBulletPoint: String = x,
        withKey: Boolean = true,
        withValue: Boolean = true
    ): Expect<String> =
        element(
            g,
            indentG,
            keyBulletPoint,
            indentKey,
            keySubBulletPoint,
            valueBulletPoint,
            indentValue,
            valueSubBulletPoint,
            index,
            actual,
            expectedKey,
            expectedValue,
            withKey = withKey,
            withValue = withValue
        )

    fun Expect<String>.elementOutOfBound(
        index: Int,
        expectedKey: String,
        expectedValue: String,
    ): Expect<String> = element(
        g,
        indentG,
        explanatoryBulletPoint,
        indentExplanatory,
        listBulletPoint,
        explanatoryBulletPoint,
        indentExplanatory,
        listBulletPoint,
        index,
        IterableToContainSpecBase.sizeExceeded,
        expectedKey,
        expectedValue,
    )

    fun Expect<String>.additionalEntries(vararg pairs: Pair<Int, String>): Expect<String> =
        toContain.exactly(1).regex(
            "$indentG\\Q${bb}${IterableToContainSpecBase.additionalElements}\\E : $lineSeparator" +
                pairs.joinToString(lineSeparator) { (index, entry) ->
                    "$indentG$indentBb$listBulletPoint${IterableToContainSpecBase.elementWithIndex(index)}\\s+: $entry.*"
                }
        )

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
            it("a to { toEqual(1) } throws AssertionError, reports a") {
                expect {
                    expect(emptyMap).toContainFun(keyValue("a") { toEqual(1) })
                }.toThrow<AssertionError> {
                    message {
                        toContainInOrderOnlyDescr()
                        toContainSize(0, 1)
                        elementOutOfBound(0, "a", "$toEqualDescr : 1")
                    }
                }
            }

            it("a to { isLessThan(1) }, b to { toEqual(3) }, c to { isLessThan(4) } } throws AssertionError, reports a, b and c") {
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
                        elementOutOfBound(0, "a", "$toBeLessThanDescr : 1")
                        elementOutOfBound(1, "b", "$toEqualDescr : 3")
                        elementOutOfBound(2, "c", "$toBeLessThanDescr : 4")
                    }
                }
            }
        }


        context("map $map") {
            listOf(
                "a to { toEqual(1) }, b to { toEqual(2) }" to listOf(
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

            it("a to { toEqual(1) } throws AssertionError, missing b") {
                expect {
                    expect(map).toContainFun(keyValue("a") { toEqual(1) })
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", "$toEqualDescr : 1")
                        additionalEntries(1 to "b=2")
                        toContainSize(2, 1)
                    }
                }
            }

            it("b to { toEqual(2) }, a to { toEqual(1) } throws AssertionError, wrong order") {
                expect {
                    expect(map).toContainFun(
                        keyValue("b") { toEqual(2) },
                        keyValue("a") { toEqual(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=1", "b", "$toEqualDescr : 2")
                        elementFailing(1, "b=2", "a", "$toEqualDescr : 1")

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }

            it("a { isLessThan(3) }, b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
                expect {
                    expect(map).toContainFun(
                        keyValue("a") { toBeLessThan(3) },
                        keyValue("b") { toBeLessThan(2) },
                        keyValue("c") { toBeLessThan(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", "$toBeLessThanDescr : 3")
                        elementFailing(
                            1,
                            "b=2",
                            "b",
                            "$toBeLessThanDescr : 2",
                            keyBulletPoint = s,
                            indentKey = indentS,
                            keySubBulletPoint = s
                        )
                        elementOutOfBound(2, "c", "$toBeLessThanDescr : 1")
                        notToContain(additionalEntriesDescr)
                    }
                }
            }
        }

        context("report options") {
            context("map $map") {
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
                            notToContainEntry("a")
                            notToContainEntry("b")
                            elementOutOfBound(2, "c", "$toEqualDescr : 3")
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
                            toContainSize(2, 3)
                            notToContainEntry("a")
                            notToContainEntry("b")
                            elementOutOfBound(2, "c", "$toEqualDescr : 3")
                        }
                    }
                }
                it("shows summary with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 2") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(1) },
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            notToContain(sizeDescr)
                            elementSuccess(0, "a=1", "a", "$toEqualDescr : 1")
                            elementFailing(
                                1, "b=2", "b", "$toEqualDescr : 1",
                                keyBulletPoint = s, indentKey = indentS, keySubBulletPoint = s
                            )
                        }
                    }
                }
                it("shows summary without report option if there are  10 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(1) },
                            keyValue("c") { toEqual(3) },
                            keyValue("d") { toEqual(4) },
                            keyValue("e") { toEqual(5) },
                            keyValue("f") { toEqual(6) },
                            keyValue("g") { toEqual(7) },
                            keyValue("h") { toEqual(8) },
                            keyValue("i") { toEqual(9) },
                            keyValue("j") { toEqual(10) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 10)
                            elementSuccess(0, "a=1", "a", "$toEqualDescr : 1")
                            elementFailing(
                                1, "b=2", "b", "$toEqualDescr : 1",
                                keyBulletPoint = s, indentKey = indentS, keySubBulletPoint = s
                            )
                            elementOutOfBound(2, "c", "$toEqualDescr : 3")
                            elementOutOfBound(3, "d", "$toEqualDescr : 4")
                            elementOutOfBound(4, "e", "$toEqualDescr : 5")
                            elementOutOfBound(5, "f", "$toEqualDescr : 6")
                            elementOutOfBound(6, "g", "$toEqualDescr : 7")
                            elementOutOfBound(7, "h", "$toEqualDescr : 8")
                            elementOutOfBound(8, "i", "$toEqualDescr : 9")
                            elementOutOfBound(9, "j", "$toEqualDescr : 10")
                        }
                    }
                }
                it("shows only failing without report option if there are 11 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(map).toContainFun(
                            keyValue("a") { toEqual(1) },
                            keyValue("b") { toEqual(1) },
                            keyValue("c") { toEqual(3) },
                            keyValue("d") { toEqual(4) },
                            keyValue("e") { toEqual(5) },
                            keyValue("f") { toEqual(6) },
                            keyValue("g") { toEqual(7) },
                            keyValue("h") { toEqual(8) },
                            keyValue("i") { toEqual(9) },
                            keyValue("j") { toEqual(10) },
                            keyValue("k") { toEqual(11) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 11)
                            notToContainEntry("a")
                            elementFailing(1, "b=2", "b", "$toEqualDescr : 1", withKey = false)
                            elementOutOfBound(2, "c", "$toEqualDescr : 3")
                            elementOutOfBound(3, "d", "$toEqualDescr : 4")
                            elementOutOfBound(4, "e", "$toEqualDescr : 5")
                            elementOutOfBound(5, "f", "$toEqualDescr : 6")
                            elementOutOfBound(6, "g", "$toEqualDescr : 7")
                            elementOutOfBound(7, "h", "$toEqualDescr : 8")
                            elementOutOfBound(8, "i", "$toEqualDescr : 9")
                            elementOutOfBound(9, "j", "$toEqualDescr : 10")
                            elementOutOfBound(10, "k", "$toEqualDescr : 11")
                        }
                    }
                }
            }
        }
    }

    describeFun(keyWithNullableValueAssertions) {

        fun Expect<Map<out String?, Int?>>.toContainFun(
            t: Pair<String?, (Expect<Int>.() -> Unit)?>,
            vararg tX: Pair<String?, (Expect<Int>.() -> Unit)?>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = keyWithNullableValueAssertions(this, t, tX, report)

        context("map $nullableMap") {
            listOf(
                "(a, null), null { toEqual(1) }, b { toEqual(2) }" to
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
                    expect(nullableMap).toContainFun(
                        keyValues.first(),
                        *keyValues.drop(1).toTypedArray()
                    )
                }
            }

            it("(a, null) throws AssertionError, missing b") {
                expect {
                    expect(nullableMap).toContainFun(keyNullableValue("a", null))
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toEqualDescr : null")
                        additionalEntries(1 to "null=1", 2 to "b=2")
                        toContainSize(3, 1)
                    }
                }
            }

            it("b to { toEqual(2) }, (a, null), null to { toEqual(1) } throws AssertionError, wrong order") {
                expect {
                    expect(nullableMap).toContainFun(
                        keyNullableValue("b") { toEqual(2) },
                        keyNullableValue("a", null),
                        keyNullableValue(null) { toEqual(1) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(
                            0, "a=null", "b", "$toEqualDescr : 2",
                            valueBulletPoint = g,
                            indentValue = indentG,
                            //TODO 1.3.0 null and 2 are not aligned, would be nice though, fix it and create a test
                            // in CreateReportTest as regression
                            valueSubBulletPoint = explanatoryBulletPoint
                        )
                        elementFailing(1, "null=1", "a", "$toEqualDescr : null")
                        elementFailing(2, "b=2", null, "$toEqualDescr : 1")

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }


            it("(a, null), c { isLessThan(1) }, b { isLessThan(2) } throws AssertionError, reports b and c") {
                expect {
                    expect(nullableMap).toContainFun(
                        keyNullableValue("a", null),
                        keyNullableValue("c") { toBeLessThan(1) },
                        keyNullableValue("b") { toBeLessThan(2) }
                    )
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", "$toEqualDescr : null")
                        elementFailing(1, "null=1", "c", "$toBeLessThanDescr : 1")
                        elementFailing(
                            2, "b=2", "b", "$toBeLessThanDescr : 2",
                            keyBulletPoint = s, indentKey = indentS, keySubBulletPoint = s
                        )

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }
        }
    }
})
