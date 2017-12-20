package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.IAssertion

abstract class MutableListBasedAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>> : BaseAssertionPlant<T, A> {

    /**
     * The instance itself but typed as [A] which is the type used for the fluent style API.
     */
    protected abstract val self: A

    /**
     * All made assertions so far. They can be cleared calling [clearAssertions].
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<IAssertion> = mutableListOf()


    override fun addAssertion(assertion: IAssertion): A {
        assertions.add(assertion)
        return self
    }

    fun getAssertions(): List<IAssertion> = assertions.toList()
    protected fun clearAssertions() = assertions.clear()
}
