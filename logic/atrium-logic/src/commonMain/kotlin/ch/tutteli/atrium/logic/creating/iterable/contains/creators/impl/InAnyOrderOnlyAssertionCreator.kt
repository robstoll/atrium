package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.assertions.impl.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InAnyOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.impl.InAnyOrderOnlyReportingOptionsImpl
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.*

/**
 * Represents the base class for `in any order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param E The type of the elements of the [Iterable] the [converter] is going to create.
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
abstract class InAnyOrderOnlyAssertionCreator<E, T : IterableLike, in SC>(
    private val converter: (T) -> Iterable<E>,
    private val searchBehaviour: InAnyOrderOnlySearchBehaviour,
    private val reportingOptions: InAnyOrderOnlyReportingOptions.() -> Unit
) : IterableLikeContains.Creator<T, SC> {

    final override fun createAssertionGroup(
        container: AssertionContainer<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            //TODO 0.20.0 explicit type should not be necessary, report
            val listFromWhichMatchesWillBeRemoved: MutableList<E?> = container.maybeSubject.fold({ mutableListOf<E?>() }) { converter(it).toMutableList() }
            val initialSize = listFromWhichMatchesWillBeRemoved.size
            val assertions = mutableListOf<Assertion>()
            //TODO 0.20.0 could be moved out to a function, is also used in InOrderOnlyBaseAssertionCreator
            val sizeAssertion = container.collectBasedOnSubject(Some(listFromWhichMatchesWillBeRemoved)) {
                _logic
                    .size { it }
                    .collectAndLogicAppend { toBe(searchCriteria.size) }
            }

            val mismatches = createAssertionsForAllSearchCriteria(container, searchCriteria, listFromWhichMatchesWillBeRemoved, assertions)
            if (mismatches == 0 && listFromWhichMatchesWillBeRemoved.isNotEmpty()) {
                assertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchesEtc(
                        listFromWhichMatchesWillBeRemoved,
                        WARNING_ADDITIONAL_ELEMENTS
                    )
                })
            }

            val description = searchBehaviour.decorateDescription(TO_CONTAIN)
            //TODO 0.20.0 could be moved out to a function, is also used in InOrderOnlyBaseAssertionCreator
            val options = InAnyOrderOnlyReportingOptionsImpl().apply(reportingOptions)
            val assertionGroup = (if (searchCriteria.size <= options.maxNumberOfExpectedElementsForSummary) {
                assertionBuilder.summary.withDescription(description)
            } else {
                assertionBuilder.list.withDescriptionAndEmptyRepresentation(description)
            }).withAssertions(assertions).build()

            if (mismatches != 0 && listFromWhichMatchesWillBeRemoved.isNotEmpty()) {
                val warningDescription =
                    if(listFromWhichMatchesWillBeRemoved.size == mismatches || initialSize < mismatches) WARNING_MISMATCHES
                    else WARNING_MISMATCHES_ADDITIONAL_ELEMENTS

                assertionBuilder.invisibleGroup
                    .withAssertions(
                        sizeAssertion,
                        assertionGroup,
                        createExplanatoryGroupForMismatchesEtc(listFromWhichMatchesWillBeRemoved, warningDescription)
                    )
                    .build()
            } else {
                assertionBuilder.invisibleGroup
                    .withAssertions(
                        sizeAssertion,
                        assertionGroup
                    )
                    .build()
            }
        }
    }

    private fun createAssertionsForAllSearchCriteria(
        container: AssertionContainer<*>,
        allSearchCriteria: List<SC>,
        list: MutableList<E?>,
        assertions: MutableList<Assertion>
    ): Int {
        var mismatches = 0
        allSearchCriteria.forEach {
            val (found, assertion) = createAssertionForSearchCriterionAndRemoveMatchFromList(container, it, list)
            if (!found) ++mismatches
            assertions.add(assertion)
        }
        return mismatches
    }

    protected abstract fun createAssertionForSearchCriterionAndRemoveMatchFromList(
        container: AssertionContainer<*>,
        searchCriterion: SC,
        list: MutableList<E?>
    ): Pair<Boolean, Assertion>

    private fun createExplanatoryGroupForMismatchesEtc(
        list: MutableList<E?>,
        warning: DescriptionIterableLikeExpectation
    ): AssertionGroup {
        val assertions = list.map { assertionBuilder.explanatory.withExplanation(it).build() }
        val additionalEntries = assertionBuilder.list
            .withDescriptionAndEmptyRepresentation(warning)
            .withAssertions(assertions)
            .build()
        return assertionBuilder.explanatoryGroup
            .withWarningType
            .withAssertion(additionalEntries)
            .failing
            .build()
    }
}
