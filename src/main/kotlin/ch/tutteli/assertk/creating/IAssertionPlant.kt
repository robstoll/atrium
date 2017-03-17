package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

interface IAssertionPlant<out T : Any> : IAssertionPlantWithCommonFields<T> {
    fun checkAssertions()
    fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): IAssertionPlant<T>
    fun addAssertion(assertion: IAssertion): IAssertionPlant<T>

    val and: IAssertionPlant<T> get() = this
}
