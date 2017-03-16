package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion

interface IAssertionFactory<out T : Any> : IAssertionFactoryBase<T> {
    fun checkAssertions()
    fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): IAssertionFactory<T>
    fun addAssertion(assertion: IAssertion): IAssertionFactory<T>

    val and: IAssertionFactory<T> get() = this
}
