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
    private val searchBehaviour: InAnyOrderOnlySearchBehaviour
) : IterableLikeContains.Creator<T, SC> {

    final override fun createAssertionGroup(
        container: AssertionContainer<T>,
        searchCriteria: List<SC>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = container.maybeSubject.fold({ mutableListOf<E?>() }) { converter(it).toMutableList() }
            val assertions = mutableListOf<Assertion>()
            val sizeAssertion = container.collectBasedOnSubject(Some(list)) {
                _logic
                    .size { it }
                    .collectAndLogicAppend { toBe(searchCriteria.size) }
            }

            val mismatches = createAssertionsForAllSearchCriteria(container, searchCriteria, list, assertions)
            if (mismatches == 0 && list.isNotEmpty()) {
                assertions.add(LazyThreadUnsafeAssertionGroup {
                    createExplanatoryGroupForMismatchesEtc(
                        list,
                        WARNING_ADDITIONAL_ELEMENTS
                    )
                })
            }

            val description = searchBehaviour.decorateDescription(TO_CONTAIN)
            val summary = assertionBuilder.summary
                .withDescription(description)
                .withAssertions(assertions)
                .build()

            if (mismatches != 0 && list.isNotEmpty()) {
                val warningDescription = when (list.size) {
                    mismatches -> WARNING_MISMATCHES
                    else -> WARNING_MISMATCHES_ADDITIONAL_ELEMENTS
                }
                assertionBuilder.invisibleGroup
                    .withAssertions(
                        sizeAssertion,
                        summary,
                        createExplanatoryGroupForMismatchesEtc(list, warningDescription)
                    )
                    .build()
            } else {
                assertionBuilder.invisibleGroup
                    .withAssertions(
                        sizeAssertion,
                        summary
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
