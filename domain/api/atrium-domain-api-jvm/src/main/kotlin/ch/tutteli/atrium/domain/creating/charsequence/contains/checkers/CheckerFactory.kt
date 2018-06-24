package ch.tutteli.atrium.domain.creating.charsequence.contains.checkers

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * The access point to an implementation of [CheckerFactory].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val checkerFactory by lazy { loadSingleService(CheckerFactory::class) }


/**
 * Defines the minimum set of [CharSequenceContains.Checker]s an implementation of the domain of Atrium
 * has to provide.
 */
interface CheckerFactory {
    /**
     * Creates an [CharSequenceContains.Checker] which verifies that an expected object is contained at least [times]
     * in the search input.
     *
     * @param times The number which the check uses to compare against the actual number of times an expected object is
     *   found in the input of the search.
     * @param nameContainsNotFun The function which should be used instead of `atLeastCall` when [times] equals to zero.
     * @param atLeastCall The function which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): CharSequenceContains.Checker

    /**
     * Creates an [CharSequenceContains.Checker] which verifies that an expected object is contained at most [times]
     * in the search input.
     *
     * @param times The number which the check uses to compare against the actual number of times an expected object is
     *   found in the input of the search.
     * @param nameContainsNotFun The function which should be used instead of `atMostCall` when [times] equals to zero.
     * @param atMostCall The function which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): CharSequenceContains.Checker

    /**
     * Creates an [CharSequenceContains.Checker] which verifies that an expected object is contained exactly [times]
     * in the search input.
     *
     * @param times The number which the check uses to compare against the actual number of times an expected object is
     *   found in the input of the search.
     * @param nameContainsNotFun The function which should be used instead of `exactlyCall` when [times] equals to zero.
     * @param exactlyCall The function call which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): CharSequenceContains.Checker

    /**
     * Creates an [CharSequenceContains.Checker] which verifies that an expected object is not contained
     * in the search input.
     */
    fun newNotChecker(): CharSequenceContains.Checker
}
