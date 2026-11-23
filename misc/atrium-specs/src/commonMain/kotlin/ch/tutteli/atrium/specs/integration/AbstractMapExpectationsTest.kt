package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.utils.ExpectationCreatorTestData
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionCollectionExpectation
import ch.tutteli.atrium.translations.DescriptionMapLikeExpectation

@Suppress("FunctionName")
abstract class AbstractMapExpectationsTest(
    private val toContainKeySpec: Fun1<Map<out String, *>, String>,
    private val toContainKeyNullableSpec: Fun1<Map<out String?, *>, String?>,
    private val notToContainKeySpec: Fun1<Map<out String, *>, String>,
    private val notToContainKeyNullableSpec: Fun1<Map<out String?, *>, String?>,
    private val getExistingFeatureSpec: Feature1<Map<out String, Int>, String, Int>,
    private val getExistingSpec: Fun2<Map<out String, Int>, String, Expect<Int>.() -> Unit>,
    private val getExistingNullableFeatureSpec: Feature1<Map<out String?, Int?>, String?, Int?>,
    private val getExistingNullableSpec: Fun2<Map<out String?, Int?>, String?, Expect<Int?>.() -> Unit>,
    private val toBeEmptySpec: Fun0<Map<*, *>>,
    private val notToBeEmptySpec: Fun0<Map<*, *>>,
    private val keysSpec: Fun1<Map<out String, Int>, Expect<Set<String>>.() -> Unit>,
    private val keysFeatureSpec: Feature0<Map<out String, Int>, Set<String>>,
    private val valuesFeatureSpec: Feature0<Map<out String, Int>, Collection<Int>>,
    private val valuesSpec: Fun1<Map<out String, Int>, Expect<Collection<Int>>.() -> Unit>,
) : ExpectationFunctionBaseTest() {

    @Suppress("UNCHECKED_CAST")
    fun <K, V, T> T.unchecked1(): SpecPair<Expect<Map<out K, V>>.() -> Unit> =
        this as SpecPair<Expect<Map<out K, V>>.() -> Unit>

    val map: Map<out String, Int> = mapOf("a" to 1, "b" to 2)
    val nullableMap: Map<out String?, Int?> = mapOf("a" to null, null to 1, "b" to 2)

    val empty = DescriptionCollectionExpectation.EMPTY.getDefault()
    val toContainKeyDescr = DescriptionMapLikeExpectation.TO_CONTAIN_KEY.getDefault()
    val notToContainKeyDescr = DescriptionMapLikeExpectation.NOT_TO_CONTAIN_KEY.getDefault()
    val keyDoesNotExist = DescriptionMapLikeExpectation.KEY_DOES_NOT_EXIST.getDefault()

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainKeySpec.forSubjectLessTest("a").unchecked1(),
            notToContainKeySpec.forSubjectLessTest("a").unchecked1(),
            toBeEmptySpec.forSubjectLessTest().unchecked1(),
            notToBeEmptySpec.forSubjectLessTest().unchecked1(),
            keysFeatureSpec.forSubjectLessTest(),
            keysSpec.forSubjectLessTest { this.toBeEmpty() },
            valuesFeatureSpec.forSubjectLessTest(),
            valuesSpec.forSubjectLessTest { this.toBeEmpty() },
            getExistingFeatureSpec.forSubjectLessTest("a"),
            getExistingSpec.forSubjectLessTest("a") { toBeGreaterThan(1) }
        ),
        SubjectLessTestData<Map<out String?, Int?>>(
            toContainKeyNullableSpec.forSubjectLessTest(null).unchecked1(),
            notToContainKeyNullableSpec.forSubjectLessTest(null).unchecked1(),
            getExistingNullableFeatureSpec.forSubjectLessTest("a"),
            getExistingNullableSpec.forSubjectLessTest("a") { toEqual(null) },
            groupPrefix = "[nullable Key]"
        )
    )

    @TestFactory
    fun expectationCreatorTest() = expectationCreatorTestFactory(
        ExpectationCreatorTestData(
            map,
            keysSpec.forExpectationCreatorTest("$toEqualDescr: a") { toContainExactly({ toEqual("a") }, { toEqual("b") }) },
            valuesSpec.forExpectationCreatorTest("$toEqualDescr: 1") { toContainExactly({ toEqual(1) }, { toEqual(2) }) },
            getExistingSpec.forExpectationCreatorTest("$toEqualDescr: 2", "b") { toEqual(2) }
        ),
        ExpectationCreatorTestData<Map<out String?, Int?>>(
            mapOf("a" to 1, "b" to null),
            getExistingNullableSpec.forExpectationCreatorTest("$toEqualDescr: 2", "b") { toEqual(null) },
            groupPrefix = "[nullable]"
        )
    )

    @TestFactory
    fun toContainKey_and_notToContainKey() = testFactory(
        toContainKeySpec, toContainKeyNullableSpec, notToContainKeySpec, notToContainKeyNullableSpec
    ) {
        val toContainKeyFunctions = uncheckedToNonNullable(toContainKeySpec, toContainKeyNullableSpec)
        val notToContainKeyFunctions = uncheckedToNonNullable(notToContainKeySpec, notToContainKeyNullableSpec)

        val fluent2 = expect(map as Map<out String, *>)

        describe("$map") {
            toContainKeyFunctions.forEach { (name, toContainKeyFun) ->
                it("$name - does not throw if the map toContain the key") {
                    fluent2.toContainKeyFun("a")
                }

                it("$name - throws an AssertionError if the map does not contain the key") {
                    expect {
                        fluent2.toContainKeyFun("c")
                    }.toThrow<AssertionError> { messageToContain("$toContainKeyDescr: \"c\"") }
                }

                it("$name - does not throw if null is passed and the map toContain null as key") {
                    fluent2.toContainKeyFun("a")
                }
            }

            notToContainKeyFunctions.forEach { (name, notToContainKeyFun) ->
                it("$name - does not throw if the map does not contain the key") {
                    fluent2.notToContainKeyFun("c")
                }

                it("$name - throws an AssertionError if the map toContain the key") {
                    expect {
                        fluent2.notToContainKeyFun("a")
                    }.toThrow<AssertionError> { messageToContain("$notToContainKeyDescr: \"a\"") }
                }
            }
        }
    }

    @TestFactory
    fun toContainKeyNullable_and_notToContainKeyNullable() = testFactory(
        toContainKeyNullableSpec, notToContainKeyNullableSpec
    ) { toContainNullableKeyFun, notToContainNullableKeyFun ->
        val map2: Map<out String?, *> = mapOf("a" to 1, null to 2)
        describe("$map2") {
            it("${toContainKeyNullableSpec.name} - does not throw if the map toContain the key") {
                expect(map2).toContainNullableKeyFun(null)
            }

            it("${notToContainKeyNullableSpec.name} - throws an AssertionError if the map toContain the key") {
                expect {
                    expect(map2).notToContainNullableKeyFun(null)
                }.toThrow<AssertionError> { messageToContain("$notToContainKeyDescr: null") }
            }
        }

        val map3: Map<out String?, *> = mapOf("a" to 1, "b" to 2)
        describe("$map3") {
            it("${toContainKeyNullableSpec.name} - throws an AssertionError if the map does not contain the key") {
                expect {
                    expect(map3).toContainNullableKeyFun(null)
                }.toThrow<AssertionError> { messageToContain("$toContainKeyDescr: null") }
            }

            it("${notToContainKeyNullableSpec.name} - does not throw if the map does not contain the key") {
                expect(map3).notToContainNullableKeyFun(null)
            }
        }
    }

    @TestFactory
    fun toBeEmpty_notToBeEmpty() = testFactory(toBeEmptySpec, notToBeEmptySpec) { toBeEmptyFun, notToBeEmptyFun ->
        val map2: Map<*, *> = mapOf<String, Int>()
        describe("empty Map") {
            it("${toBeEmptySpec.name} - does not throw") {
                expect(map2).toBeEmptyFun()
            }

            it("${notToBeEmptySpec.name} - throws an AssertionError") {
                expect {
                    expect(map2).notToBeEmptyFun()
                }.toThrow<AssertionError> { messageToContain("$notToBeDescr: $empty") }
            }
        }
        describe("$map") {
            it("${toBeEmptySpec.name} - throws an AssertionError") {
                expect {
                    expect(map as Map<*, *>).toBeEmptyFun()
                }.toThrow<AssertionError> { messageToContain("$toBeDescr: $empty") }
            }
            it("${notToBeEmptySpec.name} - does not throw") {
                expect(map as Map<*, *>).notToBeEmptyFun()
            }
        }
    }

    @TestFactory
    fun keys() = testFactoryForFeatureNonFeature(keysFeatureSpec, keysSpec) { name, keysFun, _ ->
        describe("map with two entries") {
            it("$name - toHaveSize(2) holds") {
                expect(map).keysFun { toHaveSize(2) }
            }
            it("$name - toHaveSize(1) throws AssertionError") {
                expect {
                    expect(map).keysFun { toHaveSize(1) }
                }.toThrow<AssertionError> {
                    messageToContain("keys: [a, b]")
                }
            }
        }
    }

    @TestFactory
    fun values() = testFactoryForFeatureNonFeature(valuesFeatureSpec, valuesSpec) { name, valuesFun, _ ->
        describe("map with two entries") {
            it("$name - toHaveSize(2) holds") {
                expect(map).valuesFun { toHaveSize(2) }
            }
            it("$name - toHaveSize(1) throws AssertionError") {
                expect {
                    expect(map).valuesFun { toHaveSize(1) }
                }.toThrow<AssertionError> {
                    messageToContain("values: [1, 2]")
                }
            }
        }
    }

    @TestFactory
    fun getExisting() = testFactoryForFeatureNonFeature(getExistingFeatureSpec, getExistingSpec) { name, getExistingFun, hasExtraHint ->
        describe("map $map") {
            it("$name - can perform sub-assertion on existing key") {
                expect(map).getExistingFun("a") { toEqual(1) }
            }
            it("$name - non-existing key throws" + showsSubExpectationIf(hasExtraHint)) {
                expect {
                    expect(map).getExistingFun("c") { toEqual(3) }
                }.toThrow<AssertionError> {
                    messageToContain("get(\"c\"): $keyDoesNotExist")
                    if (hasExtraHint) messageToContain("$toEqualDescr: 3")
                }
            }
        }
    }

    @TestFactory
    fun getExistingNullable() = testFactoryForFeatureNonFeature(getExistingNullableFeatureSpec, getExistingNullableSpec) { name, getExistingFun, hasExtraHint ->
        describe("map $nullableMap") {
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
                    messageToContain("get(\"c\"): $keyDoesNotExist")
                    if (hasExtraHint) messageToContain("$toEqualDescr: null")
                }
            }
        }
    }
}
