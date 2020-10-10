package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapEntryAssertionsSpec(
    isKeyValue: Fun2<Map.Entry<String, Int>, String, Int>,
    isKeyValueNullable: Fun2<Map.Entry<String?, Int?>, String?, Int?>,
    keyFeature: Feature0<Map.Entry<String, Int>, String>,
    key: Fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>,
    valueFeature: Feature0<Map.Entry<String, Int>, Int>,
    value: Fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<Map.Entry<String?, Int?>, String?>,
    nullableKey: Fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>,
    nullableValueFeature: Feature0<Map.Entry<String?, Int?>, Int?>,
    nullableValue: Fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Map.Entry<String, Int>>(
        describePrefix,
        isKeyValue.forSubjectLess("key", 1)
    ) {})
    include(object : SubjectLessSpec<Map.Entry<String?, Int?>>(
        "$describePrefix[nullable] ",
        isKeyValueNullable.forSubjectLess("key", 1)
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val mapEntry = mapEntry("hello", 1)
    val fluent = expect(mapEntry)

    describeFun(isKeyValue, isKeyValueNullable) {
        val isKeyValueFunctions = uncheckedToNonNullable(isKeyValue, isKeyValueNullable)

        context("map $mapEntry") {
            isKeyValueFunctions.forEach { (name, isKeyValueFun) ->
                it("$name - hello to 1 does not throw") {
                    fluent.isKeyValueFun("hello", 1)
                }

                it("$name - hello to 2 throws AssertionError") {
                    expect {
                        fluent.isKeyValueFun("hello", 2)
                    }.toThrow<AssertionError> {
                        message {
                            contains("value: 1", "$toBeDescr: 2")
                            containsNot("key")
                        }
                    }
                }
                it("$name - b to 1 throws AssertionError") {
                    expect {
                        fluent.isKeyValueFun("b", 1)
                    }.toThrow<AssertionError> {
                        message {
                            contains("key: \"hello\"", "$toBeDescr: \"b\"")
                            containsNot("value")
                        }
                    }
                }
            }
        }
    }

    describeFun(isKeyValueNullable) {
        val isKeyValueFun = isKeyValueNullable.lambda
        val mapEntryNullable2 = mapEntry(null as String?, null as Int?)
        val fluentNullable = expect(mapEntryNullable2)

        context("map $mapEntryNullable2") {
            it("null to null does not throw") {
                fluentNullable.isKeyValueFun(null, null)
            }

            it("null to 2 throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun(null, 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: null", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            it("b to null throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun("b", null)
                }.toThrow<AssertionError> {
                    message {
                        contains("key: null", "$toBeDescr: \"b\"")
                        containsNot("value")
                    }
                }
            }
        }
    }

    val keyName = "key"
    val valueName = "value"
    val nullMapEntry = mapEntry(null, null)
    val nullableMapEntry = mapEntry("hello", 1)
    val nullableFluent: Expect<Map.Entry<String?, Int?>> = expect(nullableMapEntry)
    val nullFluent: Expect<Map.Entry<String?, Int?>> = expect(nullMapEntry)

    include(object : SubjectLessSpec<Map.Entry<String, Int>>(describePrefix,
        keyFeature.forSubjectLess(),
        key.forSubjectLess { endsWith("a") },
        valueFeature.forSubjectLess(),
        value.forSubjectLess { isGreaterThan(2) }
    ) {})

    include(object : SubjectLessSpec<Map.Entry<String?, Int?>>(
        "$describePrefix[nullable] ",
        nullableKeyFeature.forSubjectLess(),
        nullableKey.forSubjectLess { toBe(null) },
        nullableValueFeature.forSubjectLess(),
        nullableValue.forSubjectLess { toBe(null) }
    ) {})

    include(object : AssertionCreatorSpec<Map.Entry<String, Int>>(
        describePrefix, mapEntry,
        key.forAssertionCreatorSpec("$toBeDescr: hello") { toBe("hello") },
        value.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})

    include(object : AssertionCreatorSpec<Map.Entry<String?, Int?>>(
        "$describePrefix[nullable]", nullMapEntry,
        nullableKey.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) },
        nullableValue.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) }
    ) {})

    describeFun(keyFeature, key, nullableKeyFeature, nullableKey, valueFeature, value, valueFeature, nullableValue) {
        val keyFunctions = uncheckedToNonNullable(
            unifySignatures(keyFeature, key),
            unifySignatures(nullableKeyFeature, nullableKey)
        )

        val valueFunctions = unifySignatures(valueFeature, value)
        context("$mapEntry") {
            keyFunctions.forEach { (name, keyFun, _) ->
                it("$name - startsWith(h) holds") {
                    fluent.keyFun { startsWith("h") }
                }
                it("$name - endsWith(h) fails") {
                    expect {
                        fluent.keyFun { endsWith('h') }
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$keyName: \"hello\"",
                            DescriptionCharSequenceAssertion.ENDS_WITH.getDefault() + ": \"h\""
                        )
                    }
                }
            }

            valueFunctions.forEach { (name, valueFun, _) ->
                it("$name - isGreaterThan(0) holds") {
                    fluent.valueFun { isGreaterThan(0) }
                }
                it("$name - isGreaterThan(1) fails") {
                    expect {
                        fluent.valueFun { isGreaterThan(1) }
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$valueName: 1",
                            DescriptionComparableAssertion.IS_GREATER_THAN.getDefault() + ": 1"
                        )
                    }
                }
            }
        }
    }

    describeFun(nullableKeyFeature, nullableKey, nullableValueFeature, nullableValue) {
        val keyFunctions = unifySignatures(nullableKeyFeature, nullableKey)
        val valueFunctions = unifySignatures(nullableValueFeature, nullableValue)

        context("$nullableMapEntry") {
            keyFunctions.forEach { (name, nullableKeyFun, _) ->
                it("$name - toBe(hello) holds") {
                    nullableFluent.nullableKeyFun { toBe("hello") }
                }
                it("$name - toBe(null) throws AssertionError") {
                    expect {
                        nullableFluent.nullableKeyFun { toBe(null) }
                    }.toThrow<AssertionError> {
                        messageContains("$keyName: \"hello\"")
                    }
                }
            }
            valueFunctions.forEach { (name, nullableValueFun, _) ->
                it("$name - isGreaterThan(0) holds") {
                    nullableFluent.nullableValueFun { notToBeNull { isGreaterThan(0) } }
                }
                it("$name - toBe(null) throws AssertionError") {
                    expect {
                        nullableFluent.nullableValueFun { toBe(null) }
                    }.toThrow<AssertionError> {
                        messageContains("$valueName: 1")
                    }
                }
            }
        }
        context("$nullMapEntry") {
            keyFunctions.forEach { (name, nullableKeyFun, _) ->
                it("$name - toBe(null) holds") {
                    nullFluent.nullableKeyFun { toBe(null) }
                }
                it("$name - toBe(hello) throws AssertionError") {
                    expect {
                        nullFluent.nullableKeyFun { toBe("hello") }
                    }.toThrow<AssertionError> {
                        messageContains("$keyName: null", "$toBeDescr: \"hello\"")
                    }
                }
            }
            valueFunctions.forEach { (name, nullableValueFun, _) ->
                it("$name - toBe(null) holds") {
                    nullFluent.nullableValueFun { toBe(null) }
                }
                it("$name - toBe(1) throws AssertionError") {
                    expect {
                        nullFluent.nullableValueFun { toBe(1) }
                    }.toThrow<AssertionError> {
                        messageContains("$valueName: null")
                    }
                }
            }
        }
    }
})
