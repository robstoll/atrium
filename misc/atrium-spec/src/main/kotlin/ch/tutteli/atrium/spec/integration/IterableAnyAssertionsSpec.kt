package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableAnyAssertionsSpec(
    verbs: AssertionVerbFactory,
    anyPair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Assert<Iterable<Double>>>,
    anyNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?) -> Assert<Iterable<Double?>>>,
    rootBulletPoint: String,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        anyPair.first to mapToCreateAssertion { anyPair.second(this, { toBe(2.5) }) },
        anyNullablePair.first to mapToCreateAssertion { anyNullablePair.second(this, null) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(anyPair.first, { anyPair.second(this, { toBe(2.5) }) }, listOf(2.5).asIterable(), listOf()),
        checkingTriple(anyNullablePair.first, { anyNullablePair.second(this, { toBe(2.5) }) }, listOf(2.5).asIterable(), listOf())
    ) {})

    val assert: (Iterable<Double>) -> Assert<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException

    val (anyOfNullable, anyOfNullableFun) = anyNullablePair

    nonNullableCases(
        describePrefix,
        anyPair,
        anyNullablePair
    ) { containsEntriesFun ->

        context("empty collection") {
            val fluentEmpty = assert(setOf())
            test("$isLessThanFun(1.0) throws AssertionError") {
                expect {
                    fluentEmpty.containsEntriesFun({ isLessThan(1.0) })
                }.toThrow<AssertionError> {
                    messageContains(
                        "$rootBulletPoint$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isLessThanDescr: 1.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
            test("$returnValueOfFun(...) states warning that subject is not set") {
                expect {
                    fluentEmpty.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError> { message { containsDefaultTranslationOf(DescriptionIterableAssertion.CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE) } }
            }
        }

        val fluent = assert(oneToSeven)
        context("iterable $oneToSeven") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$rootBulletPoint$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$isGreaterThanDescr: 1.0",
                            "$isLessThanDescr: 2.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }
        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }

    nullableCases(describePrefix) {

        describeFun(anyOfNullable) {

            val list = listOf(null, 1.0, null, 3.0)
            val fluent = verbs.checkImmediately(list)
            context("iterable $list") {
                context("happy cases (do not throw)") {
                    test("$toBeFun(1.0)") {
                        fluent.anyOfNullableFun({ toBe(1.0) })
                    }
                    test("null") {
                        fluent.anyOfNullableFun(null)
                    }
                }

                context("failing cases") {
                    test("$toBeFun(2.0)") {
                        expect {
                            fluent.anyOfNullableFun({ toBe(2.0) })
                        }.toThrow<AssertionError> {
                            message {
                                contains.exactly(1).values(
                                    "$rootBulletPoint$containsInAnyOrder: $separator",
                                    "$anEntryWhich: $separator",
                                    "$toBeDescr: 2.0",
                                    "$numberOfOccurrences: 0",
                                    "$atLeast: 1"
                                )
                            }
                        }
                    }
                }
            }

            context("iterable $oneToSeven") {
                test("null, throws an AssertionError") {
                    expect {
                        assert(oneToSeven).anyOfNullableFun(null)
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(1).values(
                                "$rootBulletPoint$containsInAnyOrder: $separator",
                                "$anEntryWhich: $separator",
                                "$isDescr: null",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                        }
                    }
                }
            }
        }
    }
})
