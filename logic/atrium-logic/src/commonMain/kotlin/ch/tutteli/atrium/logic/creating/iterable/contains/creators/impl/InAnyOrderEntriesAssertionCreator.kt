package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsAssertionCreator
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.*
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_NEEDS
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.NUMBER_OF_SUCH_ELEMENTS

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
    checkers: List<IterableLikeContains.Checker>,
    private val notToHaveNextOrNoneFunName: String
) : ContainsAssertionCreator<T, List<E?>, (Expect<E>.() -> Unit)?, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
), IterableLikeContains.Creator<T, (Expect<E>.() -> Unit)?> {

    override val descriptionToContain: Translatable = DescriptionIterableLikeExpectation.TO_CONTAIN

    override val descriptionNotFound: Translatable = DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND
    override val descriptionNumberOfElementsFound: Translatable =
        DescriptionIterableLikeExpectation.NUMBER_OF_ELEMENTS_FOUND

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<E?>> =
        container.mapSubjectToList(converter)

    override fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: AssertionGroup,
        multiConsumableContainer: AssertionContainer<List<E?>>
    ): AssertionGroup {
        return if (searchBehaviour is NotSearchBehaviour) {
            val assertion = decorateAssertionWithHasNext(inAnyOrderAssertion, multiConsumableContainer)
           decorateWithHintUseNotToHaveElementsOrNone(assertion, multiConsumableContainer, notToHaveNextOrNoneFunName)
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

        val featureAssertion = featureFactory(count, NUMBER_OF_SUCH_ELEMENTS)
        val assertions = mutableListOf<Assertion>(explanatoryGroup)
        if (searchBehaviour is NotSearchBehaviour) {
            val mismatches = createIndexAssertions(list) { (_, element) ->
                allCreatedAssertionsHold(multiConsumableContainer, element, searchCriterion)
            }
            if (mismatches.isNotEmpty()) assertions.add(createExplanatoryGroupForMismatches(mismatches))
        } else {
            assertions.add(featureAssertion)
        }
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(AN_ELEMENT_WHICH_NEEDS)
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
}
