package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.OneMessageAssertion
import java.util.*

/**
 * An [IAssertionPlant] which does not check each added [IAssertion] immediately but only if [checkAssertions] is called and does not fail fast.
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
internal open class AssertionPlantCheckLazily<out T : Any>(
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
