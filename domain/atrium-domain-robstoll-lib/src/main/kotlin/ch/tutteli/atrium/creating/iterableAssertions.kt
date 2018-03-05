package ch.tutteli.atrium.creating

import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviourImpl
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.NotSearchBehaviourImpl

fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NoOpSearchBehaviour>
    = IterableContainsBuilder(plant, NoOpSearchBehaviourImpl())

fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>): IterableContains.Builder<E, T, NotSearchBehaviour>
    = IterableContainsBuilder(plant, NotSearchBehaviourImpl())
