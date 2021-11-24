package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapEntryExpectationsSpec(
    toEqualKeyValue: Fun2<Map.Entry<String, Int>, String, Int>,
    toEqualKeyValueNullable: Fun2<Map.Entry<String?, Int?>, String?, Int?>,
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
        toEqualKeyValue.forSubjectLess("key", 1)
    ) {})

    include(object : SubjectLessSpec<Map.Entry<String?, Int?>>(
        "$describePrefix[nullable] ",
        toEqualKeyValueNullable.forSubjectLess("key", 1)
    ) {})

    include(object : KeyValueLikeExpectationsSpec<Map.Entry<String, Int>, Map.Entry<String?, Int?>>(
        ::mapEntry,
        ::mapEntry,
        "key",
        "value",
        keyFeature,
        key,
        valueFeature,
        value,
        nullableKeyFeature,
        nullableKey,
        nullableValueFeature,
        nullableValue,
        describePrefix
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val mapEntry = mapEntry("hello", 1)
    val fluent = expect(mapEntry)

    describeFun(toEqualKeyValue, toEqualKeyValueNullable) {
        val toEqualKeyValueFunctions = uncheckedToNonNullable(toEqualKeyValue, toEqualKeyValueNullable)

        context("map $mapEntry") {
            toEqualKeyValueFunctions.forEach { (name, toEqualKeyValueFun) ->
                it("$name - hello to 1 does not throw") {
                    fluent.toEqualKeyValueFun("hello", 1)
                }

                it("$name - hello to 2 throws AssertionError") {
                    expect {
                        fluent.toEqualKeyValueFun("hello", 2)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("value: 1", "$toEqualDescr: 2")
                            notToContain("key")
                        }
                    }
                }
                it("$name - b to 1 throws AssertionError") {
                    expect {
                        fluent.toEqualKeyValueFun("b", 1)
                    }.toThrow<AssertionError> {
                        message {
                            toContain("key: \"hello\"", "$toEqualDescr: \"b\"")
                            notToContain("value")
                        }
                    }
                }
            }
        }
    }

    describeFun(toEqualKeyValueNullable) {
        val toEqualKeyValueFun = toEqualKeyValueNullable.lambda
        val mapEntryNullable2 = mapEntry(null as String?, null as Int?)
        val fluentNullable = expect(mapEntryNullable2)

        context("map $mapEntryNullable2") {
            it("null to null does not throw") {
                fluentNullable.toEqualKeyValueFun(null, null)
            }

            it("null to 2 throws AssertionError") {
                expect {
                    fluentNullable.toEqualKeyValueFun(null, 2)
                }.toThrow<AssertionError> {
                    message {
                        toContain("value: null", "$toEqualDescr: 2")
                        notToContain("key")
                    }
                }
            }
            it("b to null throws AssertionError") {
                expect {
                    fluentNullable.toEqualKeyValueFun("b", null)
                }.toThrow<AssertionError> {
                    message {
                        toContain("key: null", "$toEqualDescr: \"b\"")
                        notToContain("value")
                    }
                }
            }
        }
    }

})
