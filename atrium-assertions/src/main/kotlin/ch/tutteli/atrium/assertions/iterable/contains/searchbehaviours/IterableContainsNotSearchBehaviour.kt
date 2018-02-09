package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable].
 */
@Deprecated("use the search behaviour from package creating, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsNotSearchBehaviour"))
open class IterableContainsNotSearchBehaviour : IterableContainsInAnyOrderSearchBehaviour() {
    override fun decorateDescription(description: Translatable): Translatable
        = DescriptionIterableAssertion.CONTAINS_NOT
}
