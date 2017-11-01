package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

abstract class IterableContainsInAnyOrderOnlySpec(
    verbs: IAssertionVerbFactory,
    containsPair: Pair<String, IAssertionPlant<Iterable<Double>>.(Double, Array<out Double>) -> IAssertionPlant<Iterable<Double>>>,
    successfulBulletPoint: String,
    failingBulletPoint: String,
    warningBulletPoint: String,
    listBulletPoint: String
) : IterableContainsSpecBase({
    val assert: (Iterable<Double>) -> IAssertionPlant<Iterable<Double>> = verbs::checkImmediately
    val expect = verbs::checkException
    val oneToFour = listOf(1.0, 2.0, 3.0, 4.0, 4.0)
    val fluent = assert(oneToFour)

    val (contains, containsFunArr) = containsPair
    fun IAssertionPlant<Iterable<Double>>.containsFun(t: Double, vararg tX: Double)
        = containsFunArr(t, tX.toTypedArray())

    val anEntryWhichIs = DescriptionIterableAssertion.AN_ENTRY_WHICH_IS.getDefault()
    val additionalEntries = DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES.getDefault()

    describe("fun $contains") {
        context("empty collection") {
            val fluentEmptyString = assert(setOf())
            test("$contains 1.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrderOnly:",
                    "$failingBulletPoint$anEntryWhichIs: 1.0"
                )
            }
            test("$contains 1.0 and 4.0 throws AssertionError") {
                expect {
                    fluentEmptyString.containsFun(1.0, 4.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrderOnly:",
                    "$failingBulletPoint$anEntryWhichIs: 1.0",
                    "$failingBulletPoint$anEntryWhichIs: 4.0"
                )
            }
        }

        context("iterable '$oneToFour'") {

            listOf(
                arrayOf(1.0, 2.0, 3.0, 4.0, 4.0),
                arrayOf(1.0, 3.0, 2.0, 4.0, 4.0),
                arrayOf(3.0, 4.0, 2.0, 1.0, 4.0),
                arrayOf(2.0, 4.0, 4.0, 1.0, 3.0),
                arrayOf(2.0, 4.0, 1.0, 4.0, 3.0),
                arrayOf(4.0, 4.0, 3.0, 2.0, 1.0)
            ).forEach {
                test("$contains ${it.joinToString()} does not throw") {
                    fluent.containsFun(it.first(), *it.drop(1).toDoubleArray())
                }
            }

            test("$contains 1.0, 2.0, 3.0, 4.0 throw AssertionError; 4.0 was missing") {
                expect {
                    fluent.containsFun(1.0, 2.0, 3.0, 4.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrderOnly:",
                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                    "$successfulBulletPoint$anEntryWhichIs: 2.0",
                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
                    "$warningBulletPoint$additionalEntries:",
                    "${listBulletPoint}4.0"
                )
            }

            test("$contains 1.0, 4.0 throw AssertionError; 2.0, 3.0 and 4.0 was missing") {
                expect {
                    fluent.containsFun(1.0, 4.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrderOnly:",
                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                    "$successfulBulletPoint$anEntryWhichIs: 4.0",
                    "$warningBulletPoint$additionalEntries:",
                    "${listBulletPoint}2.0",
                    "${listBulletPoint}3.0",
                    "${listBulletPoint}4.0"
                )
            }
            test("$contains 1.0, 3.0, 5.0 throw AssertionError; 5.0 is wrong and 2.0, 4.0 and 4.0 are missing") {
                expect {
                    fluent.containsFun(1.0, 3.0, 5.0)
                }.toThrow<AssertionError>().and.message.contains(
                    "$containsInAnyOrderOnly:",
                    "$successfulBulletPoint$anEntryWhichIs: 1.0",
                    "$successfulBulletPoint$anEntryWhichIs: 3.0",
                    "$failingBulletPoint$anEntryWhichIs: 5.0",
                    "$warningBulletPoint$additionalEntries:",
                    "${listBulletPoint}2.0",
                    "${listBulletPoint}4.0",
                    "${listBulletPoint}4.0"
                )
            }

        }
    }
})
