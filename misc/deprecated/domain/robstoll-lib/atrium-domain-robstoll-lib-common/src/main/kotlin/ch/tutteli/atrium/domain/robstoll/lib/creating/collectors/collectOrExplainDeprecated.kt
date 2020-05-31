@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.creating.MaybeSubject.Absent
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> _collectOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    subjectProvider: () -> T,
    collectingPlantFactory: (() -> T) -> C,
    assertionCreator: C.() -> Unit
): AssertionGroup = if (safeToCollect) {
    AssertImpl.collector.collect(subjectProvider, collectingPlantFactory, assertionCreator)
} else {
    val explanatoryAssertions = AssertImpl
        .collector
        .forExplanation
        .throwIfNoAssertionIsCollected
        .collect(warningCannotEvaluate, Absent, collectingPlantFactory, assertionCreator)
    AssertImpl.builder.explanatoryGroup
        .withDefaultType
        .withAssertions(explanatoryAssertions)
        .build()
}
