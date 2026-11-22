package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.additionalEntries
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entryFailing
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entryNonExisting
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.entrySuccess
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.sizeDescr
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.toContainInAnyOrderOnlyDescr
import ch.tutteli.atrium.specs.integration.MapLikeToContainFormatSpecBase.Companion.toContainSize
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.emptyMap
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.map
import ch.tutteli.atrium.specs.integration.MapLikeToContainSpecBase.Companion.nullableMap
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.testfactories.TestFactory

@Suppress("FunctionName")
abstract class AbstractMapToContainInAnyOrderOnlyKeyValuePairsExpectationsTest(
    private val keyValuePairsSpec: MFun2<String, Int, Int>,
    private val keyValuePairsNullableSpec: MFun2<String?, Int?, Int?>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            keyValuePairsSpec.forSubjectLessTest("key" to 1, arrayOf())
        ),
        SubjectLessTestData(
            keyValuePairsNullableSpec.forSubjectLessTest(null to 1, arrayOf("a" to null)),
            groupPrefix = "[nullable Key] "
        )
    )

    @TestFactory
    fun empty_map() = testFactoryNonNullable(
        keyValuePairsSpec, keyValuePairsNullableSpec
    ) { toContainKeyValuePairsFunArr ->
        it("a to 1 throws AssertionError, reports a") {
            expect {
                expect(emptyMap).toContainKeyValuePairsFunArr("a" to 1, arrayOf())
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(0, 1)
                    entryNonExisting("a", "$toEqualDescr: 1")
                }
            }
        }

        it("a to 1, b to 3, c to 4 throws AssertionError, reports a, b and c") {
            expect {
                expect(emptyMap).toContainKeyValuePairsFunArr("a" to 1, arrayOf("b" to 3, "c" to 4))
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(0, 3)
                    entryNonExisting("a", "$toEqualDescr: 1")
                    entryNonExisting("b", "$toEqualDescr: 3")
                    entryNonExisting("c", "$toEqualDescr: 4")
                }
            }
        }
    }

    @TestFactory
    fun map_a_to_1__b_to_2() = testFactoryNonNullable(
        keyValuePairsSpec, keyValuePairsNullableSpec
    ) { toContainKeyValuePairsFunArr ->
        listOf(
            listOf("a" to 1, "b" to 2),
            listOf("b" to 2, "a" to 1)
        ).forEach { pairs ->
            it("${pairs.joinToString()} does not throw") {
                expect(map).toContainKeyValuePairsFunArr(pairs.first(), pairs.drop(1).toTypedArray())
            }
        }

        it("a to 1 throws AssertionError, reports second a and missing b") {
            expect {
                expect(map).toContainKeyValuePairsFunArr("a" to 1, arrayOf())
            }.toThrow<AssertionError> {
                message {
                    toContainSize(2, 1)
                    toContainInAnyOrderOnlyDescr()
                    entrySuccess("a", 1, "$toEqualDescr: 1")
                    additionalEntries("b" to 2)
                }
            }
        }

        it("a to 1, a to 1 throws AssertionError, reports second a and missing b") {
            expect {
                expect(map).toContainKeyValuePairsFunArr("a" to 1, arrayOf("a" to 1))
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    entrySuccess("a", 1, "$toEqualDescr: 1")
                    entryNonExisting("a", "$toEqualDescr: 1")
                    additionalEntries("b" to 2)
                    notToContain(sizeDescr)
                }
            }
        }

        it("a to 1, b to 3, c to 4 throws AssertionError, reports b and c") {
            expect {
                expect(map).toContainKeyValuePairsFunArr("a" to 1, arrayOf("b" to 3, "c" to 4))
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(2, 3)
                    entrySuccess("a", 1, "$toEqualDescr: 1")
                    entryFailing("b", 2, "$toEqualDescr: 3")
                    entryNonExisting("c", "$toEqualDescr: 4")
                }
            }
        }
    }

    @TestFactory
    fun nullable_map() = testFactory(keyValuePairsNullableSpec) { toContainKeyValuePairsFunArr ->
        listOf(
            listOf("a" to null, null to 1, "b" to 2),
            listOf("a" to null, "b" to 2, null to 1),
            listOf("b" to 2, null to 1, "a" to null),
            listOf(null to 1, "a" to null, "b" to 2)
        ).forEach { pairs ->
            it("$pairs does not throw") {
                expect(nullableMap).toContainKeyValuePairsFunArr(pairs.first(), pairs.drop(1).toTypedArray())
            }
        }
        it("a to 1, c to 3, null to null, b to 2 throws AssertionError, reports all but b") {
            expect {
                expect(nullableMap).toContainKeyValuePairsFunArr("a" to 1, arrayOf("c" to 3, null to null, "b" to 2))
            }.toThrow<AssertionError> {
                message {
                    toContainInAnyOrderOnlyDescr()
                    toContainSize(3, 4)
                    entryFailing("a", null, "$toEqualDescr: 1")
                    entryNonExisting("c", "$toEqualDescr: 3")
                    entryFailing(null, "1", "$toEqualDescr: null")
                    entrySuccess("b", "2", "$toEqualDescr: 2")
                }
            }
        }
    }
}
