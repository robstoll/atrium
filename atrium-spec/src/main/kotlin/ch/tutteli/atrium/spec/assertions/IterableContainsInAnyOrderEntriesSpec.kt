package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class IterableContainsInAnyOrderEntriesSpec(
    verbs: IAssertionVerbFactory,
    containsEntryPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit) -> IAssertionPlant<Iterable<Double>>>,
    containsEntriesPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit, Array<out IAssertionPlant<Double>.() -> Unit>) -> IAssertionPlant<Iterable<Double>>>
) : IterableContainsEntriesSpecBase(verbs, {

    include(object : ch.tutteli.atrium.spec.assertions.SubjectLessAssertionSpec<Iterable<Double>>(
        containsEntryPair.first to mapToCreateAssertion { containsEntryPair.second(this, { toBe(1.2) }) },
        containsEntriesPair.first to mapToCreateAssertion { containsEntriesPair.second(this, { toBe(2.5) }, arrayOf()) }
    ) {})

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (containsEntry, containsEntryFun) = containsEntryPair
    val (containsEntries, containsEntriesFunArr) = containsEntriesPair

    fun IAssertionPlant<Iterable<Double>>.containsEntriesFun(t: IAssertionPlant<Double>.() -> Unit, vararg tX: (IAssertionPlant<Double>.() -> Unit))
        = containsEntriesFunArr(t, tX)

    describe("fun $containsEntry and $containsEntries")
    {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$containsEntry{ $isLessThanFun(1.0) } throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntryFun {
                        isLessThan(1.0)
                    }
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrder: $separator",
                    "$anEntryWhich: $separator",
                    "$isLessThanDescr: 1.0",
                    "$numberOfOccurrences: 0",
                    "$atLeast: 1"
                )
            }
            test("$containsEntries({ $isLessThanFun(1.0) }, { $isGreaterThanFun(2.0) }) throws AssertionError") {
                expect {
                    fluentEmptyString.containsEntriesFun({ isLessThan(1.0) }, { isGreaterThan(2.0) })
                }.toThrow<AssertionError>().and.message {
                    contains.exactly(2).values(
                        "$anEntryWhich: $separator",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                    contains.exactly(1).values(
                        "$containsInAnyOrder: $separator",
                        "$isLessThanDescr: 1.0",
                        "$isGreaterThanDescr: 2.0"
                    )
                }
            }
            test("$containsEntries({ $returnValueOfFun(...) }) states warning that subject is not set") {
                expect {
                    fluentEmptyString.containsEntriesFun({ returnValueOf(subject::dec).toBe(1.0) })
                }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(DescriptionIterableAssertion.WARNING_SUBJECT_NOT_SET)
            }
        }

        context("iterable '$oneToSeven'") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.containsEntryFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).values(
                        "$containsInAnyOrder: $separator",
                        "$anEntryWhich: $separator",
                        "$isGreaterThanDescr: 1.0",
                        "$isLessThanDescr: 2.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.containsEntryFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1) and another entry which is $isLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    fluent.containsEntriesFun({ isGreaterThan(1.0); isLessThan(2.1) }, { isLessThan(2.0) })
                }
            }

            context("search for entry where the lambda does not specify any assertion") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
                        fluent.containsEntryFun({})
                    }.toThrow<IllegalArgumentException>().and.message.contains("not any assertion created")
                }
            }
        }
    }
})
