package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

/**
 * Represents an [Expect] which passes on appended [Assertion]s to a given [Expect].
 */
interface DelegatingExpect<T> : Expect<T> {
    companion object {
        @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
        operator fun <T> invoke(expect: AssertionContainer<*>, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(expect, maybeSubject)
    }
}
