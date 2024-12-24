package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionMapLikeProof
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.toContainInAnyOrderDescr
import org.spekframework.spek2.style.specification.Suite


abstract class MapToContainInAnyOrderKeyValuePairsExpectationsSpec(
    keyValuePairs: MFun2<String, Int, Int>,
    keyValuePairsNullable: MFun2<String?, Int?, Int?>,
    describePrefix: String = "[Atrium] "
) : MapLikeToContainSpecBase({

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
                        expect(map).toContainFun(it.first(), it.drop(1).toTypedArray())
                    }
                }

                it("$name - a to 1 and a to 1 does not throw (no unique match)") {
                    expect(map).toContainFun("a" to 1, arrayOf("a" to 1))
                }

                it("$name - {a to 1, b to 3, c to 4} throws AssertionError, reports b and c") {
                    expect {
                        expect(map).toContainFun("a" to 1, arrayOf("b" to 3, "c" to 4))
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex(
                                "$g${toContainInAnyOrderDescr} : $lineSeparator" +
                                    "$indentG$g$f${entry("b", 2)}$lineSeparator" +
                                    "$indentGg$indentF$x$toEqualDescr : 3$lineSeparator" +
                                    "$indentG$g$f${entry("c", keyDoesNotExist)}$lineSeparator" +
                                    "$indentGg$indentF$explanatoryBulletPoint$toEqualDescr : 4"
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
                    expect(nullableMap).toContainNullableFun(it.first(), it.drop(1).toTypedArray())
                }
            }

            it("a to null and a to null does not throw (no unique match)") {
                expect(nullableMap).toContainNullableFun("a" to null, arrayOf("a" to null))
            }

            it("{a to null, null to 2, b to 3, c to 4} throws AssertionError, reports a, null, b and c") {
                expect {
                    expect(nullableMap).toContainNullableFun("a" to null, arrayOf(null to 2, "b" to 3, "c" to 4))
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex(
                            "$g${toContainInAnyOrderDescr} : $lineSeparator" +
                                "$indentG$g$f${entry(null, 1)}$lineSeparator" +
                                "$indentGg$indentF$x$toEqualDescr : 2$lineSeparator" +
                                "$indentG$g$f${entry("b", 2)}$lineSeparator" +
                                "$indentGg$indentF$x$toEqualDescr : 3$lineSeparator" +
                                "$indentG$g$f${entry("c", keyDoesNotExist)}$lineSeparator" +
                                "$indentGg$indentF$explanatoryBulletPoint$toEqualDescr : 4"
                        )
                        notToContain(entry("a"))
                    }
                }
            }
        }
    }
})
