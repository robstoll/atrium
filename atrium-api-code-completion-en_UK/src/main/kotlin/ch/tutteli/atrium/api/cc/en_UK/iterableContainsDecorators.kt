package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsNoOpDecorator

val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsNoOpDecorator>.inAnyOrder
    get() = IterableContainsBuilder(plant, IterableContainsInAnyOrderDecorator)

val <E, T : Iterable<E>> IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>.only
    get() = IterableContainsBuilder(plant, IterableContainsInAnyOrderOnlyDecorator)
