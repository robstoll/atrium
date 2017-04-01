package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

/**
 * Represents a plant for assertions and offers the possibility to check all the added assertions.
 *
 * You can think of it as an [IAssertion] factory which does more than just factoring but also provides quality assurance.
 */
interface IAssertionPlant<out T : Any> : IAssertionPlantWithCommonFields<T> {
    fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): IAssertionPlant<T>
    fun addAssertion(assertion: IAssertion): IAssertionPlant<T>
    fun checkAssertions()

    /**
     * Can be used to separate assertions when using the fluent-style API.
     *
     * For instance, `assert(1).isSmallerThan(2).and.isGreaterThan(0)`
     */
    val and: IAssertionPlant<T> get() = this
}
