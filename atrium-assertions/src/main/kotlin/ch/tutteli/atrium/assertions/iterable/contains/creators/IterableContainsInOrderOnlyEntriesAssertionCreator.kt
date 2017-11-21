package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableRawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable

class IterableContainsInOrderOnlyEntriesAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInOrderOnlyDecorator
) : IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: IAssertionPlant<E>.() -> Unit, otherExpected: Array<out IAssertionPlant<E>.() -> Unit>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)
            val list = plant.subject.toList()
            val itr = list.iterator()
            allExpected.forEachIndexed { index, it ->
                assertions.add(createEntryAssertion(itr, index, it, list))
            }
            assertions.add(createSizeFeatureAssertion(allExpected.size, list, itr))

            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions.toList())
        }
    }

    private fun createEntryAssertion(itr: Iterator<E>, index: Int, assertionCreator: IAssertionPlant<E>.() -> Unit, iterableAsList: List<E>): AssertionGroup {
        val explanatoryAssertions = createExplanatoryAssertions(assertionCreator, iterableAsList)
        val (found, entry) = if (itr.hasNext()) {
            val actual = itr.next()
            Pair(allCreatedAssertionsHold(actual, assertionCreator), actual)
        } else {
            Pair(false, TranslatableRawString(DescriptionIterableAssertion.SIZE_EXCEEDED))
        }
        val description = TranslatableWithArgs(DescriptionIterableAssertion.ENTRY_WITH_INDEX, index)
        return AssertionGroup(FeatureAssertionGroupType, description, entry, listOf(
            createEntryAssertion(explanatoryAssertions, found)
        ))
    }

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
                    BasicAssertion(description, it, true)
                }
                ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(
                    AssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES, RawString(""), assertions)
                ))
            })
        }
        return AssertionGroup(FeatureAssertionGroupType, Untranslatable(additionalEntries::size.name), RawString(actualSize.toString()), featureAssertions.toList())
    }
}
