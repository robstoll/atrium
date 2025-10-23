package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.TO_CONTAIN

abstract class IterableToContainInAnyOrderAtLeast1ValuesExpectationsSpec(
    toContainInAnyOrderValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    toContainInAnyOrderNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    describePrefix: String = "[Atrium] "
) : IterableToContainSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        toContainInAnyOrderValues.forSubjectLessTest(1.2, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        toContainInAnyOrderNullableValues.forSubjectLessTest(null, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.toContainInAnyOrderNullableValuesFun(t: Double?, vararg tX: Double?) =
        toContainInAnyOrderNullableValues(this, t, tX)

    nonNullableCases(
        describePrefix,
        toContainInAnyOrderValues,
        toContainInAnyOrderNullableValues
    ) { toContainValuesFunArr ->
        fun Expect<Iterable<Double>>.toContainFun(t: Double, vararg tX: Double) =
            toContainValuesFunArr(t, tX.toTypedArray())


        context("empty collection") {
            it("1.1 throws AssertionError") {
                expect {
                    expect(fluentEmpty()).toContainFun(1.1)
                }.toThrow<AssertionError> {
                    messageToContain(
                        "$rootBulletPoint$toContainInAnyOrder: $separator",
                        "$anElementWhichEquals: 1.1",
                        noSuchValueDescr
                    )
                }
            }
        }

        context("iterable '${oneToSeven()}'") {

            context("happy cases") {
                listOf(1.1, 2.1, 3.1, 4.1, 5.1, 6.1, 7.1).forEach {
                    it("$it does not throw") {
                        expect(oneToSeven()).toContainFun(it)
                    }
                }
                it("1.1 and 4.1 does not throw") {
                    expect(oneToSeven()).toContainFun(1.1, 4.1)
                }
                it("1.1 and 1.1 (searching twice in the same assertion) does not throw") {
                    expect(oneToSeven()).toContainFun(1.1, 1.1)
                }
            }

            context("error cases") {
                it("9.5 throws AssertionError") {
                    expect {
                        expect(oneToSeven()).toContainFun(9.5)
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
                        expect(oneToSeven()).toContainFun(9.5, 7.2)
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
                        expect(oneToSeven()).toContainFun(1.1, 9.5)
                    }.toThrow<AssertionError> {
                        message {
                            toContainRegex("$toContainInAnyOrder: $separator.*$anElementWhichEquals: 9.5")
                            notToContain.regex("$toContainInAnyOrder: $separator.*$anElementWhichEquals: 1.1")
                        }
                    }
                }

            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(toContainInAnyOrderNullableValues) {

            context("iterable ${oneToSevenNullable().toList()}") {
                listOf(
                    1.1 to arrayOf<Double>(),
                    4.1 to arrayOf<Double>(),
                    null to arrayOf<Double>(),
                    null to arrayOf(4.1, null),
                    null to arrayOf(1.1),
                    1.1 to arrayOf(4.1, null)
                ).forEach { (first, rest) ->
                    val restText = if (rest.isEmpty()) "" else ", ${rest.joinToString()}"

                    context("search for $first$restText") {
                        it("$first$restText does not throw") {
                            expect(oneToSevenNullable()).toContainInAnyOrderNullableValuesFun(first, *rest)
                        }
                    }

                }

                context("search for 2.5") {
                    it("2.5 throws AssertionError") {
                        expect {
                            expect(oneToSevenNullable()).toContainInAnyOrderNullableValuesFun(2.5)
                        }.toThrow<AssertionError> { messageToContain(TO_CONTAIN.getDefault()) }
                    }
                }
            }
        }
    }
})
