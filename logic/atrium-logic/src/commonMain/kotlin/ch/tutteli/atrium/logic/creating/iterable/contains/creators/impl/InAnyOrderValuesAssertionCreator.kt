package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.creators.impl.ContainsObjectsAssertionCreator
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.impl.createIndexAssertions
import ch.tutteli.atrium.logic.impl.decorateAssertionWithHasNext
import ch.tutteli.atrium.logic.impl.decorateWithHintUseNotToHaveElementsOrNone
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

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
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 * @param checkers The checkers which create assertions based on the search result.
 */
class InAnyOrderValuesAssertionCreator<SC, T : IterableLike>(
    private val converter: (T) -> Iterable<SC>,
    searchBehaviour: InAnyOrderSearchBehaviour,
    checkers: List<IterableLikeContains.Checker>,
    private val notToHaveNextOrNoneFunName: String
) : ContainsObjectsAssertionCreator<T, List<SC>, SC, InAnyOrderSearchBehaviour, IterableLikeContains.Checker>(
    searchBehaviour,
    checkers
), IterableLikeContains.Creator<T, SC> {

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val descriptionToContain: ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionIterableLikeExpectation.TO_CONTAIN

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val descriptionNumberOfOccurrences: ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionIterableLikeExpectation.NUMBER_OF_SUCH_ELEMENTS

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val groupDescription: ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionIterableLikeExpectation.AN_ELEMENT_WHICH_EQUALS

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val descriptionNotFound: ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionIterableLikeExpectation.ELEMENT_NOT_FOUND

    //TODO 1.3.0 replace with Representable and remove suppression
    @Suppress("DEPRECATION")
    override val descriptionNumberOfElementsFound: ch.tutteli.atrium.reporting.translating.Translatable =
        DescriptionIterableLikeExpectation.NUMBER_OF_ELEMENTS_FOUND

    override fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<List<SC>> =
        container.mapSubjectToList(converter)

    override fun search(multiConsumableContainer: AssertionContainer<List<SC>>, searchCriterion: SC): Int =
        multiConsumableContainer.maybeSubject.fold({ -1 }) { subject -> subject.filter { it == searchCriterion }.size }

    /**
     * Override in any subclass that wants to report mismatched elements individually when the [searchBehaviour]
     * is [NotSearchBehaviour]
     */
    override fun mismatchesForNotSearchBehaviour(
        multiConsumableContainer: AssertionContainer<List<SC>>,
        searchCriterion: SC
    ): List<Assertion> {
        val list = multiConsumableContainer.maybeSubject.getOrElse { emptyList() }
        return createIndexAssertions(list) { (_, element) -> element == searchCriterion }
    }

    /**
     * Override in a subclass if you want to decorate the assertion.
     */
    override fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: AssertionGroup,
        multiConsumableContainer: AssertionContainer<List<SC>>
    ): AssertionGroup {
        return if (searchBehaviour is NotSearchBehaviour) {
            val assertion = decorateAssertionWithHasNext(inAnyOrderAssertion, multiConsumableContainer)
            decorateWithHintUseNotToHaveElementsOrNone(assertion, multiConsumableContainer, notToHaveNextOrNoneFunName)
        } else {
            inAnyOrderAssertion
        }
    }
}
