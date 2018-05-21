package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderAtLeast1EntriesSpec(
    verbs: AssertionVerbFactory,
    containsInAnyOrderNullableEntriesPair: Pair<String, Assert<Iterable<Double?>>.((Assert<Double>.() -> Unit)?, Array<out (Assert<Double>.() -> Unit)?>) -> Assert<Iterable<Double?>>>,
    describePrefix: String = "[Atrium] "
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : SubjectLessAssertionSpec<Iterable<Double?>>(describePrefix,
        containsInAnyOrderNullableEntriesPair.first to mapToCreateAssertion { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }
    ) {})

    include(object : CheckingAssertionSpec<Iterable<Double?>>(verbs, describePrefix,
        checkingTriple(containsInAnyOrderNullableEntriesPair.first, { containsInAnyOrderNullableEntriesPair.second(this, null, arrayOf()) }, listOf(null) as Iterable<Double?>, listOf(1.2))
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Iterable<Double?>) -> Assert<Iterable<Double?>> = verbs::checkImmediately
    val expect = verbs::checkException
    val list = listOf(null, 1.0, null, 3.0)
    val fluent = assert(list)

    val (containsInAnyOrderNullableEntries, containsInAnyOrderNullableEntriesFunArr) = containsInAnyOrderNullableEntriesPair
    fun Assert<Iterable<Double?>>.containsInAnyOrderNullableEntriesFun(t: (Assert<Double>.() -> Unit)?, vararg tX: (Assert<Double>.() -> Unit)?)
        = containsInAnyOrderNullableEntriesFunArr(t, tX)

    describeFun(containsInAnyOrderNullableEntries) {
        absentSubjectTests(verbs, Assert<Iterable<Double?>>::containsInAnyOrderNullableEntriesFun)

        context("iterable $list") {
            context("happy cases (do not throw)") {
                test("$toBeFun(1.0)") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) })
                }
                test("null") {
                    fluent.containsInAnyOrderNullableEntriesFun(null)
                }
                test("$toBeFun(1.0) and null") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(1.0) }, null)
                }
                test("$toBeFun(3.0), null and $toBeFun(1.0)") {
                    fluent.containsInAnyOrderNullableEntriesFun({ toBe(3.0) }, null, { toBe(1.0) })
                }
                test("null, null, null") {
                    //finds twice the same entry with null but that is fine since we do not search for unique entries in this case
                    fluent.containsInAnyOrderNullableEntriesFun(null, null, null)
                }
            }

            context("failing cases") {
                test("$toBeFun(2.0)") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({ toBe(2.0) })
                    }.toThrow<AssertionError> {
                        messageContains(
                            "$containsInAnyOrder: $separator",
                            "$anEntryWhich: $separator",
                            "$toBeDescr: 2.0",
                            "$numberOfOccurrences: 0",
                            "$atLeast: 1"
                        )
                    }
                }

                test("$isLessThanFun(1.0) and $isLessThanFun(3.0)") {
                    expect {
                        fluent.containsInAnyOrderNullableEntriesFun({ isLessThan(1.0) }, { isGreaterThan(3.0) })
                    }.toThrow<AssertionError> {
                        message {
                            contains.exactly(2).values(
                                "$anEntryWhich: $separator",
                                "$numberOfOccurrences: 0",
                                "$atLeast: 1"
                            )
                            contains.exactly(1).values(
                                "$containsInAnyOrder: $separator",
                                "$isLessThanDescr: 1.0",
                                "$isGreaterThanDescr: 3.0"
                            )
                        }
                    }
                }
            }
        }

        context("iterable $oneToSeven") {
            test("null") {
                expect {
                    assert(oneToSeven).containsInAnyOrderNullableEntriesFun(null)
                }.toThrow<AssertionError> {
                    messageContains(
                        "$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isDescr: null",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }
        }

        context("search for entry where the lambda does not specify any assertion") {
            it("throws an ${IllegalArgumentException::class.simpleName}") {
                expect {
                    fluent.containsInAnyOrderNullableEntriesFun({})
                }.toThrow<IllegalArgumentException> { messageContains("not any assertion created") }
            }
        }
    }
})
