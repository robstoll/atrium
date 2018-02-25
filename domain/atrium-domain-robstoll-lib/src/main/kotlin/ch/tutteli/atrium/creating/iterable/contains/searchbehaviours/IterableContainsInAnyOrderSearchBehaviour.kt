package ch.tutteli.atrium.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable].
 */
class IterableContainsInAnyOrderSearchBehaviourImpl : IterableContainsInAnyOrderSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER, description)
}
