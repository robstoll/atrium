package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class MapFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keysValPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Set<String>>>,
    keysFunPair: Pair<String, Assert<Map<String, Int>>.(assertionCreator: Assert<Set<String>>.() -> Unit) -> Assert<Map<String, Int>>>,
    valuesValPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Collection<Int>>>,
    valuesFunPair: Pair<String, Assert<Map<String, Int>>.(assertionCreator: Assert<Collection<Int>>.() -> Unit) -> Assert<Map<String, Int>>>,
    getExistingPair: Pair<String, Assert<Map<String, Int>>.(String, assertionCreator: Assert<Int>.() -> Unit) -> Assert<Map<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        "val ${keysValPair.first} val" to mapToCreateAssertion { keysValPair.second(this).isEmpty() },
        "fun ${keysFunPair.first} fun" to mapToCreateAssertion { keysFunPair.second(this) { isEmpty() } },
        "val ${valuesValPair.first} val" to mapToCreateAssertion { valuesValPair.second(this).isEmpty() },
        "fun ${valuesFunPair.first} fun" to mapToCreateAssertion { valuesFunPair.second(this) { isEmpty() } },
        getExistingPair.first to mapToCreateAssertion { getExistingPair.second(this, "a" ){ isGreaterThan(1) } }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple("val ${keysValPair.first}", { keysValPair.second(this).containsStrictly("a") }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("fun ${keysFunPair.first}", { keysFunPair.second(this) { contains("a").and.hasSize(1) } }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("val ${valuesValPair.first}", { valuesValPair.second(this).containsStrictly(1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple("fun ${valuesFunPair.first}", { valuesFunPair.second(this) { contains(1).hasSize(1) } }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(getExistingPair.first, { getExistingPair.second(this, "a") { isGreaterThan(1) } }, mapOf("a" to 2), mapOf("a" to 1, "b" to 2))
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(mapOf("a" to 1, "b" to 2))

    val (keysValName, keysVal) = keysValPair
    val (keysFunName, keysFun) = keysFunPair
    val (valuesValName, valuesVal) = valuesValPair
    val (valuesFunName, valuesFun) = valuesFunPair
    val (getExisting, getExistingFun) = getExistingPair

    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
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
//                }.toThrow<IllegalArgumentException> { messageContains("not a single assertion") }
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
//                }.toThrow<IllegalArgumentException> { messageContains("not a single assertion") }
//            }
        }
    }

    describeFun(getExisting) {
        context("map with two entries with key 'a' and 'b'") {
            test("can perform sub-assertion on getExisting('a')") {
                fluent.getExistingFun("a") { toBe(1) }
            }
            test("getExisting('c') throws but shows intended sub-assertion") {
                expect {
                    fluent.getExistingFun("c") { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist", "$toBeDescr: 3")
                }
            }
        }
    }
})
