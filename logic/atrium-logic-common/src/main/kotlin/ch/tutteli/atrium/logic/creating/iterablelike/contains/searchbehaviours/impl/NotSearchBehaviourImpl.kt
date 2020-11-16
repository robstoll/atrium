package ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

class NotSearchBehaviourImpl : NotSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable =
        DescriptionIterableAssertion.CONTAINS_NOT
}
