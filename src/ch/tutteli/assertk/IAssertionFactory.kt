package ch.tutteli.assertk

interface IAssertionFactory<out T : Any> {
    val subject: T
    val assertionVerb: String
    val assertionChecker: IAssertionChecker

    fun checkAssertions()
    fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean): IAssertionFactory<T>
    fun createAndAddAssertion(description: String, expected: String, test: () -> Boolean): IAssertionFactory<T>
    fun addAssertion(assertion: IAssertion): IAssertionFactory<T>

    val and: IAssertionFactory<T> get() = this
}
