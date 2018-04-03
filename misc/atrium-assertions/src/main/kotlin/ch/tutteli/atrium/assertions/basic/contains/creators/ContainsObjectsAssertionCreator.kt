package ch.tutteli.atrium.assertions.basic.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.basic.contains.Contains
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 * with `==`).
 *
 * It provides a template to fulfill the job of creating the sophisticated `contains` assertion.
 *
 * @param T The type of the [AssertionPlant.subject].
 * @param S The type of the search criteria.
 * @param B The type of the current [Contains.SearchBehaviour].
 * @param C The type of the checkers in use (typically a sub interface of [Contains.Checker]).
 *
 * @property searchBehaviour The chosen search behaviour.
 *
 * @constructor Represents the base class for [Contains.Creator]s which use bare objects as search criteria (matching them
 *   with `==`).
 * @param searchBehaviour The chosen search behaviour.
 * @param checkers The [Contains.Checker]s which shall be applied to the search result.
 */
@Deprecated("Please open an issue if you used this class, will be removed with 1.0.0")
abstract class ContainsObjectsAssertionCreator<T : Any, S, B : ch.tutteli.atrium.domain.creating.basic.contains.Contains.SearchBehaviour, C : ch.tutteli.atrium.domain.creating.basic.contains.Contains.Checker>(
    private val searchBehaviour: B,
    checkers: List<C>
) : ContainsAssertionCreator<T, S, C>(checkers) {

    final override fun createAssertionGroupForSearchCriteriaAssertions(assertions: List<Assertion>): AssertionGroup
        = AssertImpl.builder.invisibleGroup.create(assertions)

    final override fun searchAndCreateAssertion(plant: AssertionPlant<T>, searchCriterion: S, featureFactory: (Int, Translatable) -> AssertionGroup): AssertionGroup {
        val count = search(plant, searchCriterion)
        val featureAssertion = featureFactory(count, descriptionNumberOfOccurrences)
        val description = searchBehaviour.decorateDescription(descriptionContains)
        return AssertImpl.builder
            .list(description, searchCriterion ?: RawString.NULL)
            .create(featureAssertion)
    }

    /**
     * Provides the translation for `contains`.
     */
    protected abstract val descriptionContains: Translatable

    /**
     * Provides the translation for `number of occurrences`.
     */
    protected abstract val descriptionNumberOfOccurrences: Translatable

    /**
     * Searches for something matching the given [searchCriterion] in the given [plant]'s
     * [subject][AssertionPlant.subject] and returns the number of occurrences.
     *
     * @param plant The plant or rather its [subject][AssertionPlant.subject] in which we shall look for something
     *   matching the given [searchCriterion].
     * @param searchCriterion The search criterion used to determine whether something matches or not.
     *
     * @return The number of times the [searchCriterion] matched in the [plant]'s [subject][AssertionPlant.subject].
     */
    protected abstract fun search(plant: AssertionPlant<T>, searchCriterion: S): Int
}
