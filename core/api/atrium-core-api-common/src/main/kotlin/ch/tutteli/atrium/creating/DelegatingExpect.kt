package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

interface DelegatingExpect<T>: Expect<T> {
    companion object{
        operator fun <T> invoke(assertionHolder: AssertionHolder, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(assertionHolder, maybeSubject)
    }
}
