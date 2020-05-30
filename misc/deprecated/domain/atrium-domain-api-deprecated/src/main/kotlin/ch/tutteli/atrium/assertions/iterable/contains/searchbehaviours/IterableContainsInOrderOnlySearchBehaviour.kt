@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the *deprecated* search behaviour that expected entries have to appear in the given order within the [Iterable] and
 * that the resulting assertion should not hold if there are less entries than expected or more.
 */
@Deprecated(
    "Use the interface InOrderOnlySearchBehaviour instead; will be removed with 1.0.0",
    ReplaceWith(
        "InOrderOnlySearchBehaviour",
        "ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour"
    )
)
open class IterableContainsInOrderOnlySearchBehaviour : IterableContains.SearchBehaviour,
    ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ORDER_ONLY, description)
}
