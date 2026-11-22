package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.integration.utils.keyValueLikeTestFactory
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractMapEntryExpectationsTest(
    private val toEqualKeyValueSpec: Fun2<Map.Entry<String, Int>, String, Int>,
    private val toEqualKeyValueNullableSpec: Fun2<Map.Entry<String?, Int?>, String?, Int?>,
    private val keyFeature: Feature0<Map.Entry<String, Int>, String>,
    private val key: Fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>,
    private val valueFeature: Feature0<Map.Entry<String, Int>, Int>,
    private val value: Fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>,
    private val nullableKeyFeature: Feature0<Map.Entry<String?, Int?>, String?>,
    private val nullableKey: Fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>,
    private val nullableValueFeature: Feature0<Map.Entry<String?, Int?>, Int?>,
    private val nullableValue: Fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toEqualKeyValueSpec.forSubjectLessTest("key", 1)
        ),
        SubjectLessTestData<Map.Entry<String?, Int?>>(
            toEqualKeyValueNullableSpec.forSubjectLessTest("key", 1),
            groupPrefix = "[nullable]"
        )
    )

    @TestFactory
    fun keyValueLikeTest() = keyValueLikeTestFactory(
        ::mapEntry,
        ::mapEntry,
        "key",
        "value",
        keyFeature,
        key,
        valueFeature,
        value,
        nullableKeyFeature,
        nullableKey,
        nullableValueFeature,
        nullableValue
    )

    @TestFactory
    fun toEqualKeyValue() = testFactoryNonNullable(
        toEqualKeyValueSpec, toEqualKeyValueNullableSpec
    ) { toEqualKeyValueFun ->
        val mapEntry = mapEntry("hello", 1)
        describe("map $mapEntry") {
            it("hello to 1 does not throw") {
                expect(mapEntry).toEqualKeyValueFun("hello", 1)
            }

            it("hello to 2 throws AssertionError") {
                expect {
                    expect(mapEntry).toEqualKeyValueFun("hello", 2)
                }.toThrow<AssertionError> {
                    message {
                        toContain("value: 1", "$toEqualDescr: 2")
                        notToContain("key")
                    }
                }
            }
            it("b to 1 throws AssertionError") {
                expect {
                    expect(mapEntry).toEqualKeyValueFun("b", 1)
                }.toThrow<AssertionError> {
                    message {
                        toContain("key: \"hello\"", "$toEqualDescr: \"b\"")
                        notToContain("value")
                    }
                }
            }
        }
    }

    @TestFactory
    fun toEqualKeyValueNullable() = testFactory(toEqualKeyValueNullableSpec) { toEqualKeyValueFun ->
        val mapEntryNullable2 = mapEntry(null as String?, null as Int?)

        describe("map $mapEntryNullable2") {
            it("null to null does not throw") {
                expect(mapEntryNullable2).toEqualKeyValueFun(null, null)
            }

            it("null to 2 throws AssertionError") {
                expect {
                    expect(mapEntryNullable2).toEqualKeyValueFun(null, 2)
                }.toThrow<AssertionError> {
                    message {
                        toContain("value: null", "$toEqualDescr: 2")
                        notToContain("key")
                    }
                }
            }
            it("b to null throws AssertionError") {
                expect {
                    expect(mapEntryNullable2).toEqualKeyValueFun("b", null)
                }.toThrow<AssertionError> {
                    message {
                        toContain("key: null", "$toEqualDescr: \"b\"")
                        notToContain("value")
                    }
                }
            }
        }
    }
}
