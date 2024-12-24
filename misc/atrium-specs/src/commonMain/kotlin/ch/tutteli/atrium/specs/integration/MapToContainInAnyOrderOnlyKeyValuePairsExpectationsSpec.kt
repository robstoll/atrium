package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite

abstract class MapToContainInAnyOrderOnlyKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainFormatSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyValuePairs.forSubjectLessTest("key" to 1, arrayOf())
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyValuePairsNullable.forSubjectLessTest(null to 1, arrayOf("a" to null))
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    describeFun(keyValuePairs, keyValuePairsNullable) {
        val toContainKeyValuePairsFunctions = uncheckedToNonNullable(keyValuePairs, keyValuePairsNullable)

        context("empty map") {
            toContainKeyValuePairsFunctions.forEach { (name, toContainFun) ->
                it("$name - a to 1 throws AssertionError, reports a") {
                    expect {
                        expect(emptyMap).toContainFun("a" to 1, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(0, 1)
                            entryNonExisting("a", "$toEqualDescr : 1")
                        }
                    }
                }

                it("$name - a to 1, b to 3, c to 4 throws AssertionError, reports a, b and c") {
                    expect {
                        expect(emptyMap).toContainFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(0, 3)
                            entryNonExisting("a", "$toEqualDescr : 1")
                            entryNonExisting("b", "$toEqualDescr : 3")
                            entryNonExisting("c", "$toEqualDescr : 4")
                        }
                    }
                }
            }
        }

        context("map $map") {
            toContainKeyValuePairsFunctions.forEach { (name, toContainFun) ->
                listOf(
                    listOf("a" to 1, "b" to 2),
                    listOf("b" to 2, "a" to 1)
                ).forEach {
                    it("$name - ${it.joinToString()} does not throw") {
                        expect(map).toContainFun(it.first(), it.drop(1).toTypedArray())
                    }
                }

                it("$name - a to 1 throws AssertionError, reports second a and missing b") {
                    expect {
                        expect(map).toContainFun("a" to 1, arrayOf())
                    }.toThrow<AssertionError> {
                        message {
                            toContainSize(2, 1)
                            toContainInAnyOrderOnlyDescr()
                            entrySuccess("a", 1, "$toEqualDescr : 1")
                            additionalEntries("b" to 2)
                        }
                    }
                }

                it("$name - a to 1, a to 1 throws AssertionError, reports second a and missing b") {
                    expect {
                        expect(map).toContainFun("a" to 1, arrayOf("a" to 1))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            entrySuccess("a", 1, "$toEqualDescr : 1")
                            entryNonExisting("a", "$toEqualDescr : 1")
                            additionalEntries("b" to 2)
                            notToContain(sizeDescr)
                        }
                    }
                }

                it("$name - a to 1, b to 3, c to 4 throws AssertionError, reports b and c") {
                    expect {
                        expect(map).toContainFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            toContainInAnyOrderOnlyDescr()
                            toContainSize(2, 3)
                            entrySuccess("a", 1, "$toEqualDescr : 1")
                            entryFailing("b", 2, "$toEqualDescr : 3")
                            entryNonExisting("c", "$toEqualDescr : 4")
                        }
                    }
                }
            }

        }
    }

    describeFun(keyValuePairsNullable) {
        val toContainFun = keyValuePairsNullable.lambda
        context("map: $nullableMap") {
            listOf(
                listOf("a" to null, null to 1, "b" to 2),
                listOf("a" to null, "b" to 2, null to 1),
                listOf("b" to 2, null to 1, "a" to null),
                listOf(null to 1, "a" to null, "b" to 2)
            ).forEach {
                it("$it does not throw") {
                    expect(nullableMap).toContainFun(it.first(), it.drop(1).toTypedArray())
                }
            }
            it("a to 1, c to 3, null to null, b to 2 throws AssertionError, reports all but b") {
                expect {
                    expect(nullableMap).toContainFun("a" to 1, arrayOf("c" to 3, null to null, "b" to 2))
                }.toThrow<AssertionError> {
                    message {
                        toContainInAnyOrderOnlyDescr()
                        toContainSize(3, 4)
                        entryFailing("a", null, "$toEqualDescr : 1")
                        entryNonExisting("c", "$toEqualDescr : 3")
                        entryFailing(null, "1", "$toEqualDescr : null")
                        entrySuccess("b", "2", "$toEqualDescr : 2")
                    }
                }
            }
        }
    }
})
