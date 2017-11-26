package ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours

import ch.tutteli.atrium.assertions.DescriptionIterableAssertion
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable] but that
 * the resulting assertion should not hold if there are less entries than expected or more.
 */
object IterableContainsInAnyOrderOnlySearchBehaviour : IIterableContains.ISearchBehaviour {
    override fun decorateDescription(description: ITranslatable): ITranslatable
        = TranslatableWithArgs(DescriptionIterableAssertion.IN_ANY_ORDER_ONLY, description)
}
