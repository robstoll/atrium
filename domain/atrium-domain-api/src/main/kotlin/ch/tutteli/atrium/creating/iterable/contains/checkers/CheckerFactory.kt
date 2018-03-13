package ch.tutteli.atrium.creating.iterable.contains.checkers

import ch.tutteli.atrium.SingleServiceLoader
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import java.util.*

/**
 * The access point to an implementation of [CheckerFactory].
 *
 * It loads the implementation lazily via [ServiceLoader].
 */
val checkerFactory by lazy { SingleServiceLoader.load(CheckerFactory::class.java) }


/**
 * Defines the minimum set of [IterableContains.Checker]s an implementation of the domain of Atrium
 * has to provide.
 */
interface CheckerFactory {
    /**
     * Creates an [IterableContains.Checker] which verifies that an expected entry is contained at least [times]
     * in the [Iterable].
     *
     * @param times The number which the check uses to compare against the actual number of times an expected entry is
     *   found in the [Iterable].
     * @param nameContainsNotFun The function which should be used instead of [atLeastCall] when [times] equals to zero.
     * @param atLeastCall The function which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newAtLeastChecker(
        times: Int,
        nameContainsNotFun: String,
        atLeastCall: (Int) -> String
    ): IterableContains.Checker

    /**
     * Creates an [IterableContains.Checker] which verifies that an expected entry is contained at most [times]
     * in the [Iterable].
     *
     * @param times The number which the check uses to compare against the actual number of times an expected entry is
     *   found in the [Iterable].
     * @param nameContainsNotFun The function which should be used instead of `atMostCall` when [times] equals to zero.
     * @param atMostCall The function which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newAtMostChecker(
        times: Int,
        nameContainsNotFun: String,
        atMostCall: (Int) -> String
    ): IterableContains.Checker

    /**
     * Creates a [IterableContains.Checker] which verifies that an expected entry is contained exactly [times]
     * in the [Iterable].
     *
     * @param times The number which the check uses to compare against the actual number of times an expected entry is
     *   found in the [Iterable].
     * @param nameContainsNotFun The function which should be used instead of `exactlyCall` when [times] equals to zero.
     * @param exactlyCall The function call which was used and should not be used if [times] equals to zero.
     *
     * @throws IllegalArgumentException In case [times] is smaller than 1.
     */
    fun newExactlyChecker(
        times: Int,
        nameContainsNotFun: String,
        exactlyCall: (Int) -> String
    ): IterableContains.Checker

    /**
     * Creates  a [IterableContains.Checker] which verifies that an expected entry is not contained in the [Iterable].
     */
    fun newNotChecker(): IterableContains.Checker
}
