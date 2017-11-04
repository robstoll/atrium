package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.BasicAssertion
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.reporting.translating.ITranslatable
import java.util.*

abstract class BaseAssertionPlant<out T : Any> : IAssertionPlant<T> {
    /**
     * All made assertions so far. They can be cleared calling [clearAssertions].
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<IAssertion> = ArrayList()

    override fun createAndAddAssertion(description: ITranslatable, expected: Any, test: () -> Boolean): IAssertionPlant<T>
        = addAssertion(BasicAssertion(description, expected, test))

    override fun addAssertion(assertion: IAssertion): IAssertionPlant<T> {
        assertions.add(assertion)
        return this
    }

    fun getAssertions(): List<IAssertion> = assertions.toList()
    fun clearAssertions() = assertions.clear()
}
