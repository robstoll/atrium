package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionMapLikeAssertion

class InAnyOrderOnlySearchBehaviourImpl : InAnyOrderOnlySearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionMapLikeAssertion.IN_ANY_ORDER_ONLY, description)
}
