package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

//TODO 1.2.0 include InOrderReportOptions --> finish https://github.com/robstoll/atrium/pull/1171
abstract class MapToContainInOrderOnlyKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyValuePairs.forSubjectLess("key" to 1, arrayOf())
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyValuePairsNullable.forSubjectLess(null to 1, arrayOf("a" to null))
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun entry(index: Int) = IterableToContainSpecBase.elementWithIndex(index)

    fun Expect<String>.element(
        successFailureBulletPoint: String,
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: Int?,
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
                "$indentToKeyValue$indentKeyValueBulletPoint$indentFeatureArrow${if (explainingValue) "$indentFeatureBulletPoint$explanatoryBulletPoint" else featureBulletPoint}$toEqualDescr: $expectedValue"
        )
    }

    fun Expect<String>.elementSuccess(
        index: Int,
        actual: Any,
        expectedKey: String,
        expectedValue: Int?
    ): Expect<String> = element(successfulBulletPoint, index, actual, expectedKey, expectedValue)

    fun Expect<String>.elementFailing(
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: Int?,
        explainingValue: Boolean = false
    ): Expect<String> =
        element(failingBulletPoint, index, actual, expectedKey, expectedValue, explainingValue = explainingValue)

    fun Expect<String>.elementOutOfBound(
        index: Int,
        expectedKey: String,
        expectedValue: Int
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

    describeFun(keyValuePairs, keyValuePairsNullable) {
        val toContainKeyValuePairsFunctions = uncheckedToNonNullable(keyValuePairs, keyValuePairsNullable)

        context("empty map") {
            toContainKeyValuePairsFunctions.forEach { (name, toContainFun) ->
                it("$name - a to 1 throws AssertionError, reports a") {
                    expect {
                        expect(emptyMap).toContainFun("a" to 1, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContainInOrderOnlyDescr()
                            toContainSize(0, 1)
                            elementOutOfBound(0, "a", 1)
                        }
                    }
                }

                it("$name - a to 2, b to 3, c to 4 } throws AssertionError, reports a, b and c") {
                    expect {
                        expect(emptyMap).toContainFun("a" to 2, arrayOf("b" to 3, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInOrderOnlyDescr()
                            toContainSize(0, 3)
                            elementOutOfBound(0, "a", 2)
                            elementOutOfBound(1, "b", 3)
                            elementOutOfBound(2, "c", 4)
                        }
                    }
                }
            }
        }

        context("map $map") {
            val fluent = expect(map)

            toContainKeyValuePairsFunctions.forEach { (name, toContainFun) ->
                listOf(
                    listOf("a" to 1, "b" to 2)
                ).forEach {
                    it("$name - ${it.joinToString()} does not throw") {
                        fluent.toContainFun(it.first(), it.drop(1).toTypedArray())
                    }
                }

                it("$name - a to 1 throws AssertionError, missing b") {
                    expect {
                        fluent.toContainFun("a" to 1, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "a=1", "a", 1)
                            additionalEntries(1 to "b=2")
                            toContainSize(2, 1)
                        }
                    }
                }

                it("$name - b 2, a to 1 throws AssertionError, wrong order") {
                    expect {
                        fluent.toContainFun("b" to 2, arrayOf("a" to 1))
                    }.toThrow<AssertionError> {
                        message {
                            elementFailing(0, "a=1", "b", 2)
                            elementFailing(1, "b=2", "a", 1)

                            notToContain(additionalEntriesDescr, sizeDescr)
                        }
                    }
                }

                it("$name - a to 1, b to 1, c to 4 throws AssertionError, reports b and c") {
                    expect {
                        fluent.toContainFun("a" to 1, arrayOf("b" to 1, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            elementSuccess(0, "a=1", "a", 1)
                            elementFailing(1, "b=2", "b", 1)
                            elementOutOfBound(2, "c", 4)
                            notToContain(additionalEntriesDescr)
                        }
                    }
                }
            }
        }
    }

    describeFun(keyValuePairsNullable) {
        val toContainFun = keyValuePairsNullable.lambda
        val nullableFluent = expect(nullableMap)

        context("map: $nullableMap") {
            listOf(
                listOf("a" to null, null to 1, "b" to 2)
            ).forEach {
                it("$it does not throw") {
                    expect(nullableMap).toContainFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            it("a to null throws AssertionError, missing b") {
                expect {
                    nullableFluent.toContainFun("a" to null, arrayOf())
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", null)
                        additionalEntries(1 to "null=1", 2 to "b=2")
                        toContainSize(3, 1)
                    }
                }
            }

            it("b to 2, a to null, null to 1 throws AssertionError, wrong order") {
                expect {
                    nullableFluent.toContainFun("b" to 2, arrayOf("a" to null, null to 1))
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=null", "b", 2)
                        elementFailing(1, "null=1", "a", null)
                        elementFailing(2, "b=2", null, 1)

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }


            it("a to null, c to 3, b to 3 throws AssertionError, reports b and c") {
                expect {
                    nullableFluent.toContainFun("a" to null, arrayOf("c" to 3, "b" to 3))
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=null", "a", null)
                        elementFailing(1, "null=1", "c", 3)
                        elementFailing(2, "b=2", "b", 3)

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }
        }
    }
})
