package ch.tutteli.atrium.domain.creating.collectors

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Responsible to collect assertions made in a sub-[AssertionPlant] and intended for explanation.
 */
@Deprecated("Switch from Assert to Expect and use AssertionCollector.collectForComposition instead; will be removed with 1.0.0")
interface AssertionCollectorForExplanation {

    /**
     * Collects the [Assertion] created by [assertionCreator] and uses the given [maybeSubject] as
     * [CollectingAssertionPlant.subject] if it is [MaybeSubject.Present].
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
     *   containing a warning if [maybeSubject] is [MaybeSubject.Absent] and an assertion function tries to access it.
     * @throws IllegalArgumentException Might throw it in case not a single [Assertion] was collected
     *   (e.g. ThrowingAssertionCollectorForExplanation does).
     */
    @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
    @Deprecated("Switch from Assert to Expect and use the other overload; will be removed with 1.0.0")
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
    @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
    @Deprecated("Switch from Assert to Expect and use the other overload; will be removed with 1.0.0")
    fun <T> collectNullable(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        assertionCreator: (CollectingAssertionPlantNullable<T>.() -> Unit)?
    ): List<Assertion> = collect(
        warningCannotEvaluate, maybeSubject, coreFactory::newCollectingPlantNullable, assertionCreator
    )

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
    @Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
    @Deprecated("Switch from Assert to Expect and use the other overload; will be removed with 1.0.0")
    fun <T, A : BaseAssertionPlant<T, A>, C : BaseCollectingAssertionPlant<T, A, C>> collect(
        warningCannotEvaluate: Translatable,
        maybeSubject: MaybeSubject<T>,
        collectingPlantFactory: (() -> T) -> C,
        assertionCreator: (C.() -> Unit)?
    ): List<Assertion>
}


/**
 * The access point to an implementation of [NonThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Switch from Assert to Expect and use AssertionCollector.collectForComposition instead -- adds a failing assertion instead of throwing; this interface will be removed with 1.0.0")
@Suppress("DEPRECATION")
val nonThrowingAssertionCollectorForExplanation: NonThrowingAssertionCollectorForExplanation by lazy {
    loadSingleService(NonThrowingAssertionCollectorForExplanation::class)
}

/**
 * Represents an assertion collector meant for explanation which does *not* throw in case not a single [Assertion]
 * was collected.
 */
@Deprecated("Switch from Assert to Expect and use AssertionCollector.collectForComposition instead -- adds a failing assertion instead of throwing; this interface will be removed with 1.0.0")
@Suppress("DEPRECATION")
interface NonThrowingAssertionCollectorForExplanation : AssertionCollectorForExplanation


/**
 * The access point to an implementation of [ThrowingAssertionCollectorForExplanation].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
@Deprecated("Switch from Assert to Expect and use AssertionCollector.collectForComposition instead -- adds a failing assertion instead of throwing; this interface will be removed with 1.0.0")
@Suppress("DEPRECATION")
val throwingAssertionCollectorForExplanation: ThrowingAssertionCollectorForExplanation by lazy {
    loadSingleService(ThrowingAssertionCollectorForExplanation::class)
}

/**
 * Represents an assertion collector meant for explanation which throws in case not a single [Assertion] was collected.
 */
@Deprecated("Switch from Assert to Expect and use AssertionCollector.collectForComposition instead -- adds a failing assertion instead of throwing; this interface will be removed with 1.0.0")
@Suppress("DEPRECATION")
interface ThrowingAssertionCollectorForExplanation : AssertionCollectorForExplanation
