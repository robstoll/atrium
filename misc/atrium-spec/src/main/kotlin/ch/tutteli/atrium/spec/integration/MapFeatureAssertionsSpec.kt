// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class MapFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keysValPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Set<String>>>,
    keysFunPair: Pair<String, Assert<Map<String, Int>>.(assertionCreator: Assert<Set<String>>.() -> Unit) -> Assert<Map<String, Int>>>,
    valuesValPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Collection<Int>>>,
    valuesFunPair: Pair<String, Assert<Map<String, Int>>.(assertionCreator: Assert<Collection<Int>>.() -> Unit) -> Assert<Map<String, Int>>>,
    getExistingPlantPair: Pair<String, Assert<Map<String, Int>>.(String) -> Assert<Int>>,
    getExistingPair: Pair<String, Assert<Map<String, Int>>.(String, assertionCreator: Assert<Int>.() -> Unit) -> Assert<Map<String, Int>>>,
    getExistingNullablePlantPair: Pair<String, Assert<Map<String, Int?>>.(String) -> AssertionPlantNullable<Int?>>,
    getExistingNullablePair: Pair<String, Assert<Map<String, Int?>>.(String, assertionCreator: AssertionPlantNullable<Int?>.() -> Unit) -> Assert<Map<String, Int?>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        "val ${keysValPair.first}" to mapToCreateAssertion { keysValPair.second(this).isEmpty() },
        "fun ${keysFunPair.first}" to mapToCreateAssertion { keysFunPair.second(this) { isEmpty() } },
        "val ${valuesValPair.first}" to mapToCreateAssertion { valuesValPair.second(this).isEmpty() },
        "fun ${valuesFunPair.first}" to mapToCreateAssertion { valuesFunPair.second(this) { isEmpty() } },
        "${getExistingPlantPair.first} returns plant" to mapToCreateAssertion { getExistingPlantPair.second(this, "a" ).isGreaterThan(1) },
        getExistingPair.first to mapToCreateAssertion { getExistingPair.second(this, "a" ){ isGreaterThan(1) } }
    ){})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Map<String, Int?>>("$describePrefix[nullable Key] ",
        "${getExistingNullablePlantPair.first} returns plant" to mapToCreateAssertion { getExistingNullablePlantPair.second(this, "a" ).toBe(null) },
        getExistingNullablePair.first to mapToCreateAssertion { getExistingNullablePair.second(this, "a" ){ toBe(null) } }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple("val ${keysValPair.first}", { keysValPair.second(this).containsExactly("a") }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("fun ${keysFunPair.first}", { keysFunPair.second(this) { contains("a").and.hasSize(1) } }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("val ${valuesValPair.first}", { valuesValPair.second(this).containsExactly(1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("fun ${valuesFunPair.first}", { valuesFunPair.second(this) { contains(1).hasSize(1) } }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("${getExistingPlantPair.first} returns plant", { getExistingPlantPair.second(this, "a").isGreaterThan(1) }, mapOf("a" to 2), mapOf("a" to 1, "b" to 2)),
        checkingTriple(getExistingPair.first, { getExistingPair.second(this, "a") { isGreaterThan(1) } }, mapOf("a" to 2), mapOf("a" to 1, "b" to 2))
    ){})
    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Map<String, Int?>>(verbs, "$describePrefix[nullable Key] ",
        checkingTriple("${getExistingNullablePlantPair.first} plant", { getExistingNullablePlantPair.second(this, "a").toBe(1) }, mapOf("a" to 1), mapOf("a" to null, "b" to 2)),
        checkingTriple(getExistingNullablePair.first, { getExistingNullablePair.second(this, "a") { toBe(1) } }, mapOf("a" to 1), mapOf("a" to null, "b" to 2))
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val map = mapOf("a" to 1, "b" to 2)
    val fluent = assert(map)
    val mapNullable = mapOf("a" to 1, "b" to null)
    val fluentNullable = verbs.checkImmediately(mapNullable)

    val (keysValName, keysVal) = keysValPair
    val (keysFunName, keysFun) = keysFunPair
    val (valuesValName, valuesVal) = valuesValPair
    val (valuesFunName, valuesFun) = valuesFunPair
    val (getExistingPlant, getExistingPlantFun) = getExistingPlantPair
    val (getExisting, getExistingFun) = getExistingPair
    val (getExistingNullablePlant, getExistingNullablePlantFun) = getExistingNullablePlantPair
    val (getExistingNullable, getExistingNullableFun) = getExistingNullablePair

    val toBeDescr = TO_BE.getDefault()
    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()

    describeFun("val $keysValName") {
        context("map with two entries") {
            test("hasSize(2) holds") {
                fluent.keysVal().hasSize(2)
            }
            test("hasSize(1) fails") {
                expect {
                    fluent.keysVal().hasSize(1)
                }.toThrow<AssertionError> {
                    messageContains("keys: [a, b]")
                }
            }
        }
    }

    describeFun("fun $keysFunName") {
        context("map with two entries") {
            test("hasSize(2) holds") {
                fluent.keysFun { hasSize(2) }
            }
            test("hasSize(1) fails") {
                expect {
                    fluent.keysFun { hasSize(1) }
                }.toThrow<AssertionError> {
                    messageContains("keys: [a, b]")
                }
            }
            //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.keysFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }


    describeFun("val $valuesValName") {
        context("map with two entries") {
            test("hasSize(2) holds") {
                fluent.valuesVal().hasSize(2)
            }
            test("hasSize(1) fails") {
                expect {
                    fluent.valuesVal().hasSize(1)
                }.toThrow<AssertionError> {
                    messageContains("values: [1, 2]")
                }
            }
        }
    }

    describeFun("fun $valuesFunName") {
        context("map with two entries") {
            test("hasSize(2) holds") {
                fluent.valuesFun { hasSize(2) }
            }
            test("hasSize(1) fails") {
                expect {
                    fluent.valuesFun { hasSize(1) }
                }.toThrow<AssertionError> {
                    messageContains("values: [1, 2]")
                }
            }
            //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.valuesFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }

    describeFun("$getExistingPlant returns plant") {
        context("map $map") {
            test("can perform sub-assertion on existing key") {
                fluent.getExistingPlantFun("a").toBe(1)
            }
            test("non-existing key throws") {
                expect {
                    fluent.getExistingPlantFun("c").toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist")
                }
            }
        }
    }

    describeFun(getExisting) {
        context("map $map") {
            test("can perform sub-assertion on existing key") {
                fluent.getExistingFun("a") { toBe(1) }
            }
            test("non-existing key throws but shows intended sub-assertion") {
                expect {
                    fluent.getExistingFun("c") { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist", "$toBeDescr: 3")
                }
            }
            // the new infix throws now an AssertionError instead - not going to test it any more (is covered by bc-tests)
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.getExistingFun("a") { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }

    describeFun("$getExistingNullablePlant for nullable returns plant") {
        context("map $mapNullable") {
            test("can perform sub-assertion on existing key") {
                fluentNullable.getExistingNullablePlantFun("a").toBe(1)
            }
            test("can perform sub-assertion on existing key with null as value") {
                fluentNullable.getExistingNullablePlantFun("b").toBe(null)
            }
            test("non-existing key throws") {
                expect {
                    fluent.getExistingNullablePlantFun("c").toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist")
                }
            }
        }
    }

    describeFun("$getExistingNullable for nullable") {
        context("map $mapNullable") {
            test("can perform sub-assertion on existing key") {
                fluentNullable.getExistingNullableFun("a") { toBe(1) }
            }
            test("can perform sub-assertion on existing key with value null") {
                fluentNullable.getExistingNullableFun("b") { toBe(null) }
            }

            //TODO problem with down-cast which is not subject-less
//            test("non-existing key throws but shows intended sub-assertion") {
//                expect {
//                    fluentNullable.getExistingNullableFun("c") { toBe(null) }
//                }.toThrow<AssertionError> {
//                    messageContains("get(\"c\"): $keyDoesNotExist", "$toBeDescr: null")
//                }
//            }
        }
    }
})
