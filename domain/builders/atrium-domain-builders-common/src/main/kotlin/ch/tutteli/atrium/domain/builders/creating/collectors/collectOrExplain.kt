package ch.tutteli.atrium.domain.builders.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.collectors.AssertionCollector
import ch.tutteli.atrium.reporting.translating.Translatable


/**
 * Depending on the given [safeToCollect] it either [AssertionCollector.collect]s the assertions the given
 * [assertionCreator] might create or it uses it to create an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param safeToCollect Indicates whether it is safe to use the [AssertionPlant.subjectProvider] (means it does
 *   not throw a [PlantHasNoSubjectException] if called)
 * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
 * @param plant The plant from which the [AssertionPlant.subject] will be used as subject of the
 *   [CollectingAssertionPlant].
 * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
 *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
 *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
 */
fun <T : Any> AssertionCollector.collectOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    plant : AssertionPlant<T>,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): AssertionGroup = collectOrExplain(safeToCollect, warningCannotEvaluate, plant.subjectProvider, assertionCreator)

/**
 * Depending on the given [safeToCollect] it either [AssertionCollector.collect]s the assertions the given
 * [assertionCreator] might create or it uses it to create an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param safeToCollect Indicates whether it is safe to use the [subjectProvider] (means it does
 *   not throw a [PlantHasNoSubjectException] if called)
 * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
 * @param subjectProvider Provides the subject which is used as [CollectingAssertionPlant.subject].
 * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
 *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
 *   end up creating a sub assertion plant which delegates created [Assertion]s to the [CollectingAssertionPlant].
 */
fun <T : Any> AssertionCollector.collectOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    subjectProvider: () -> T,
    assertionCreator: CollectingAssertionPlant<T>.() -> Unit
): AssertionGroup = collectOrExplain(
    safeToCollect, warningCannotEvaluate, subjectProvider, coreFactory::newCollectingPlant, assertionCreator
)

/**
 * Depending on the given [safeToCollect] it either [AssertionCollector.collect]s the assertions the given
 * [assertionCreator] might create or it uses it to create an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param safeToCollect Indicates whether it is safe to use the [AssertionPlantNullable.subjectProvider] (means it does
 *   not throw a [PlantHasNoSubjectException]if called)
 * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
 * @param plant The plant from which the [AssertionPlantNullable.subject] will be used as subject of the
 *   [CollectingAssertionPlantNullable].
 * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
 *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
 *   end up creating a sub assertion plant which delegates created [Assertion]s to the
 *   [CollectingAssertionPlantNullable].
 */
fun <T> AssertionCollector.collectNullableOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    plant: AssertionPlantNullable<T>,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): AssertionGroup = collectNullableOrExplain(
    safeToCollect, warningCannotEvaluate, plant.subjectProvider, assertionCreator
)

/**
 * Depending on the given [safeToCollect] it either [AssertionCollector.collect]s the assertions the given
 * [assertionCreator] might create or it uses it to create an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param safeToCollect Indicates whether it is safe to use the [subjectProvider] (means it does
 *   not throw a [PlantHasNoSubjectException])
 * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
 * @param subjectProvider Provides the subject which is used as [CollectingAssertionPlantNullable.subject].
 * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
 *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
 *   end up creating a sub assertion plant which delegates created [Assertion]s to the
 *   [CollectingAssertionPlantNullable].
 */
fun <T> AssertionCollector.collectNullableOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    subjectProvider: () -> T,
    assertionCreator: CollectingAssertionPlantNullable<T>.() -> Unit
): AssertionGroup = collectOrExplain(
    safeToCollect, warningCannotEvaluate, subjectProvider, coreFactory::newCollectingPlantNullable, assertionCreator
)

/**
 * Depending on the given [safeToCollect] it either [AssertionCollector.collect]s the assertions the given
 * [assertionCreator] might create or it uses it to create an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 *
 * @param safeToCollect Indicates whether it is safe to use the [subjectProvider] (means it does
 *   not throw a [PlantHasNoSubjectException])
 * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
 * @param subjectProvider Provides the subject which is used as [BaseCollectingAssertionPlant.subject].
 * @param collectingPlantFactory The factory method which creates the appropriate collecting plant which is suitable
 *   for the given [assertionCreator].
 * @param assertionCreator A lambda which typically creates a sub [AssertionPlant] and adds assertions
 *   to it. For instance, if you create a feature assertion or a type transformation assertion, you will typically
 *   end up creating a sub assertion plant which delegates created [Assertion]s to the [BaseCollectingAssertionPlant].
 */
fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> AssertionCollector.collectOrExplain(
    safeToCollect: Boolean,
    warningCannotEvaluate: Translatable,
    subjectProvider: () -> T,
    collectingPlantFactory: (() -> T) -> C,
    assertionCreator: C.() -> Unit
): AssertionGroup {
    return if (safeToCollect) {
        AssertImpl.collector.collect(subjectProvider, collectingPlantFactory, assertionCreator)
    } else {
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
