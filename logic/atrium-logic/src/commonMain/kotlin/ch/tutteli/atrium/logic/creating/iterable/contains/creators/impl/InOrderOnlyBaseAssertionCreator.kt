//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.impl.InOrderOnlyReportingOptionsImpl
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.WARNING_ADDITIONAL_ELEMENTS
import ch.tutteli.kbox.ifWithinBound
import ch.tutteli.kbox.mapRemainingWithCounter

abstract class InOrderOnlyBaseAssertionCreator<E, T : IterableLike, SC>(
    private val converter: (T) -> Iterable<E>,
    private val searchBehaviour: IterableLikeContains.SearchBehaviour,
    private val reportingOptions: InOrderOnlyReportingOptions.() -> Unit
) : IterableLikeContains.Creator<T, SC> {

    final override fun createAssertionGroup(
        container: AssertionContainer<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val maybeList = container.extractSubjectTurnToList(converter)

            val list = maybeList.getOrElse { emptyList() }
            var index = 0
            val assertion = container.collectBasedOnSubject(maybeList) {
                index = addAssertionsAndReturnIndex(searchCriteria)
                val remainingList = list.ifWithinBound(index,
                    { list.subList(index, list.size) },
                    { emptyList() }
                )
                if (list.size > index) {
                    _logic.append(
                        createAdditionalElementsAssertion(
                            container,
                            index,
                            list,
                            remainingList.iterator()
                        )
                    )
                }
            }

            val description = searchBehaviour.decorateDescription(DescriptionIterableLikeExpectation.TO_CONTAIN)
            val options = InOrderOnlyReportingOptionsImpl().apply(reportingOptions)
            val assertionGroup = (if (searchCriteria.size <= options.maxNumberOfExpectedElementsForSummary) {
                assertionBuilder.summary.withDescription(description)
            } else {
                assertionBuilder.list.withDescriptionAndEmptyRepresentation(description)
            }).withAssertion(assertion).build()

            assertionBuilder.invisibleGroup
                .withAssertions(
                    container.collectBasedOnSubject(Some(list)) {
                        _logic
                            .size { it }
                            .collectAndLogicAppend { toBe(index) }
                    },
                    assertionGroup
                )
                .build()
        }
    }


    private fun <E> createAdditionalElementsAssertion(
        container: AssertionContainer<*>,
        expectedSize: Int,
        iterableAsList: List<E?>,
        itr: Iterator<E?>
    ): Assertion {
        return container.collectBasedOnSubject(Some(iterableAsList)) {
            _logic.append(LazyThreadUnsafeAssertionGroup {
                val additionalEntries = itr.mapRemainingWithCounter { counter, it ->
                    //TODO 1.3.0 replace with Representable and remove suppression
                    @Suppress("DEPRECATION")
                    val description = ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
                        DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX,
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
                            .withDescriptionAndEmptyRepresentation(WARNING_ADDITIONAL_ELEMENTS)
                            .withAssertions(additionalEntries)
                            .build()
                    )
                    .failing
                    .build()
            })
        }
    }

    protected abstract fun Expect<List<E>>.addAssertionsAndReturnIndex(searchCriteria: List<SC>): Int
}
