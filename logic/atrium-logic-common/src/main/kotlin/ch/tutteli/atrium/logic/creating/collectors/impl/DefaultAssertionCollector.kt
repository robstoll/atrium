package ch.tutteli.atrium.logic.creating.collectors.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.impl.DefaultComponentFactoryContainer
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

    @Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalComponentFactoryContainer::class)
    override fun <T> collectForComposition(
        maybeSubject: Option<T>,
        assertionCreator: Expect<T>.() -> Unit
        //TODO 0.16.0 don't use DefaultComponentFactoryContainer but the one from the container
    ): List<Assertion> = CollectingExpect(maybeSubject, DefaultComponentFactoryContainer)
        .addAssertionsCreatedBy(assertionCreator)
        .getAssertions()
}
