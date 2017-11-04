package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInAnyOrderEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderDecorator,
    private val checkers: List<IIterableContains.IChecker>
) : IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: IAssertionPlant<E>.() -> Unit, otherExpected: Array<out IAssertionPlant<E>.() -> Unit>): IAssertionGroup {
        val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        val assertions = mutableListOf<IAssertion>()
        listOf(expected, *otherExpected).forEach {
            assertions.add(create(plant, it))
        }
        return AssertionGroup(ListAssertionGroupType, description, RawString(""), assertions.toList())
    }

    private fun <E : Any, T : Iterable<E>> create(plant: IAssertionPlant<T>, createAssertions: IAssertionPlant<E>.() -> Unit): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val itr = plant.subject.iterator()
            val (explanatoryAssertions, count) = getExplanatoryAssertionsAndMatchingCount(itr, createAssertions)
            val assertions = checkers.map { it.createAssertion(count) }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES, RawString(count.toString()), assertions)
            AssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.AN_ENTRY_WHICH, RawString(""), listOf(
                ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, explanatoryAssertions),
                featureAssertion
            ))
        }
    }

    private fun <E : Any> getExplanatoryAssertionsAndMatchingCount(itr: Iterator<E>, createAssertions: IAssertionPlant<E>.() -> Unit): Pair<List<IAssertion>, Int> {
        return if (itr.hasNext()) {
            val first = itr.next()
            val group = AssertionCollector.collectAssertionsForExplanation(createAssertions, first)
            val sequence = sequenceOf(first) + itr.asSequence()
            val count = sequence.count { checkIfAssertionsHold(it, createAssertions) }
            group to count
        } else {
            val group = AssertionCollector.collectAssertionsForExplanation(createAssertions, null)
            group to 0
        }
    }

    private fun <E : Any> checkIfAssertionsHold(it: E, createAssertions: IAssertionPlant<E>.() -> Unit): Boolean {
        val plant = AtriumFactory.newCheckingPlant(it)
        plant.createAssertions()
        return plant.allAssertionsHold()
    }
}
