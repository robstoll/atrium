package ch.tutteli.atrium.assertions.iterable.contains.searchers

import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator

class IterableContainsInAnyOrderSearcher<E, T : Iterable<E>>
    : IterableContainsAssertionCreator.ISearcher<E, T, IterableContainsInAnyOrderDecorator> {

    override fun search(searchIn: T, searchFor: (E) -> Boolean): Int {
        return searchIn.filter(searchFor).size
    }
}
