package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

interface DelegatingExpect<T> : Expect<T> {
    companion object {
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class)
        operator fun <T> invoke(assertionHolder: AssertionHolder, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(assertionHolder, maybeSubject)
    }
}
