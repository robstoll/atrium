package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

class InOrderSearchBehaviourImpl : InOrderSearchBehaviour {
    override fun decorateDescription(description: Translatable): Translatable =
        TranslatableWithArgs(DescriptionIterableAssertion.IN_ORDER, description)
}
