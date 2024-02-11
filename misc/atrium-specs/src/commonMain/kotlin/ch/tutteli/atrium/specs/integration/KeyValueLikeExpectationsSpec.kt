package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class KeyValueLikeExpectationsSpec<T : Any, TNullable : Any>(
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
    describePrefix: String = "[Atrium] "
) : Spek({

    val mapEntry = creator("hello", 1)
    val nullMapEntry = creatorNullable(null, null)

    include(object : SubjectLessSpec<T>(describePrefix,
        keyFeature.forSubjectLess(),
        key.forSubjectLess { toEndWith("a") },
        valueFeature.forSubjectLess(),
        value.forSubjectLess { toBeGreaterThan(2) }
    ) {})
    include(object : SubjectLessSpec<TNullable>(
        "$describePrefix[nullable] ",
        keyFeatureNullable.forSubjectLess(),
        keyNullable.forSubjectLess { toEqual(null) },
        valueFeatureNullable.forSubjectLess(),
        valueNullable.forSubjectLess { toEqual(null) }
    ) {})

    include(object : AssertionCreatorSpec<T>(
        describePrefix, mapEntry,
        key.forAssertionCreatorSpec("$toEqualDescr: hello") { toEqual("hello") },
        value.forAssertionCreatorSpec("$toEqualDescr: 1") { toEqual(1) }
    ) {})
    include(object : AssertionCreatorSpec<TNullable>(
        "$describePrefix[nullable]", nullMapEntry,
        keyNullable.forAssertionCreatorSpec("$toEqualDescr: null") { toEqual(null) },
        valueNullable.forAssertionCreatorSpec("$toEqualDescr: null") { toEqual(null) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val nullableMapEntry = creatorNullable("hello", 1)

    describeFun(keyFeature, key, keyFeatureNullable, keyNullable, valueFeature, value, valueFeature, valueNullable) {
        val keyFunctions = uncheckedToNonNullable(
            unifySignatures(keyFeature, key),
            unifySignatures(keyFeatureNullable, keyNullable)
        )

        val valueFunctions = unifySignatures(valueFeature, value)
        context("$mapEntry") {
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
    }


    describeFun(keyFeatureNullable, keyNullable, valueFeatureNullable, valueNullable) {
        val keyFunctions = unifySignatures(keyFeatureNullable, keyNullable)
        val valueFunctions = unifySignatures(valueFeatureNullable, valueNullable)

        context("$nullableMapEntry") {
            keyFunctions.forEach { (name, nullableKeyFun, _) ->
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
            valueFunctions.forEach { (name, nullableValueFun, _) ->
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
        context("$nullMapEntry") {
            keyFunctions.forEach { (name, nullableKeyFun, _) ->
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
            valueFunctions.forEach { (name, nullableValueFun, _) ->
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
})
