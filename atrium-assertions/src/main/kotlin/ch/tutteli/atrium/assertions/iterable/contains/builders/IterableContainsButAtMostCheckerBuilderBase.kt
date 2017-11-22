package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.validateButAtMost
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IDecorator
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtMostChecker

/**
 * The base class for builders which create the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied to the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create the second step of a `contains at least but at most` check
 *              within the fluent API of a sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `Iterable contains not` assertion.
 * @param nameAtMostFun The name of the function which represents a `Iterable contains at most` assertion.
 * @param nameAtLeastFun The name of the function which represents a `Iterable contains at least` assertion.
 * @param nameAtMostFun The name of the function which was called and created this builder.
 * @param nameExactlyFun The name of the function which represents a `Iterable contains exactly` assertion.
 */
abstract class IterableContainsButAtMostCheckerBuilderBase<E, T : Iterable<E>, D : IDecorator>(
    val times: Int,
    atLeastBuilder: IterableContainsAtLeastCheckerBuilderBase<E, T, D>,
    containsBuilder: IterableContainsBuilder<E, T, D>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameButAtMostFun: String,
    nameExactlyFun: String
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    init {
        validateButAtMost(atLeastBuilder.times, times, nameAtLeastFun, nameButAtMostFun, nameExactlyFun)
    }

    override val checkers: List<IChecker> = listOf(
        *atLeastBuilder.checkers.toTypedArray(),
        IterableContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun)
    )
}
