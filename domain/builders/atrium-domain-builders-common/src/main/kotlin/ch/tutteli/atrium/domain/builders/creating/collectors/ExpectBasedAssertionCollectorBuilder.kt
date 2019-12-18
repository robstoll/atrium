package ch.tutteli.atrium.domain.builders.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector

class ExpectBasedAssertionCollectorBuilder<T>(val expect: Expect<T>) {

    /**
     * Uses the [Expect.maybeSubject] and delegates to [assertionCollector].[collect][AssertionCollector.collect].
     *
     * See [AssertionCollector.collect] for more information.
     */
    fun collect(
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion = assertionCollector.collect(expect.maybeSubject, assertionCreator)
}
