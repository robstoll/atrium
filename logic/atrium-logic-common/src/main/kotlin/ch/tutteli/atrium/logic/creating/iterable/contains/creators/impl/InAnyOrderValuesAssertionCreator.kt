package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.logic.impl.createExplanatoryGroupForMismatches
import ch.tutteli.atrium.logic.impl.createHasElementAssertion
import ch.tutteli.atrium.logic.impl.createIndexAssertions
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.identity

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by expected objects (equality comparison).
 *
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
 * @param SC The type of the elements of the iterable, used as search criteria.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by expected objects (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderValuesAssertionCreator<SC, T : IterableLike>(
    private val converter: (T) -> Iterable<SC>,
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableLikeContains.Checker>
) : ContainsObjectsAssertionCreator<T, List<SC>, SC, InAnyOrderSearchBehaviour, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
), IterableLikeContains.Creator<T, SC> {

    override val descriptionContains: Translatable = DescriptionIterableAssertion.CONTAINS
    override val descriptionNumberOfOccurrences: Translatable = DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES
    override val groupDescription: Translatable = DescriptionIterableAssertion.AN_ELEMENT_WHICH_EQUALS

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<SC>> =
        turnSubjectToList(container, converter)

    override fun search(multiConsumableContainer: AssertionContainer<List<SC>>, searchCriterion: SC): Int =
        multiConsumableContainer.maybeSubject.fold({ -1 }) { subject -> subject.filter { it == searchCriterion }.size }

    override fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<List<SC>>,
        searchCriterion: SC,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        if (searchBehaviour is NotSearchBehaviour) {
            val list = multiConsumableContainer.maybeSubject.getOrElse { emptyList() }
            val mismatches = createIndexAssertions(list) { (_, element) -> element == searchCriterion }
            return if (multiConsumableContainer.hasNext(::identity).holds()) {
                assertionBuilder.fixedClaimGroup
                    .withListType
                    .withClaim(mismatches.isEmpty())
                    .withDescriptionAndRepresentation(groupDescription, searchCriterion)
                    .withAssertion(createExplanatoryGroupForMismatches(mismatches))
                    .build()
            } else {
                assertionBuilder.invisibleGroup
                    .withAssertion(
                        assertionBuilder.createDescriptive(groupDescription, searchCriterion, falseProvider)
                    ).build()
            }
        } else {
            return super.searchAndCreateAssertion(multiConsumableContainer, searchCriterion, featureFactory)
        }
    }

    /**
     * Override in a subclass if you want to decorate the assertion.
     */
    override fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: AssertionGroup,
        multiConsumableContainer: AssertionContainer<List<SC>>
    ): AssertionGroup {
        return if (searchBehaviour is NotSearchBehaviour) {
            assertionBuilder.invisibleGroup.withAssertions(
                multiConsumableContainer.hasNext(::identity),
                inAnyOrderAssertion
            ).build()
        } else {
            inAnyOrderAssertion
        }
    }
}
