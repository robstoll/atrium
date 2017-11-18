package ch.tutteli.atrium.assertions.iterable.contains.builders


import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IChecker
import ch.tutteli.atrium.assertions.iterable.contains.IIterableContains.IDecorator
import ch.tutteli.atrium.assertions.iterable.contains.checkers.IterableContainsExactlyChecker

/**
 * The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param D The decoration behaviour which should be applied for the input of the search.
 *
 * @property times The number which the check will compare against the actual number of times an expected object
 *                 is found in the input of the search.
 *
 * @constructor The base class for builders which create a `contains exactly` check within the fluent API of a sophisticated
 *              `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *              found in the input of the search.
 * @param containsBuilder The previously used [IterableContainsBuilder].
 * @param nameContainsNotFun The name of the function which represents a `Iterable contains not` assertion.
 * @param nameExactlyFun The name of the function which was called and created this builder.
 */
abstract class IterableContainsExactlyCheckerBuilderBase<E, T : Iterable<E>, D : IDecorator>(
    val times: Int,
    containsBuilder: IterableContainsBuilder<E, T, D>,
    nameContainsNotFun: String,
    nameExactlyFun: String
) : IterableContainsCheckerBuilder<E, T, D>(containsBuilder) {

    override val checkers: List<IChecker> =
        listOf(IterableContainsExactlyChecker(times, nameContainsNotFun, nameExactlyFun))
}
