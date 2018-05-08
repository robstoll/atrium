package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.only
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.creating.Assert
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
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E, E, T, E>(searchBehaviour) {

    override fun CollectingAssertionPlant<List<E>>.createSingleEntryAssertion(
        currentIndex: Int,
        sizeExceededProvider: () -> RawString,
        searchCriteria: List<E>
    ) {
        val entryProvider = { this.subject[currentIndex] }
        val representationProvider = { subject.ifWithinBound(currentIndex, entryProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX, currentIndex)
        AssertImpl.feature.property(this, entryProvider, representationProvider, featureName) {
            addAssertion(AssertImpl.builder.descriptive.create(
                DescriptionAnyAssertion.TO_BE,
                searchCriteria[0],
                {
                    this@createSingleEntryAssertion.subject.ifWithinBound(currentIndex,
                        { entryProvider() == searchCriteria[0] },
                        { false }
                    )
                }
            ))
        }
    }

    override fun Assert<List<E?>>.createSublistAssertion(searchCriteria: List<E>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(AssertImpl.iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, searchCriteria))
    }
}
