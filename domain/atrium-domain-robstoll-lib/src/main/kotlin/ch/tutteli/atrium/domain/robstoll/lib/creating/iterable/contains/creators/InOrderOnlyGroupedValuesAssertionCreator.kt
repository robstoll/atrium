package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.only
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

class InOrderOnlyGroupedValuesAssertionCreator<E : Any, in T : Iterable<E>>(
    private val searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : IterableContains.Creator<T, List<E>> {

    override fun createAssertionGroup(plant: AssertionPlant<T>, searchCriteria: List<List<E>>): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertion = AssertImpl.collector.collect({ plant.subject.toList() }) {
                var index = 0
                val sizeExceededProvider = { RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) }
                searchCriteria.forEach { values ->
                    val currentIndex = index
                    val untilIndex = index + values.size
                    if (values.size == 1) {
                        createEntryAssertion(currentIndex, sizeExceededProvider, values)
                    } else {
                        createSublistAssertion(currentIndex, untilIndex, sizeExceededProvider, values)
                    }
                    index = untilIndex
                }
                val remainingList = if (index < subject.size) subject.subList(index, subject.size) else listOf()
                addAssertion(createSizeFeatureAssertionForInOrderOnly(index, subject, remainingList.iterator()))
            }
            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertImpl.builder.summary(description).create(assertion)
        }
    }

    private fun CollectingAssertionPlant<List<E>>.createEntryAssertion(
        currentIndex: Int,
        sizeExceededProvider: () -> RawString,
        values: List<E>
    ) {
        val entryProvider = { this.subject[currentIndex] }
        val representationProvider = { subject.ifWithinBound(currentIndex, entryProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX, currentIndex)
        AssertImpl.feature.property(this, entryProvider, representationProvider, featureName) {
            addAssertion(AssertImpl.builder.descriptive.create(
                DescriptionAnyAssertion.TO_BE,
                values[0],
                {
                    this@createEntryAssertion.subject.ifWithinBound(currentIndex,
                        { entryProvider() == values[0] },
                        { false }
                    )
                }
            ))
        }
    }

    private fun CollectingAssertionPlant<List<E>>.createSublistAssertion(
        currentIndex: Int,
        untilIndex: Int,
        sizeExceededProvider: () -> RawString,
        values: List<E>
    ) {
        val subListProvider = {
            val safeUntilIndex = if (untilIndex < subject.size) untilIndex else subject.size
            subject.subList(currentIndex, safeUntilIndex)
        }.evalOnce()
        val representationProvider = { subject.ifWithinBound(currentIndex, subListProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, currentIndex, untilIndex - 1)
        AssertImpl.feature.property(this, subListProvider, representationProvider, featureName) {
            val inAnyOrderOnly = contains.inAnyOrder.only
            addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, values))
        }
    }
}
