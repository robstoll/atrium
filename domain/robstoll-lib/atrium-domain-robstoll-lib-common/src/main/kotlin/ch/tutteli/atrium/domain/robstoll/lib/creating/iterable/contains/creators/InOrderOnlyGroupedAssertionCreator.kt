package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.core.evalOnce
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.CollectingAssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

abstract class InOrderOnlyGroupedAssertionCreator<E, in T : Iterable<E>, SC>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyBaseAssertionCreator<E, T, List<SC>>(searchBehaviour),
    //TODO use protected visibility once https://youtrack.jetbrains.com/issue/KT-24328 is implemented
    InOrderOnlyMatcher<E, SC> {

    final override fun CollectingAssertionContainer<List<E>>.createAssertionsAndReturnIndex(
        searchCriteria: List<List<SC>>
    ): Int {
        var index = 0
        searchCriteria.forEach { group ->
            val currentIndex = index
            val untilIndex = index + group.size
            if (group.size == 1) {
                createSingleEntryAssertion(currentIndex, group[0], DescriptionIterableAssertion.INDEX)
            } else {
                createSublistAssertion(currentIndex, untilIndex, group, maybeSubject.getOrElse { emptyList() })
            }
            index = untilIndex
        }
        return index
    }

    private fun CollectingAssertionContainer<List<E>>.createSublistAssertion(
        currentIndex: Int,
        untilIndex: Int,
        groupOfSearchCriteria: List<SC>,
        subject: List<E>
    ) {
        val subListProvider = {
            val safeUntilIndex = if (untilIndex < subject.size) untilIndex else subject.size
            subject.subList(currentIndex, safeUntilIndex)
        }.evalOnce()
        val sizeExceededProvider = { RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) }
        val representationProvider = { subject.ifWithinBound(currentIndex, subListProvider, sizeExceededProvider) }
        val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, currentIndex, untilIndex - 1)
        //TODO #40 use ExpectImpl once new feature mechanism is implemented
        AssertImpl.feature.property(this.asAssert(), subListProvider, representationProvider, featureName) {
            //TODO #40 remove asAssert() once we use ExpectImpl
            asExpect().
            createSublistAssertion(groupOfSearchCriteria)
        }
    }

    protected abstract fun Expect<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<SC>)
}
