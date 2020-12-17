package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect

@ExperimentalNewExpectTypes
internal class DelegatingExpectImpl<T>(private val assertionHolder: AssertionHolder, maybeSubject: Option<T>) :
    BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {
    override fun addAssertion(assertion: Assertion): Expect<T> {
        assertionHolder.addAssertion(assertion)
        return this
    }
}
