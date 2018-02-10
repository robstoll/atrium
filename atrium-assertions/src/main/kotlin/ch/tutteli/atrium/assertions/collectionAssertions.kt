package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertImpl
import ch.tutteli.atrium.creating.AssertionPlant

@Deprecated("use AssertImpl.collection.hasSize, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.collection.hasSize(plant, size)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Collection<*>> _hasSize(plant: AssertionPlant<T>, size: Int): Assertion
    = AssertImpl.collection.hasSize(plant, size)

@Deprecated("use AssertImpl.collection.isEmpty, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.collection.isEmpty(plant)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Collection<*>> _isEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.collection.isEmpty(plant)

@Deprecated("use AssertImpl.collection.isNotEmpty, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.collection.isNotEmpty(plant)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Collection<*>> _isNotEmpty(plant: AssertionPlant<T>): Assertion
    = AssertImpl.collection.isNotEmpty(plant)
