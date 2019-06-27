package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.CollectingAssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createSizeFeatureAssertionForInOrderOnly
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

abstract class InOrderOnlyGroupedAssertionCreator<E, in T : Iterable<E>, SC>(
    private val searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : IterableContains.Creator<T, List<SC>>,
    InOrderOnlyMatcher<E, SC> {

    override fun createAssertionGroup(
        plant: AssertionPlant<T>,
        searchCriteria: List<List<SC>>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertion = AssertImpl.collector.collectForSubject({ plant.subject.toList() }) {
                var index = 0
                searchCriteria.forEach { group ->
                    val currentIndex = index
                    val untilIndex = index + group.size
                    if (group.size == 1) {
                        createSingleEntryAssertion(currentIndex, group[0], DescriptionIterableAssertion.INDEX)
                    } else {
                        createSublistAssertion(currentIndex, untilIndex, group)
                    }
                    index = untilIndex
                }
                val remainingList = if (index < subject.size) subject.subList(index, subject.size) else listOf()
                addAssertion(createSizeFeatureAssertionForInOrderOnly(index, subject, remainingList.iterator()))
            }
            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertImpl.builder.summary
                .withDescription(description)
                .withAssertion(assertion)
                .build()
        }
    }

    private fun CollectingAssertionPlant<List<E>>.createSublistAssertion(
        currentIndex: Int,
        untilIndex: Int,
        groupOfSearchCriteria: List<SC>
    ) {
        val subListProvider = {
            val safeUntilIndex = if (untilIndex < subject.size) untilIndex else subject.size
            subject.subList(currentIndex, safeUntilIndex)
        }.evalOnce()
        val sizeExceededProvider = { RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) }
        val representationProvider = { subject.ifWithinBound(currentIndex, subListProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, currentIndex, untilIndex - 1)
        AssertImpl.feature.property(this, subListProvider, representationProvider, featureName) {
            subject
            createSublistAssertion(groupOfSearchCriteria)
        }
    }

    protected abstract fun Assert<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<SC>)
}
