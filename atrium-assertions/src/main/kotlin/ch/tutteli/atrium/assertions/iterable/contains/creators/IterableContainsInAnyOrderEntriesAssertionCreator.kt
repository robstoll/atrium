package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AN_ENTRY_WHICH
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.WARNING_SUBJECT_NOT_SET
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.AssertionCollector
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

class IterableContainsInAnyOrderEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderDecorator,
    checkers: List<IIterableContains.IChecker>
) : ContainsAssertionCreator<T, IAssertionPlant<E>.() -> Unit, IIterableContains.IChecker>(checkers),
    IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(assertions: List<IAssertion>): IAssertionGroup {
        val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        return AssertionGroup(ListAssertionGroupType, description, RawString(""), assertions)
    }

    override fun searchAndCreateAssertion(plant: IAssertionPlant<T>, searchCriterion: IAssertionPlant<E>.() -> Unit, featureFactory: (Int, ITranslatable) -> IAssertionGroup): IAssertionGroup {
        val itr = plant.subject.iterator()
        val (explanatoryAssertions, count) = createExplanatoryAssertionsAndMatchingCount(itr, searchCriterion)
        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        return AssertionGroup(ListAssertionGroupType, AN_ENTRY_WHICH, RawString(""), listOf(
            ExplanatoryAssertionGroup(ExplanatoryAssertionGroupType, explanatoryAssertions),
            featureAssertion
        ))
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
