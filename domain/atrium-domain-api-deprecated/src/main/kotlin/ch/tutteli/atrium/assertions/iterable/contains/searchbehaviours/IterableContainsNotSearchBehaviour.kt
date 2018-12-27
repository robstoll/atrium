@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the *deprecated* search behaviour that expected entries might appear in any order within the [Iterable].
 */
@Deprecated(
    "Use the interface NotSearchBehaviour instead; will be removed with 1.0.0",
    ReplaceWith(
        "NotSearchBehaviour",
        "ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour"
    )
)
open class IterableContainsNotSearchBehaviour : IterableContainsInAnyOrderSearchBehaviour(),
    ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
{
    override fun decorateDescription(description: Translatable): Translatable
        = DescriptionIterableAssertion.CONTAINS_NOT
}
