package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNoOpSearchBehaviour())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>)
    = IterableContainsBuilder(plant, IterableContainsNotSearchBehaviour())
