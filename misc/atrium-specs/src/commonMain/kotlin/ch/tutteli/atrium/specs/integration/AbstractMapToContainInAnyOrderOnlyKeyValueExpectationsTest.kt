package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.additionalEntries
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.additionalEntriesDescr
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entryFailing
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entryFailingExplaining
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entryNonExisting
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entrySuccess
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.sizeDescr
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.toContainInAnyOrderOnlyDescr
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.toContainSize
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.map
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.nullableMap
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractMapToContainInAnyOrderOnlyKeyValueExpectationsTest(
    private val keyWithValueAssertionsSpec: MFun2<String, Int, Expect<Int>.() -> Unit>,
    private val keyWithNullableValueAssertionsSpec: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>
) : ExpectationFunctionBaseTest() {

    private val emptyMap: Map<out String, Int> = mapOf()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            keyWithValueAssertionsSpec.forSubjectLessTest(
                keyValue("a") { toEqual(1) },
                arrayOf(keyValue("a") { toBeLessThanOrEqualTo(2) })
            )
        ),
        SubjectLessTestData(
            keyWithNullableValueAssertionsSpec.forSubjectLessTest(
                keyNullableValue(null) { toEqual(1) },
                arrayOf(keyNullableValue("a", null))
            ),
            groupPrefix = "[nullable Key]"
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            map,
            ExpectationCreatorTriple<Map<out String, Int>>(
                keyWithValueAssertionsSpec.name, "$toBeLessThanDescr: 2",
                {
                    keyWithValueAssertionsSpec(
                        this,
                        keyValue("a") { toBeLessThan(2) },
                        arrayOf(keyValue("b") { toBeLessThan(3) })
                    )
                },
                { keyWithValueAssertionsSpec(this, keyValue("a") { }, arrayOf(keyValue("a") { })) }
            )
        ),
        ExpectationCreatorTestData(
            mapOf("a" to 1, "b" to null),
            ExpectationCreatorTriple<Map<out String?, Int?>>(
                keyWithNullableValueAssertionsSpec.name, "$toBeLessThanDescr: 2",
                {
                    keyWithNullableValueAssertionsSpec(
                        this,
                        keyNullableValue("a") { toBeLessThan(2) },
                        arrayOf(keyNullableValue("b", null))
                    )
                },
                { keyWithNullableValueAssertionsSpec(this, keyNullableValue("a") { }, arrayOf()) }
            ),
            groupPrefix = "[nullable]"
        )
    )

    @TestFactory
    fun empty_map() = testFactoryNonNullable(
        keyWithValueAssertionsSpec, keyWithNullableValueAssertionsSpec
    ) { toContainKeyValueAssertionsFun ->
        it("a to { toBe(1) } throws AssertionError, reports a") {
            expect {
                expect(emptyMap).toContainKeyValueAssertionsFun(keyValue("a") { toEqual(1) }, arrayOf())
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(0, 1)
                    entryNonExisting("a", "$toEqualDescr: 1")
                    notToContain(additionalEntriesDescr)
                }
            }
        }

        it("a to { isLessThan(1) }, b to { toBe(3) }, c to { isLessThan(4) } } throws AssertionError, reports a, b and c") {
            expect {
                expect(emptyMap).toContainKeyValueAssertionsFun(
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

    @TestFactory
    fun map_a_to_1_b_to_2() = testFactoryNonNullable(
        keyWithValueAssertionsSpec, keyWithNullableValueAssertionsSpec
    ) { toContainKeyValueAssertionsFun ->
        listOf(
            "a to { toBe(1) }, b to { toBe(2) }" to listOf(
                keyValue("a") { toEqual(1) },
                keyValue("b") { toEqual(2) }),
            "b to { toBe(2) }, a to { toBe(1) }" to listOf(
                keyValue("b") { toEqual(2) },
                keyValue("a") { toEqual(1) }),
            "b to { isGreaterThan(1) }, a to { isLessThan(2) }" to listOf(
                keyValue("b") { toBeGreaterThan(1) },
                keyValue("a") { toBeLessThan(2) }),
            "a to { isGreaterThan(0) }, b to { isLessThan(3) }" to listOf(
                keyValue("a") { toBeGreaterThan(0) },
                keyValue("b") { toBeLessThan(3) })
        ).forEach { (description, list) ->
            it("$description does not throw") {
                expect(map).toContainKeyValueAssertionsFun(list.first(), list.drop(1).toTypedArray())
            }
        }

        it("a to { isLessThan(2) } throws AssertionError, reports second a and missing b") {
            expect {
                expect(map).toContainKeyValueAssertionsFun(keyValue("a") { toBeLessThan(2) }, arrayOf())
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(2, 1)
                    entrySuccess("a", 1, "$toBeLessThanDescr: 2")
                    additionalEntries("b" to 2)
                }
            }
        }

        it("a to { isLessThan(2) }, a to { toBe(1) } throws AssertionError, reports second a and missing b") {
            expect {
                expect(map).toContainKeyValueAssertionsFun(
                    keyValue("a") { toBeLessThan(2) },
                    arrayOf(keyValue("a") { toEqual(1) })
                )
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

        it("a to { isLessThan(3), b to { isLessThan(1), c to { toBe(4) } throws AssertionError, reports b and c") {
            expect {
                expect(map).toContainKeyValueAssertionsFun(
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

    @TestFactory
    fun nullable_map() = testFactory(keyWithNullableValueAssertionsSpec) { toContainKeyWithNullableValueAssertionsFun ->
        listOf(
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
                expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
                    keyValues.first(),
                    keyValues.drop(1).toTypedArray()
                )
            }
        }

        it("a to { toBe(1) } throws AssertionError, reports failure and missing null and b") {
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

        it("a to { toBe(1) }, c to { isLessThan(3) }, null to null, b to { isLessThan(3) } throws AssertionError, reports all but b") {
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
