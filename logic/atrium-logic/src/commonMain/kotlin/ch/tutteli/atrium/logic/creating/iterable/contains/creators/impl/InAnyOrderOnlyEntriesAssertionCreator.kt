//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.allCreatedAssertionsHold
import ch.tutteli.atrium.logic.impl.createExplanatoryAssertionGroup
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries have
 * to appear in the [Iterable] but in any order -- an entry is identified by holding a group of assertions
 * created by an assertion creator lambda.
 *
 * @param E The type of the elements of the [Iterable] the [converter] is going to create.
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected
 *   entries have to appear in the [Iterable] but in any order -- an entry is identified by holding a group
 *   of assertions created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 */
class InAnyOrderOnlyEntriesAssertionCreator<E : Any, T : IterableLike>(
    converter: (T) -> Iterable<E?>,
    searchBehaviour: InAnyOrderOnlySearchBehaviour,
    reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
) : InAnyOrderOnlyAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(converter, searchBehaviour, reportingOptions) {

    override fun createAssertionForSearchCriterionAndRemoveMatchFromList(
        container: AssertionContainer<*>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        list: MutableList<E?>
    ): Pair<Boolean, Assertion> {
        val explanatoryAssertionGroup = createExplanatoryAssertionGroup(container, searchCriterion)
        val found = removeMatch(container, list, searchCriterion)
        return found to createEntryAssertion(explanatoryAssertionGroup, found)
    }

    private fun createEntryAssertion(explanatoryAssertionGroup: AssertionGroup, found: Boolean): AssertionGroup =
        assertionBuilder.fixedClaimGroup
            .withListType
            .withClaim(found)
            .withDescriptionAndEmptyRepresentation(AN_ELEMENT_WHICH_NEEDS)
            .withAssertion(explanatoryAssertionGroup)
            .build()


    private fun removeMatch(
        container: AssertionContainer<*>,
        list: MutableList<E?>,
        assertionCreator: (Expect<E>.() -> Unit)?
    ): Boolean {
        val itr = list.iterator()
        while (itr.hasNext()) {
            if (allCreatedAssertionsHold(container, itr.next(), assertionCreator)) {
                itr.remove()
                return true
            }
        }
        return false
    }
}
