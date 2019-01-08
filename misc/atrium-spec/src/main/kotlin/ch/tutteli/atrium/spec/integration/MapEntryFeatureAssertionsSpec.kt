package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class MapEntryFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keyValPair: Pair<String, Assert<Map.Entry<String, Int>>.() -> Assert<String>>,
    keyFunPair: Pair<String, Assert<Map.Entry<String, Int>>.(assertionCreator: Assert<String>.() -> Unit) -> Assert<Map.Entry<String, Int>>>,
    valueValPair: Pair<String, Assert<Map.Entry<String, Int>>.() -> Assert<Int>>,
    valueFunPair: Pair<String, Assert<Map.Entry<String, Int>>.(assertionCreator: Assert<Int>.() -> Unit) -> Assert<Map.Entry<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    //@formatter:off
    include(object : SubjectLessAssertionSpec<Map.Entry<String, Int>>(describePrefix,
        "val ${keyValPair.first} val" to mapToCreateAssertion { keyValPair.second(this).startsWith("a") },
        "fun ${keyFunPair.first} fun" to mapToCreateAssertion { keyFunPair.second(this) { endsWith("a") } },
        "val ${valueValPair.first} val" to mapToCreateAssertion { valueValPair.second(this).isGreaterThan(1) } ,
        "fun ${valueFunPair.first} fun" to mapToCreateAssertion { valueFunPair.second(this) { isGreaterThan(2) } }
    ){})

    data class MapEntry(override val key: String,override val value: Int) : Map.Entry<String, Int>{
        override fun toString(): String = "Map.Entry($key, $value)"
    }
    fun mapEntry(key: String, value: Int): Map.Entry<String, Int> = MapEntry(key, value)

    include(object : CheckingAssertionSpec<Map.Entry<String, Int>>(verbs, describePrefix,
        checkingTriple("val ${keyValPair.first}", { keyValPair.second(this).startsWith("a") }, mapEntry("a", 1), mapEntry("ba", 2)),
        checkingTriple("fun ${keyFunPair.first}", { keyFunPair.second(this) { contains("a") } }, mapEntry("ba", 1), mapEntry("bc", 1)),
        checkingTriple("val ${valueValPair.first}", { valueValPair.second(this).toBe(1) }, mapEntry("a", 1), mapEntry("a", 2)),
        checkingTriple("fun ${valueFunPair.first}", { valueFunPair.second(this) { isGreaterThan(1) } }, mapEntry("a", 2), mapEntry("a", 1))
    ){})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Map.Entry<String, Int>) -> Assert<Map.Entry<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val mapEntry = mapEntry("hello", 1)
    val fluent = assert(mapEntry)

    val (keyValName, keyVal) = keyValPair
    val (keyFunName, keyFun) = keyFunPair
    val (valueValName, valueVal) = valueValPair
    val (valueFunName, valueFun) = valueFunPair

    describeFun("val $keyValName") {
        context("$mapEntry") {
            test("startsWith(h) holds") {
                fluent.keyVal().startsWith("h")
            }
            test("endsWith(h) fails") {
                expect {
                    fluent.keyVal().endsWith("h")
                }.toThrow<AssertionError> {
                    messageContains("key: \"hello\"")
                }
            }
        }
    }

    describeFun("fun $keyFunName") {
        context("map with two entries") {
            test("startsWith(h) holds") {
                fluent.keyFun { startsWith("h") }
            }
            test("endsWith(h) fails") {
                expect {
                    fluent.keyFun {endsWith("h") }
                }.toThrow<AssertionError> {
                    messageContains("key: \"hello\"")
                }
            }
            //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.keyFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }


    describeFun("val $valueValName") {
        context("map with two entries") {
            test("isGreaterThan(0) holds") {
                fluent.valueVal().isGreaterThan(0)
            }
            test("isGreaterThan(1) fails") {
                expect {
                    fluent.valueVal().isGreaterThan(1)
                }.toThrow<AssertionError> {
                    messageContains("value: 1")
                }
            }
        }
    }

    describeFun("fun $valueFunName") {
        context("map with two entries") {
            test("isGreaterThan(0) holds") {
                fluent.valueFun{ isGreaterThan(0) }
            }
            test("isGreaterThan(1) fails") {
                expect {
                    fluent.valueFun { isGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageContains("value: 1")
                }
            }
            //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.valueFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
        }
    }
})
