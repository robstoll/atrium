//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation.ELEMENT_WITH_INDEX

/**
 * Represents the base class for `in order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the subject of this expectation for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 */
abstract class InOrderOnlyAssertionCreator<E, T : IterableLike, SC>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InOrderOnlySearchBehaviour,
    reportingOptions: InOrderOnlyReportingOptions.() -> Unit
) : InOrderOnlyBaseAssertionCreator<E, T, SC>(converter, searchBehaviour, reportingOptions),
    InOrderOnlyMatcher<E, SC> {

    override fun Expect<List<E>>.addAssertionsAndReturnIndex(searchCriteria: List<SC>): Int {
        var index = 0
        searchCriteria.forEachIndexed { currentIndex, searchCriterion ->
            _logic.addSingleEntryAssertion(currentIndex, searchCriterion, ELEMENT_WITH_INDEX)
            index = currentIndex
        }
        ++index
        return index
    }
}
