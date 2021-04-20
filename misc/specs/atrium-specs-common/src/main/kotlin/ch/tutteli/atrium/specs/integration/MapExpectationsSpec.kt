package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion
import ch.tutteli.atrium.translations.DescriptionMapLikeAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapExpectationsSpec(
    containsKey: Fun1<Map<out String, *>, String>,
    containsKeyNullable: Fun1<Map<out String?, *>, String?>,
    containsNotKey: Fun1<Map<out String, *>, String>,
    containsNotKeyNullable: Fun1<Map<out String?, *>, String?>,
    getExistingFeature: Feature1<Map<out String, Int>, String, Int>,
    getExisting: Fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>,
    getExistingNullableFeature: Feature1<Map<out String?, Int?>, String?, Int?>,
    getExistingNullable: Fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>,
    isEmpty: Fun0<Map<*, *>>,
    isNotEmpty: Fun0<Map<*, *>>,
    keys: Fun1<Map<out String, Int>, Expect<Set<String>>.() -> Unit>,
    keysFeature: Feature0<Map<out String, Int>, Set<String>>,
    valuesFeature: Feature0<Map<out String, Int>, Collection<Int>>,
    values: Fun1<Map<out String, Int>, Expect<Collection<Int>>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    @Suppress("UNCHECKED_CAST")
    fun <K, V, T> T.unchecked1(): Pair<String, Expect<Map<out K, V>>.() -> Unit> =
        this as Pair<String, Expect<Map<out K, V>>.() -> Unit>

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        containsKey.forSubjectLess("a").unchecked1(),
        containsNotKey.forSubjectLess("a").unchecked1(),
        isEmpty.forSubjectLess().unchecked1(),
        isNotEmpty.forSubjectLess().unchecked1(),
        keysFeature.forSubjectLess(),
        keys.forSubjectLess { this.toBeEmpty() },
        valuesFeature.forSubjectLess(),
        values.forSubjectLess { this.toBeEmpty() },
        getExistingFeature.forSubjectLess("a"),
        getExisting.forSubjectLess("a") { isGreaterThan(1) }
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        containsKeyNullable.forSubjectLess(null).unchecked1(),
        containsNotKeyNullable.forSubjectLess(null).unchecked1(),
        getExistingNullableFeature.forSubjectLess("a"),
        getExistingNullable.forSubjectLess("a") { toEqual(null) }
    ) {})

    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        keys.forAssertionCreatorSpec("$toBeDescr: a") { containsExactly({ toEqual("a") }, { toEqual("b") }) },
        values.forAssertionCreatorSpec("$toBeDescr: 1") { containsExactly({ toEqual(1) }, { toEqual(2) }) },
        getExisting.forAssertionCreatorSpec("$toBeDescr: 2", "b") { toEqual(2) }
    ) {})

    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        getExistingNullable.forAssertionCreatorSpec("$toBeDescr: 2", "b") { toEqual(null) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(map)
    val nullableFluent = expect(nullableMap)

    val empty = DescriptionCollectionAssertion.EMPTY.getDefault()
    val containsKeyDescr = DescriptionMapLikeAssertion.CONTAINS_KEY.getDefault()
    val containsNotKeyDescr = DescriptionMapLikeAssertion.CONTAINS_NOT_KEY.getDefault()
    val keyDoesNotExist = DescriptionMapLikeAssertion.KEY_DOES_NOT_EXIST.getDefault()

    describeFun(containsKey, containsKeyNullable, containsNotKey, containsNotKeyNullable) {
        val containsKeyFunctions = uncheckedToNonNullable(containsKey, containsKeyNullable)
        val containsNotKeyFunctions = uncheckedToNonNullable(containsNotKey, containsNotKeyNullable)

        val fluent2 = expect(map as Map<out String, *>)

        context("$map") {
            containsKeyFunctions.forEach { (name, containsKeyFun) ->
                it("$name - does not throw if the map contains the key") {
                    fluent2.containsKeyFun("a")
                }

                it("$name - throws an AssertionError if the map does not contain the key") {
                    expect {
                        fluent2.containsKeyFun("c")
                    }.toThrow<AssertionError> { messageContains("$containsKeyDescr: \"c\"") }
                }

                it("$name - does not throw if null is passed and the map contains null as key") {
                    fluent2.containsKeyFun("a")
                }
            }

            containsNotKeyFunctions.forEach { (name, containsNotKeyFun) ->
                it("$name - does not throw if the map does not contain the key") {
                    fluent2.containsNotKeyFun("c")
                }

                it("$name - throws an AssertionError if the map contains the key") {
                    expect {
                        fluent2.containsNotKeyFun("a")
                    }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: \"a\"") }
                }
            }
        }
    }

    describeFun(containsKeyNullable, containsNotKeyNullable) {
        val containsNullableKeyFun = containsKeyNullable.lambda
        val containsNotNullableKeyFun = containsNotKeyNullable.lambda

        val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
        context("$map2") {
            it("${containsKeyNullable.name} - does not throw if the map contains the key") {
                expect(map2).containsNullableKeyFun(null)
            }

            it("${containsNotKeyNullable.name} - throws an AssertionError if the map contains the key") {
                expect {
                    expect(map2).containsNotNullableKeyFun(null)
                }.toThrow<AssertionError> { messageContains("$containsNotKeyDescr: null") }
            }
        }

        val map3: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
        context("$map3") {
            it("${containsKeyNullable.name} - throws an AssertionError if the map does not contain the key") {
                expect {
                    expect(map3).containsNullableKeyFun(null)
                }.toThrow<AssertionError> { messageContains("$containsKeyDescr: null") }
            }

            it("${containsNotKeyNullable.name} - does not throw if the map does not contain the key") {
                expect(map3).containsNotNullableKeyFun(null)
            }
        }
    }

    describeFun(isEmpty, isNotEmpty) {
        val isEmptyFun = isEmpty.lambda
        val isNotEmptyFun = isNotEmpty.lambda

        val map2: Map<*, *> = mapOf<String, Int>()
        context("empty Map") {
            it("${isEmpty.name} - does not throw") {
                expect(map2).isEmptyFun()
            }

            it("${isNotEmpty.name} - throws an AssertionError") {
                expect {
                    expect(map2).isNotEmptyFun()
                }.toThrow<AssertionError> { messageContains("$isNotDescr: $empty") }
            }
        }
        context("$map") {
            it("${isEmpty.name} - throws an AssertionError") {
                expect {
                    expect(map as Map<*, *>).isEmptyFun()
                }.toThrow<AssertionError> { messageContains("$isDescr: $empty") }
            }
            it("${isNotEmpty.name} - does not throw") {
                expect(map as Map<*, *>).isNotEmptyFun()
            }
        }
    }

    describeFun(keysFeature, keys, valuesFeature, values) {
        val keysFunctions = unifySignatures(keysFeature, keys)
        val valuesFunctions = unifySignatures(valuesFeature, values)

        context("map with two entries") {
            keysFunctions.forEach { (name, keysFun, _) ->
                it("$name - toHaveSize(2) holds") {
                    fluent.keysFun { toHaveSize(2) }
                }
                it("$name - toHaveSize(1) throws AssertionError") {
                    expect {
                        fluent.keysFun { toHaveSize(1) }
                    }.toThrow<AssertionError> {
                        messageContains("keys: [a, b]")
                    }
                }
            }
            valuesFunctions.forEach { (name, valuesFun, _) ->
                it("$name - toHaveSize(2) holds") {
                    fluent.valuesFun { toHaveSize(2) }
                }
                it("$name - toHaveSize(1) throws AssertionError") {
                    expect {
                        fluent.valuesFun { toHaveSize(1) }
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
                    fluent.getExistingFun("a") { toEqual(1) }
                }
                it("$name - non-existing key throws" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        fluent.getExistingFun("c") { toEqual(3) }
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
        context("map $nullableMap") {
            getExistingFunctions.forEach { (name, getExistingFun, hasExtraHint) ->
                it("$name - can perform sub-assertion on existing key") {
                    nullableFluent.getExistingFun("a") { toEqual(null) }
                }
                it("$name - can perform sub-assertion on existing key which is null") {
                    nullableFluent.getExistingFun(null) { toEqual(1) }
                }
                it("$name - can perform sub-assertion on existing key whose value is null") {
                    nullableFluent.getExistingFun("b") { toEqual(2) }
                }
                it("$name - non-existing key throws" + showsSubAssertionIf(hasExtraHint)) {
                    expect {
                        nullableFluent.getExistingFun("c") { toEqual(null) }
                    }.toThrow<AssertionError> {
                        messageContains("get(\"c\"): $keyDoesNotExist")
                        if (hasExtraHint) messageContains("$toBeDescr: null")
                    }
                }
            }
        }
    }
})
