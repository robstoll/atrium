package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.IReporter

/**
 * Represents an assertion plant for nullable types.
 *
 * In contrast to a [IAssertionPlant] it does not provide a method to create further [IAssertion]s
 * ([IAssertionPlant.createAndAddAssertion]) nor a method to add assertions ([IAssertionPlant.addAssertion])
 * and as consequence no method to check them ([IAssertionPlant.checkAssertions]).
 * Yet, it provides one method [isNull] which immediately evaluates if the [subject] is `null` as expected.
 */
interface IAssertionPlantNullable<out T : Any?> : IAssertionPlantWithCommonFields<T> {
    /**
     * Checks whether [subject] is `null` and reports an error if it is not.
     *
     * @throws AssertionError Reporting the failure might throw an [AssertionError].
     *
     * @see IReporter
     */
    fun isNull()
}
