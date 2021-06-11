package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.CollectingExpect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.collectBasedOnSubject
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsAssertionCreator
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.logic.impl.allCreatedAssertionsHold
import ch.tutteli.atrium.logic.impl.createExplanatoryAssertionGroup
import ch.tutteli.atrium.logic.impl.createExplanatoryGroupForMismatches
import ch.tutteli.atrium.logic.impl.createIndexAssertions
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.AN_ELEMENT_WHICH
import ch.tutteli.kbox.identity

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where an expected entry can appear
 * in any order and is identified by holding a group of assertions, created by an assertion creator lambda.
 *
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
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
class InAnyOrderEntriesAssertionCreator<E : Any, T : IterableLike>(
    private val converter: (T) -> Iterable<E?>,
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableLikeContains.Checker>
) : ContainsAssertionCreator<T, List<E?>, (Expect<E>.() -> Unit)?, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
),
    IterableLikeContains.Creator<T, (Expect<E>.() -> Unit)?> {

    override val descriptionContains: Translatable = DescriptionIterableAssertion.CONTAINS
    override val descriptionNotFound: Translatable = DescriptionIterableAssertion.ELEMENT_NOT_FOUND
    override val descriptionNumberOfElementsFound: Translatable = DescriptionIterableAssertion.NUMBER_OF_ELEMENTS_FOUND

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<E?>> =
        turnSubjectToList(container, converter)

    override fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: AssertionGroup,
        multiConsumableContainer: AssertionContainer<List<E?>>
    ): AssertionGroup {
        val hasNext = multiConsumableContainer.hasNext(::identity)
        return if (searchBehaviour is NotSearchBehaviour && !hasNext.holds()) {
            assertionBuilder.invisibleGroup
                .withAssertions(
                    hasNext,
                    assertionBuilder.explanatoryGroup
                        .withDefaultType
                        .withAssertion(inAnyOrderAssertion)
                        .build()
                )
                .build()
        } else {
            inAnyOrderAssertion
        }
    }

    override fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<List<E?>>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        featureFactory: (Int, Translatable) -> AssertionGroup
    ): AssertionGroup {
        val list = multiConsumableContainer.maybeSubject.getOrElse { emptyList() }
        val (explanatoryGroup, count) = createExplanatoryAssertionsAndMatchingCount(
            multiConsumableContainer,
            list,
            searchCriterion
        )

        val featureAssertion = featureFactory(count, DescriptionIterableAssertion.NUMBER_OF_OCCURRENCES)
        val assertions = mutableListOf<Assertion>(explanatoryGroup)
        if (searchBehaviour is NotSearchBehaviour) {
            createEmptyAssertionHintIfNecessary(multiConsumableContainer, searchCriterion, count)
                ?.let { assertions.add(it) }
            val mismatches = createIndexAssertions(list) { (_, element) ->
                allCreatedAssertionsHold(multiConsumableContainer, element, searchCriterion)
            }
            if (mismatches.isNotEmpty()) assertions.add(createExplanatoryGroupForMismatches(mismatches))
        } else {
            assertions.add(featureAssertion)
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(AN_ELEMENT_WHICH)
            .withAssertions(assertions)
            .build()
    }

    private fun createExplanatoryAssertionsAndMatchingCount(
        container: AssertionContainer<*>,
        list: List<E?>,
        assertionCreatorOrNull: (Expect<E>.() -> Unit)?
    ): Pair<AssertionGroup, Int> {
        val group = createExplanatoryAssertionGroup(container, assertionCreatorOrNull)
        val count = list.count { allCreatedAssertionsHold(container, it, assertionCreatorOrNull) }
        return group to count
    }

    //TODO 0.18.0 check if this is still state of the art to add a hint that no assertion was created
    // in the assertionCreator-lambda, maybe it is a special case and needs to be handled like this,
    // maybe it would be enough to collect
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalComponentFactoryContainer::class)
    private fun createEmptyAssertionHintIfNecessary(
        container: AssertionContainer<*>,
        searchCriterion: (Expect<E>.() -> Unit)?,
        count: Int
    ): Assertion? {
        if (searchCriterion != null && count == 0) {
            val collectingExpect = CollectingExpect<E>(None, container.components)
            // not using addAssertionsCreatedBy on purpose so that we don't append a failing assertion
            collectingExpect.searchCriterion()
            val collectedAssertions = collectingExpect.getAssertions()
            if (collectedAssertions.isEmpty()) {
                // no assertion created in the lambda, so return the failing assertion containing the hint
                return container.collectBasedOnSubject(None, searchCriterion)
            }
        }
        return null
    }
}
