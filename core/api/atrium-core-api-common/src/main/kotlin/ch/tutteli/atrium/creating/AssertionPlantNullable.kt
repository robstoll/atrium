@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.creating

/**
 * Represents an assertion plant for nullable types.
 *
 * It is the entry point for two assertion functions, the first makes the assumption that [subject] is `null`
 * and the other that [subject] is not `null`. It only provides a reduced set of [ReportingAssertionPlantNullable]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
interface AssertionPlantNullable<out T : Any?> : BaseAssertionPlant<T, AssertionPlantNullable<T>>
