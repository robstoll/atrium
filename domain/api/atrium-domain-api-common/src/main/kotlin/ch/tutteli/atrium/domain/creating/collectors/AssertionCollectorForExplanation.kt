package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.translating.Translatable

interface AssertionCollectorForExplanation {

    /**
     * Collects the [Assertion] created by [assertionCreator] and uses the given [maybeSubject] as
     * [CollectingAssertionPlant.subject] if [MaybeSubject.Present].
     *
     * In case [maybeSubject] is [MaybeSubject.Absent] and [assertionCreator] is accessed, then a
     * [PlantHasNoSubjectException] is thrown and caught in which case a single [AssertionGroup] with an
     * [ExplanatoryAssertionGroupType] is returned containing the given [warningCannotEvaluate].
     *
     * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
     * @param assertionCreator The function which should at least create one assertion.
     * @param maybeSubject The subject which will be used for the [CollectingAssertionPlant].
     *
     * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     *   containing a warning if [maybeSubject] is `null` and an assertion function tries to access it.
     * @throws IllegalArgumentException Might throw it in case not a single [Assertion] was collected
     *   (e.g. ThrowingAssertionCollectorForExplanation does).
     */
    fun <T : Any> collect(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        assertionCreator: (CollectingAssertionPlant<T>.() -> Unit)?
    ): List<Assertion> = collect(warningCannotEvaluate, maybeSubject, coreFactory::newCollectingPlant, assertionCreator)

    /**
     * Collects the [Assertion] created by [assertionCreator] and uses the given [maybeSubject] as
     * [CollectingAssertionPlantNullable.subject] if it is [MaybeSubject.Present].
     *
     * In case [maybeSubject] is [MaybeSubject.Absent] and [assertionCreator] is accessed, then a
     * [PlantHasNoSubjectException] is thrown and caught in which case a single [AssertionGroup] with an
     * [ExplanatoryAssertionGroupType] is returned containing the given [warningCannotEvaluate].
     *
     * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
     * @param assertionCreator The function which should at least create one assertion.
     * @param maybeSubject The subject which will be used for the [CollectingAssertionPlantNullable].
     *
     * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     *   containing a warning if [maybeSubject] is `null` and an assertion function tries to access it.
     * @throws IllegalArgumentException Might throw it in case not a single [Assertion] was collected
     *   (e.g. ThrowingAssertionCollectorForExplanation does).
     */
    fun <T> collectNullable(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        assertionCreator: (CollectingAssertionPlantNullable<T>.() -> Unit)?
    ): List<Assertion> =
        collect(warningCannotEvaluate, maybeSubject, coreFactory::newCollectingPlantNullable, assertionCreator)

    /**
     * Collects the [Assertion] created by [assertionCreator] with the collecting assertion plant created by the given
     * [collectingPlantFactory] and uses the given [maybeSubject] as [BaseCollectingAssertionPlant.subject] if it is
     * [MaybeSubject.Present].
     *
     * In case [maybeSubject] is [MaybeSubject.Absent] and [assertionCreator] is accessed, then a
     * [PlantHasNoSubjectException] is thrown and caught in which case a single [AssertionGroup] with an
     * [ExplanatoryAssertionGroupType] is returned containing the given [warningCannotEvaluate].
     *
     * @param warningCannotEvaluate The translatable used to explain why the assertions could not be evaluated.
     * @param maybeSubject The subject which will be used for the [AssertionPlant].
     * @param collectingPlantFactory The factory method which creates the appropriate collecting plant which is suitable
     *   for the given [assertionCreator].
     * @param assertionCreator The function which should at least create one assertion.

     *
     * @return A list with the collected assertion or an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
     *   containing a warning if [maybeSubject] is `null` and an assertion function tries to access it.
     * @throws IllegalArgumentException Might throw it in case not a single [Assertion] was collected
     *   (e.g. ThrowingAssertionCollectorForExplanation does).
     */
    fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> collect(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        collectingPlantFactory: (() -> T) -> C,
        assertionCreator: (C.() -> Unit)?
    ): List<Assertion>
}
