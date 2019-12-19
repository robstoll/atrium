package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createSizeFeatureAssertionForInOrderOnly
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.ifWithinBound

abstract class InOrderOnlyBaseAssertionCreator<E, in T : Iterable<E>, SC>(
    private val searchBehaviour: IterableContains.SearchBehaviour
) : IterableContains.Creator<T, SC> {

    final override fun createAssertionGroup(
        subjectProvider: SubjectProvider<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val subject = turnSubjectToList(subjectProvider).maybeSubject.getOrElse { emptyList() }
            val assertion = assertionCollector.collect(Some(subject)) {
                val index = createAssertionsAndReturnIndex(searchCriteria)
                val remainingList = subject.ifWithinBound(index,
                    { subject.subList(index, subject.size) },
                    { emptyList() }
                )
                addAssertion(createSizeFeatureAssertionForInOrderOnly(index, subject, remainingList.iterator()))
            }
            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            assertionBuilder.summary
                .withDescription(description)
                .withAssertion(assertion)
                .build()
        }
    }

    protected abstract fun Expect<List<E>>.createAssertionsAndReturnIndex(searchCriteria: List<SC>): Int
}
