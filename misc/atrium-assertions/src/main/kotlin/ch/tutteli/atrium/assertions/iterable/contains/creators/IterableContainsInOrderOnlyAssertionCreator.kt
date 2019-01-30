@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.iterable.contains.IterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the base class for `in order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
@Deprecated("Please open an issue if you used this class; will be removed with 1.0.0")
abstract class IterableContainsInOrderOnlyAssertionCreator<E, T : Iterable<E?>, SC>(
    private val searchBehaviour: IterableContainsInOrderOnlySearchBehaviour
) : IterableContains.Creator<T, SC> {

    fun createAssertionGroup(plant: AssertionPlant<T>, searchCriterion: SC, otherSearchCriteria: Array<out SC>)
        = createAssertionGroup(plant, listOf(searchCriterion, *otherSearchCriteria))

    final override fun createAssertionGroup(plant: AssertionPlant<T>, searchCriteria: List<SC>): AssertionGroup {

        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<Assertion>()
            val list = plant.subject.toList()
            val itr = list.iterator()
            searchCriteria.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(list, it, createEntryAssertionTemplate(itr, index, it)))
            }
            assertions.add(createSizeFeatureAssertion(searchCriteria.size, list, itr))

            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertImpl.builder.summary
                .withDescription(description)
                .withAssertions(assertions)
                .build()
        }
    }

    abstract fun createEntryAssertion(iterableAsList: List<E?>, searchCriterion: SC, template: ((Boolean) -> Assertion) -> AssertionGroup): AssertionGroup

    private fun createEntryAssertionTemplate(itr: Iterator<E?>, index: Int, searchCriterion: SC): ((Boolean) -> Assertion) -> AssertionGroup
        = { createEntryFeatureAssertion ->

        val (found, entryRepresentation) = if (itr.hasNext()) {
            val entry = itr.next()
            Pair(matches(entry, searchCriterion), entry ?: RawString.NULL)
        } else {
            Pair(false, RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        AssertImpl.builder.feature
            .withDescriptionAndRepresentation(description, entryRepresentation)
            .withAssertion(createEntryFeatureAssertion(found))
            .build()
    }

    abstract fun matches(actual: E?, searchCriterion: SC): Boolean

    private fun createSizeFeatureAssertion(expectedSize: Int, iterableAsList: List<E?>, itr: Iterator<E?>): AssertionGroup {
        val additionalEntries = mutableListOf<E?>()
        val actualSize = iterableAsList.size
        while (itr.hasNext()) {
            additionalEntries.add(itr.next())
        }

        val sizeAssertion = AssertImpl.builder.descriptive
            .withTest { actualSize == expectedSize }
            .withDescriptionAndRepresentation(DescriptionAnyAssertion.TO_BE, RawString.create(expectedSize.toString()))
            .build()

        val featureAssertions = mutableListOf<Assertion>()
        featureAssertions.add(sizeAssertion)
        if (actualSize > expectedSize) {
            featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                val assertions = additionalEntries.mapIndexed { index, it ->
                    val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, expectedSize + index)
                    AssertImpl.builder.descriptive
                        .holding
                        .withDescriptionAndRepresentation(description, it)
                        .build()
                }
                val warningAdditionalAssertions = AssertImpl.builder.list
                    .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES)
                    .withAssertions(assertions)
                    .build()
                AssertImpl.builder.explanatoryGroup
                    .withWarningType
                    .withAssertion(warningAdditionalAssertions)
                    .build()
            })
        }
        return AssertImpl.builder.feature
            .withDescriptionAndRepresentation(Untranslatable(additionalEntries::size.name), RawString.create(actualSize.toString()))
            .withAssertions(featureAssertions)
            .build()
    }
}
