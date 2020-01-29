package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapFeatureAssertionsSpec(
    keysFeature: Feature0<Map<String, Int>, Set<String>>,
    keys: Fun1<Map<String, Int>, Expect<Set<String>>.() -> Unit>,
    valuesFeature: Feature0<Map<String, Int>, Collection<Int>>,
    values: Fun1<Map<String, Int>, Expect<Collection<Int>>.() -> Unit>,
    getExistingFeature: Feature1<Map<String, Int>, String, Int>,
    getExisting: Fun2<Map<String, Int>, String, Expect<Int>.() -> Unit>,
    getExistingNullableFeature: Feature1<Map<String?, Int?>, String?, Int?>,
    getExistingNullable: Fun2<Map<String?, Int?>, String?, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val map = mapOf("a" to 1, "b" to 2)
    val mapNullable = mapOf("a" to 1, "b" to null, null to 3)

    include(object : SubjectLessSpec<Map<String, Int>>(describePrefix,
        keysFeature.forSubjectLess(),
        keys.forSubjectLess { isEmpty() },
        valuesFeature.forSubjectLess(),
        values.forSubjectLess { isEmpty() },
        getExistingFeature.forSubjectLess("a"),
        getExisting.forSubjectLess("a") { isGreaterThan(1) }
    ) {})
    include(object : SubjectLessSpec<Map<String?, Int?>>("$describePrefix[nullable] ",
        getExistingNullableFeature.forSubjectLess("a"),
        getExistingNullable.forSubjectLess("a") { toBe(null) }
    ) {})

    include(object : AssertionCreatorSpec<Map<String, Int>>(
        describePrefix, map,
        keys.forAssertionCreatorSpec("$toBeDescr: a") { containsExactly({ toBe("a") }, { toBe("b") }) },
        values.forAssertionCreatorSpec("$toBeDescr: 1") { containsExactly({ toBe(1) }, { toBe(2) }) },
        getExisting.forAssertionCreatorSpec("$toBeDescr: 2", "b") { toBe(2) }
    ) {})
    include(object : AssertionCreatorSpec<Map<String?, Int?>>(
        "$describePrefix[nullable] ", mapNullable,
        getExistingNullable.forAssertionCreatorSpec("$toBeDescr: 2", "b") { toBe(null) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(map)
    val fluentNullable = expect(mapNullable)

    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()

    describeFun(keysFeature, keys, valuesFeature, values) {
        val keysFunctions = unifySignatures(keysFeature, keys)
        val valuesFunctions = unifySignatures(valuesFeature, values)

        context("map with two entries") {
            keysFunctions.forEach { (name, keysFun, _) ->
                it("$name - hasSize(2) holds") {
                    fluent.keysFun { hasSize(2) }
                }
                it("$name - hasSize(1) fails") {
                    expect {
                        fluent.keysFun { hasSize(1) }
                    }.toThrow<AssertionError> {
                        messageContains("keys: [a, b]")
                    }
                }
            }
            valuesFunctions.forEach { (name, valuesFun, _) ->
                it("$name - hasSize(2) holds") {
                    fluent.valuesFun { hasSize(2) }
                }
                it("$name - hasSize(1) fails") {
                    expect {
                        fluent.valuesFun { hasSize(1) }
                    }.toThrow<AssertionError> {
                        messageContains("values: [1, 2]")
                    }
                }
            }
        }
    }

    describeFun(getExistingFeature, getExisting) {
        val getExistingFunctions = unifySignatures(getExistingFeature, getExisting)

        context("map $map") {
            getExistingFunctions.forEach { (name, getExistingFun, hasExtraHint) ->
                it("$name - can perform sub-assertion on existing key") {
                    fluent.getExistingFun("a") { toBe(1) }
                }
                it("$name - non-existing key throws" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        fluent.getExistingFun("c") { toBe(3) }
                    }.toThrow<AssertionError> {
                        messageContains("get(\"c\"): $keyDoesNotExist")
                        if (hasExtraHint) messageContains("$toBeDescr: 3")
                    }
                }
            }
        }
    }

    describeFun(getExistingNullableFeature, getExistingNullable) {

        val getExistingFunctions = unifySignatures(getExistingNullableFeature, getExistingNullable)
        context("map $mapNullable") {
            getExistingFunctions.forEach { (name, getExistingFun, hasExtraHint) ->
                it("$name - can perform sub-assertion on existing key") {
                    fluentNullable.getExistingFun("a") { toBe(1) }
                }
                it("$name - can perform sub-assertion on existing key which is null") {
                    fluentNullable.getExistingFun(null) { toBe(3) }
                }
                it("$name - can perform sub-assertion on existing key whose value is null") {
                    fluentNullable.getExistingFun("b") { toBe(null) }
                }
                it("$name - non-existing key throws" + if (hasExtraHint) " but shows intended sub-assertion" else "") {
                    expect {
                        fluentNullable.getExistingFun("c") { toBe(null) }
                    }.toThrow<AssertionError> {
                        messageContains("get(\"c\"): $keyDoesNotExist")
                        if (hasExtraHint) messageContains("$toBeDescr: null")
                    }
                }
            }
        }
    }
})
