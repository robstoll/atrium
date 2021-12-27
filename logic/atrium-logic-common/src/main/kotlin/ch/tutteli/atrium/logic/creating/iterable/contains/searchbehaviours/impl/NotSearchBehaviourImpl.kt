package ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.impl

import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

class NotSearchBehaviourImpl : NotSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable =
        DescriptionIterableLikeExpectation.NOT_TO_CONTAIN
}
