package ch.tutteli.atrium.assertions.basic.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.StringBasedRawString
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 *
 * @param T The type of the [IAssertionPlant.subject].
 * @param S The type of the search criteria.
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property checkers The [Contains.Checker]s which shall be applied to the search result.
 *
 * @constructor Represents the base class for [Contains.Creator]s, providing a template to fulfill its job.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
abstract class ContainsAssertionCreator<T : Any, S, C : Contains.Checker>(
    private val checkers: List<C>
) : Contains.Creator<T, S> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): IAssertionGroup {
        val assertions = listOf(searchCriterion, *otherSearchCriteria).map { createForSearchCriterion(plant, it) }
        return createAssertionGroupForSearchCriteriaAssertions(assertions)
    }

    /**
     * Creates an [IAssertionGroup] representing the sophisticated `contains` assertion as a whole based on the given
     * [assertions] which where created for the search criteria.
     *
     * @param assertions The assertions representing search criteria passed to [createAssertionGroup].
     *
     * @return The newly created [IAssertionGroup].
     */
    protected abstract fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<IAssertion>): IAssertionGroup

    private fun createForSearchCriterion(plant: IAssertionPlant<T>, searchCriterion: S): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            searchAndCreateAssertion(plant, searchCriterion, this::featureFactory)
        }
    }

    /**
     * Searches for something fulfilling the given [searchCriterion] in the given [plant]'s
     * [subject][IAssertionPlant.subject] and should pass on the number of occurrences to the given
     * [featureFactory] which creates feature assertions based on the [checkers], which in turn can be used to create
     * a resulting [IAssertionGroup] representing the assertion for a search criteria as a whole.
     *
     * @param plant The plant for which the assertion is created.
     * @param searchCriterion A search criterion.
     * @param featureFactory The feature factory which should be called, passing the number of occurrences (matching
     *        the given [searchCriterion]) including a translation for `number of occurrences`.
     *
     * @return The newly created [IAssertionGroup].
     */
    protected abstract fun searchAndCreateAssertion(
        plant: IAssertionPlant<T>,
        searchCriterion: S,
        featureFactory: (numberOfOccurrences: Int, description: ITranslatable) -> IAssertionGroup
    ): IAssertionGroup

    private fun featureFactory(count: Int, numberOfOccurrences: ITranslatable): IAssertionGroup {
        val assertions = checkers.map { it.createAssertion(count) }
        return AssertionGroup(FeatureAssertionGroupType, numberOfOccurrences, RawString.create(count.toString()), assertions)
    }

}
