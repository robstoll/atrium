// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

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

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class KeyValueLikeFeatureAssertionsSpec<T: Any, TNullable: Any>(
    verbs: AssertionVerbFactory,
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyValPair: Pair<String, Assert<T>.() -> Assert<String>>,
    keyFunPair: Pair<String, Assert<T>.(assertionCreator: Assert<String>.() -> Unit) -> Assert<T>>,
    valueValPair: Pair<String, Assert<T>.() -> Assert<Int>>,
    valueFunPair: Pair<String, Assert<T>.(assertionCreator: Assert<Int>.() -> Unit) -> Assert<T>>,
    nullableKeyValPair: Pair<String, Assert<TNullable>.() -> AssertionPlantNullable<String?>>,
    nullableValueValPair: Pair<String, Assert<TNullable>.() -> AssertionPlantNullable<Int?>>,
    describePrefix: String = "[Atrium] "
) : Spek({


    //@formatter:off
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<T>(describePrefix,
        "val ${keyValPair.first}" to mapToCreateAssertion { keyValPair.second(this).startsWith("a") },
        "fun ${keyFunPair.first}" to mapToCreateAssertion { keyFunPair.second(this) { endsWith("a") } },
        "val ${valueValPair.first}" to mapToCreateAssertion { valueValPair.second(this).isGreaterThan(1) } ,
        "fun ${valueFunPair.first}" to mapToCreateAssertion { valueFunPair.second(this) { isGreaterThan(2) } }
    ){})
    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<TNullable>("$describePrefix[nullable] ",
        "val ${nullableKeyValPair.first}" to mapToCreateAssertion { nullableKeyValPair.second(this).toBe(null) },
        "val ${nullableValueValPair.first}" to mapToCreateAssertion { nullableValueValPair.second(this).toBe(null) }
        //TODO should also be possible, notToBeNull is not subjectLess
//        "fun ${nullableKeyFunPair.first}" to mapToCreateAssertion { nullableKeyFunPair.second(this) { toBe("a") } },
//        "val ${nullableValueValPair.first}" to mapToCreateAssertion { nullableValueValPair.second(this).notToBeNull { isGreaterThan(1) } } ,
    ){})


    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<T>(verbs, describePrefix,
        checkingTriple("val ${keyValPair.first}", { keyValPair.second(this).startsWith("a") }, creator("a", 1), creator("ba", 2)),
        checkingTriple("fun ${keyFunPair.first}", { keyFunPair.second(this) { contains("a") } }, creator("ba", 1), creator("bc", 1)),
        checkingTriple("val ${valueValPair.first}", { valueValPair.second(this).toBe(1) }, creator("a", 1), creator("a", 2)),
        checkingTriple("fun ${valueFunPair.first}", { valueFunPair.second(this) { isGreaterThan(1) } }, creator("a", 2), creator("a", 1))
    ){})

     include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<TNullable>(verbs, "$describePrefix[nullable] ",
        checkingTriple("val ${nullableKeyValPair.first}", { nullableKeyValPair.second(this).toBe(null) }, creatorNullable(null, 1), creatorNullable("ba", 2)),
        checkingTriple("val ${nullableValueValPair.first}", { nullableValueValPair.second(this).notToBeNull { isGreaterThan(1) } }, creatorNullable("a", 2), creatorNullable("a", 1))
    ){})
    //@formatter:on

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    val assert: (T) -> Assert<T> = verbs::checkImmediately
    val expect = verbs::checkException
    val mapEntry = creator("hello", 1)
    val nullableMapEntry = creatorNullable("hello", 1)
    val nullMapEntry = creatorNullable(null, null)
    val fluent = assert(mapEntry)
    val nullableFluent = verbs.checkImmediately(nullableMapEntry)
    val nullFluent = verbs.checkImmediately(nullMapEntry)


    val (keyValName, keyVal) = keyValPair
    val (keyFunName, keyFun) = keyFunPair
    val (valueValName, valueVal) = valueValPair
    val (valueFunName, valueFun) = valueFunPair

    val (nullableKeyValName, nullableKeyVal) = nullableKeyValPair
    val (nullableValueValName, nullableValueVal) = nullableValueValPair

    describeFun("val $keyValName") {
        context("$mapEntry") {
            test("startsWith(h) holds") {
                fluent.keyVal().startsWith("h")
            }
            test("endsWith(h) fails") {
                expect {
                    fluent.keyVal().endsWith("h")
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
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
                    messageContains("$keyName: \"hello\"")
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
                    messageContains("$valueName: 1")
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
                    messageContains("$valueName: 1")
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

    describeFun("val $nullableKeyValName for nullable") {
        context("$nullableMapEntry") {
            test("toBe(hello)") {
                nullableFluent.nullableKeyVal().toBe("hello")
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableKeyVal().toBe(null)
            }
            test("toBe(hello) fails") {
                expect {
                    nullFluent.nullableKeyVal().toBe("hello")
                }.toThrow<AssertionError> {
                    messageContains("$keyName: null")
                }
            }
        }
    }

    describeFun("val $nullableValueValName for nullable") {
        context("$nullableMapEntry") {
            test("isGreaterThan(0) holds") {
                nullableFluent.nullableValueVal().notToBeNull { isGreaterThan(0) }
            }
            test("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: 1")
                }
            }
        }
        context("$nullMapEntry") {
            test("toBe(null)") {
                nullFluent.nullableValueVal().toBe(null)
            }
            test("toBe(1) fails") {
                expect {
                    nullFluent.nullableValueVal().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: null")
                }
            }
        }
    }
})
