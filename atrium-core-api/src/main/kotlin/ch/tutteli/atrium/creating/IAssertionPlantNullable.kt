package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IBasicAssertion
import ch.tutteli.atrium.reporting.IReporter
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

/**
 * Represents an assertion plant for nullable types.
 *
 * It is the entry point for two assertion functions, the first makes the assumption that [subject] is `null`
 * and the other that [subject] is not `null`. It only provides a reduced set of [IReportingAssertionPlantNullable]
 * which is actually created when a user of Atrium is using an assertion verb function.
 *
 * @param T The type of the [subject] of this [IAssertionPlant].
 */
interface IAssertionPlantNullable<out T : Any?> : IBaseAssertionPlant<T, IAssertionPlantNullable<T>> {

    //TODO move to atrium-assertions
    /**
     * Makes the assertion that [subject] is `null` and checks the assertion.
     *
     * @throws AssertionError Reporting the failure might throw an [AssertionError].
     *
     * @see IReporter
     */
    fun isNull()

    //TODO move to atrium-translations
    /**
     * Use this description in an implementation to create an [IBasicAssertion].
     */
    object AssertionDescription : ISimpleTranslatable {
        override val name = "TO_BE"
        override val value = "to be"
    }
}
