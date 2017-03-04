package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.IAssertionChecker

interface IAssertionFactory<out T : Any> {
    val subject: T
    val assertionVerb: String
    val assertionChecker: IAssertionChecker

    fun checkAssertions()
    fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): IAssertionFactory<T>
    fun addAssertion(assertion: IAssertion): IAssertionFactory<T>

    val and: IAssertionFactory<T> get() = this
}
