package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable] but that
 * the resulting assertion should not hold if there are less entries than expected or more.
 */
@Deprecated(
    "use the search behaviour from package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour")
)
open class IterableContainsInAnyOrderOnlySearchBehaviour : IterableContains.SearchBehaviour,
    ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY, description)
}
