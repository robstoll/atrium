@file:JvmName("IAssertionPlantExtensions")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a plant for [IAssertion]s and offers methods to [create][createAndAddAssertion] and
 * to [add][addAssertion] assertions to this plant.
 *
 * It is the entry point for most assertion functions and provides only a reduced set of [IReportingAssertionPlant]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IAssertionPlant<out T : Any> {
    /**
     * The subject for which this plant will create [IAssertion]s.
     */
    val subject: T

    /**
     * Creates an [IAssertion] based on [description], [expected] and [test] and [adds][addAssertion] it
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
    fun createAndAddAssertion(description: ITranslatable, expected: Any, test: () -> Boolean): IAssertionPlant<T>

    /**
     * Adds the given [assertion] to the plant.
     *
     * @param assertion The assertion which will be added to this plant.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately
     *         evaluated (see [IReportingAssertionPlant]).
     */
    fun addAssertion(assertion: IAssertion): IAssertionPlant<T>

    /**
     * Can be used to separate assertions when using the fluent API.
     *
     * For instance, `assert(1).isLessThan(2).and.isGreaterThan(0)`
     *
     * @return This plant to support a fluent API.
     */
    val and: IAssertionPlant<T> get() = this
}

