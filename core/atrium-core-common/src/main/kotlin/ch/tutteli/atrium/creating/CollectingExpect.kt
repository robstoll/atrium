package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.CollectingExpectImpl
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer

/**
 * Represents a container for [Assertion] which is intended to serve as receiver object for lambdas which create
 * [Assertion]s, in which this [Expect] collects the assertions created this way.
 *
 * @param T The type of the subject of this [Expect].
 */
interface CollectingExpect<T> : Expect<T> {

    /**
     * Returns the [Assertion]s which have been [added][addAssertion] to this container.
     *
     * @return The [Assertion]s which have been [added][addAssertion] to this container.
     */
    fun getAssertions(): List<Assertion>

    @Deprecated(
        "use appendAsGroup; will be removed with 0.18.0",
        ReplaceWith("this.appendAsGroup(assertionCreator)")
    )
    override fun addAssertionsCreatedBy(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T>

    /**
     * Appends the [Assertion]s the given [assertionCreator] creates to this container and
     * returns an [Expect] which includes them.
     *
     * Whether the returned [Expect] is the same as the initial one is up to the implementation (i.e. if a mutable
     * structure is used or an immutable). Atrium strives for an immutable data structure in the long run and will
     * little by little refactor the code accordingly.
     *
     * @param assertionCreator The lambda which will create assertions.
     *
     * @return an [Expect] for the subject of `this` expectation.
     *
     * @throws AssertionError Might throw an [AssertionError] in case [Assertion]s are immediately evaluated.
    */
    fun appendAsGroup(assertionCreator: Expect<T>.() -> Unit): CollectingExpect<T>

    companion object {
        @Suppress(
            "DEPRECATION",
            "DeprecatedCallableAddReplaceWith" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */
        )
        @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        @Deprecated("Use the overload which expects a ComponentFactoryContainer; will be removed with 0.18.0")
        operator fun <T> invoke(maybeSubject: Option<T>): CollectingExpect<T> =
            CollectingExpectImpl(maybeSubject, DefaultComponentFactoryContainer)

        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        operator fun <T> invoke(
            maybeSubject: Option<T>,
            componentFactoryContainer: ComponentFactoryContainer
        ): CollectingExpect<T> =
            CollectingExpectImpl(maybeSubject, componentFactoryContainer)
    }
}
