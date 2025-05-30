package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by an expected
 *   object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 */
class InAnyOrderOnlyValuesAssertionCreator<E, T : IterableLike>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InAnyOrderOnlySearchBehaviour,
    reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
) : InAnyOrderOnlyAssertionCreator<E, T, E>(converter, searchBehaviour, reportingOptions) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(
        container: AssertionContainer<*>,
        searchCriterion: E,
        list: MutableList<E?>
    ): Pair<Boolean, Assertion> {
        val found: Boolean = list.remove(searchCriterion)
        return found to assertionBuilder.createDescriptive(AN_ELEMENT_WHICH_EQUALS, searchCriterion) { found }
    }
}
