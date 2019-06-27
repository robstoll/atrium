package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.createSizeFeatureAssertionForInOrderOnly
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents the base class for `in order only` assertion creators and provides a corresponding template to fulfill
 * its responsibility.
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
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
abstract class InOrderOnlyAssertionCreator<E, in T : Iterable<E>, SC>(
    private val searchBehaviour: InOrderOnlySearchBehaviour
) : IterableContains.Creator<T, SC>,
    //TODO use protected visibility once https://youtrack.jetbrains.com/issue/KT-24328 is implemented
    InOrderOnlyMatcher<E, SC>
{

    final override fun createAssertionGroup(plant: AssertionPlant<T>, searchCriteria: List<SC>): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val assertion = AssertImpl.collector.collectForSubject({ plant.subject.toList() }) {
                var index = 0
                searchCriteria.forEachIndexed { currentIndex, searchCriterion ->
                    createSingleEntryAssertion(currentIndex, searchCriterion, DescriptionIterableAssertion.ENTRY_WITH_INDEX)
                    index = currentIndex
                }
                ++index
                val remainingList = if (index < subject.size) subject.subList(index, subject.size) else listOf()
                addAssertion(createSizeFeatureAssertionForInOrderOnly(index, subject, remainingList.iterator()))
            }
            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertImpl.builder.summary
                .withDescription(description)
                .withAssertion(assertion)
                .build()
        }
    }
}
