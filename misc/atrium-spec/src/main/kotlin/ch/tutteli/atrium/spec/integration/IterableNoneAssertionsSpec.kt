package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.include

abstract class IterableNoneAssertionsSpec(
    verbs: AssertionVerbFactory,
    nonePair: Pair<String, Assert<Iterable<Double>>.(Assert<Double>.() -> Unit) -> Assert<Iterable<Double>>>,
    noneNullablePair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?) -> Assert<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterablePredicateSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double>>(describePrefix,
        nonePair.first to mapToCreateAssertion { nonePair.second(this, { toBe(2.3) }) },
        noneNullablePair.first to mapToCreateAssertion { noneNullablePair.second(this, { toBe(2.3) }) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double>>(verbs, describePrefix,
        checkingTriple(nonePair.first, { nonePair.second(this, { toBe(2.3) }) }, listOf(2.1).asIterable(), listOf(2.1, 2.3)),
        checkingTriple(noneNullablePair.first, { noneNullablePair.second(this, { toBe(2.3) }) }, listOf(2.1).asIterable(), listOf(2.1, 2.3))
    ) {})

    fun SpecBody.describeFun(funName: String, body: SpecBody.() -> Unit)
        = group("fun `$funName`", body = body)

    val expect = verbs::checkException

    val (containsNotNullable, containsNotNullableFun) = noneNullablePair

    val containsNotDescr = DescriptionIterableAssertion.CONTAINS_NOT.getDefault()

    nonNullableCases(
        describePrefix,
        nonePair,
        noneNullablePair
    ){ containsNotFun ->

        val fluent = verbs.checkImmediately(oneToSeven)

        context("iterable $oneToSeven") {
            group("happy case") {
                listOf(1.1, 2.2, 3.3).forEach {
                    test("$toBeDescr($it) does not throw") {
                        fluent.containsNotFun({ toBe(1.1) })
                    }
                }
            }

            group("failing assertions; search string at different positions") {
                test("$toBeDescr(4.0) throws AssertionError") {
                    expect {
                        fluent.containsNotFun({ toBe(4.0) })
                    }.toThrow<AssertionError> {
                        message {
                            containsRegex(
                                "$containsNotDescr: $separator"+
                                ".*$anEntryWhich: $separator"+
                                ".*$toBeDescr: 4.0"
                            )
                        }
                    }
                }
            }
        }
    }

    nullableCases(describePrefix) {
        describeFun(containsNotNullable) {
            context("empty iterable") {
                test("null does not throw") {
                    verbs.checkImmediately(listOf<Double?>()).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSeven") {
                test("null does not throw") {
                    verbs.checkImmediately(oneToSeven).containsNotNullableFun(null)
                }
            }
            context("iterable $oneToSevenNullable"){
                test("null throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun(null)
                    }.toThrow<AssertionError> {
                        message{
                            containsRegex(
                                "$containsNotDescr: $separator"+
                                    ".*$anEntryWhich: $separator"+
                                    ".*$isDescr: null"
                            )
                        }
                    }
                }

                test("1.0 throws AssertionError") {
                    expect {
                        verbs.checkImmediately(oneToSevenNullable).containsNotNullableFun({ toBe(1.0) })
                    }.toThrow<AssertionError> {
                        message{
                            containsRegex(
                                "$containsNotDescr: $separator"+
                                    ".*$anEntryWhich: $separator"+
                                    ".*$toBeDescr: 1.0"
                            )
                        }
                    }
                }
            }
        }
    }
})
