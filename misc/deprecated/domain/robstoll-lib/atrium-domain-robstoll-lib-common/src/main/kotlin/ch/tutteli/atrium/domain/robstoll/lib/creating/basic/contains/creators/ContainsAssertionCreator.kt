//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject].
 * @param SC The type of the search criteria.
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property checkers The [Contains.Checker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsAssertionCreator<in T : Any, TT : Any, in SC, C : Contains.Checker>(
    protected val searchBehaviour: Contains.SearchBehaviour,
    private val checkers: List<C>
) : Contains.Creator<T, SC> {

    /**
     * Provides the translation for `contains`.
     */
    protected abstract val descriptionContains: Translatable

    final override fun createAssertionGroup(
        subjectProvider: SubjectProvider<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        val transformedSubjectProvider = makeSubjectMultipleTimesConsumable(subjectProvider)
        val assertions = searchCriteria.map {
            LazyThreadUnsafeAssertionGroup {
                searchAndCreateAssertion(transformedSubjectProvider, it, this::featureFactory)
            }
        }
        val description = searchBehaviour.decorateDescription(descriptionContains)
        return assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(description)
            .withAssertions(assertions)
            .build()
    }

    /**
     * Make the underlying subject multiple times consumable.
     */
    protected abstract fun makeSubjectMultipleTimesConsumable(subjectProvider: SubjectProvider<T>): SubjectProvider<TT>

    /**
     * Searches for something fulfilling the given [searchCriterion] in the given [subjectProvider]'s
     * [subject][SubjectProvider.subject] and should pass on the number of occurrences to the given
     * [featureFactory] which creates feature assertions based on the [checkers], which in turn can be used to create
     * a resulting [AssertionGroup] representing the assertion for a search criteria as a whole.
     *
     * @param subjectProvider Provides the subject of the assertion for which the assertion is created.
     * @param searchCriterion A search criterion.
     * @param featureFactory The feature factory which should be called, passing the number of occurrences (matching
     *   the given [searchCriterion]) including a translation for `number of occurrences`.
     *
     * @return The newly created [AssertionGroup].
     */
    protected abstract fun searchAndCreateAssertion(
        subjectProvider: SubjectProvider<TT>,
        searchCriterion: SC,
        featureFactory: (numberOfOccurrences: Int, description: Translatable) -> AssertionGroup
    ): AssertionGroup

    private fun featureFactory(count: Int, numberOfOccurrences: Translatable): AssertionGroup {
        val assertions = checkers.map { it.createAssertion(count) }
        return assertionBuilder.feature
            .withDescriptionAndRepresentation(numberOfOccurrences, Text(count.toString()))
            .withAssertions(assertions)
            .build()
    }
}
