package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.DelegatingExpectImpl

/**
 * Represents an [Expect] which passes on appended assertions to a given [AssertionHolder].
 *
 * Notice, that [AssertionHolder] has its mere purpose to facilitate the transition from [Assert] to [Expect].
 * It might well be that we are going to remove it with 1.0.0 without previous notice.
 * Hence, to be on the safe side, you should use [Expect] instead.
 */
interface DelegatingExpect<T> : Expect<T> {
    companion object {
        @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
        @UseExperimental(ExperimentalNewExpectTypes::class)
        operator fun <T> invoke(assertionHolder: AssertionHolder, maybeSubject: Option<T>): Expect<T> =
            DelegatingExpectImpl(assertionHolder, maybeSubject)
    }
}
