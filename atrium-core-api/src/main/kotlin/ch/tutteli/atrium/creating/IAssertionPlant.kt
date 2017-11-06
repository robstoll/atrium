@file:JvmName("IAssertionPlantExtensions")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a plant for [IAssertion]s based on a non nullable [subject].
 *
 * It is the entry point for most assertion functions and provides only a reduced set of [IReportingAssertionPlant]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IAssertionPlant<out T : Any> : IBaseAssertionPlant<T, IAssertionPlant<T>> {

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
    fun createAndAddAssertion(description: ITranslatable, expected: Any, test: () -> Boolean): IAssertionPlant<T>
        = addAssertion(BasicAssertion(description, expected, test))

    /**
     * Can be used to separate assertions when using the fluent API.
     *
     * For instance, `assert(1).isLessThan(2).and.isGreaterThan(0)`
     *
     * @return This plant to support a fluent API.
     */
    val and: IAssertionPlant<T> get() = this
}

