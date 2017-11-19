package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable

class IterableContainsInAnyOrderOnlyObjectsAssertionCreator<E, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderOnlyDecorator
) : IIterableContains.ICreator<T, E> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: E, otherExpected: Array<out E>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toMutableList()
            val actualSize = list.size
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)
            findAndRemove(allExpected, assertions, list)
            assertions.add(createSizeFeatureAssertion(allExpected, actualSize, list))

            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions)
        }
    }

    private fun findAndRemove(allExpected: List<E>, assertions: MutableList<IAssertion>, list: MutableList<E>) {
        allExpected.forEach {
            val found: Boolean = list.remove(it)
            assertions.add(BasicAssertion(DescriptionIterableAssertion.AN_ENTRY_WHICH_IS, it ?: RawString.NULL, found))
        }
    }

    private fun createSizeFeatureAssertion(allExpected: List<E>, actualSize: Int, list: MutableList<E>): IAssertionGroup {
        val featureAssertions = mutableListOf<IAssertion>()
        featureAssertions.add(BasicAssertion(DescriptionAnyAssertion.TO_BE, RawString(allExpected.size.toString()), { actualSize == allExpected.size }))
        //TODO is not as accurate as `in any order entries` where I distinguish between additional entries and mismatches
        if (list.isNotEmpty()) {
            featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                val assertions = mutableListOf<IAssertion>()
                list.forEach {
                    assertions.add(ExplanatoryAssertion(it))
                }
                val additionalEntries = AssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES, RawString(""), assertions)
                ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(additionalEntries))
            })
        }
        return AssertionGroup(FeatureAssertionGroupType, Untranslatable(list::size.name), RawString(actualSize.toString()), featureAssertions.toList())
    }
}
