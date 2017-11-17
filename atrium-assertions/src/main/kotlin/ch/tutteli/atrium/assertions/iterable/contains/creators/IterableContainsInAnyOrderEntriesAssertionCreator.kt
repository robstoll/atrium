package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInAnyOrderEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderDecorator,
    private val checkers: List<IIterableContains.IChecker>
) : IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: IAssertionPlant<E>.() -> Unit, otherExpected: Array<out IAssertionPlant<E>.() -> Unit>): IAssertionGroup {
        val description = decorator.decorateDescription(CONTAINS)
        val assertions = listOf(expected, *otherExpected).map { create(plant, it) }
        return AssertionGroup(ListAssertionGroupType, description, RawString(""), assertions)
    }

    private fun <E : Any, T : Iterable<E>> create(plant: IAssertionPlant<T>, assertionCreator: IAssertionPlant<E>.() -> Unit): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val itr = plant.subject.iterator()
            val (explanatoryAssertions, count) = createExplanatoryAssertionsAndMatchingCount(itr, assertionCreator)
            val assertions = checkers.map { it.createAssertion(count) }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, NUMBER_OF_OCCURRENCES, RawString(count.toString()), assertions)
            AssertionGroup(ListAssertionGroupType, AN_ENTRY_WHICH, RawString(""), listOf(
                ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, explanatoryAssertions),
                featureAssertion
            ))
        }
    }

    private fun <E : Any> createExplanatoryAssertionsAndMatchingCount(itr: Iterator<E>, assertionCreator: IAssertionPlant<E>.() -> Unit): Pair<List<IAssertion>, Int> {
        return if (itr.hasNext()) {
            val first = itr.next()
            val group = collectIterableAssertionsForExplanation(assertionCreator, first)
            val sequence = sequenceOf(first) + itr.asSequence()
            val count = sequence.count { checkIfAssertionsHold(it, assertionCreator) }
            group to count
        } else {
            val group = collectIterableAssertionsForExplanation(assertionCreator, null)
            group to 0
        }
    }

    private fun <E : Any> checkIfAssertionsHold(it: E, assertionCreator: IAssertionPlant<E>.() -> Unit): Boolean {
        val plant = AtriumFactory.newCheckingPlant(it)
        plant.assertionCreator()
        return plant.allAssertionsHold()
    }
}

internal fun <E : Any> collectIterableAssertionsForExplanation(assertionCreator: IAssertionPlant<E>.() -> Unit, subject: E?)
    = AssertionCollector
    .throwIfNoAssertionIsCollected
    .collectAssertionsForExplanation("the iterator was empty and thus no subject available", WARNING_SUBJECT_NOT_SET, assertionCreator, subject)
