//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

abstract class InOrderOnlyGroupedAssertionCreator<E, in T : Iterable<E>, SC>(
    searchBehaviour: InOrderOnlyGroupedSearchBehaviour
) : InOrderOnlyBaseAssertionCreator<E, T, List<SC>>(searchBehaviour),
    //TODO use protected visibility once https://youtrack.jetbrains.com/issue/KT-24328 is implemented
    InOrderOnlyMatcher<E, SC> {

    final override fun Expect<List<E>>.createAssertionsAndReturnIndex(
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

    private fun Expect<List<E>>.createSublistAssertion(
        currentIndex: Int,
        untilIndex: Int,
        groupOfSearchCriteria: List<SC>,
        subject: List<E>
    ) {
        ExpectImpl.feature.extractor(this)
            .withDescription(
                TranslatableWithArgs(DescriptionIterableAssertion.INDEX_FROM_TO, currentIndex, untilIndex - 1)
            )
            .withRepresentationForFailure(DescriptionIterableAssertion.SIZE_EXCEEDED)
            .withFeatureExtraction {
                Option.someIf(currentIndex <= it.size) {
                    val safeUntilIndex = if (untilIndex < subject.size) untilIndex else subject.size
                    subject.subList(currentIndex, safeUntilIndex)
                }
            }
            .withoutOptions()
            .build()
            .addToInitial {
                createSublistAssertion(groupOfSearchCriteria)
            }
    }

    protected abstract fun Expect<List<E>>.createSublistAssertion(groupOfSearchCriteria: List<SC>)
}
