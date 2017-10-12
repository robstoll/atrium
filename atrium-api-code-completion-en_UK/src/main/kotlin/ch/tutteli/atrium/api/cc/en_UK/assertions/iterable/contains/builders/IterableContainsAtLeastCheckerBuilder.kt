package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.atLeast
import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsAtLeastCheckerBuilderBase
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator

open class IterableContainsAtLeastCheckerBuilder<E, T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>
) : IterableContainsAtLeastCheckerBuilderBase<E, T, IterableContainsInAnyOrderDecorator>(
    times,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::atLeast.name
)
