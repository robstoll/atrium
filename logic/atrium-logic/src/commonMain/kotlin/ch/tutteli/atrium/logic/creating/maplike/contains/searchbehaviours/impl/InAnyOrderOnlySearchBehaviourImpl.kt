package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.translations.DescriptionMapLikeExpectation

class InAnyOrderOnlySearchBehaviourImpl : InAnyOrderOnlySearchBehaviour {
    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override fun decorateDescription(description: ch.tutteli.atrium.reporting.translating.Translatable): ch.tutteli.atrium.reporting.translating.Translatable =
        ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(DescriptionMapLikeExpectation.IN_ANY_ORDER_ONLY, description)
}
