package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.CollectingExpectImpl

/**
 * Represents a container for [Assertion] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this [Expect] collects the assertions created this way.
 *
 * @param T The type of the [subject] of this [Expect].
 */
interface CollectingExpect<T> : Expect<T> {

    /**
     * Returns the [Assertion]s which have been [added][addAssertion] to this container.
     *
     * @return The [Assertion]s which have been [added][addAssertion] to this container.
     */
    fun getAssertions(): List<Assertion>

    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T>

    companion object {
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class)
        operator fun <T> invoke(maybeSubject: Option<T>): CollectingExpect<T> =
            CollectingExpectImpl(maybeSubject)
    }
}
