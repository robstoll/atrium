package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect

/**
 * Mainly holds a reference to a given [expect] (usually the [Expect] passed to `_domain`)
 * so that you do not have to pass it to [AssertionCollector] yourself.
 *
 * In case you want to operate on arbitrary subjects, then use [assertionCollector] directly.
 *
 * @since 0.9.0
 */
class DelegateToAssertionCollector<T>(val expect: Expect<T>) {

    /**
     * Uses the [Expect.maybeSubject] and delegates to [assertionCollector].[collect][AssertionCollector.collect].
     *
     * See [AssertionCollector.collect] for more information.
     */
    fun collect(
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion = assertionCollector.collect(expect.maybeSubject, assertionCreator)
}
