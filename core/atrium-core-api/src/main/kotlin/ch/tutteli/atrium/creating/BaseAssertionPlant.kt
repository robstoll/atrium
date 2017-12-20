package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a plant for [IAssertion]s and offers methods to [addAssertion]s to this plant.
 *
 * It defines what [AssertionPlant] and [AssertionPlantNullable] have in common but is typically not used as entry
 * point for assertion functions (with a few exceptions like equality and identity assertions). Most of the time you
 * want to define an assertion function for [AssertionPlant].
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 * @param A A subtype of [BaseAssertionPlant] which is used in the fluent style API.
 */
interface BaseAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>> {
    /**
     * The subject for which this plant will create [IAssertion]s.
     */
    val subject: T

    /**
     * Adds the given [assertion] to this plant.
     *
     * @param assertion The assertion which will be added to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately
     *         evaluated (see [IReportingAssertionPlant]).
     */
    fun addAssertion(assertion: IAssertion): A
}
