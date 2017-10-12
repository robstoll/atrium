package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IterableContainsAssertionCreator
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsBuilder<E, out T : Iterable<E>, D : IterableContainsAssertionCreator.IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
