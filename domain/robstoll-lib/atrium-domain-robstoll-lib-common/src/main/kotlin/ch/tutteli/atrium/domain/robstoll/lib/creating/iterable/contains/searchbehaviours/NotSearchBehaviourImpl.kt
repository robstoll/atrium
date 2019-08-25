package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

class NotSearchBehaviourImpl : NotSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable =
        DescriptionIterableAssertion.CONTAINS_NOT
}
