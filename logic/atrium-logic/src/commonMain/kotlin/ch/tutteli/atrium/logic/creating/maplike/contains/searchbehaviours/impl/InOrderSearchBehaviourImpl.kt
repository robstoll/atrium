package ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.maplike.contains.searchbehaviours.InOrderSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionMapLikeExpectation

class InOrderSearchBehaviourImpl : InOrderSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionMapLikeExpectation.IN_ORDER, description)
}
