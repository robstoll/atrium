package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.anElementWhichEquals
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.emptyIterable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.noSuchValueDescr
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSeven
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.oneToSevenNullable
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.separator
import ch.tutteli.atrium.specs.integration.IterableToContainSpecBase.Companion.toContainInAnyOrder
import ch.tutteli.atrium.specs.integration.utils.SubjectLessTestData
import ch.tutteli.atrium.specs.invoke
import ch.tutteli.atrium.testfactories.TestFactory
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.TO_CONTAIN

@Suppress("FunctionName")
abstract class AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
    private val toContainInAnyOrderValuesSpec: Fun2<Iterable<Double>, Double, Array<out Double>>,
    private val toContainInAnyOrderNullableValuesSpec: Fun2<Iterable<Double?>, Double?, Array<out Double?>>
) : ExpectationFunctionBaseTest() {

    @TestFactory
    fun subjectLessTest() = subjectLessTestFactory(
        SubjectLessTestData(
            toContainInAnyOrderValuesSpec.forSubjectLessTest(1.2, arrayOf())
        ),
        SubjectLessTestData(
            toContainInAnyOrderNullableValuesSpec.forSubjectLessTest(null, arrayOf())
        )
    )

    fun Expect<Iterable<Double?>>.toContainInAnyOrderNullableValuesFun(t: Double?, vararg tX: Double?) =
        toContainInAnyOrderNullableValuesSpec(this, t, tX)

    @TestFactory
    fun empty_collection() = testFactoryNonNullable(
        toContainInAnyOrderValuesSpec,
        toContainInAnyOrderNullableValuesSpec
    ) { toContainInAnyOrderValuesFun ->
        it("1.1 throws AssertionError") {
            expect {
                expect(emptyIterable()).toContainInAnyOrderValuesFun(1.1, arrayOf())
            }.toThrow<AssertionError> {
                messageToContain(
                    "$rootBulletPoint$toContainInAnyOrder: $separator",
                    "$anElementWhichEquals: 1.1",
                    noSuchValueDescr
                )
            }
        }
    }

    @TestFactory
    fun one_to_seven() = testFactoryNonNullable(
        toContainInAnyOrderValuesSpec,
        toContainInAnyOrderNullableValuesSpec
    ) { toContainInAnyOrderValuesFun ->
        describe("happy cases") {
            listOf(1.1, 2.1, 3.1, 4.1, 5.1, 6.1, 7.1).forEach {
                it("$it does not throw") {
                    expect(oneToSeven()).toContainInAnyOrderValuesFun(it, arrayOf())
                }
            }
            it("1.1 and 4.1 does not throw") {
                expect(oneToSeven()).toContainInAnyOrderValuesFun(1.1, arrayOf(4.1))
            }
            it("1.1 and 1.1 (searching twice in the same assertion) does not throw") {
                expect(oneToSeven()).toContainInAnyOrderValuesFun(1.1, arrayOf(1.1))
            }
        }
        describe("error cases") {
            it("9.5 throws AssertionError") {
                expect {
                    expect(oneToSeven()).toContainInAnyOrderValuesFun(9.5, arrayOf())
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichEquals: 9.5",
                        noSuchValueDescr
                    )
                }
            }
            it("9.5 and 7.2 throws AssertionError") {
                expect {
                    expect(oneToSeven()).toContainInAnyOrderValuesFun(9.5, arrayOf(7.2))
                }.toThrow<AssertionError> {
                    message {
                        toContain.exactly(2).values(
                            noSuchValueDescr
                        )
                        toContain.exactly(1).values(
                            "$rootBulletPoint$toContainInAnyOrder: $separator",
                            "$anElementWhichEquals: 9.5",
                            "$anElementWhichEquals: 7.2"
                        )
                    }
                }
            }
            it("1.1 and 9.5 throws AssertionError") {
                expect {
                    expect(oneToSeven()).toContainInAnyOrderValuesFun(1.1, arrayOf(9.5))
                }.toThrow<AssertionError> {
                    message {
                        toContainRegex("$toContainInAnyOrder: $separator.*$anElementWhichEquals: 9.5")
                        notToContain.regex("$toContainInAnyOrder: $separator.*$anElementWhichEquals: 1.1")
                    }
                }
            }
        }
    }

    @TestFactory
    fun nullable_cases() = testFactory(toContainInAnyOrderNullableValuesSpec) {
        describeIterable(::oneToSevenNullable) {
            listOf(
                1.1 to arrayOf<Double>(),
                4.1 to arrayOf<Double>(),
                null to arrayOf<Double>(),
                null to arrayOf(4.1, null),
                null to arrayOf(1.1),
                1.1 to arrayOf(4.1, null)
            ).forEach { (first, rest) ->
                val restText = if (rest.isEmpty()) "" else ", ${rest.joinToString()}"

                describe("search for $first$restText") {
                    it("$first$restText does not throw") {
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableValuesFun(first, *rest)
                    }
                }

            }

            describe("search for 2.5") {
                it("2.5 throws AssertionError") {
                    expect {
                        expect(oneToSevenNullable()).toContainInAnyOrderNullableValuesFun(2.5)
                    }.toThrow<AssertionError> { messageToContain(TO_CONTAIN.getDefault()) }
                }
            }
        }
    }
}
