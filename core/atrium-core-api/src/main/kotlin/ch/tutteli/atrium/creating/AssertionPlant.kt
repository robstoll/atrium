@file:JvmName("IAssertionPlantExtensions")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a plant for [IAssertion]s based on a non nullable [subject].
 *
 * It is the entry point for most assertion functions and provides only a reduced set of [ReportingAssertionPlant]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [AssertionPlant].
 */
interface AssertionPlant<out T : Any> : BaseAssertionPlant<T, AssertionPlant<T>> {

    /**
     * Adds the assertions created by the [assertionCreator] lambda to this plant.
     *
     * @param assertionCreator The receiver function which might create and add assertions to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately
     *         evaluated (see [IReportingAssertionPlant]).
     */
    fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): AssertionPlant<T>

    /**
     * Creates an [IBasicAssertion] based on the given [description], [expected] and [test] and [adds][addAssertion] it
     * to the plant.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately
     *         evaluated (see [IReportingAssertionPlant]).
     */
    fun createAndAddAssertion(description: Translatable, expected: Any, test: () -> Boolean): AssertionPlant<T>
        = addAssertion(BasicAssertion(description, expected, test))
}

