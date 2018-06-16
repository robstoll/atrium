package ch.tutteli.atrium.creating

/**
 * Type alias for [Assert] which should be used in API modules.
 */
typealias AssertionPlant<T> = Assert<T>

/**
 * DSL Marker for [Assert] (and its type alias [AssertionPlant]).
 */
@DslMarker
annotation class AssertMarker
