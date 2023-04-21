package ch.tutteli.atrium.logic.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionsOption
import ch.tutteli.atrium.assertions.builders.ExplanatoryGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.collectForCompositionBasedOnSubject

/**
 * Collects the assertions [assertionCreator] creates and uses them as [AssertionGroup.assertions].
 */
//TODO 1.1.0 in case we somehow incorporate the current container in AssertionsOptions, then remove container as parameter
@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalComponentFactoryContainer::class)
fun <T, G : ExplanatoryAssertionGroupType, R : ExplanatoryGroup.FinalStep> AssertionsOption<G, R>.collectAssertions(
    container: AssertionContainer<*>,
    maybeSubject: Option<T>,
    assertionCreator: Expect<T>.() -> Unit
): ExplanatoryGroup.FinalStep {
    //TODO 1.1.0 simplify with new ProofContainer where we intend to return a flag for collectForCompositionBasedOnSubject which indicates whether
    // no proof was created or not, this way we don't have to collect twice.
    val collectingExpect = CollectingExpect<T>(None, container.components)
    // not using addAssertionsCreatedBy on purpose so that we don't append a failing assertion
    collectingExpect.assertionCreator()
    return withAssertions(container.collectForCompositionBasedOnSubject(maybeSubject, assertionCreator))
        .let {
            if (collectingExpect.getAssertions().isEmpty()) it.failing
            else it
        }
}
