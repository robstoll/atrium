@file:Suppress("DEPRECATION" /* TODO remove file with 1.0.0 */)

package ch.tutteli.atrium.creating

/**
 * Type alias for [AssertionPlant] which should be used in API modules.
 */
@Deprecated("Switch from Assert to Expect; will be removed with 1.0.0")
typealias Assert<T> = AssertionPlant<T>

/**
 * DSL Marker for [AssertionPlant] (and its type alias [Assert]).
 */
@DslMarker
@Deprecated("Switch from AssertMarker to ExpectMarker; will be removed with 1.0.0")
annotation class AssertMarker
