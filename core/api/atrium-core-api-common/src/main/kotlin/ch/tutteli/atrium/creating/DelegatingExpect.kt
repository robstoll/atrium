package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

/**
 * Represents an [Expect] which passes on appended assertions to a given [Expect].
 */
interface DelegatingExpect<T> : Expect<T> {
    companion object {
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class)
        operator fun <T> invoke(expect: AssertionContainer<*>, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(expect, maybeSubject)
    }
}
