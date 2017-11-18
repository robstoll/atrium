package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.containsNot
import ch.tutteli.atrium.api.cc.en_UK.exactly
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsExactlyCheckerBuilderBase
import ch.tutteli.atrium.assertions.iterable.contains.decorators.IterableContainsInAnyOrderDecorator

/**
 * Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 *              `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
open class IterableContainsExactlyCheckerBuilder<E, T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderDecorator>
) : IterableContainsExactlyCheckerBuilderBase<E, T, IterableContainsInAnyOrderDecorator>(
    times,
    containsBuilder,
    containsBuilder.plant::containsNot.name,
    containsBuilder::exactly.name
)
