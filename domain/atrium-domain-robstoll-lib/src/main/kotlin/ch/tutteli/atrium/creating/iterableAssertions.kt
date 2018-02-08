package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>): IterableContainsBuilder<E, T, IterableContainsNoOpSearchBehaviour>
    = IterableContainsBuilder(plant, IterableContainsNoOpSearchBehaviour())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>): IterableContainsBuilder<E, T, IterableContainsNotSearchBehaviour>
    = IterableContainsBuilder(plant, IterableContainsNotSearchBehaviour())
