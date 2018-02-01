package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNoOpSearchBehaviour())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNotSearchBehaviour())
