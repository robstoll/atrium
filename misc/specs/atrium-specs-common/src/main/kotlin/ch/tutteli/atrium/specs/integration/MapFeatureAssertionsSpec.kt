package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.verbs.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapFeatureAssertionsSpec(
    verbs: AssertionVerbFactory,
    keysFeature: Feature0<Map<String, Int>, Set<String>>,
    keys: Fun1<Map<String, Int>, Expect<Set<String>>.() -> Unit>,
    valuesFeature: Feature0<Map<String, Int>, Collection<Int>>,
    values: Fun1<Map<String, Int>, Expect<Collection<Int>>.() -> Unit>,
    getExistingFeature: Feature1<Map<String, Int>, String, Int>,
    getExisting: Fun2<Map<String, Int>, String, Expect<Int>.() -> Unit>,
    getExistingNullableFeature: Feature1<Map<String?, Int?>, String?, Int?>,
    getExistingNullable: Fun2<Map<String?, Int?>, String?, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Map<String, Int>>(describePrefix,
        keysFeature.forSubjectLess().adjustName { "$it feature" },
        keys.forSubjectLess { isEmpty() },
        valuesFeature.forSubjectLess().adjustName { "$it feature" },
        values.forSubjectLess { isEmpty() },
        getExistingFeature.forSubjectLess("a"),
        getExisting.forSubjectLess("a") { isGreaterThan(1) }
    ) {})
    include(object : SubjectLessSpec<Map<String?, Int?>>("$describePrefix[nullable Key] ",
        getExistingNullableFeature.forSubjectLess("a"),
        getExistingNullable.forSubjectLess("a") { toBe(null) }
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Expect<Map<String, Int>> = verbs::check
    val expect = verbs::checkException
    val map = mapOf("a" to 1, "b" to 2)
    val fluent = assert(map)
    val mapNullable = mapOf("a" to 1, "b" to null, null to 3)
    val fluentNullable = verbs.check(mapNullable)

    val toBeDescr = DescriptionAnyAssertion.TO_BE.getDefault()
    val keyDoesNotExist = DescriptionMapAssertion.KEY_DOES_NOT_EXIST.getDefault()

    describeFun("${keysFeature.name} feature") {
        val keysVal = keysFeature.lambda

        context("map with two entries") {
            it("hasSize(2) holds") {
                fluent.keysVal().hasSize(2)
            }
            it("hasSize(1) fails") {
                expect {
                    fluent.keysVal().hasSize(1)
                }.toThrow<AssertionError> {
                    messageContains("keys: [a, b]")
                }
            }
        }
    }

    describeFun(keys.name) {
        val keysFun = keys.lambda

        context("map with two entries") {
            it("hasSize(2) holds") {
                fluent.keysFun { hasSize(2) }
            }
            it("hasSize(1) fails") {
                expect {
                    fluent.keysFun { hasSize(1) }
                }.toThrow<AssertionError> {
                    messageContains("keys: [a, b]")
                }
            }
            it("throws if no assertion is made") {
                expect {
                    fluent.keysFun { }
                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
            }
        }
    }


    describeFun("${valuesFeature.name} feature") {
        val valuesVal = valuesFeature.lambda

        context("map with two entries") {
            it("hasSize(2) holds") {
                fluent.valuesVal().hasSize(2)
            }
            it("hasSize(1) fails") {
                expect {
                    fluent.valuesVal().hasSize(1)
                }.toThrow<AssertionError> {
                    messageContains("values: [1, 2]")
                }
            }
        }
    }

    describeFun(values.name) {
        val valuesFun = values.lambda

        context("map with two entries") {
            it("hasSize(2) holds") {
                fluent.valuesFun { hasSize(2) }
            }
            it("hasSize(1) fails") {
                expect {
                    fluent.valuesFun { hasSize(1) }
                }.toThrow<AssertionError> {
                    messageContains("values: [1, 2]")
                }
            }
            it("throws if no assertion is made") {
                expect {
                    fluent.valuesFun { }
                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
            }
        }
    }

    describeFun("${getExistingFeature.name} feature") {
        val getExistingFun = getExistingFeature.lambda
        context("map $map") {
            it("can perform sub-assertion on existing key") {
                fluent.getExistingFun("a").toBe(1)
            }
            it("non-existing key throws") {
                expect {
                    fluent.getExistingFun("c").toBe(1)
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist")
                }
            }
        }
    }

    describeFun(getExisting.name) {
        val getExistingFun = getExisting.lambda

        context("map $map") {
            it("can perform sub-assertion on existing key") {
                fluent.getExistingFun("a") { toBe(1) }
            }
            it("non-existing key throws but shows intended sub-assertion") {
                expect {
                    fluent.getExistingFun("c") { toBe(3) }
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist", "$toBeDescr: 3")
                }
            }
            it("throws if no assertion is made") {
                expect {
                    fluent.getExistingFun("a") { }
                }.toThrow<IllegalStateException> { messageContains("There was not any assertion created") }
            }
        }
    }

    describeFun("${getExistingNullableFeature.name} nullable feature") {
        val getExistingNullableFun = getExistingNullableFeature.lambda

        context("map $mapNullable") {
            it("can perform sub-assertion on existing key") {
                fluentNullable.getExistingNullableFun("a").toBe(1)
            }
            it("can perform sub-assertion on existing key which is null") {
                fluentNullable.getExistingNullableFun(null).toBe(3)
            }
            it("can perform sub-assertion on existing key whose value is null") {
                fluentNullable.getExistingNullableFun("b").toBe(null)
            }
            it("non-existing key throws") {
                expect {
                    fluentNullable.getExistingNullableFun("c").toBe(null)
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist")
                }
            }
        }
    }

    describeFun("$getExistingNullable for nullable") {
        val getExistingNullableFun = getExistingNullable.lambda

        context("map $mapNullable") {
            it("can perform sub-assertion on existing key") {
                fluentNullable.getExistingNullableFun("a") { toBe(1) }
            }
            it("can perform sub-assertion on existing key which is null") {
                fluentNullable.getExistingNullableFun(null) { toBe(3) }
            }
            it("can perform sub-assertion on existing key whose value is null") {
                fluentNullable.getExistingNullableFun("b") { toBe(null) }
            }
            it("non-existing key throws but shows intended sub-assertion") {
                expect {
                    fluentNullable.getExistingNullableFun("c") { toBe(null) }
                }.toThrow<AssertionError> {
                    messageContains("get(\"c\"): $keyDoesNotExist", "$toBeDescr: null")
                }
            }
        }
    }
})
