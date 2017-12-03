package ch.tutteli.atrium.creating

/**
 * Represents an assertion plant for nullable types.
 *
 * It is the entry point for two assertion functions, the first makes the assumption that [subject] is `null`
 * and the other that [subject] is not `null`. It only provides a reduced set of [IReportingAssertionPlantNullable]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IAssertionPlantNullable<out T : Any?> : IBaseAssertionPlant<T, IAssertionPlantNullable<T>>
