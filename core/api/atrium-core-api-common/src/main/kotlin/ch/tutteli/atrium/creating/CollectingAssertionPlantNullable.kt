@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents an [AssertionPlantNullable] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this assertion plant collects the so created assertions.
 *
 * In contrast to [ReportingAssertionPlantNullable], this plant does not offer reporting capabilities.
 * It merely offers a method to [getAssertions] (the collected ones).
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
@Deprecated("Switch from CollectingAssertionPlantNullable to CollectingAssertionContainer; will be removed with 1.0.0")
interface CollectingAssertionPlantNullable<out T> : AssertionPlantNullable<T>,
    BaseCollectingAssertionPlant<T, AssertionPlantNullable<T>, CollectingAssertionPlantNullable<T>>
