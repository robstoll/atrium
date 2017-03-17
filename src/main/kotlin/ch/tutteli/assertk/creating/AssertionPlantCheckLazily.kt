package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.IAssertion
import ch.tutteli.assertk.assertions.OneMessageAssertion
import java.util.ArrayList

internal open class AssertionPlantCheckLazily<out T : Any> constructor(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : IAssertionPlant<T> {

    private val assertions: MutableList<IAssertion> = ArrayList()

    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = addAssertion(OneMessageAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
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
