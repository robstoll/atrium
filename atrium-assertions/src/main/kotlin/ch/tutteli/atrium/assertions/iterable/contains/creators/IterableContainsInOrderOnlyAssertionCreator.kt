package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Represents the base class for `in order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param S The type of the search criterion.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
abstract class IterableContainsInOrderOnlyAssertionCreator<E, T : Iterable<E?>, S>(
    private val searchBehaviour: IterableContainsInOrderOnlySearchBehaviour
) : IterableContains.Creator<T, S> {

    override final fun createAssertionGroup(plant: AssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<Assertion>()
            val allSearchCriteria = listOf(searchCriterion, *otherSearchCriteria)
            val list = plant.subject.toList()
            val itr = list.iterator()
            allSearchCriteria.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(list, it, createEntryAssertionTemplate(itr, index, it)))
            }
            assertions.add(createSizeFeatureAssertion(allSearchCriteria.size, list, itr))


            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup.Builder.summary.create(description, RawString.EMPTY, assertions.toList())
        }
    }

    abstract fun createEntryAssertion(iterableAsList: List<E?>, searchCriterion: S, template: ((Boolean) -> Assertion) -> AssertionGroup): AssertionGroup

    private fun createEntryAssertionTemplate(itr: Iterator<E?>, index: Int, searchCriterion: S): ((Boolean) -> Assertion) -> AssertionGroup
        = { createEntryFeatureAssertion ->

        val (found, entryRepresentation) = if (itr.hasNext()) {
            val entry = itr.next()
            Pair(matches(entry, searchCriterion), entry ?: RawString.NULL)
        } else {
            Pair(false, RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        AssertionGroup.Builder.feature.create(description, entryRepresentation, createEntryFeatureAssertion(found))
    }

    abstract fun matches(actual: E?, searchCriterion: S): Boolean

    private fun createSizeFeatureAssertion(expectedSize: Int, iterableAsList: List<E?>, itr: Iterator<E?>): AssertionGroup {
        val additionalEntries = mutableListOf<E?>()
        val actualSize = iterableAsList.size
        while (itr.hasNext()) {
            additionalEntries.add(itr.next())
        }
        val featureAssertions = mutableListOf<Assertion>()
        featureAssertions.add(BasicDescriptiveAssertion(DescriptionAnyAssertion.TO_BE, RawString.create(expectedSize.toString()), { actualSize == expectedSize }))
        if (actualSize > expectedSize) {
            featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                val assertions = additionalEntries.mapIndexed { index, it ->
                    val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, expectedSize + index)
                    BasicDescriptiveAssertion(description, it ?: RawString.NULL, true)
                }
                AssertionGroup.Builder.explanatory.withWarning.create(
                    AssertionGroup.Builder.list.create(DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES, RawString.EMPTY, assertions)
                )
            })
        }
        return AssertionGroup.Builder.feature.create(Untranslatable(additionalEntries::size.name), RawString.create(actualSize.toString()), featureAssertions.toList())
    }
}
