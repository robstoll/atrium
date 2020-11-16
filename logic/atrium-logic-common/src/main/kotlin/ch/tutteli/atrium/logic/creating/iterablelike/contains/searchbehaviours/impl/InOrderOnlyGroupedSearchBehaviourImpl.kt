package ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

class InOrderOnlyGroupedSearchBehaviourImpl : InOrderOnlyGroupedSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionIterableAssertion.IN_ORDER_ONLY_GROUPED, description)
}
