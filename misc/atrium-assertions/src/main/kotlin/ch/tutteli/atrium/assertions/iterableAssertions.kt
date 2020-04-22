// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

@Deprecated("Use AssertImpl.iterable.containsBuilder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.containsBuilder(plant)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsBuilder(plant: AssertionPlant<T>)
    = AssertImpl.iterable.containsBuilder(plant)

@Deprecated("Use AssertImpl.iterable.containsNotBuilder; will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.iterable.containsNotBuilder(plant)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <E, T : Iterable<E>> _containsNotBuilder(plant: AssertionPlant<T>)
    = AssertImpl.iterable.containsNotBuilder(plant)
