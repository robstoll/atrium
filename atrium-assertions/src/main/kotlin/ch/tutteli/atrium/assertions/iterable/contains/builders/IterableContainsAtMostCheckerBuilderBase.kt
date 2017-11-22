package ch.tutteli.atrium.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.basic.contains.builders.validateAtMost
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtLeastChecker
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsAtMostChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IDecorator

/**
 * The base class for builders which create a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains at most` check within the fluent API of a
 *              sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `CharSequence contains not` assertion.
 * @param nameAtMostFun The name of the function which was called and created this builder.
 * @param nameAtLeastFun The name of the function which represents a `CharSequence contains at least` assertion.
 * @param nameExactlyFun The name of the function which represents a `CharSequence contains exactly` assertion.
 */
abstract class IterableContainsAtMostCheckerBuilderBase<E, T : Iterable<E>, D : IDecorator>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, D>,
    nameContainsNotFun: String,
    nameAtMostFun: String,
    nameAtLeastFun: String,
    nameExactlyFun: String
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    init {
        validateAtMost(times, nameAtMostFun, nameAtLeastFun, nameExactlyFun)
    }

    override val checkers: List<IChecker> = listOf(
        IterableContainsAtLeastChecker(1, nameContainsNotFun, nameAtMostFun),
        IterableContainsAtMostChecker(times, nameContainsNotFun, nameAtMostFun)
    )
}
