package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IOneMessageAssertion
import ch.tutteli.atrium.assertions.OneMessageAssertion
import ch.tutteli.atrium.creating.IAssertionPlantWithCommonFields.CommonFields
import java.util.*

/**
 * An [IAssertionPlant] which does not check each added [IAssertion] immediately but only if [checkAssertions] is called and does not fail fast.
 *
 * This class is not thread-safe, but is also not intended for long-running procedures.
 */
internal open class AssertionPlantCheckLazily<out T : Any> constructor(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<T>) : IAssertionPlant<T> {

    private val assertions: MutableList<IAssertion> = ArrayList()

    /**
     * Creates a [IOneMessageAssertion] and adds it ([addAssertion]) to the assertions
     * which should be checked ([checkAssertions]]) later on.
     */
    override final fun createAndAddAssertion(description: String, expected: Any, test: () -> Boolean)
        = addAssertion(OneMessageAssertion(description, expected, test))

    /**
     * Adds the given [assertion] to the [IAssertion]s which should be checked when calling [checkAssertions].
     */
    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        assertions.add(assertion)
        return this
    }

    /**
     * Checks the so far added [IAssertion] ([addAssertion]) by using [CommonFields.check].
     *
     * Forgets about the previous added [IAssertion]s afterwards.
     */
    override final fun checkAssertions() {
        try {
            commonFields.check(assertions)
        } finally {
            assertions.clear()
        }
    }

}
