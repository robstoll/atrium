package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.OneMessageAssertion
import java.util.ArrayList

internal open class AssertionFactoryCheckLazily<out T : Any> constructor(
    override val commonFields: IAssertionFactoryBase.CommonFields<T>) : IAssertionFactory<T> {

    private val assertions: MutableList<IAssertion> = ArrayList()

    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = addAssertion(OneMessageAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionFactory<T> {
        assertions.add(assertion)
        return this
    }

    override final fun checkAssertions() {
        try {
            commonFields.check(assertions)
        } finally {
            assertions.clear()
        }
    }

}
