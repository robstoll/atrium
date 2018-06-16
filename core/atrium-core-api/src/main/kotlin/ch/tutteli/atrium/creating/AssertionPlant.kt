@file:JvmName("IAssertionPlantExtensions")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a plant for [Assertion]s based on a non nullable [subject].
 *
 * It is the entry point for most assertion functions and provides only a reduced set of [ReportingAssertionPlant]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * This class should actually be named [AssertionPlant] but since a typealias looks ugly in code completion and most
 * users will interact with [Assert], we decided to switch the names. [AssertionPlant] still exists as type alias.
 *
 * @param T The type of the [subject] of this [Assert].
 */
@AssertMarker
interface Assert<out T : Any> : BaseAssertionPlant<T, Assert<T>> {

    /**
     * Adds the assertions created by the [assertionCreator] lambda to this plant.
     *
     * @param assertionCreator The receiver function which might create and add assertions to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionPlant]).
     */
    fun addAssertionsCreatedBy(assertionCreator: AssertionPlant<T>.() -> Unit): AssertionPlant<T>

    /**
     * Creates a [DescriptiveAssertion] based on the given [description], [expected] and [test] and
     * [adds][addAssertion] it to the plant.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately
     *   evaluated (see [ReportingAssertionPlant]).
     */
    fun createAndAddAssertion(description: Translatable, expected: Any, test: () -> Boolean): AssertionPlant<T>
        = addAssertion(assertionBuilder.createDescriptive(description, expected, test))
}

