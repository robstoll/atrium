package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IAssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    decorator: IterableContainsInOrderOnlyDecorator
) : IterableContainsInOrderOnlyAssertionCreator<E, T, E>(decorator) {

    override fun createEntryAssertion(iterableAsList: List<E>, expected: E, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup
        = template(createEntryFeatureAssertion(expected))

    private fun createEntryFeatureAssertion(expected: E): (Boolean) -> IAssertion
        = { found -> BasicAssertion(DescriptionAnyAssertion.TO_BE, expected ?: RawString.NULL, found) }

    override fun holds(actual: E, expected: E): Boolean
        = actual == expected
}
