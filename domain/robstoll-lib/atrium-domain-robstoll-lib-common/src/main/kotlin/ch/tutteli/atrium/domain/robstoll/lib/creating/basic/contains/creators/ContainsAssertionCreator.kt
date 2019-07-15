package ch.tutteli.atrium.domain.robstoll.lib.creating.basic.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.basic.contains.Contains
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param SC The type of the search criteria.
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property checkers The [Contains.Checker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsAssertionCreator<in T : Any, in SC, C : Contains.Checker>(
    protected val searchBehaviour: Contains.SearchBehaviour,
    private val checkers: List<C>
) : Contains.Creator<T, SC> {

    final override fun createAssertionGroup(subjectProvider: SubjectProvider<T>, searchCriteria: List<SC>): AssertionGroup {
        val assertions = searchCriteria.map {
            LazyThreadUnsafeAssertionGroup { searchAndCreateAssertion(subjectProvider, it, this::featureFactory) }
        }
        val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
        return AssertImpl.builder.list
            .withDescriptionAndEmptyRepresentation(description)
            .withAssertions(assertions)
            .build()
    }

    /**
     * Searches for something fulfilling the given [searchCriterion] in the given [subjectProvider]'s
     * [subject][AssertionPlant.subject] and should pass on the number of occurrences to the given
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
        subjectProvider: SubjectProvider<T>,
        searchCriterion: SC,
        featureFactory: (numberOfOccurrences: Int, description: Translatable) -> AssertionGroup
    ): AssertionGroup

    private fun featureFactory(count: Int, numberOfOccurrences: Translatable): AssertionGroup {
        val assertions = checkers.map { it.createAssertion(count) }
        return AssertImpl.builder.feature
            .withDescriptionAndRepresentation(numberOfOccurrences, RawString.create(count.toString()))
            .withAssertions(assertions)
            .build()
    }
}
