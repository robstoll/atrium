package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.IReporter

/**
 * Represents a plant for [IAssertion]s and offers the possibility to [check][checkAssertions] all
 * the [added][addAssertion] assertions which includes error reporting.
 *
 * You can think of it as an [IAssertion] factory which does more than just factoring
 * but also provides quality assurance capabilities.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IReportingAssertionPlant<out T : Any> : IAssertionPlant<T>, IAssertionPlantWithCommonFields<T> {
    /**
     * The subject for which this plant will create, check and report [IAssertion]s.
     */
    override val subject get() = commonFields.subject

    /**
     * Checks the so far [added][addAssertion] [IAssertion]s and reports if one of them fails.
     *
     * Calling this method more than once should not re-report previously failing assertions.
     * This method will typically use an [IAssertionChecker] for checking and an [IReporter] for error reporting.
     *
     * @return This plant to support a fluent API.
     *
     * @throws AssertionError Reporting a failing assertion might cause that an [AssertionError] is thrown.
     *
     * @see IAssertionChecker
     * @see IReporter
     */
    fun checkAssertions(): IAssertionPlant<T>
}

/**
 * Uses `this` plant as receiver of the given [createAssertions] function and
 * then calls [IReportingAssertionPlant.checkAssertions].
 *
 * @param createAssertions The receiver function which might create and add assertions to this plant.
 *
 * @return This plant to support a fluent API.
 *
 * @throws AssertionError Might throw an [AssertionError] in case a created [IAssertion] does not hold.
 */
inline fun <T : Any> IReportingAssertionPlant<T>.createAssertionsAndCheckThem(createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T> {
    createAssertions()
    checkAssertions()
    return this
}
