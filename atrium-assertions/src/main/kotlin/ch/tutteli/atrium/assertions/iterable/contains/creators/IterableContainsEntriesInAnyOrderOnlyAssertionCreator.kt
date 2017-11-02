package ch.tutteli.atrium.assertions.iterable.contains.creators

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderOnlyDecorator
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Untranslatable

class IterableContainsEntriesInAnyOrderOnlyAssertionCreator<E : Any, T : Iterable<E>>(
    private val decorator: IterableContainsInAnyOrderOnlyDecorator
) : IIterableContains.ICreator<T, IAssertionPlant<E>.() -> Unit> {

    override fun createAssertionGroup(plant: IAssertionPlant<T>, expected: IAssertionPlant<E>.() -> Unit, otherExpected: Array<out IAssertionPlant<E>.() -> Unit>): IAssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toMutableList()
            val actualSize = list.size
            val assertions = mutableListOf<IAssertion>()
            val allExpected = listOf(expected, *otherExpected)
            val mismatches = createAssertionsForExpected(allExpected, list, assertions)
            val featureAssertions = createSizeFeatureAssertion(allExpected, actualSize)
            if (mismatches == 0 && list.isNotEmpty()) {
                featureAssertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchEtc(list, DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES)
                })
            }
            assertions.add(AssertionGroup(FeatureAssertionGroupType, Untranslatable(list::size.name), RawString(actualSize.toString()), featureAssertions))

            val description = decorator.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            val summary = AssertionGroup(SummaryAssertionGroupType, description, RawString(""), assertions)
            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> DescriptionIterableAssertion.WARNING_MISMATCHES
                    else -> DescriptionIterableAssertion.WARNING_MISMATCHES_ADDITIONAL_ENTRIES
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

    private fun createAssertionsForExpected(allExpected: List<IAssertionPlant<E>.() -> Unit>, list: MutableList<E>, assertions: MutableList<IAssertion>): Int {
        var mismatches = 0
        allExpected.forEach {
            val explanatoryAssertions = collectAssertionsForExplanation(it)
            val found: Boolean = removeMatch(list, it)
            if (!found) ++mismatches
            val fixHoldsAssertionGroup = FixHoldsAssertionGroup(ListAssertionGroupType, DescriptionIterableAssertion.AN_ENTRY_WHICH, RawString(""), explanatoryAssertions, found)
            assertions.add(fixHoldsAssertionGroup)
        }
        return mismatches
    }

    private fun removeMatch(list: MutableList<E>, createAssertions: IAssertionPlant<E>.() -> Unit): Boolean {
        val itr = list.iterator()
        while (itr.hasNext()) {
            val checkingPlant = AtriumFactory.newCheckingPlant(itr.next())
            checkingPlant.createAssertions()
            if (checkingPlant.allAssertionsHold()) {
                itr.remove()
                return true
            }
        }
        return false
    }

    private fun createExplanatoryGroupForMismatchEtc(list: MutableList<E>, warning: DescriptionIterableAssertion): ExplanatoryAssertionGroup {
        val assertions = list.map { ExplanatoryAssertion(it) }
        val additionalEntries = AssertionGroup(ListAssertionGroupType, warning, RawString(""), assertions)
        return ExplanatoryAssertionGroup(WarningAssertionGroupType, listOf(additionalEntries))
    }

    private fun createSizeFeatureAssertion(allExpected: List<IAssertionPlant<E>.() -> Unit>, actualSize: Int): MutableList<IAssertion>
        = mutableListOf(BasicAssertion(DescriptionAnyAssertion.TO_BE, RawString(allExpected.size.toString()), actualSize == allExpected.size))


    private fun <E : Any> collectAssertionsForExplanation(createAssertions: IAssertionPlant<E>.() -> Unit): List<IAssertion> {
        val collectingAssertionPlant = AtriumFactory.newCollectingPlant<E>()
        collectingAssertionPlant.createAssertions()
        val collectedAssertions = collectingAssertionPlant.getAssertions()
        if (collectedAssertions.isEmpty()) throw IllegalArgumentException("There was not any assertion created which could identify an entry. Specify at least one assertion")
        return collectedAssertions
    }
}
