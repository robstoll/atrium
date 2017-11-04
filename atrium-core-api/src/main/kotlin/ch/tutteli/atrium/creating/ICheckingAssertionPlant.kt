package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

/**
 * Represents a plant for [IAssertion]s and offers the possibility to check whether [allAssertionsHold] which have been
 * [added][addAssertion] to this plant (since the last check).
 *
 * In contrast to [IReportingAssertionPlant], this plant does not offer error reporting capabilities but merely checks
 * whether the added [IAssertion]s hold.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface ICheckingAssertionPlant<out T : Any> : IAssertionPlant<T> {
    /**
     * Checks whether the newly [added][addAssertion] [IAssertion]s hold.
     *
     * Calling this method more than once should not re-check previously [added][addAssertion] [IAssertion]s.
     * In contrast to [IReportingAssertionPlant], this method should not report or throw an exception
     * if an assertion does not hold. It merely states whether the newly [added][addAssertion] [IAssertion]s hold.
     *
     * However, it should throw an [IllegalStateException] in case no new assertions have been added to this plant.
     * Particularly after one called [allAssertionsHold] and a second call happens without adding an additional
     * assertion in between.
     *
     * @return `true` if the [added][addAssertion] [IAssertion]s hold; `false` otherwise.
     *
     * @throws IllegalStateException in case no new assertions have been added to this plant.
     */
    fun allAssertionsHold(): Boolean
}
