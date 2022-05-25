package ch.tutteli.atrium.logic.creating.basic.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.basic.contains.Contains
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.creating.basic.contains.checkers.AtLeastChecker
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 *
 * @param T The type of the subject of this expectation.
 * @param TT The type of the subject of this expectation after making it multiple times consumable.
 * @param SC The type of the search criteria.
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property checkers The [Contains.Checker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsAssertionCreator<T : Any, TT : Any, in SC, C : Contains.Checker>(
    protected val searchBehaviour: Contains.SearchBehaviour,
    private val checkers: List<C>
) : Contains.Creator<T, SC> {


    /**
     * Provides the translation for `to contain`.
     */
    protected abstract val descriptionToContain: Translatable

    //TODO remove with 0.19.0
    /**
     * Provides the translation for `contains`.
     */
    @Deprecated(
        "Use descriptionToContain instead; will be removed with 0.19.0",
        ReplaceWith("this.descriptionToContain ")
    )
    protected abstract val descriptionContains: Translatable


    /**
     * Provides the translation for when an item is not found in a `toContain.atLeast(1)` check.
     */
    protected abstract val descriptionNotFound: Translatable

    /**
     * Provides the translation for `and N such elements were found` when an item is not found in a
     * `toContain.atLeast|atMost|...`  check.
     */
    protected abstract val descriptionNumberOfElementsFound: Translatable

    final override fun createAssertionGroup(
        container: AssertionContainer<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        val multiConsumableContainer = makeSubjectMultipleTimesConsumable(container)
        val assertions = searchCriteria.map {
            LazyThreadUnsafeAssertionGroup {
                searchAndCreateAssertion(multiConsumableContainer, it, this::featureFactory)
            }
        }
        val description = searchBehaviour.decorateDescription(descriptionToContain)
        val inAnyOrderAssertion = assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(description)
            .withAssertions(assertions)
            .build()
        return decorateInAnyOrderAssertion(inAnyOrderAssertion, multiConsumableContainer)
    }

    /**
     * Override in a subclass if you want to decorate the assertion.
     */
    protected open fun decorateInAnyOrderAssertion(
        inAnyOrderAssertion: AssertionGroup,
        multiConsumableContainer: AssertionContainer<TT>
    ): AssertionGroup = inAnyOrderAssertion

    /**
     * Make the underlying subject multiple times consumable.
     */
    protected abstract fun makeSubjectMultipleTimesConsumable(container: AssertionContainer<T>): AssertionContainer<TT>

    /**
     * Searches for something fulfilling the given [searchCriterion] in the subject of this expectation associated with
     * the given [multiConsumableContainer] and should pass on the number of occurrences to the given
     * [featureFactory] which creates feature assertions based on the [checkers], which in turn can be used to create
     * a resulting [AssertionGroup] representing the assertion for a search criteria as a whole.
     *
     * @param multiConsumableContainer Provides the subject of this expectation for which the assertion is created.
     * @param searchCriterion A search criterion.
     * @param featureFactory The feature factory which should be called, passing the number of occurrences (matching
     *   the given [searchCriterion]) including a translation for `number of occurrences`.
     *
     * @return The newly created [AssertionGroup].
     */
    protected abstract fun searchAndCreateAssertion(
        multiConsumableContainer: AssertionContainer<TT>,
        searchCriterion: SC,
        featureFactory: (numberOfOccurrences: Int, description: Translatable) -> AssertionGroup
    ): AssertionGroup

    private fun featureFactory(count: Int, numberOfOccurrences: Translatable): AssertionGroup {
        val assertions = checkers.map { it.createAssertion(count) }
        val checker = checkers.firstOrNull()
        return if (checkers.size == 1 && checker is AtLeastChecker && checker.times == 1) {
            if (checker.createAssertion(count).holds()) {
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withExplanatoryAssertion(
                        TranslatableWithArgs(descriptionNumberOfElementsFound, count.toString())
                    )
                    .build()
            } else {
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withExplanatoryAssertion(descriptionNotFound)
                    .failing
                    .build()
            }
        } else {
            assertionBuilder.feature
                .withDescriptionAndRepresentation(numberOfOccurrences, Text(count.toString()))
                .withAssertions(assertions)
                .build()
        }
    }
}
