package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import org.spekframework.spek2.style.specification.Suite


abstract class MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainSpecBase({

    include(object : SubjectLessSpec<Map<out String, Int>>(
        describePrefix,
        keyValuePairs.forSubjectLess("key" to 1, arrayOf())
    ) {})

    include(object : SubjectLessSpec<Map<out String?, Int?>>(
        "$describePrefix[nullable Key] ",
        keyValuePairsNullable.forSubjectLess(null to 1, arrayOf("a" to null))
    ) {})

    fun describeFun(vararg pairs: SpecPair<*>, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, pairs.map { it.name }.toTypedArray(), body = body)

    val fluent = expect(map)
    val nullableFluent = expect(nullableMap)

    describeFun(keyValuePairs, keyValuePairsNullable) {
        val toContainFunctions = uncheckedToNonNullable(keyValuePairs, keyValuePairsNullable)

        context("map $map") {
            toContainFunctions.forEach { (name, toContainFun) ->
                listOf(
                    listOf("a" to 1),
                    listOf("b" to 2),
                    listOf("a" to 1, "b" to 2),
                    listOf("b" to 2, "a" to 1)
                ).forEach {
                    it("$name - $it does not throw") {
                        fluent.toContainFun(it.first(), it.drop(1).toTypedArray())
                    }
                }

                it("$name - a to 1 and a to 1 does not throw (no unique match)") {
                    fluent.toContainFun("a" to 1, arrayOf("a" to 1))
                }

                it("$name - {a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
                    expect {
                        fluent.toContainFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            toContain(
                                entry("b", 2),
                                "$toBeDescr: 3",
                                entry("c", keyDoesNotExist),
                                "$toBeDescr: 4"
                            )
                            notToContain(entry("a"))
                        }
                    }
                }
            }
        }
    }

    describeFun(keyValuePairsNullable) {
        val toContainNullableFun = keyValuePairsNullable.lambda
        context("map $nullableMap") {
            listOf(
                listOf("a" to null),
                listOf(null to 1),
                listOf("b" to 2),
                listOf("a" to null, "b" to 2),
                listOf(null to 1, "b" to 2),
                listOf(null to 1, "a" to null),
                listOf(null to 1, "a" to null, "b" to 2),
                listOf("b" to 2, null to 1, "a" to null)
            ).forEach {
                it("$it does not throw") {
                    nullableFluent.toContainNullableFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            it("a to null and a to null does not throw (no unique match)") {
                nullableFluent.toContainNullableFun("a" to null, arrayOf("a" to null))
            }

            it("{a to null, null to 2, b to 3, c to 4} throws AssertionError, reports a, null, b and c") {
                expect {
                    nullableFluent.toContainNullableFun("a" to null, arrayOf(null to 2, "b" to 3, "c" to 4))
                }.toThrow<AssertionError> {
                    message {
                        toContain(
                            entry("b", 2),
                            "$toBeDescr: 3",
                            entry("c", keyDoesNotExist),
                            "$toBeDescr: 4"
                        )
                        notToContain(entry("a"))
                    }
                }
            }
        }
    }
})
