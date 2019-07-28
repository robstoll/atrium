package ch.tutteli.atrium.domain.robstoll.lib.creating.collectors

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.BaseAssertionPlant
import ch.tutteli.atrium.creating.BaseCollectingAssertionPlant
import ch.tutteli.atrium.creating.MaybeSubject
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> _collectOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    subjectProvider: () -> T,
    collectingPlantFactory: (() -> T) -> C,
    assertionCreator: C.() -> Unit
): AssertionGroup {
    return if (safeToCollect) {
        @Suppress("DEPRECATION")
        AssertImpl.collector.collect(subjectProvider, collectingPlantFactory, assertionCreator)
    } else {
        @Suppress("DEPRECATION")
        val explanatoryAssertions = AssertImpl
            .collector
            .forExplanation
            .throwIfNoAssertionIsCollected
            .collect(warningCannotEvaluate, MaybeSubject.Absent, collectingPlantFactory, assertionCreator)
        AssertImpl.builder.explanatoryGroup
            .withDefaultType
            .withAssertions(explanatoryAssertions)
            .build()
    }
}
