package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNoOpSearchBehaviourImpl
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviourImpl

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, IterableContainsNoOpSearchBehaviour>
    = IterableContainsBuilder(plant, IterableContainsNoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, IterableContainsNotSearchBehaviour>
    = IterableContainsBuilder(plant, IterableContainsNotSearchBehaviourImpl())
