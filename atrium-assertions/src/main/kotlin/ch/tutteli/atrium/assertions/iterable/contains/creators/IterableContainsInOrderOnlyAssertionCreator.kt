package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInOrderOnlySearchBehaviour
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

abstract class IterableContainsInOrderOnlyAssertionCreator<E, T : Iterable<E>, S>(
    private val decorator: IterableContainsInOrderOnlySearchBehaviour
) : IIterableContains.ICreator<T, S> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, searchCriterion: S, otherSearchCriteria: Array<out S>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<IAssertion>()
            val allSearchCriteria = listOf(searchCriterion, *otherSearchCriteria)
            val list = plant.subject.toList()
            val itr = list.iterator()
            allSearchCriteria.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(list, it, createEntryAssertionTemplate(itr, index, it)))
            }
            assertions.add(createSizeFeatureAssertion(allSearchCriteria.size, list, itr))


            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions.toList())
        }
    }

    abstract fun createEntryAssertion(iterableAsList: List<E>, searchCriterion: S, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup

    private fun createEntryAssertionTemplate(itr: Iterator<E>, index: Int, searchCriterion: S): ((Boolean) -> IAssertion) -> IAssertionGroup
        = { createEntryFeatureAssertion ->

        val (found, entryRepresentation) = if (itr.hasNext()) {
            val entry = itr.next()
            Pair(matches(entry, searchCriterion), entry ?: RawString.NULL)
        } else {
            Pair(false, TranslatableRawString(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        AssertionGroup(FeatureAssertionGroupType, description, entryRepresentation, listOf(
            createEntryFeatureAssertion(found)
        ))
    }

    abstract fun matches(actual: E, searchCriterion: S): Boolean

    private fun createSizeFeatureAssertion(expectedSize: Int, iterableAsList: List<E>, itr: Iterator<E>): IAssertionGroup {
        val additionalEntries = mutableListOf<E>()
        val actualSize = iterableAsList.size
        while (itr.hasNext()) {
            additionalEntries.add(itr.next())
        }
        val featureAssertions = mutableListOf<IAssertion>()
        featureAssertions.add(BasicAssertion(DescriptionAnyAssertion.TO_BE, RawString(expectedSize.toString()), { actualSize == expectedSize }))
        if (actualSize > expectedSize) {
            featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                val assertions = additionalEntries.mapIndexed { index, it ->
                    val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, expectedSize + index)
                    BasicAssertion(description, it ?: RawString.NULL, true)
                }
                ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(
                    AssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES, RawString(""), assertions)
                ))
            })
        }
        return AssertionGroup(FeatureAssertionGroupType, Untranslatable(additionalEntries::size.name), RawString(actualSize.toString()), featureAssertions.toList())
    }
}
