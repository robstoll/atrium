package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class KeyValueLikeFeatureAssertionsSpec<T : Any, TNullable : Any>(
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyFeature: Feature0<T, String>,
    key: Fun1<T, Expect<String>.() -> Unit>,
    valueFeature: Feature0<T, Int>,
    value: Fun1<T, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<TNullable, String?>,
    nullableKey: Fun1<TNullable, Expect<String?>.() -> Unit>,
    nullableValueFeature: Feature0<TNullable, Int?>,
    nullableValue: Fun1<TNullable, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val mapEntry = creator("hello", 1)
    val nullMapEntry = creatorNullable(null, null)

    include(object : SubjectLessSpec<T>(describePrefix,
        keyFeature.forSubjectLess(),
        key.forSubjectLess { endsWith("a") },
        valueFeature.forSubjectLess(),
        value.forSubjectLess { isGreaterThan(2) }
    ) {})
    include(object : SubjectLessSpec<TNullable>(
        "$describePrefix[nullable] ",
        nullableKeyFeature.forSubjectLess(),
        nullableKey.forSubjectLess { toBe(null) },
        nullableValueFeature.forSubjectLess(),
        nullableValue.forSubjectLess { toBe(null) }
    ) {})

    include(object : AssertionCreatorSpec<T>(
        describePrefix, mapEntry,
        key.forAssertionCreatorSpec("$toBeDescr: hello") { toBe("hello") },
        value.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})
    include(object : AssertionCreatorSpec<TNullable>(
        "$describePrefix[nullable]", nullMapEntry,
        nullableKey.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) },
        nullableValue.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val nullableMapEntry = creatorNullable("hello", 1)

    val fluent = expect(mapEntry)
    val nullableFluent = expect(nullableMapEntry)
    val nullFluent = expect(nullMapEntry)

    describeFun(keyFeature, key, valueFeature, value) {
        val keyFunctions = unifySignatures(keyFeature, key)
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
