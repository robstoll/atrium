package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
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
    nullableKeyValPair: Pair<String, Assert<Map.Entry<String?, Int?>>.() -> AssertionPlantNullable<String?>>,
    nullableKeyFunPair: Pair<String, Assert<Map.Entry<String?, Int?>>.(assertionCreator: AssertionPlantNullable<String?>.() -> Unit) -> Assert<Map.Entry<String?, Int?>>>,
    nullableValueValPair: Pair<String, Assert<Map.Entry<String?, Int?>>.() -> AssertionPlantNullable<Int?>>,
    nullableValueFunPair: Pair<String, Assert<Map.Entry<String?, Int?>>.(assertionCreator: AssertionPlantNullable<Int?>.() -> Unit) -> Assert<Map.Entry<String?, Int?>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    data class MapEntry<K, V>(override val key: K, override val value: V) : Map.Entry<K, V>{
        override fun toString(): String = "Map.Entry($key, $value)"
    }
    fun <K, V> mapEntry(key: K, value: V): Map.Entry<K, V> = MapEntry(key, value)

    //@formatter:off
    include(object : SubjectLessAssertionSpec<Map.Entry<String, Int>>(describePrefix,
        "val ${keyValPair.first}" to mapToCreateAssertion { keyValPair.second(this).startsWith("a") },
        "fun ${keyFunPair.first}" to mapToCreateAssertion { keyFunPair.second(this) { endsWith("a") } },
        "val ${valueValPair.first}" to mapToCreateAssertion { valueValPair.second(this).isGreaterThan(1) } ,
        "fun ${valueFunPair.first}" to mapToCreateAssertion { valueFunPair.second(this) { isGreaterThan(2) } }
    ){})
    include(object : SubjectLessAssertionSpec<Map.Entry<String?, Int?>>("$describePrefix[nullable] ",
        "val ${nullableKeyValPair.first}" to mapToCreateAssertion { nullableKeyValPair.second(this).toBe(null) },
        "fun ${nullableKeyFunPair.first}" to mapToCreateAssertion { nullableKeyFunPair.second(this) { toBe(null) } },
        "val ${nullableValueValPair.first}" to mapToCreateAssertion { nullableValueValPair.second(this).toBe(null) } ,
        //TODO should also be possible, notToBeNull is not subjectLess
//        "fun ${nullableKeyFunPair.first}" to mapToCreateAssertion { nullableKeyFunPair.second(this) { notToBeNullBut("a") } },
//        "val ${nullableValueValPair.first}" to mapToCreateAssertion { nullableValueValPair.second(this).notToBeNull { isGreaterThan(1) } } ,
        "fun ${nullableValueFunPair.first}" to mapToCreateAssertion { nullableValueFunPair.second(this) { toBe(null) } }
    ){})


    include(object : CheckingAssertionSpec<Map.Entry<String, Int>>(verbs, describePrefix,
        checkingTriple("val ${keyValPair.first}", { keyValPair.second(this).startsWith("a") }, mapEntry("a", 1), mapEntry("ba", 2)),
        checkingTriple("fun ${keyFunPair.first}", { keyFunPair.second(this) { contains("a") } }, mapEntry("ba", 1), mapEntry("bc", 1)),
        checkingTriple("val ${valueValPair.first}", { valueValPair.second(this).toBe(1) }, mapEntry("a", 1), mapEntry("a", 2)),
        checkingTriple("fun ${valueFunPair.first}", { valueFunPair.second(this) { isGreaterThan(1) } }, mapEntry("a", 2), mapEntry("a", 1))
    ){})

     include(object : CheckingAssertionSpec<Map.Entry<String?, Int?>>(verbs, "$describePrefix[nullable] ",
        checkingTriple("val ${nullableKeyValPair.first}", { nullableKeyValPair.second(this).toBe(null) }, mapEntry<String?, Int?>(null, 1), mapEntry("ba", 2)),
        checkingTriple("fun ${nullableKeyFunPair.first}", { nullableKeyFunPair.second(this) { notToBeNullBut("a") } }, mapEntry<String?, Int?>("a", 1), mapEntry("bc", 1)),
        checkingTriple("val ${nullableValueValPair.first}", { nullableValueValPair.second(this).notToBeNull { isGreaterThan(1) } }, mapEntry<String?, Int?>("a", 2), mapEntry("a", 1)),
        checkingTriple("fun ${nullableValueFunPair.first}", { nullableValueFunPair.second(this) { toBe(null) } }, mapEntry<String?, Int?>("a", null), mapEntry("a", 1))
    ){})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (Map.Entry<String, Int>) -> Assert<Map.Entry<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val mapEntry = mapEntry("hello", 1)
    val nullableMapEntry = mapEntry<String?, Int?>("hello", 1)
    val nullMapEntry = mapEntry<String?, Int?>(null, null)
    val fluent = assert(mapEntry)
    val nullableFluent = verbs.checkImmediately(nullableMapEntry)
    val nullFluent = verbs.checkImmediately(nullMapEntry)


    val (keyValName, keyVal) = keyValPair
    val (keyFunName, keyFun) = keyFunPair
    val (valueValName, valueVal) = valueValPair
    val (valueFunName, valueFun) = valueFunPair

    val (nullableKeyValName, nullableKeyVal) = nullableKeyValPair
    val (nullableKeyFunName, nullableKeyFun) = nullableKeyFunPair
    val (nullableValueValName, nullableValueVal) = nullableValueValPair
    val (nullableValueFunName, nullableValueFun) = nullableValueFunPair

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
        context("$mapEntry") {
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
        }
        //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.keyFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
    }


    describeFun("val $valueValName") {
        context("$mapEntry") {
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
        context("$mapEntry") {
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

    describeFun("val $nullableKeyValName") {
        context("$nullableMapEntry") {
            test("notToBeNullBut(hello)") {
                nullableFluent.nullableKeyVal().notToBeNullBut("hello")
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("key: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableKeyVal().toBe(null)
            }
            test("notToBeNullBut(hello) fails") {
                expect {
                    nullFluent.nullableKeyVal().notToBeNullBut("hello")
                }.toThrow<AssertionError> {
                    messageContains("key: null")
                }
            }
        }
    }

    describeFun("fun $nullableKeyFunName") {
        context("$nullableMapEntry") {
            test("notToBeNullBut(hello)") {
                nullableFluent.nullableKeyFun { notToBeNullBut("hello") }
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyFun { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("key: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableKeyFun { toBe(null) }
            }
            test("notToBeNullBut(hello) fails") {
                expect {
                    nullFluent.nullableKeyFun { notToBeNullBut("hello") }
                }.toThrow<AssertionError> {
                    messageContains("key: null")
                }
            }
        }
        //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.keyFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
    }


    describeFun("val $nullableValueValName") {
        context("$nullableMapEntry") {
            test("isGreaterThan(0) holds") {
                nullableFluent.nullableValueVal().notToBeNull { isGreaterThan(0) }
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("value: 1")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableValueFun { toBe(null) }
            }
            test("notToBeNullBut(1) fails") {
                expect {
                    nullFluent.nullableValueFun { notToBeNullBut(1) }
                }.toThrow<AssertionError> {
                    messageContains("value: null")
                }
            }
        }
    }

    describeFun("fun $nullableValueFunName") {
        context("map with two entries") {
            test("isGreaterThan(0) holds") {
                nullableFluent.nullableValueFun { notToBeNull { isGreaterThan(0) } }
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueFun { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("value: 1")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableValueFun { toBe(null) }
            }
            test("notToBeNullBut(1) fails") {
                expect {
                    nullFluent.nullableValueFun { notToBeNullBut(1) }
                }.toThrow<AssertionError> {
                    messageContains("value: null")
                }
            }
        }
        //TODO should fail but does not since it has a feature assertion which itself is empty
//            test("throws if no assertion is made") {
//                expect {
//                    fluent.valueFun { }
//                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
//            }
    }
})
