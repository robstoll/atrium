package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.CollectingExpectImpl

interface CollectingExpect<T>: Expect<T> {

    /**
     * Returns the [Assertion]s which have been [added][addAssertion] to this container.
     *
     * @return The [Assertion]s which have been [added][addAssertion] to this container.
     */
    fun getAssertions(): List<Assertion>

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T>

    companion object{
        operator fun <T> invoke(maybeSubject: Option<T>): CollectingExpect<T> =
            CollectingExpectImpl(maybeSubject)
    }
}
