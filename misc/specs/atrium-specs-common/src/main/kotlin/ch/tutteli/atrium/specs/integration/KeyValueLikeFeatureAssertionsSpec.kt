package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class KeyValueLikeFeatureAssertionsSpec<T : Any, TNullable : Any>(
    creator: (String, Int) -> T,
    creatorNullable: (String?, Int?) -> TNullable,
    keyName: String,
    valueName: String,
    keyFeature: Feature0<T, String>,
    key: Fun1<T, Expect<String>.() -> Unit>,
    valueFeature: Feature0<T, Int>,
    value: Fun1<T, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<TNullable, String?>,
    nullableKey: Fun1<TNullable, Expect<String?>.() -> Unit>,
    nullableValueFeature: Feature0<TNullable, Int?>,
    nullableValue: Fun1<TNullable, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    val mapEntry = creator("hello", 1)
    val nullMapEntry = creatorNullable(null, null)
    val toBeDescr = TO_BE.getDefault()

    include(object : SubjectLessSpec<T>(describePrefix,
        keyFeature.forSubjectLess().adjustName { "$it feature" },
        key.forSubjectLess { endsWith("a") },
        valueFeature.forSubjectLess().adjustName { "$it feature" },
        value.forSubjectLess { isGreaterThan(2) }
    ) {})
    include(object : SubjectLessSpec<TNullable>(
        "$describePrefix[nullable] ",
        nullableKeyFeature.forSubjectLess().adjustName { "$it feature" },
        nullableValueFeature.forSubjectLess().adjustName { "$it feature" }
    ) {})

    include(object : AssertionCreatorSpec<T>(
        describePrefix, mapEntry,
        key.forAssertionCreatorSpec("$toBeDescr: hello") { toBe("hello") },
        value.forAssertionCreatorSpec("$toBeDescr: 1") { toBe(1) }
    ) {})
    include(object : AssertionCreatorSpec<TNullable>(
        describePrefix, nullMapEntry,
        nullableKey.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) },
        nullableValue.forAssertionCreatorSpec("$toBeDescr: null") { toBe(null) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val nullableMapEntry = creatorNullable("hello", 1)

    val fluent = expect(mapEntry)
    val nullableFluent = expect(nullableMapEntry)
    val nullFluent = expect(nullMapEntry)

    describeFun("${keyFeature.name} feature") {
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

    describeFun(key.name) {
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


    describeFun("${valueFeature.name} feature") {
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

    describeFun(value.name) {
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

    describeFun("${nullableKeyFeature.name} nullable feature") {
        val nullableKeyFun = nullableKeyFeature.lambda

        context("$nullableMapEntry") {
            it("toBe(hello)") {
                nullableFluent.nullableKeyFun().toBe("hello")
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyFun().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableKeyFun().toBe(null)
            }
            it("toBe(hello) fails") {
                expect {
                    nullFluent.nullableKeyFun().toBe("hello")
                }.toThrow<AssertionError> {
                    messageContains("$keyName: null")
                }
            }
        }
    }

    describeFun("${nullableKey.name} nullable") {
        val nullableKeyFun = nullableKey.lambda

        context("$nullableMapEntry") {
            it("toBe(hello)") {
                nullableFluent.nullableKeyFun { toBe("hello") }
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableKeyFun { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("$keyName: \"hello\"")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableKeyFun { toBe(null) }
            }
            it("toBe(hello) fails") {
                expect {
                    nullFluent.nullableKeyFun { toBe("hello") }
                }.toThrow<AssertionError> {
                    messageContains("$keyName: null")
                }
            }
        }
    }


    describeFun("${nullableValueFeature.name} nullable feature") {
        val nullableValueFun = nullableValueFeature.lambda

        context("$nullableMapEntry") {
            it("isGreaterThan(0) holds") {
                nullableFluent.nullableValueFun().notToBeNull { isGreaterThan(0) }
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueFun().toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: 1")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableValueFun().toBe(null)
            }
            it("toBe(1) fails") {
                expect {
                    nullFluent.nullableValueFun().toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("$valueName: null")
                }
            }
        }
    }

    describeFun("${nullableValue.name} nullable") {
        val nullableValueFun = nullableValue.lambda

        context("$nullableMapEntry") {
            it("isGreaterThan(0) holds") {
                nullableFluent.nullableValueFun { notToBeNull { isGreaterThan(0) } }
            }
            it("toBe(null) fails") {
                expect {
                    nullableFluent.nullableValueFun { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("$valueName: 1")
                }
            }
        }
        context("$nullMapEntry") {
            it("toBe(null)") {
                nullFluent.nullableValueFun { toBe(null) }
            }
            it("toBe(1) fails") {
                expect {
                    nullFluent.nullableValueFun { toBe(1) }
                }.toThrow<AssertionError> {
                    messageContains("$valueName: null")
                }
            }
        }
    }
})
