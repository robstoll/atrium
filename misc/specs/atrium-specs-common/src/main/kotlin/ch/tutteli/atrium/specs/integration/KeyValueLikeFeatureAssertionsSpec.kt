package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class KeyValueLikeFeatureAssertionsSpec<T : Any, TNullable : Any>(
    verbs: AssertionVerbFactory,
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyFeature: Feature0<T, String>,
    key: Fun1<T, Expect<String>.() -> Unit>,
    valueFeature: Feature0<T, Int>,
    value: Fun1<T, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<TNullable, String?>,
    nullableValueFeature: Feature0<TNullable, Int?>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val mapEntry = creator("hello", 1)
    val nullMapEntry = creatorNullable(null, null)
    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()

    //@formatter:off
    include(object : SubjectLessSpec<T>(describePrefix,
        "val ${keyFeature.first}" to expectLambda { keyFeature.second(this).startsWith("a") },
        "fun ${key.first}" to expectLambda { key.second(this) { endsWith("a") } },
        "val ${valueFeature.first}" to expectLambda { valueFeature.second(this).isGreaterThan(1) } ,
        "fun ${value.first}" to expectLambda { value.second(this) { isGreaterThan(2) } }
    ){})
    include(object : SubjectLessSpec<TNullable>("$describePrefix[nullable] ",
        "val ${nullableKeyFeature.first}" to expectLambda { nullableKeyFeature.second(this).toBe(null) },
        "val ${nullableValueFeature.first}" to expectLambda { nullableValueFeature.second(this).toBe(null) },
        "fun ${nullableKeyFeature.first}" to expectLambda { nullableKeyFeature.second(this).toBe(null) },
        "val ${nullableValueFeature.first}" to expectLambda { nullableValueFeature.second(this).notToBeNull { isGreaterThan(1) } }
    ){})

    include(object : AssertionCreatorSpec<T>(
        verbs, describePrefix, mapEntry,
        key.forAssertionCreatorSpec("$toBeDescr: hello") { toBe("hello") },
        value.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})
    //@formatter:on

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (T) -> Expect<T> = verbs::check
    val expect = verbs::checkException

    val nullableMapEntry = creatorNullable("hello", 1)

    val fluent = assert(mapEntry)
    val nullableFluent = verbs.check(nullableMapEntry)
    val nullFluent = verbs.check(nullMapEntry)

    describeFun("val ${keyFeature.name}") {
        val keyVal = keyFeature.lambda

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

    describeFun("fun ${key.name}") {
        val keyFun = key.lambda

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
    }


    describeFun("val ${valueFeature.name}") {
        val valueVal = valueFeature.lambda

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

    describeFun("fun ${value.name}") {
        val valueFun = value.lambda

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
        }
    }

    describeFun("val ${nullableKeyFeature.name} for nullable") {
        val nullableKeyVal = nullableKeyFeature.lambda

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

    describeFun("val ${nullableValueFeature.name} for nullable") {
        val nullableValueVal = nullableValueFeature.lambda

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
