package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.BaseAssertionPlant

abstract class MutableListBasedAssertionPlant<out T : Any?, out A : BaseAssertionPlant<T, A>>(
    override val subjectProvider: () -> T
) : BaseAssertionPlant<T, A> {

    final override val subject : T by lazy { subjectProvider() }

    /**
     * The instance itself but typed as [A] which is the type used for the fluent style API.
     */
    protected abstract val self: A

    /**
     * All made assertions so far. They can be cleared calling [clearAssertions].
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<Assertion> = mutableListOf()


    override fun addAssertion(assertion: Assertion): A {
        assertions.add(assertion)
        return self
    }

    fun getAssertions(): List<Assertion> = assertions.toList()
    protected fun clearAssertions() = assertions.clear()
}
