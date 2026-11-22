package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.entry
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.keyDoesNotExist
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.map
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.nullableMap
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.toBeLessThanDescr
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTriple
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

fun keyValue(key: String, assertionCreator: Expect<Int>.() -> Unit): Pair<String, Expect<Int>.() -> Unit> =
    key to assertionCreator

fun keyNullableValue(
    key: String?,
    assertionCreator: (Expect<Int>.() -> Unit)?
): Pair<String?, (Expect<Int>.() -> Unit)?> = key to assertionCreator

@Suppress("FunctionName")
abstract class AbstractMapToContainInAnyOrderKeyValueExpectationsTest(
    private val keyWithValueAssertionsSpec: MFun2<String, Int, Expect<Int>.() -> Unit>,
    private val keyWithNullableValueAssertionsSpec: MFun2<String?, Int?, (Expect<Int>.() -> Unit)?>
) : ExpectationFunctionBaseTest() {

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
                keyWithValueAssertionsSpec.name, "$toEqualDescr: 1",
                { keyWithValueAssertionsSpec(this, keyValue("a") { toEqual(1) }, arrayOf()) },
                { keyWithValueAssertionsSpec(this, keyValue("a") { }, arrayOf()) }
            )
        ),
        ExpectationCreatorTestData(
            mapOf("a" to 1, "b" to null),
            ExpectationCreatorTriple<Map<out String?, Int?>>(
                keyWithNullableValueAssertionsSpec.name, "$toEqualDescr: 1",
                { keyWithNullableValueAssertionsSpec(this, keyNullableValue("a") { toEqual(1) }, arrayOf()) },
                { keyWithNullableValueAssertionsSpec(this, keyNullableValue("a") { }, arrayOf()) }
            ),
            groupPrefix = "[nullable]"
        )
    )

    @TestFactory
    fun map_a_to_1_b_to_2() = testFactoryNonNullable(
        keyWithValueAssertionsSpec, keyWithNullableValueAssertionsSpec
    ) { toContainKeyValueAssertionsFun ->
        listOf(
            "a { toBe(1) }" to listOf(keyValue("a") { toEqual(1) }),
            "b { toBe(2) }" to listOf(keyValue("b") { toEqual(2) }),
            "a { toBe(1) }, b { toBe(2) }" to listOf(keyValue("a") { toEqual(1) }, keyValue("b") { toEqual(2) }),
            "b { toBe(2) }, a { toBe(1) }" to listOf(keyValue("b") { toEqual(2) }, keyValue("a") { toEqual(1) })
        ).forEach { (description, keyValues) ->
            it("$description does not throw") {
                expect(map).toContainKeyValueAssertionsFun(
                    keyValues.first(),
                    keyValues.drop(1).toTypedArray()
                )
            }
        }

        it("a { isLessThan(2) } and a { isGreaterThan(0) } does not throw (no unique match)") {
            expect(map).toContainKeyValueAssertionsFun(
                keyValue("a") { toBeLessThan(2) },
                arrayOf(keyValue("a") { toBeGreaterThan(0) })
            )
        }

        it("a { isLessThan(3), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
            expect {
                expect(map).toContainKeyValueAssertionsFun(
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

    @TestFactory
    fun nullable_map() = testFactory(keyWithNullableValueAssertionsSpec) { toContainKeyWithNullableValueAssertionsFun ->
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
                expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
                    keyValues.first(),
                    keyValues.drop(1).toTypedArray()
                )
            }
        }

        it("(a, null), b { isLessThan(2) }, c { isLessThan(1) }} throws AssertionError, reports b and c") {
            expect {
                expect(nullableMap).toContainKeyWithNullableValueAssertionsFun(
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
