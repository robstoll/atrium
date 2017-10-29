package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString

class IterableContainsEntriesInAnyOrderAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderDecorator,
    private val checkers: List<IIterableContains.IChecker>
) : IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: IAssertionPlant<E>.() -> Unit, otherExpected: Array<out IAssertionPlant<E>.() -> Unit>): IAssertionGroup {
        val assertions = mutableListOf<IAssertion>()
        listOf(expected, *otherExpected).forEach {
            assertions.add(create(plant, it))
        }
        return InvisibleAssertionGroup(assertions.toList())
    }

    private fun <E : Any, T : Iterable<E>> create(plant: IAssertionPlant<T>, createAssertions: IAssertionPlant<E>.() -> Unit): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            val count = plant.subject.count { checkIfAssertionsHold(it, createAssertions) }
            val assertions = mutableListOf<IAssertion>()
            checkers.forEach {
                assertions.add(it.createAssertion(count))
            }
            val featureAssertion = AssertionGroup(FeatureAssertionGroupType, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES, RawString(count.toString()), assertions.toList())
            val collectingAssertionPlant = AtriumFactory.newCollectingPlant<E>()
            collectingAssertionPlant.createAssertions()
            val entryWhich = TranslatableRawString(DescriptionIterableAssertion.AN_ENTRY_WHICH)
            val explanatoryAssertionGroup = ExplanatoryAssertionGroup(description, entryWhich, collectingAssertionPlant.getAssertions())
            IndentAssertionGroup.createWithExplanatoryAssertionGroup(explanatoryAssertionGroup, featureAssertion)
        }
    }

    private fun <E : Any> checkIfAssertionsHold(it: E, createAssertions: IAssertionPlant<E>.() -> Unit): Boolean {
        val plant = AtriumFactory.newCheckingPlant(it)
        plant.createAssertions()
        return plant.allAssertionsHold()
    }
}
