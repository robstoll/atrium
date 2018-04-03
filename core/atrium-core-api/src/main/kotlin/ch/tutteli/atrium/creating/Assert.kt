package ch.tutteli.atrium.creating

/**
 * Type alias for [AssertionPlant] which should be used in API modules.
 */
typealias Assert<T> = AssertionPlant<T>

/**
 * DSL Marker for [AssertionPlant] (and its type alias [Assert]).
 */
@DslMarker
annotation class AssertMarker
