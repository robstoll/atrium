package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.AN_ENTRY_WHICH_IS
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.reporting.RawString

class IterableContainsInAnyOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    decorator: IterableContainsInAnyOrderOnlyDecorator
) : IterableContainsInAnyOrderOnlyAssertionCreator<E, T, E>(decorator) {

    override fun createAssertionForExpectedAndRemoveMatchFromList(expected: E, list: MutableList<E>): Pair<Boolean, IAssertion> {
        val found: Boolean = list.remove(expected)
        return Pair(found, BasicAssertion(AN_ENTRY_WHICH_IS, expected ?: RawString.NULL, found))
    }
}
