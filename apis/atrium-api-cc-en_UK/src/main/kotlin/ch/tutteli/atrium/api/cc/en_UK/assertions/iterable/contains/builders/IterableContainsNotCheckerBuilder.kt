package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders


import ch.tutteli.atrium.assertions.iterable.contains.IterableContains.SearchBehaviour
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilderBase

/**
 *  Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains not` assertion for [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
open class IterableContainsNotCheckerBuilder<out E, out T : Iterable<E>, out S : SearchBehaviour>(
    containsBuilder: IterableContainsBuilder<E, T, S>
) : IterableContainsNotCheckerBuilderBase<E, T, S>(containsBuilder)

