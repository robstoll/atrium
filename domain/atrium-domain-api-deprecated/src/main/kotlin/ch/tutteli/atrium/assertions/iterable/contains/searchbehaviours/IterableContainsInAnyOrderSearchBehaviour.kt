@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the *deprecated* search behaviour that expected entries might appear in any order within the [Iterable].
 */
@Deprecated(
    "use the interface InAnyOrderSearchBehaviour instead, will be removed with 1.0.0",
    ReplaceWith(
        "InAnyOrderSearchBehaviour",
        "ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour"
    )
)
open class IterableContainsInAnyOrderSearchBehaviour : IterableContains.SearchBehaviour,
    ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER, description)
}
