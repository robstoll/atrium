package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.assertions.LazyThreadUnsafeAssertionGroup
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by an
 *   expected object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class InOrderOnlyGroupedValuesAssertionCreator<E : Any, in T : Iterable<E>>(
    private val searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : IterableContains.Creator<T, List<E>> {
    override fun createAssertionGroup(
        plant: AssertionPlant<T>,
        searchCriteria: List<List<E>>
    ): AssertionGroup {
        return LazyThreadUnsafeAssertionGroup {
            val list = plant.subject.toList()
            val listSize = list.size
            var index = 0

            val assertions = mutableListOf<Assertion>()
            searchCriteria.forEach { values ->
                index = if (values.size == 1) {
                    val featureName = TranslatableWithArgs(DescriptionIterableAssertion.INDEX, index)
                    val (representation, equals: () -> Boolean) = if (index < listSize) {
                        val entry = list[index]
                        entry to { entry == values[0] }
                    } else {
                        RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) to { false }
                    }
                    val featureAssertionGroup = AssertImpl.builder
                        .feature(featureName, representation)
                        .create(AssertImpl.builder.descriptive.create(DescriptionAnyAssertion.TO_BE, values[0], equals))
                    assertions.add(featureAssertionGroup)
                    index + 1
                } else {
                    val untilIndex = index + values.size
                    val safeUntilIndex = if (untilIndex < listSize) untilIndex else listSize

                    val (representation, sublist) = if (index < listSize) {
                        val tmpSublist = list.subList(index, safeUntilIndex)
                        tmpSublist to tmpSublist
                    } else {
                        RawString.create(DescriptionIterableAssertion.SIZE_EXCEEDED) to listOf<T>()
                    }

                    val collectingPlant = AssertImpl.coreFactory.newCollectingPlant { sublist }
                    val builder = AssertImpl.iterable.containsBuilder(collectingPlant)
                    val inAnyOrder = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(builder)
                    val inAnyOrderOnly = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(inAnyOrder)
                    val assertion = AssertImpl.iterable.contains.valuesInAnyOrderOnly(inAnyOrderOnly, values)
                    collectingPlant.addAssertion(assertion)
                    //TODO shall we provide a domain builder? instead of the above it would look as follows:
                    //AssertImpl.iterable.contains.implBuilder(collectingPlant).inAnyOrder.only.values(values)

                    val featureName =
                        TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, index, untilIndex - 1)
                    val featureAssertionGroup = AssertImpl.builder
                        .feature(featureName, representation)
                        .create(collectingPlant.getAssertions())
                    assertions.add(featureAssertionGroup)
                    untilIndex
                }
            }
            val remainingList = if(index < listSize) list.subList(index, listSize) else listOf()
            assertions.add(createSizeFeatureAssertionForInOrderOnly(index, list, remainingList.iterator()))
            val description = searchBehaviour.decorateDescription(DescriptionIterableAssertion.CONTAINS)
            AssertImpl.builder.summary(description).create(assertions)
        }
    }
}
