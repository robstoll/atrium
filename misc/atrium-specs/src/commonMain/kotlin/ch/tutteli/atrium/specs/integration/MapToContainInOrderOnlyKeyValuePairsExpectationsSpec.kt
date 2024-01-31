package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.nonNullableCases
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.element as elementInFormatSpecBase
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import org.spekframework.spek2.style.specification.Suite

abstract class MapToContainInOrderOnlyKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun3<String, Int, Int, InOrderOnlyReportingOptions.() -> Unit>,
    keyValuePairsNullable: MFun3<String?, Int?, Int?, InOrderOnlyReportingOptions.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyValuePairs.forSubjectLess("key" to 1, arrayOf(), emptyInOrderOnlyReportOptions)
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyValuePairsNullable.forSubjectLess(null to 1, arrayOf("a" to null), emptyInOrderOnlyReportOptions)
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    fun Expect<String>.element(
        successFailureBulletPoint: String,
        index: Int,
        actual: Any,
        expectedKey: String?,
        expectedValue: Int?,
        explaining: Boolean = false,
        explainingValue: Boolean = false,
        withBulletPoint: Boolean = true,
        withKey: Boolean = true,
        withValue: Boolean = true
    ): Expect<String> = this.elementInFormatSpecBase(
        successFailureBulletPoint,
        index,
        actual,
        expectedKey,
        "$toEqualDescr: $expectedValue",
        explaining,
        explainingValue,
        withBulletPoint,
        withKey,
        withValue,
    )

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
        explainingValue: Boolean = false,
        withBulletPoint: Boolean = true,
        withKey: Boolean = true,
        withValue: Boolean = true
    ): Expect<String> =
        element(
            failingBulletPoint,
            index,
            actual,
            expectedKey,
            expectedValue,
            explainingValue = explainingValue,
            withBulletPoint = withBulletPoint,
            withKey = withKey,
            withValue = withValue,
        )

    fun Expect<String>.elementOutOfBound(
        index: Int,
        expectedKey: String,
        expectedValue: Int,
        withBulletPoint: Boolean = true
    ): Expect<String> = element(
        failingBulletPoint,
        index,
        IterableToContainSpecBase.sizeExceeded,
        expectedKey,
        expectedValue,
        explaining = true,
        withBulletPoint = withBulletPoint
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
        keyValuePairs,
        keyValuePairsNullable
    ) { toContainValuesFunArr ->

        fun Expect<Map<out String, Int>>.toContainFun(
            t: Pair<String, Int>,
            vararg tX: Pair<String, Int>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = toContainValuesFunArr(t, tX, report)

        context("empty map") {
            it("a to 1 throws AssertionError, reports a") {
                expect {
                    expect(emptyMap).toContainFun("a" to 1)
                }.toThrow<AssertionError> {
                    message {
                        toContainInOrderOnlyDescr()
                        toContainSize(0, 1)
                        elementOutOfBound(0, "a", 1)
                    }
                }
            }

            it("a to 2, b to 3, c to 4 } throws AssertionError, reports a, b and c") {
                expect {
                    expect(emptyMap).toContainFun("a" to 2, "b" to 3, "c" to 4)
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

        context("map $map") {
            val fluent = expect(map)

            listOf(
                listOf("a" to 1, "b" to 2)
            ).forEach {
                it("${it.joinToString()} does not throw") {
                    fluent.toContainFun(it.first(), *it.drop(1).toTypedArray())
                }
            }

            it("a to 1 throws AssertionError, missing b") {
                expect {
                    fluent.toContainFun("a" to 1)
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", 1)
                        additionalEntries(1 to "b=2")
                        toContainSize(2, 1)
                    }
                }
            }

            it("b 2, a to 1 throws AssertionError, wrong order") {
                expect {
                    fluent.toContainFun("b" to 2, "a" to 1)
                }.toThrow<AssertionError> {
                    message {
                        elementFailing(0, "a=1", "b", 2)
                        elementFailing(1, "b=2", "a", 1)

                        notToContain(additionalEntriesDescr, sizeDescr)
                    }
                }
            }

            it("a to 1, b to 1, c to 4 throws AssertionError, reports b and c") {
                expect {
                    fluent.toContainFun("a" to 1, "b" to 1, "c" to 4)
                }.toThrow<AssertionError> {
                    message {
                        elementSuccess(0, "a=1", "a", 1)
                        elementFailing(1, "b=2", "b", 1)
                        elementOutOfBound(2, "c", 4)
                        notToContain(additionalEntriesDescr)
                    }
                }
            }

            fun Expect<String>.notToContainEntry(key: String): Expect<String> =
                notToContain.regex("\\Q${DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX.getDefault()}.*$key=\\E")

            context("report options") {
                it("shows only failing with report option `showOnlyFailing`") {
                    expect {
                        fluent.toContainFun("a" to 1, "b" to 1, "c" to 3,
                            report = { showOnlyFailing() }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 3)
                            notToContainEntry("a")
                            notToContainEntry("b")
                            elementOutOfBound(2, "c", 3, withBulletPoint = false)
                        }
                    }
                }
                it("shows only failing with report option `showOnlyFailingIfMoreExpectedElementsThan(2)` because there are 3") {
                    expect {
                        fluent.toContainFun(
                            "a" to 1,
                            "b" to 2,
                            "c" to 3,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(2) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 3)
                            notToContainEntry("a")
                            notToContainEntry("b")
                            elementOutOfBound(2, "c", 3, withBulletPoint = false)
                        }
                    }
                }
                it("shows summary with report option `showOnlyFailingIfMoreExpectedElementsThan(3)` because there are 2") {
                    expect {
                        fluent.toContainFun(
                            "a" to 1,
                            "b" to 1,
                            report = { showOnlyFailingIfMoreExpectedElementsThan(3) }
                        )
                    }.toThrow<AssertionError> {
                        message {
                            notToContain(sizeDescr)
                            elementSuccess(0, "a=1", "a", 1)
                            elementFailing(1, "b=2", "b", 1)
                        }
                    }
                }
                it("shows summary without report option if there are  10 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(map).toContainFun(
                            "a" to 1,
                            "b" to 1,
                            "c" to 3,
                            "d" to 4,
                            "e" to 5,
                            "f" to 6,
                            "g" to 7,
                            "h" to 8,
                            "i" to 9,
                            "j" to 10,
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 10)
                            elementSuccess(0, "a=1", "a", 1)
                            elementFailing(1, "b=2", "b", 1)
                            elementOutOfBound(2, "c", 3)
                            elementOutOfBound(3, "d", 4)
                            elementOutOfBound(4, "e", 5)
                            elementOutOfBound(5, "f", 6)
                            elementOutOfBound(6, "g", 7)
                            elementOutOfBound(7, "h", 8)
                            elementOutOfBound(8, "i", 9)
                            elementOutOfBound(9, "j", 10)
                        }
                    }
                }
                it("shows only failing without report option if there are 11 expected elements because default for showOnlyFailingIfMoreExpectedElementsThan is 10") {
                    expect {
                        expect(map).toContainFun(
                            "a" to 1,
                            "b" to 1,
                            "c" to 3,
                            "d" to 4,
                            "e" to 5,
                            "f" to 6,
                            "g" to 7,
                            "h" to 8,
                            "i" to 9,
                            "j" to 10,
                            "k" to 11,
                        )
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 11)
                            notToContainEntry("a")
                            elementFailing(
                                1,
                                "b=2",
                                "b",
                                1,
                                withBulletPoint = false,
                                withKey = false,
                            )
                            elementOutOfBound(2, "c", 3, withBulletPoint = false)
                            elementOutOfBound(3, "d", 4, withBulletPoint = false)
                            elementOutOfBound(4, "e", 5, withBulletPoint = false)
                            elementOutOfBound(5, "f", 6, withBulletPoint = false)
                            elementOutOfBound(6, "g", 7, withBulletPoint = false)
                            elementOutOfBound(7, "h", 8, withBulletPoint = false)
                            elementOutOfBound(8, "i", 9, withBulletPoint = false)
                            elementOutOfBound(9, "j", 10, withBulletPoint = false)
                            elementOutOfBound(10, "k", 11, withBulletPoint = false)
                        }
                    }
                }
            }
        }
    }

    describeFun(keyValuePairsNullable) {

        fun Expect<Map<out String?, Int?>>.toContainFun(
            t: Pair<String?, Int?>,
            vararg tX: Pair<String?, Int?>,
            report: InOrderOnlyReportingOptions.() -> Unit = emptyInOrderOnlyReportOptions
        ) = keyValuePairsNullable(this, t, tX, report)

        val nullableFluent = expect(nullableMap)

        context("map: $nullableMap") {
            listOf(
                listOf("a" to null, null to 1, "b" to 2)
            ).forEach {
                it("$it does not throw") {
                    expect(nullableMap).toContainFun(it.first(), *it.drop(1).toTypedArray())
                }
            }

            it("a to null throws AssertionError, missing b") {
                expect {
                    nullableFluent.toContainFun("a" to null)
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
                    nullableFluent.toContainFun("b" to 2, "a" to null, null to 1)
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
                    nullableFluent.toContainFun("a" to null, "c" to 3, "b" to 3)
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
