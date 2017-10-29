package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.DescriptionNumberAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

abstract class IterableContainsEntriesSpec(
    verbs: IAssertionVerbFactory,
    entryPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit) -> IAssertionPlant<Iterable<Double>>>,
    entriesPair: Pair<String, IAssertionPlant<Iterable<Double>>.(IAssertionPlant<Double>.() -> Unit, Array<out IAssertionPlant<Double>.() -> Unit>) -> IAssertionPlant<Iterable<Double>>>
) : IterableContainsSpecBase({

    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(oneToSeven)

    val (entry, entryFun) = entryPair
    val (entries, entriesFunArr) = entriesPair

    fun IAssertionPlant<Iterable<Double>>.entriesFun(t: IAssertionPlant<Double>.() -> Unit, vararg tX: (IAssertionPlant<Double>.() -> Unit))
        = entriesFunArr(t, tX)

    val isLessThanFun = verbs.checkImmediately(1.0)::isLessThan.name
    val isGreaterThanFun = verbs.checkImmediately(1.0)::isGreaterThan.name
    val anEntryWhich = DescriptionIterableAssertion.AN_ENTRY_WHICH.getDefault()
    val isLessThan = DescriptionNumberAssertion.IS_LESS_THAN.getDefault()
    val isGreaterThan = DescriptionNumberAssertion.IS_GREATER_THAN.getDefault()

    describe("fun $entry and $entries") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$entry{ $isLessThanFun(1.0) } throws AssertionError") {
                expect {
                    fluentEmptyString.entryFun {
                        isLessThan(1.0)
                    }
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrder: $anEntryWhich",
                    "$isLessThan: 1.0",
                    "$numberOfOccurrences: 0",
                    "$atLeast: 1"
                )
            }
            test("$entries({ $isLessThanFun(1.0) }, { $isGreaterThanFun(2.0) }) throws AssertionError") {
                expect {
                    fluentEmptyString.entriesFun({ isLessThan(1.0) }, { isGreaterThan(2.0) })
                }.toThrow<AssertionError>().and.message {
                    contains.exactly(2).values(
                        "$containsInAnyOrder: $anEntryWhich",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                    contains.exactly(1).value("$isLessThan: 1.0")
                    contains.exactly(1).value("$isGreaterThan: 2.0")
                }
            }
        }

        context("iterable '$oneToSeven'") {
            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.0)") {
                it("throws AssertionError containing both assumptions in one assertion") {
                    expect {
                        fluent.entryFun({ isGreaterThan(1.0); isLessThan(2.0) })
                    }.toThrow<AssertionError>().and.message.contains.exactly(1).values(
                        "$containsInAnyOrder: $anEntryWhich",
                        "$isGreaterThan: 1.0",
                        "$isLessThan: 2.0",
                        "$numberOfOccurrences: 0",
                        "$atLeast: 1"
                    )
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1)") {
                it("does not throw an exception") {
                    fluent.entryFun({ isGreaterThan(1.0); isLessThan(2.1) })
                }
            }

            context("search for entry which $isGreaterThanFun(1.0) and $isLessThanFun(2.1) and another entry which is $isLessThanFun(2.0)") {
                it("does not throw an exception") {
                    //finds twice the entry 1.0 but that is fine since we do not search for unique entries in this case
                    fluent.entriesFun({ isGreaterThan(1.0); isLessThan(2.1) }, {isLessThan(2.0)})
                }
            }
        }
    }
})
