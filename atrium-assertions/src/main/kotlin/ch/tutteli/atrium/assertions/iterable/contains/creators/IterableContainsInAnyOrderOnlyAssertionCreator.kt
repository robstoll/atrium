package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.*
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents the base class for `in any order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param S The type of the search criterion.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
abstract class IterableContainsInAnyOrderOnlyAssertionCreator<E, T : Iterable<E?>, S>(
    private val searchBehaviour: IterableContainsInAnyOrderOnlySearchBehaviour
) : IterableContains.Creator<T, S> {

    final override fun createAssertionGroup(plant: AssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toMutableList()
            val actualSize = list.size
            val assertions = mutableListOf<Assertion>()
            val allSearchCriteria = listOf(searchCriterion, *otherSearchCriteria)

            val mismatches = createAssertionsForAllSearchCriteria(allSearchCriteria, list, assertions)
            val featureAssertions = createSizeFeatureAssertion(allSearchCriteria, actualSize)
            if (mismatches == 0 && list.isNotEmpty()) {
                featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchesEtc(list, WARNING_ADDITIONAL_ENTRIES)
                })
            }
            assertions.add(AssertionGroupBuilder.feature.create(Untranslatable(list::size.name), RawString.create(actualSize.toString()), featureAssertions))

            val description = searchBehaviour.decorateDescription(CONTAINS)
            val summary = AssertionGroupBuilder.summary.create(description, RawString.EMPTY, assertions.toList())
            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> WARNING_MISMATCHES
                    else -> WARNING_MISMATCHES_ADDITIONAL_ENTRIES
                }
                AssertionGroupBuilder.invisible.create(listOf(
                    summary,
                    createExplanatoryGroupForMismatchesEtc(list, warningDescription)
                ))
            } else {
                summary
            }
        }
    }

    private fun createAssertionsForAllSearchCriteria(allSearchCriteria: List<S>, list: MutableList<E?>, assertions: MutableList<Assertion>): Int {
        var mismatches = 0
        allSearchCriteria.forEach {
            val (found, assertion) = createAssertionForSearchCriterionAndRemoveMatchFromList(it, list)
            if (!found) ++mismatches
            assertions.add(assertion)
        }
        return mismatches
    }

    protected abstract fun createAssertionForSearchCriterionAndRemoveMatchFromList(searchCriterion: S, list: MutableList<E?>): Pair<Boolean, Assertion>

    private fun createSizeFeatureAssertion(allSearchCriteria: List<S>, actualSize: Int): MutableList<Assertion>
        = mutableListOf(BasicDescriptiveAssertion(DescriptionAnyAssertion.TO_BE, RawString.create(allSearchCriteria.size.toString()), { actualSize == allSearchCriteria.size }))

    private fun createExplanatoryGroupForMismatchesEtc(list: MutableList<E?>, warning: DescriptionIterableAssertion): ExplanatoryAssertionGroup {
        val assertions = list.map { BasicExplanatoryAssertion(it) }
        val additionalEntries = AssertionGroupBuilder.list.create(warning, RawString.EMPTY, assertions)
        return AssertionGroupBuilder.explanatory.withWarning.create(additionalEntries)
    }
}
