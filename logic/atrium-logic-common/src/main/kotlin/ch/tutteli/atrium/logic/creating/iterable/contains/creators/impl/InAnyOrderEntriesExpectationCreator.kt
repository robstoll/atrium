package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsExpectationCreator
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.allCreatedAssertionsHold
import ch.tutteli.atrium.logic.impl.createExplanatoryAssertionGroup
import ch.tutteli.atrium.logic.impl.createHasElementAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ELEMENT_WHICH

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where expected entries
 *   can appear in any order and are identified by holding a group of assertions, created by an assertion
 *   creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderEntriesExpectationCreator<E : Any, T : IterableLike>(
    private val converter: (T) -> Iterable<E?>,
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableLikeContains.Checker>
) : ContainsExpectationCreator<T, List<E?>, (Expect<E>.() -> Unit)?, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
),
    IterableLikeContains.Creator<T, (Expect<E>.() -> Unit)?> {

    override val descriptionContains: Translatable = DescriptionIterableAssertion.CONTAINS

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<E?>> =
        turnSubjectToList(container, converter)

    override fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<List<E?>>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val list = multiConsumableContainer.maybeSubject.getOrElse { emptyList() }
        val (explanatoryGroup, count) = createExplanatoryAssertionsAndMatchingCount(list, searchCriterion)

        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        val assertions = mutableListOf<Assertion>(explanatoryGroup, featureAssertion)
        val groupType = if (searchBehaviour is NotSearchBehaviour) {
            assertions.add(createHasElementAssertion(list))
            addEmptyAssertionCreatorLambdaIfNecessary(assertions, searchCriterion, count)
            DefaultSummaryAssertionGroupType
        } else {
            DefaultListAssertionGroupType
        }
        return assertionBuilder.customType(groupType)
            .withDescriptionAndEmptyRepresentation(AN_ELEMENT_WHICH)
            .withAssertions(assertions)
            .build()
    }

    private fun createExplanatoryAssertionsAndMatchingCount(
        list: List<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Pair<AssertionGroup, Int> {
        val group = createExplanatoryAssertionGroup(assertionCreatorOrNull)
        val count = list.count { allCreatedAssertionsHold(it, assertionCreatorOrNull) }
        return group to count
    }

    //TODO 0.17.0 check if this is still state of the art to add a the hint that no assertion was created
    // in the assertionCreator-lambda, maybe it is a special case and needs to be handled like this,
    // maybe it would be enough to collect
    private fun addEmptyAssertionCreatorLambdaIfNecessary(
        assertions: MutableList<Assertion>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        count: Int
    ) {
        if (searchCriterion != null && count == 0) {
            val collectingExpect = CollectingExpect<E>(None)
            // not using addAssertionsCreatedBy on purpose so that we don't append a failing assertion
            collectingExpect.searchCriterion()
            val collectedAssertions = collectingExpect.getAssertions()
            if (collectedAssertions.isEmpty()) {
                // no assertion created in the lambda, so lets add the failing assertion containing the hint
                assertions.add(assertionCollector.collect(None, searchCriterion))
            }
        }
    }
}
