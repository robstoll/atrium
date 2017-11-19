package ch.tutteli.atrium.assertions.base.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.base.contains.IContains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

abstract class ContainsObjectsAssertionCreator<T : Any, E, D : IContains.IDecorator, C : IContains.IChecker>(
    private val decorator: D,
    checkers: List<C>
) : ContainsAssertionCreator<T, E, C>(checkers) {

    override fun createAssertionGroup(assertions: List<IAssertion>): IAssertionGroup
        = InvisibleAssertionGroup(assertions)

    override final fun searchAndCreateAssertion(plant: IAssertionPlant<T>, expected: E, featureFactory: (Int, ITranslatable) -> IAssertionGroup): IAssertionGroup {
        val count = search(plant, expected)
        val featureAssertion = featureFactory(count, numberOfOccurrences)
        val description = decorator.decorateDescription(descriptionContains)
        return AssertionGroup(ListAssertionGroupType, description, expected ?: RawString.NULL, listOf(featureAssertion))
    }

    protected abstract val descriptionContains: ITranslatable
    protected abstract val numberOfOccurrences: ITranslatable

    protected abstract fun search(plant: IAssertionPlant<T>, expected: E): Int
}
