package ch.tutteli.atrium.specs.integration.utils

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.applyExpectationCreatorTestSetup
import ch.tutteli.atrium.specs.integration.applySubjectLessTestSetup
import ch.tutteli.atrium.testfactories.PlatformTestNode
import ch.tutteli.atrium.testfactories.PlatformTestNodeContainer
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation

fun <T : Any, TNullable : Any> keyValueLikeTestFactory(
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyFeature: Feature0<T, String>,
    key: Fun1<T, Expect<String>.() -> Unit>,
    valueFeature: Feature0<T, Int>,
    value: Fun1<T, Expect<Int>.() -> Unit>,
    keyFeatureNullable: Feature0<TNullable, String?>,
    keyNullable: Fun1<TNullable, Expect<String?>.() -> Unit>,
    valueFeatureNullable: Feature0<TNullable, Int?>,
    valueNullable: Fun1<TNullable, Expect<Int?>.() -> Unit>,
): PlatformTestNodeContainer<PlatformTestNode> = testFactory {
    val mapEntry = creator("hello", 1)
    val nullMapEntry = creatorNullable(null, null)
    val nullableMapEntry = creatorNullable("hello", 1)

    describe("check expectation function can be used in a proof explanation without subject defined and does not throw") {
        applySubjectLessTestSetup(
            SubjectLessTestData(
                keyFeature.forSubjectLessTest(),
                key.forSubjectLessTest { toEndWith("a") },
                valueFeature.forSubjectLessTest(),
                value.forSubjectLessTest { toBeGreaterThan(2) }
            ),
            arrayOf(
                SubjectLessTestData(
                    keyFeatureNullable.forSubjectLessTest(),
                    keyNullable.forSubjectLessTest { toEqual(null) },
                    valueFeatureNullable.forSubjectLessTest(),
                    valueNullable.forSubjectLessTest { toEqual(null) },
                    groupPrefix = "[nullable]"
                )
            )
        )
    }
    describe("specifying two expectationCreator-lambda, one is empty") {
        applyExpectationCreatorTestSetup(
            ExpectationCreatorTestData(
                mapEntry,
                key.forExpectationCreatorTest("$toEqualDescr: hello") { toEqual("hello") },
                value.forExpectationCreatorTest("$toEqualDescr: 1") { toEqual(1) }
            ),
            arrayOf(
                ExpectationCreatorTestData(
                    nullMapEntry,
                    keyNullable.forExpectationCreatorTest("$toEqualDescr: null") { toEqual(null) },
                    valueNullable.forExpectationCreatorTest("$toEqualDescr: null") { toEqual(null) },
                    groupPrefix = "[nullable]"
                )
            )
        )
    }

    val keyFunctions = uncheckedToNonNullable(
        unifySignatures(keyFeature, key),
        unifySignatures(keyFeatureNullable, keyNullable)
    )

    val valueFunctions = unifySignatures(valueFeature, value)

    describe("$mapEntry") {
        keyFunctions.forEach { (name, keyFun, _) ->
            it("$name - startsWith(h) holds") {
                expect(mapEntry).keyFun { toStartWith("h") }
            }
            it("$name - endsWith(h) fails") {
                expect {
                    expect(mapEntry).keyFun { toEndWith("h") }
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$keyName: \"hello\"",
                        DescriptionCharSequenceExpectation.TO_END_WITH.getDefault() + ": \"h\""
                    )
                }
            }
        }

        valueFunctions.forEach { (name, valueFun, _) ->
            it("$name - isGreaterThan(0) holds") {
                expect(mapEntry).valueFun { toBeGreaterThan(0) }
            }
            it("$name - isGreaterThan(1) fails") {
                expect {
                    expect(mapEntry).valueFun { toBeGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$valueName: 1",
                        DescriptionComparableExpectation.TO_BE_GREATER_THAN.getDefault() + ": 1"
                    )
                }
            }
        }
    }

    val nullableKeyFunctions = unifySignatures(keyFeatureNullable, keyNullable)
    val nullableValueFunctions = unifySignatures(valueFeatureNullable, valueNullable)

    describe("$nullableMapEntry") {
        nullableKeyFunctions.forEach { (name, nullableKeyFun, _) ->
            it("$name - toBe(hello) holds") {
                expect(nullableMapEntry).nullableKeyFun { toEqual("hello") }
            }
            it("$name - toBe(null) throws AssertionError") {
                expect {
                    expect(nullableMapEntry).nullableKeyFun { toEqual(null) }
                }.toThrow<AssertionError> {
                    messageToContain("$keyName: \"hello\"")
                }
            }
        }
        nullableValueFunctions.forEach { (name, nullableValueFun, _) ->
            it("$name - isGreaterThan(0) holds") {
                expect(nullableMapEntry).nullableValueFun { notToEqualNull { toBeGreaterThan(0) } }
            }
            it("$name - toBe(null) throws AssertionError") {
                expect {
                    expect(nullableMapEntry).nullableValueFun { toEqual(null) }
                }.toThrow<AssertionError> {
                    messageToContain("$valueName: 1")
                }
            }
        }
    }
    describe("$nullMapEntry") {
        nullableKeyFunctions.forEach { (name, nullableKeyFun, _) ->
            it("$name - toBe(null) holds") {
                expect(nullMapEntry).nullableKeyFun { toEqual(null) }
            }
            it("$name - toBe(hello) throws AssertionError") {
                expect {
                    expect(nullMapEntry).nullableKeyFun { toEqual("hello") }
                }.toThrow<AssertionError> {
                    messageToContain("$keyName: null", "$toEqualDescr: \"hello\"")
                }
            }
        }
        nullableValueFunctions.forEach { (name, nullableValueFun, _) ->
            it("$name - toBe(null) holds") {
                expect(nullMapEntry).nullableValueFun { toEqual(null) }
            }
            it("$name - toBe(1) throws AssertionError") {
                expect {
                    expect(nullMapEntry).nullableValueFun { toEqual(1) }
                }.toThrow<AssertionError> {
                    messageToContain("$valueName: null")
                }
            }
        }
    }
}
