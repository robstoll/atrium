package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

class IterableContainsInOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    private val decorator: IterableContainsInOrderOnlyDecorator
) : IIterableContains.ICreator<T, E> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)
            val list = plant.subject.toList()
            val itr = list.iterator()
            allExpected.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(itr, index, it))
            }
            assertions.add(createSizeFeatureAssertion(allExpected.size, list.size, itr))

            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions.toList())
        }
    }

    private fun createEntryAssertion(itr: Iterator<E>, index: Int, expected: E): AssertionGroup {
        val (found, entry) = if (itr.hasNext()) {
            val actual = itr.next()
            Pair(actual == expected, actual ?: RawString.NULL)
        } else {
            Pair(false, TranslatableRawString(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        return AssertionGroup(FeatureAssertionGroupType, description, entry, listOf(
            BasicAssertion(DescriptionAnyAssertion.TO_BE, expected ?: RawString.NULL, found)
        ))
    }

    private fun createSizeFeatureAssertion(expectedSize: Int, actualSize: Int, itr: Iterator<E>): IAssertionGroup {
        val list = mutableListOf<E>()
        while (itr.hasNext()) {
            list.add(itr.next())
        }
        val featureAssertions = mutableListOf<IAssertion>()
        featureAssertions.add(BasicAssertion(DescriptionAnyAssertion.TO_BE, RawString(expectedSize.toString()), { actualSize == expectedSize }))
        if (actualSize > expectedSize) {
            featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                val assertions = list.mapIndexed { index, it ->
                    val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, expectedSize + index)
                    BasicAssertion(description, it ?: RawString.NULL, true)
                }
                val additionalEntries = AssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES, RawString(""), assertions)
                ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(additionalEntries))
            })
        }
        return AssertionGroup(FeatureAssertionGroupType, Untranslatable(list::size.name), RawString(actualSize.toString()), featureAssertions.toList())
    }
}
