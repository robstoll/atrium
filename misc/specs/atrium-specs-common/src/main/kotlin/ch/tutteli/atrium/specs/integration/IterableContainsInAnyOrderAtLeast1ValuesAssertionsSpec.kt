package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.*
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class IterableContainsInAnyOrderAtLeast1ValuesAssertionsSpec(
    containsInAnyOrderValues: Fun2<Iterable<Double>, Double, Array<out Double>>,
    containsInAnyOrderNullableValues: Fun2<Iterable<Double?>, Double?, Array<out Double?>>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterableContainsSpecBase({

    include(object : SubjectLessSpec<Iterable<Double>>(
        describePrefix,
        containsInAnyOrderValues.forSubjectLess(1.2, arrayOf())
    ) {})
    include(object : SubjectLessSpec<Iterable<Double?>>(
        describePrefix,
        containsInAnyOrderNullableValues.forSubjectLess(null, arrayOf())
    ) {})

    fun Expect<Iterable<Double?>>.containsInAnyOrderNullableValuesFun(t: Double?, vararg tX: Double?) =
        containsInAnyOrderNullableValues(this, t, tX)

    nonNullableCases(
        describePrefix,
        containsInAnyOrderValues,
        containsInAnyOrderNullableValues
    ) { containsValuesFunArr ->
        fun Expect<Iterable<Double>>.containsFun(t: Double, vararg tX: Double) =
            containsValuesFunArr(t, tX.toTypedArray())


        context("empty collection") {
            it("1.0 throws AssertionError") {
                expect {
                    fluentEmpty.containsFun(1.0)
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$containsInAnyOrder: $separator",
                        "$anEntryWhichIs: 1.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
        }

        val fluent = expect(oneToSeven)
        context("iterable '$oneToSeven'") {

            context("happy cases") {
                (1..7).forEach {
                    val d = it.toDouble()
                    it("$d does not throw") {
                        fluent.containsFun(d)
                    }
                }
                it("1.0 and 4.0 does not throw") {
                    fluent.containsFun(1.0, 4.0)
                }
                it("1.0 and 1.0 (searching twice in the same assertion) does not throw") {
                    fluent.containsFun(1.0, 1.0)
                }
            }

            context("error cases") {
                it("9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5)
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhichIs: 9.5",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
                it("9.5 and 7.1 throws AssertionError") {
                    expect {
                        fluent.containsFun(9.5, 7.1)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhichIs: 9.5",
                                "$anEntryWhichIs: 7.1"
                            )
                        }
                    }
                }
                it("1.0 and 9.5 throws AssertionError") {
                    expect {
                        fluent.containsFun(1.0, 9.5)
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex("$containsInAnyOrder: $separator.*$anEntryWhichIs: 9.5")
                            containsNot.regex("$containsInAnyOrder: $separator.*$anEntryWhichIs: 1.0")
                        }
                    }
                }

            }
        }
    }

    nullableCases(describePrefix) {

        describeFun("${containsInAnyOrderNullableValues.name} for nullable") {

            val list = listOf(null, 1.0, null, 3.0).asIterable()
            val fluent = expect(list)

            context("iterable $list") {
                listOf(
                    1.0 to arrayOf<Double>(),
                    3.0 to arrayOf<Double>(),
                    null to arrayOf<Double>(),
                    null to arrayOf(3.0, null),
                    null to arrayOf(1.0),
                    1.0 to arrayOf(3.0, null)
                ).forEach { (first, rest) ->
                    val restText = if (rest.isEmpty()) "" else ", ${rest.joinToString()}"

                    context("search for $first$restText") {
                        it("$first$restText does not throw") {
                            fluent.containsInAnyOrderNullableValuesFun(first, *rest)
                        }
                    }

                }

                context("search for 2.5") {
                    it("2.5 throws AssertionError") {
                        expect {
                            fluent.containsInAnyOrderNullableValuesFun(2.5)
                        }.toThrow<AssertionError> { messageContains(DescriptionIterableAssertion.CONTAINS.getDefault()) }
                    }
                }
            }
        }
    }
})
