package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

abstract class IterableContainsInOrderOnlyAssertionCreator<E, T : Iterable<E>, T2>(
    private val decorator: IterableContainsInOrderOnlyDecorator
) : IIterableContains.ICreator<T, T2> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)
            val list = plant.subject.toList()
            val itr = list.iterator()
            allExpected.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(list, it, createEntryAssertionTemplate(itr, index, it)))
            }
            assertions.add(createSizeFeatureAssertion(allExpected.size, list, itr))


            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions.toList())
        }
    }

    abstract fun createEntryAssertion(iterableAsList: List<E>, expected: T2, template: ((Boolean) -> IAssertion) -> IAssertionGroup): IAssertionGroup

    private fun createEntryAssertionTemplate(itr: Iterator<E>, index: Int, expected: T2): ((Boolean) -> IAssertion) -> IAssertionGroup
        = { createEntryFeatureAssertion ->

        val (found, entry) = if (itr.hasNext()) {
            val actual = itr.next()
            Pair(holds(actual, expected), actual ?: RawString.NULL)
        } else {
            Pair(false, TranslatableRawString(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        AssertionGroup(FeatureAssertionGroupType, description, entry, listOf(
            createEntryFeatureAssertion(found)
        ))
    }

    abstract fun holds(actual: E, expected: T2): Boolean

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
