package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCollectionProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapExpectationsSpec(
    toContainKey: Fun1<Map<out String, *>, String>,
    toContainKeyNullable: Fun1<Map<out String?, *>, String?>,
    notToContainKey: Fun1<Map<out String, *>, String>,
    notToContainKeyNullable: Fun1<Map<out String?, *>, String?>,
    getExistingFeature: Feature1<Map<out String, Int>, String, Int>,
    getExisting: Fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>,
    getExistingNullableFeature: Feature1<Map<out String?, Int?>, String?, Int?>,
    getExistingNullable: Fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>,
    toBeEmpty: Fun0<Map<*, *>>,
    notToBeEmpty: Fun0<Map<*, *>>,
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
        toContainKey.forSubjectLessTest("a").unchecked1(),
        notToContainKey.forSubjectLessTest("a").unchecked1(),
        toBeEmpty.forSubjectLessTest().unchecked1(),
        notToBeEmpty.forSubjectLessTest().unchecked1(),
        keysFeature.forSubjectLessTest(),
        keys.forSubjectLessTest { this.toBeEmpty() },
        valuesFeature.forSubjectLessTest(),
        values.forSubjectLessTest { this.toBeEmpty() },
        getExistingFeature.forSubjectLessTest("a"),
        getExisting.forSubjectLessTest("a") { toBeGreaterThan(1) }
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        toContainKeyNullable.forSubjectLessTest(null).unchecked1(),
        notToContainKeyNullable.forSubjectLessTest(null).unchecked1(),
        getExistingNullableFeature.forSubjectLessTest("a"),
        getExistingNullable.forSubjectLessTest("a") { toEqual(null) }
    ) {})

    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String, Int>>(
        describePrefix, map,
        keys.forExpectationCreatorTest("$toEqualDescr\\s+: a") { toContainExactly({ toEqual("a") }, { toEqual("b") }) },
        values.forExpectationCreatorTest("$toEqualDescr\\s+: 1") { toContainExactly({ toEqual(1) }, { toEqual(2) }) },
        getExisting.forExpectationCreatorTest("$toEqualDescr\\s+: 2", "b") { toEqual(2) }
    ) {})

    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)

    include(object : AssertionCreatorSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable] ", mapOf("a" to 1, "b" to null),
        getExistingNullable.forExpectationCreatorTest("$toEqualDescr: 2", "b") { toEqual(null) }
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val empty = DescriptionCollectionProof.EMPTY.string
    val toContainKeyDescr = DescriptionMapLikeProof.TO_CONTAIN_KEY.getDefault()
    val notToContainKeyDescr = DescriptionMapLikeProof.NOT_TO_CONTAIN_KEY.getDefault()
    val keyDoesNotExist = DescriptionMapLikeProof.KEY_DOES_NOT_EXIST.getDefault()

    describeFun(toContainKey, toContainKeyNullable, notToContainKey, notToContainKeyNullable) {
        val toContainKeyFunctions = uncheckedToNonNullable(toContainKey, toContainKeyNullable)
        val notToContainKeyFunctions = uncheckedToNonNullable(notToContainKey, notToContainKeyNullable)

        val map1 : Map<out String, *> = map
        context("$map") {
            toContainKeyFunctions.forEach { (name, toContainKeyFun) ->
                it("$name - does not throw if the map toContain the key") {
                    expect(map1).toContainKeyFun("a")
                }

                it("$name - throws an AssertionError if the map does not contain the key") {
                    expect {
                        expect(map1).toContainKeyFun("c")
                    }.toThrow<AssertionError> {
                        message { toContainDescr(toContainKeyDescr, "\"c\"") }
                    }
                }

                it("$name - does not throw if null is passed and the map toContain null as key") {
                    expect(map1).toContainKeyFun("a")
                }
            }

            notToContainKeyFunctions.forEach { (name, notToContainKeyFun) ->
                it("$name - does not throw if the map does not contain the key") {
                    expect(map1).notToContainKeyFun("c")
                }

                it("$name - throws an AssertionError if the map toContain the key") {
                    expect {
                        expect(map1).notToContainKeyFun("a")
                    }.toThrow<AssertionError> {
                        message { toContainDescr(notToContainKeyDescr, "\"a\"") }
                    }
                }
            }
        }
    }

    describeFun(toContainKeyNullable, notToContainKeyNullable) {
        val toContainNullableKeyFun = toContainKeyNullable.lambda
        val notToContainNullableKeyFun = notToContainKeyNullable.lambda

        val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
        context("$map2") {
            it("${toContainKeyNullable.name} - does not throw if the map toContain the key") {
                expect(map2).toContainNullableKeyFun(null)
            }

            it("${notToContainKeyNullable.name} - throws an AssertionError if the map toContain the key") {
                expect {
                    expect(map2).notToContainNullableKeyFun(null)
                }.toThrow<AssertionError> {
                    message { toContainDescr(notToContainKeyDescr, "null") }
                }
            }
        }

        val map3: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
        context("$map3") {
            it("${toContainKeyNullable.name} - throws an AssertionError if the map does not contain the key") {
                expect {
                    expect(map3).toContainNullableKeyFun(null)
                }.toThrow<AssertionError> {
                    message { toContainDescr(toContainKeyDescr, "null") }
                }
            }

            it("${notToContainKeyNullable.name} - does not throw if the map does not contain the key") {
                expect(map3).notToContainNullableKeyFun(null)
            }
        }
    }

    describeFun(toBeEmpty, notToBeEmpty) {
        val toBeEmptyFun = toBeEmpty.lambda
        val notToBeEmptyFun = notToBeEmpty.lambda

        val map2: Map<*, *> = mapOf<String, Int>()
        context("empty Map") {
            it("${toBeEmpty.name} - does not throw") {
                expect(map2).toBeEmptyFun()
            }

            it("${notToBeEmpty.name} - throws an AssertionError") {
                expect {
                    expect(map2).notToBeEmptyFun()
                }.toThrow<AssertionError> {
                    message { toContainNotToBeDescr(empty) }
                }
            }
        }
        context("$map") {
            it("${toBeEmpty.name} - throws an AssertionError") {
                expect {
                    expect(map as Map<*, *>).toBeEmptyFun()
                }.toThrow<AssertionError> {
                    message { toContainToBeDescr(empty) }
                }
            }
            it("${notToBeEmpty.name} - does not throw") {
                expect(map as Map<*, *>).notToBeEmptyFun()
            }
        }
    }

    describeFun(keysFeature, keys, valuesFeature, values) {
        val keysFunctions = unifySignatures(keysFeature, keys)
        val valuesFunctions = unifySignatures(valuesFeature, values)

        context("map with two entries") {
            keysFunctions.forEach { (name, keysFun, _) ->
                it("$name - toHaveSize(2) holds") {
                    expect(map).keysFun { toHaveSize(2) }
                }
                it("$name - toHaveSize(1) throws AssertionError") {
                    expect {
                        expect(map).keysFun { toHaveSize(1) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr("keys", "[a, b]") }
                    }
                }
            }
            valuesFunctions.forEach { (name, valuesFun, _) ->
                it("$name - toHaveSize(2) holds") {
                    expect(map).valuesFun { toHaveSize(2) }
                }
                it("$name - toHaveSize(1) throws AssertionError") {
                    expect {
                        expect(map).valuesFun { toHaveSize(1) }
                    }.toThrow<AssertionError> {
                        message { toContainDescr("values", "[1, 2]") }
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
                    expect(map).getExistingFun("a") { toEqual(1) }
                }
                it("$name - non-existing key throws" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(map).getExistingFun("c") { toEqual(3) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr("get(\"c\")", keyDoesNotExist)
                            if (hasExtraHint) toContainToEqualDescr(3)
                        }
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
                    expect(nullableMap).getExistingFun("a") { toEqual(null) }
                }
                it("$name - can perform sub-assertion on existing key which is null") {
                    expect(nullableMap).getExistingFun(null) { toEqual(1) }
                }
                it("$name - can perform sub-assertion on existing key whose value is null") {
                    expect(nullableMap).getExistingFun("b") { toEqual(2) }
                }
                it("$name - non-existing key throws" + showsSubExpectationIf(hasExtraHint)) {
                    expect {
                        expect(nullableMap).getExistingFun("c") { toEqual(null) }
                    }.toThrow<AssertionError> {
                        message {
                            toContainDescr("get(\"c\")", keyDoesNotExist)
                            if (hasExtraHint) toContainToEqualDescr(null)
                        }
                    }
                }
            }
        }
    }
})
