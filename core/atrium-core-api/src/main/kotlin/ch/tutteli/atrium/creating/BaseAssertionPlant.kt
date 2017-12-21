package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion

/**
 * Represents a plant for [Assertion]s and offers methods to [addAssertion]s to this plant.
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
     * The subject for which this plant will create [Assertion]s.
     */
    val subject: T

    /**
     * Adds the given [assertion] to this plant.
     *
     * @param assertion The assertion which will be added to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *         evaluated (see [ReportingAssertionPlant]).
     */
    fun addAssertion(assertion: Assertion): A
}
