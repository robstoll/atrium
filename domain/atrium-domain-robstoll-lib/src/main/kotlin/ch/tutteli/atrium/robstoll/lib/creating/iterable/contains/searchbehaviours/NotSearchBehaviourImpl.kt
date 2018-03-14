package ch.tutteli.atrium.robstoll.lib.creating.iterable.contains.searchbehaviours

import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the search behaviour that expected entries might appear in any order within the [Iterable].
 */
class NotSearchBehaviourImpl : NotSearchBehaviour {

    override fun decorateDescription(description: Translatable): Translatable
        = DescriptionIterableAssertion.CONTAINS_NOT
}
