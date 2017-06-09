package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IOneMessageAssertion
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Represents an assertion plant for nullable types.
 *
 * In contrast to a [IAssertionPlant] it does not provide a method to create further [IAssertion]s
 * ([IAssertionPlant.createAndAddAssertion]) nor a method to add assertions ([IAssertionPlant.addAssertion])
 * and as consequence no method to check the assertions ([IAssertionPlant.checkAssertions]).
 * Yet, it provides one method [isNull] which immediately evaluates if the [subject] is `null` as expected.
 */
interface IAssertionPlantNullable<out T : Any?> : IAssertionPlantWithCommonFields<T> {
    /**
     * Makes the assertion that [subject] is `null` and checks the assertion.
     *
     * @throws AssertionError Reporting the failure might throw an [AssertionError].
     *
     * @see IReporter
     */
    fun isNull()

    /**
     * Use this description in an implementation to create an [IOneMessageAssertion].
     */
    object AssertionDescription : ISimpleTranslatable {
        override val name = "TO_BE"
        override val value = "to be"
    }
}
