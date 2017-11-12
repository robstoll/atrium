package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a plant for [IAssertion]s and offers methods to [create][createAndAddAssertion] and
 * to [add][addAssertion] assertions to this plant.
 *
 * It defines what [IAssertionPlant] and [IAssertionPlantNullable] have in common but is typically not used as entry
 * point for assertion functions (with a few exceptions like equality and identity assertions). Most of the time you
 * want to define an assertion function for [IAssertionPlant].
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 * @param A A subtype of [IBaseAssertionPlant] which is used in the fluent style API.
 */
interface IBaseAssertionPlant<out T : Any?, out A : IBaseAssertionPlant<T, A>> {
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
