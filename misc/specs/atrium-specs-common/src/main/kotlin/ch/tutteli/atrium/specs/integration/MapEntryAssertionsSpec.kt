package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.AssertionVerbFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class MapEntryAssertionsSpec(
    verbs: AssertionVerbFactory,
    isKeyValue: Fun2<Map.Entry<String, Int>, String, Int>,
    isKeyValueNullable: Fun2<Map.Entry<String?, Int?>, String?, Int?>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessSpec<Map.Entry<String, Int>>(describePrefix,
        isKeyValue.forSubjectLess("key", 1)
    ) {})
    include(object : SubjectLessSpec<Map.Entry<String?, Int?>>("$describePrefix[nullable] ",
        isKeyValueNullable.forSubjectLess("key", 1)
    ) {})

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

    val expect = verbs::checkException
    val mapEntry = mapEntry("a", 1)
    val fluent = verbs.check(mapEntry)
    val mapEntryNullable = mapEntry("a" as String?, 1 as Int?)
    val fluentNullable = verbs.check(mapEntryNullable)

    describeFun(isKeyValue.name) {
        val isKeyValueFun = isKeyValue.lambda
        context("map $mapEntry") {
            it("a to 1 does not throw") {
                fluent.isKeyValueFun("a", 1)
            }

            it("a to 2 throws AssertionError") {
                expect {
                    fluent.isKeyValueFun("a", 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: 1", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            it("b to 1 throws AssertionError") {
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

    describeFun("${isKeyValueNullable.name} nullable") {
        val isKeyValueFun = isKeyValueNullable.lambda
        context("map $mapEntryNullable") {
            it("a to 1 does not throw") {
                fluentNullable.isKeyValueFun("a", 1)
            }

            it("a to 2 throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun("a", 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: 1", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            it("b to 1 throws AssertionError") {
                expect {
                    fluentNullable.isKeyValueFun("b", 1)
                }.toThrow<AssertionError> {
                    message {
                        contains("key: \"a\"", "$toBeDescr: \"b\"")
                        containsNot("value")
                    }
                }
            }
        }
        val mapEntryNullable2 = mapEntry(null as String?, null as Int?)
        val fluentNullable2 = verbs.check(mapEntryNullable2)

        context("map $mapEntryNullable2") {
            it("null to null does not throw") {
                fluentNullable2.isKeyValueFun(null, null)
            }

            it("null to 2 throws AssertionError") {
                expect {
                    fluentNullable2.isKeyValueFun(null, 2)
                }.toThrow<AssertionError> {
                    message {
                        contains("value: null", "$toBeDescr: 2")
                        containsNot("key")
                    }
                }
            }
            it("b to null throws AssertionError") {
                expect {
                    fluentNullable2.isKeyValueFun("b", null)
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
