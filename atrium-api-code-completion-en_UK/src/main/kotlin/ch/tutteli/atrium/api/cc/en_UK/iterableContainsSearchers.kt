package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.assertions.iterable.contains.searchers.IterableContainsInAnyOrderSearcher
import ch.tutteli.atrium.creating.IAssertionPlant

fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>.value(expected: E): IAssertionPlant<T>
    = values(expected)

fun <E, T : Iterable<E>> IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderDecorator>.values(expected: E, vararg otherExpected: E): IAssertionPlant<T>
    = addAssertion(IterableContainsInAnyOrderSearcher(), expected, otherExpected)
