package ch.tutteli.atrium.core.robstoll.lib.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.Expect

/**
 * A base class for [Assert]ion container which are based on a mutable list, it provides [getCopyOfAssertions] to get
 * so far added assertions as well as [clearAssertions] to reset them -- this class is intentionally not thread-safe
 * and thus not intended for multi-thread usage.
 */
abstract class MutableListBasedAssertionContainer<T>(
    override val maybeSubject: Option<T>
) : Expect<T> {

    @Deprecated(
        "Do not access subject as it might break reporting. In contexts where it is safe to access the subject, it is passed by parameter and can be accessed via `it`. See KDoc for migration hints; will be removed with 1.0.0",
        ReplaceWith("it")
    )
    final override val subject: T by lazy {
        maybeSubject.getOrElse {
            @Suppress("DEPRECATION")
            throw ch.tutteli.atrium.creating.PlantHasNoSubjectException()
        }
    }

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
    protected fun clearAssertions(): Unit = assertions.clear()
}
