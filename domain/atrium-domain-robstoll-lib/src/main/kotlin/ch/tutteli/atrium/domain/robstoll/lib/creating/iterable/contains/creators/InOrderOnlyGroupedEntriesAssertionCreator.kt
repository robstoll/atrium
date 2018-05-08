package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.api.cc.en_GB.contains
import ch.tutteli.atrium.api.cc.en_GB.inAnyOrder
import ch.tutteli.atrium.api.cc.en_GB.only
import ch.tutteli.atrium.assertions.Assertion
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
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

class InOrderOnlyGroupedEntriesAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyGroupedAssertionCreator<E?, T, (AssertionPlant<E>.() -> Unit)?>(searchBehaviour),
    InOrderOnlyMatcher<E?, (AssertionPlant<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher()
{

    override fun Assert<List<E?>>.createSublistAssertion(groupOfSearchCriteria: List<(AssertionPlant<E>.() -> Unit)?>) {
        val inAnyOrderOnly = contains.inAnyOrder.only
        addAssertion(AssertImpl.iterable.contains.nullableEntriesInAnyOrderOnly(inAnyOrderOnly, groupOfSearchCriteria))
    }
}
