package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.IterableAssertions

@Deprecated("use IterableAssertions.containsBuilder instead, will be removed with 1.0.0", ReplaceWith("IterableAssertions.containsBuilder(plant)"))
fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>)
    = IterableAssertions.containsBuilder(plant)

@Deprecated("use IterableAssertions.containsNotBuilder instead, will be removed with 1.0.0", ReplaceWith("IterableAssertions.containsNotBuilder(plant)"))
fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>)
    = IterableAssertions.containsNotBuilder(plant)
