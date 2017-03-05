package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.OneMessageAssertion
import ch.tutteli.assertk.checking.IAssertionChecker
import java.util.ArrayList

internal open class AssertionFactoryCheckLazily<out T : Any> constructor(
    override final val assertionVerb: String,
    override final val subject: T,
    override final val assertionChecker: IAssertionChecker) : IAssertionFactory<T> {

    private val assertions: MutableList<IAssertion> = ArrayList()

    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = addAssertion(OneMessageAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        assertions.add(assertion)
        return this
    }

    override final fun checkAssertions() {
        try {
            assertionChecker.check(assertionVerb, subject, assertions)
        } finally {
            assertions.clear()
        }
    }

}
