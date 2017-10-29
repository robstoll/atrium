package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.creating.IAssertionPlant

class IterableContainsBuilder<E, out T : Iterable<E>, D : IIterableContains.IDecorator>(
    val plant: IAssertionPlant<T>, val decorator: D
)
