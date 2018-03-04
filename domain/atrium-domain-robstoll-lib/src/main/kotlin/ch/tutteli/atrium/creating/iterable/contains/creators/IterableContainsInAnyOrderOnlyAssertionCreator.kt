package ch.tutteli.atrium.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.*

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
abstract class IterableContainsInAnyOrderOnlyAssertionCreator<E, in T : Iterable<E?>, S>(
    private val searchBehaviour: InAnyOrderOnlySearchBehaviour
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
            assertions.add(AssertionBuilder.feature.create(Untranslatable(list::size.name), RawString.create(actualSize.toString()), featureAssertions))

            val description = searchBehaviour.decorateDescription(CONTAINS)
            val summary = AssertionBuilder.summary.create(description, RawString.EMPTY, assertions.toList())
            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> WARNING_MISMATCHES
                    else -> WARNING_MISMATCHES_ADDITIONAL_ENTRIES
                }
                AssertionBuilder.invisibleGroup.create(listOf(
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
        = mutableListOf(
        AssertionBuilder.descriptive.create(
            DescriptionAnyAssertion.TO_BE,
            RawString.create(allSearchCriteria.size.toString()),
            { actualSize == allSearchCriteria.size }
        ))

    private fun createExplanatoryGroupForMismatchesEtc(list: MutableList<E?>, warning: DescriptionIterableAssertion): AssertionGroup {
        val assertions = list.map { AssertionBuilder.explanatory.create(it) }
        val additionalEntries = AssertionBuilder.list.create(warning, RawString.EMPTY, assertions)
        return AssertionBuilder.explanatoryGroup.withWarning.create(additionalEntries)
    }
}
