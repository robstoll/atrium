package ch.tutteli.atrium.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.DelegatingExpect
import ch.tutteli.atrium.creating.Expect

@ExperimentalNewExpectTypes
internal class DelegatingExpectImpl<T>(private val container: AssertionContainer<*>, maybeSubject: Option<T>) :
    BaseExpectImpl<T>(maybeSubject), DelegatingExpect<T> {

    override fun addAssertion(assertion: Assertion): Expect<T> {
        container.addAssertion(assertion)
        return this
    }
}
