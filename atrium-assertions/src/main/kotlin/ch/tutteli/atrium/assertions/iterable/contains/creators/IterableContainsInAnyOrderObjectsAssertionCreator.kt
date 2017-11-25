package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.basic.contains.creators.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsInAnyOrderObjectsAssertionCreator<S, T : Iterable<S>>(
    decorator: IterableContainsInAnyOrderDecorator,
    checkers: List<IIterableContains.IChecker>
) : ContainsObjectsAssertionCreator<T, S, IterableContainsInAnyOrderDecorator, IIterableContains.IChecker>(decorator, checkers),
    IIterableContains.ICreator<T, S> {

    override val descriptionContains = DescriptionIterableAssertion.CONTAINS
    override val numberOfOccurrences = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES

    override fun search(plant: IAssertionPlant<T>, searchCriterion: S): Int
        = plant.subject.filter({ it == searchCriterion }).size
}
