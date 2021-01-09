package ch.tutteli.atrium.logic.creating.collectors.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.collectors.AssertionCollector

class DefaultAssertionCollector : AssertionCollector {
    
    override fun <T> collect(maybeSubject: Option<T>, assertionCreator: Expect<T>.() -> Unit): Assertion {
        val collectedAssertions = collectForComposition(maybeSubject, assertionCreator)
        return if (collectedAssertions.size > 1) {
            assertionBuilder.invisibleGroup.withAssertions(collectedAssertions).build()
        } else {
            collectedAssertions[0]
        }
    }

    override fun <T> collectForComposition(
        maybeSubject: Option<T>,
        assertionCreator: Expect<T>.() -> Unit
    ): List<Assertion> = CollectingExpect(maybeSubject)
        .addAssertionsCreatedBy(assertionCreator)
        .getAssertions()
}
