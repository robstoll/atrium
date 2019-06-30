package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.PlantHasNoSubjectException

/**
 * A base class for [Assert]ion container which are based on a mutable list, it provides [getCopyOfAssertions] to get
 * so far added assertions as well as [clearAssertions] to reset them -- this class is intentionally not thread-safe
 * and thus not intended for multi-thread usage.
 */
abstract class MutableListBasedAssertionContainer<T>(
    override val maybeSubject: Option<T>
) : Expect<T> {

    //TODO #88 deprecate
    final override val subject: T by lazy { maybeSubject.getOrElse { throw PlantHasNoSubjectException() } }

    /**
     * All made assertions so far. They can be cleared calling [clearAssertions].
     * This list is intentionally not thread-safe, this class is not intended for multi-thread usage.
     */
    private val assertions: MutableList<Assertion> = mutableListOf()

    override fun addAssertion(assertion: Assertion): Expect<T> {
        assertions.add(assertion)
        return this
    }

    /**
     * Returns a copy of the so far added assertions (assertions can be cleared calling [clearAssertions]).
     */
    protected fun getCopyOfAssertions(): List<Assertion> = assertions.toList()

    /**
     * Clears the list of assertions
     */
    protected fun clearAssertions() = assertions.clear()
}
