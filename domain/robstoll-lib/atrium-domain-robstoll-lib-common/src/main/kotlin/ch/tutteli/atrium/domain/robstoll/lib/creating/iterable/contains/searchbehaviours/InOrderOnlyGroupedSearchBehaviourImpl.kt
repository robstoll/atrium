package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

class InOrderOnlyGroupedSearchBehaviourImpl : InOrderOnlyGroupedSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionIterableAssertion.IN_ORDER_ONLY_GROUPED, description)
}
