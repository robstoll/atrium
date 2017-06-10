@file:JvmName("IAssertionPlantExtensions")
package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents a plant for [IAssertion]s and offers the possibility to check all the added assertions.
 *
 * You can think of it as an [IAssertion] factory which does more than just factoring
 * but also provides quality assurance capabilities.
 */
interface IAssertionPlant<out T : Any> : IAssertionPlantWithCommonFields<T> {
    /**
     * Creates an [IAssertion] based on [description], [expected] and [test] and [adds][addAssertion] it
     * to the plant.
     *
     * @param description The description of the assertion, e.g., `is less than`.
     * @param expected The expected value, e.g., `5`
     * @param test Indicates whether the assertion holds or fails.
     *
     * @return This plant to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately evaluated.
     */
    fun createAndAddAssertion(description: ITranslatable, expected: Any, test: () -> Boolean): IAssertionPlant<T>

    /**
     * Adds the given [assertion] to the plant.
     *
     * @param assertion The assertion which will be added to this plant.
     *
     * @return This plant to support a fluent-style API.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [IAssertion]s are immediately evaluated.
     */
    fun addAssertion(assertion: IAssertion): IAssertionPlant<T>

    /**
     * Checks the so far [added][addAssertion] [IAssertion]s and reports if one of them fails.
     *
     * Calling this method more than once should not re-report previously failing assertions.
     * This method will typically use an [IAssertionChecker] for checking and an [IReporter] for error reporting.
     *
     * @return This plant to support a fluent-style API.
     *
     * @throws AssertionError Reporting a failing assertion might cause that an [AssertionError] is thrown.
     *
     * @see IAssertionChecker
     * @see IReporter
     */
    fun checkAssertions(): IAssertionPlant<T>

    /**
     * Can be used to separate assertions when using the fluent-style API.
     *
     * For instance, `assert(1).isLessThan(2).and.isGreaterThan(0)`
     *
     * @return This plant to support a fluent-style API.
     */
    val and: IAssertionPlant<T> get() = this
}

/**
 * Uses `this` plant as receiver of the given [createAssertions] function and
 * then calls [IAssertionPlant.checkAssertions].
 *
 * @return This plant to support a fluent-style API.
 *
 * @throws AssertionError Might throw an [AssertionError] in case a created [IAssertion] does not hold.
 */
inline fun <T : Any> IAssertionPlant<T>.createAssertionsAndCheckThem(createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
    createAssertions()
    checkAssertions()
    return this
}
