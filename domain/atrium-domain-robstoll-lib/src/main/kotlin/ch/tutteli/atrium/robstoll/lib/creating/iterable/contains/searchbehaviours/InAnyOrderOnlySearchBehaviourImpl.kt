package ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable] but that
 * the resulting assertion should not hold if there are less entries than expected or more.
 */
class InAnyOrderOnlySearchBehaviourImpl : InAnyOrderOnlySearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY, description)
}
