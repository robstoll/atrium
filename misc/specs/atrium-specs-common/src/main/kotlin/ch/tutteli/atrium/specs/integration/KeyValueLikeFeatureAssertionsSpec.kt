package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class KeyValueLikeFeatureAssertionsSpec<T : Any, TNullable : Any>(
    verbs: AssertionVerbFactory,
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyValPair: Feature0<T, String>,
    keyFunPair: Fun1<T, Expect<String>.() -> Unit>,
    valueValPair: Feature0<T, Int>,
    valueFunPair: Fun1<T, Expect<Int>.() -> Unit>,
    nullableKeyValPair: Feature0<TNullable, String?>,
    nullableValueValPair: Feature0<TNullable, Int?>,
    describePrefix: String = "[Atrium] "
) : Spek({


    //@formatter:off
    include(object : SubjectLessSpec<T>(describePrefix,
        "val ${keyValPair.first}" to expectLambda { keyValPair.second(this).startsWith("a") },
        "fun ${keyFunPair.first}" to expectLambda { keyFunPair.second(this) { endsWith("a") } },
        "val ${valueValPair.first}" to expectLambda { valueValPair.second(this).isGreaterThan(1) } ,
        "fun ${valueFunPair.first}" to expectLambda { valueFunPair.second(this) { isGreaterThan(2) } }
    ){})
    include(object : SubjectLessSpec<TNullable>("$describePrefix[nullable] ",
        "val ${nullableKeyValPair.first}" to expectLambda { nullableKeyValPair.second(this).toBe(null) },
        "val ${nullableValueValPair.first}" to expectLambda { nullableValueValPair.second(this).toBe(null) },
        "fun ${nullableKeyValPair.first}" to expectLambda { nullableKeyValPair.second(this).toBe(null) },
        "val ${nullableValueValPair.first}" to expectLambda { nullableValueValPair.second(this).notToBeNull { isGreaterThan(1) } }
    ){})
    //@formatter:on

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (T) -> Expect<T> = verbs::check
    val expect = verbs::checkException
    val mapEntry = creator("hello", 1)
    val nullableMapEntry = creatorNullable("hello", 1)
    val nullMapEntry = creatorNullable(null, null)
    val fluent = assert(mapEntry)
    val nullableFluent = verbs.check(nullableMapEntry)
    val nullFluent = verbs.check(nullMapEntry)


    val (keyValName, keyVal) = keyValPair
    val (keyFunName, keyFun) = keyFunPair
    val (valueValName, valueVal) = valueValPair
    val (valueFunName, valueFun) = valueFunPair

    val (nullableKeyValName, nullableKeyVal) = nullableKeyValPair
    val (nullableValueValName, nullableValueVal) = nullableValueValPair

    describeFun("val $keyValName") {
        context("$mapEntry") {
            it("startsWith(h) holds") {
                fluent.keyVal().startsWith("h")
            }
            it("endsWith(h) fails") {
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
            it("startsWith(h) holds") {
                fluent.keyFun { startsWith("h") }
            }
            it("endsWith(h) fails") {
                expect {
                    fluent.keyFun { endsWith("h") }
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
                }
            }
        }
        it("throws if no assertion is made") {
            expect {
                fluent.keyFun { }
            }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
        }
    }


    describeFun("val $valueValName") {
        context("$mapEntry") {
            it("isGreaterThan(0) holds") {
                fluent.valueVal().isGreaterThan(0)
            }
            it("isGreaterThan(1) fails") {
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
            it("isGreaterThan(0) holds") {
                fluent.valueFun { isGreaterThan(0) }
            }
            it("isGreaterThan(1) fails") {
                expect {
                    fluent.valueFun { isGreaterThan(1) }
                }.toThrow<AssertionError> {
                    messageContains("$valueName: 1")
                }
            }
            it("throws if no assertion is made") {
                expect {
                    fluent.valueFun { }
                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
            }
        }
    }

    describeFun("val $nullableKeyValName for nullable") {
        context("$nullableMapEntry") {
            it("toBe(hello)") {
                nullableFluent.nullableKeyVal().toBe("hello")
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableKeyVal().toBe(null)
            }
            it("toBe(hello) fails") {
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
            it("isGreaterThan(0) holds") {
                nullableFluent.nullableValueVal().notToBeNull { isGreaterThan(0) }
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueVal().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: 1")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableValueVal().toBe(null)
            }
            it("toBe(1) fails") {
                expect {
                    nullFluent.nullableValueVal().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: null")
                }
            }
        }
    }
})
