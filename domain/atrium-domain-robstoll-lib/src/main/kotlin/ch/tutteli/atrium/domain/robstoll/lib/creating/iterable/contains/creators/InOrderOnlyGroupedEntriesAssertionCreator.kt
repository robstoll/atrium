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
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

class InOrderOnlyGroupedEntriesAssertionCreator<E : Any, in T : Iterable<E?>>(
    private val searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : IterableContains.Creator<T, List<(AssertionPlant<E>.() -> Unit)?>> {

    override fun createAssertionGroup(
        plant: AssertionPlant<T>,
        searchCriteria: List<List<(AssertionPlant<E>.() -> Unit)?>>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertion = AssertImpl.collector.collect({ plant.subject.toList() }) {
                var index = 0
                val sizeExceededProvider = { RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) }
                searchCriteria.forEach { assertionCreators ->
                    val currentIndex = index
                    val untilIndex = index + assertionCreators.size
                    if (assertionCreators.size == 1) {
                        createSingleEntryAssertion(currentIndex, sizeExceededProvider, assertionCreators)
                    } else {
                        createSublistAssertion(currentIndex, untilIndex, sizeExceededProvider, assertionCreators)
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

    private fun CollectingAssertionPlant<List<E?>>.createSingleEntryAssertion(
        currentIndex: Int,
        sizeExceededProvider: () -> RawString,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ) {
        val list = this@createSingleEntryAssertion.subject
        val entryProvider = { this.subject[currentIndex] }
            val searchCriterion = assertionCreators[0]
            val explanatoryAssertions = createExplanatoryAssertions(searchCriterion, list)

            val (found, entryRepresentation) = list.ifWithinBound(currentIndex,
                {
                    val entry = entryProvider()
                    allCreatedAssertionsHold(entry, searchCriterion) to (entry ?: RawString.NULL)
                },
                { false to sizeExceededProvider() }
            )
            val description = TranslatableWithArgs(DescriptionIterableAssertion.INDEX, currentIndex)
            addAssertion(AssertImpl.builder
                .feature(description, entryRepresentation)
                .create(createEntryAssertion(explanatoryAssertions, found)))
    }

    private fun CollectingAssertionPlant<List<E?>>.createSublistAssertion(
        currentIndex: Int,
        untilIndex: Int,
        sizeExceededProvider: () -> RawString,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ) {
        val subListProvider = {
            val safeUntilIndex = if (untilIndex < subject.size) untilIndex else subject.size
            subject.subList(currentIndex, safeUntilIndex)
        }.evalOnce()
        val representationProvider = { subject.ifWithinBound(currentIndex, subListProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, currentIndex, untilIndex - 1)
        AssertImpl.feature.property(this, subListProvider, representationProvider, featureName) {
            val inAnyOrderOnly = contains.inAnyOrder.only
            addAssertion(AssertImpl.iterable.contains.nullableEntriesInAnyOrderOnly(inAnyOrderOnly, assertionCreators))
        }
    }
}
