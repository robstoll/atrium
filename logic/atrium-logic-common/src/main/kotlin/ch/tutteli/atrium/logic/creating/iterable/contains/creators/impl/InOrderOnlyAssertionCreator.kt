package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the base class for `in order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 * @param SC The type of the search criteria.
 *
 * @property searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 *
 * @constructor Represents the base class for `in any order only` assertion creators and provides a corresponding
 *   template to fulfill its responsibility.
 * @param searchBehaviour The search behaviour -- in this case representing `in order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
abstract class InOrderOnlyAssertionCreator<E, T : IterableLike, SC>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InOrderOnlySearchBehaviour
) : InOrderOnlyBaseAssertionCreator<E, T, SC>(converter, searchBehaviour),
    //TODO use protected visibility once https://youtrack.jetbrains.com/issue/KT-24328 is implemented
    InOrderOnlyMatcher<E, SC> {

    override fun Expect<List<E>>.createAssertionsAndReturnIndex(searchCriteria: List<SC>): Int {
        var index = 0
        searchCriteria.forEachIndexed { currentIndex, searchCriterion ->
            createSingleEntryAssertion(currentIndex, searchCriterion, DescriptionIterableAssertion.ENTRY_WITH_INDEX)
            index = currentIndex
        }
        ++index
        return index
    }
}
