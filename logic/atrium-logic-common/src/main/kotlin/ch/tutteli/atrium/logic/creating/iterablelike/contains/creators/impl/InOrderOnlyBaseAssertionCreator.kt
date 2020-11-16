package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.creating.iterablelike.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.size
import ch.tutteli.atrium.logic.toBe
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.identity
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.mapRemainingWithCounter

abstract class InOrderOnlyBaseAssertionCreator<E, T : IterableLike, SC>(
    private val converter: (T) -> Iterable<E>,
    private val searchBehaviour: IterableLikeContains.SearchBehaviour
) : IterableLikeContains.Creator<T, SC> {

    final override fun createAssertionGroup(
        container: AssertionContainer<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val subject = turnSubjectToList(container, converter).maybeSubject.getOrElse { emptyList() }
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


    private fun <E> createSizeFeatureAssertionForInOrderOnly(
        expectedSize: Int,
        iterableAsList: List<E?>,
        itr: Iterator<E?>
    ): Assertion {
        return assertionCollector.collect(Some(iterableAsList)) {
            _logic.size(::identity).collectAndAppend {
                _logicAppend { toBe(expectedSize) }
                if (iterableAsList.size > expectedSize) {
                    addAssertion(LazyThreadUnsafeAssertionGroup {
                        val additionalEntries = itr.mapRemainingWithCounter { counter, it ->
                            val description = TranslatableWithArgs(
                                DescriptionIterableAssertion.ENTRY_WITH_INDEX,
                                expectedSize + counter
                            )
                            assertionBuilder.descriptive
                                .holding
                                .withDescriptionAndRepresentation(description, it)
                                .build()
                        }

                        assertionBuilder.explanatoryGroup
                            .withWarningType
                            .withAssertion(
                                assertionBuilder.list
                                    .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.WARNING_ADDITIONAL_ENTRIES)
                                    .withAssertions(additionalEntries)
                                    .build()
                            )
                            .build()
                    })
                }
            }
        }
    }

    protected abstract fun Expect<List<E>>.createAssertionsAndReturnIndex(searchCriteria: List<SC>): Int
}
