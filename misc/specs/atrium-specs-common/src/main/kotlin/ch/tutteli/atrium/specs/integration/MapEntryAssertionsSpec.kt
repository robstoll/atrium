package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapEntryAssertionsSpec(
    isKeyValue: Fun2<Map.Entry<String, Int>, String, Int>,
    isKeyValueNullable: Fun2<Map.Entry<String?, Int?>, String?, Int?>,
    keyFeature: Feature0<Map.Entry<String, Int>, String>,
    key: Fun1<Map.Entry<String, Int>, Expect<String>.() -> Unit>,
    valueFeature: Feature0<Map.Entry<String, Int>, Int>,
    value: Fun1<Map.Entry<String, Int>, Expect<Int>.() -> Unit>,
    nullableKeyFeature: Feature0<Map.Entry<String?, Int?>, String?>,
    nullableKey: Fun1<Map.Entry<String?, Int?>, Expect<String?>.() -> Unit>,
    nullableValueFeature: Feature0<Map.Entry<String?, Int?>, Int?>,
    nullableValue: Fun1<Map.Entry<String?, Int?>, Expect<Int?>.() -> Unit>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Map.Entry<String, Int>>(
        describePrefix,
        isKeyValue.forSubjectLess("key", 1)
    ) {})
    include(object : SubjectLessSpec<Map.Entry<String?, Int?>>(
        "$describePrefix[nullable] ",
        isKeyValueNullable.forSubjectLess("key", 1)
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val mapEntry = mapEntry("a", 1)
    val fluent = expect(mapEntry)

    describeFun(isKeyValue, isKeyValueNullable) {
        val isKeyValueFunctions = uncheckedToNonNullable(isKeyValue, isKeyValueNullable)

        context("map $mapEntry") {
            isKeyValueFunctions.forEach { (name, isKeyValueFun) ->
                it("$name - a to 1 does not throw") {
                    fluent.isKeyValueFun("a", 1)
                }

                it("$name - a to 2 throws AssertionError") {
                    expect {
                        fluent.isKeyValueFun("a", 2)
                    }.toThrow<AssertionError> {
                        message {
                            contains("value: 1", "$toBeDescr: 2")
                            containsNot("key")
                        }
                    }
                }
                it("$name - b to 1 throws AssertionError") {
                    expect {
                        fluent.isKeyValueFun("b", 1)
                    }.toThrow<AssertionError> {
                        message {
                            contains("key: \"a\"", "$toBeDescr: \"b\"")
                            containsNot("value")
                        }
                    }
                }
            }
        }
    }

    describeFun(isKeyValueNullable) {
        val isKeyValueFun = isKeyValueNullable.lambda
        val mapEntryNullable2 = mapEntry(null as String?, null as Int?)
        val fluentNullable = expect(mapEntryNullable2)

        context("map $mapEntryNullable2") {
            it("null to null does not throw") {
                fluentNullable.isKeyValueFun(null, null)
            }

            it("null to 2 throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun(null, 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: null", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            it("b to null throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun("b", null)
                }.toThrow<AssertionError> {
                    message {
                        contains("key: null", "$toBeDescr: \"b\"")
                        containsNot("value")
                    }
                }
            }
        }
    }
})
