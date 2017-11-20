package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.DescriptionIterableAssertion.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable

abstract class IterableContainsInAnyOrderOnlyAssertionCreator<E, T : Iterable<E>, T2>(
    private val decorator: IterableContainsInAnyOrderOnlyDecorator
) : IIterableContains.ICreator<T, T2> {

    override final fun createAssertionGroup(plant: IAssertionPlant<T>, expected: T2, otherExpected: Array<out T2>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toMutableList()
            val actualSize = list.size
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)

            val mismatches = createAssertionsForExpected(allExpected, list, assertions)
            val featureAssertions = createSizeFeatureAssertion(allExpected, actualSize)
            if (mismatches == 0 && list.isNotEmpty()) {
                featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchEtc(list, WARNING_ADDITIONAL_ENTRIES)
                })
            }
            assertions.add(AssertionGroup(FeatureAssertionGroupType, Untranslatable(list::size.name), RawString(actualSize.toString()), featureAssertions))

            val description = decorator.decorateDescription(CONTAINS)
            val summary = AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions.toList())
            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> WARNING_MISMATCHES
                    else -> WARNING_MISMATCHES_ADDITIONAL_ENTRIES
                }
                InvisibleAssertionGroup(listOf(
                    summary,
                    createExplanatoryGroupForMismatchEtc(list, warningDescription)
                ))
            } else {
                summary
            }
        }
    }

    private fun createAssertionsForExpected(allExpected: List<T2>, list: MutableList<E>, assertions: MutableList<IAssertion>): Int {
        var mismatches = 0
        allExpected.forEach {
            val (found, assertion) = createAssertionForExpectedAndRemoveMatchFromList(it, list)
            if (!found) ++mismatches
            assertions.add(assertion)
        }
        return mismatches
    }

    protected abstract fun createAssertionForExpectedAndRemoveMatchFromList(expected: T2, list: MutableList<E>): Pair<Boolean, IAssertion>

    private fun createSizeFeatureAssertion(allExpected: List<T2>, actualSize: Int): MutableList<IAssertion>
        = mutableListOf(BasicAssertion(DescriptionAnyAssertion.TO_BE, RawString(allExpected.size.toString()), { actualSize == allExpected.size }))

    private fun createExplanatoryGroupForMismatchEtc(list: MutableList<E>, warning: DescriptionIterableAssertion): ExplanatoryAssertionGroup {
        val assertions = list.map { ExplanatoryAssertion(it) }
        val additionalEntries = AssertionGroup(ListAssertionGroupType, warning, RawString(""), assertions)
        return ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(additionalEntries))
    }
}
