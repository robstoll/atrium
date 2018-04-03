package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl

@Deprecated("Use AssertImpl.comparable.isLessThan, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.comparable.isLessThan(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Comparable<T>> _isLessThan(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.comparable.isLessThan(plant, expected)

@Deprecated("Use AssertImpl.comparable.isLessOrEquals, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.comparable.isLessOrEquals(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Comparable<T>> _isLessOrEquals(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.comparable.isLessOrEquals(plant, expected)

@Deprecated("Use AssertImpl.comparable.isGreaterThan, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.comparable.isGreaterThan(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Comparable<T>> _isGreaterThan(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.comparable.isGreaterThan(plant, expected)

@Deprecated("Use AssertImpl.comparable.isGreaterOrEquals, will be removed with 1.0.0",
    ReplaceWith(
        "AssertImpl.comparable.isGreaterOrEquals(plant, expected)",
        "ch.tutteli.atrium.creating.AssertImpl"
    )
)
fun <T : Comparable<T>> _isGreaterOrEquals(plant: AssertionPlant<T>, expected: T)
    = AssertImpl.comparable.isGreaterOrEquals(plant, expected)
