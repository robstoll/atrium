package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 *  Represents the *deprecated* builder of a `contains not at all` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains not at all` check within the fluent API of a sophisticated
 *   `contains not` assertion for [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.IterableContainsNotCheckerBuilder")
)
open class IterableContainsNotCheckerBuilder<out E, out T : Iterable<E>>(
    containsBuilder: IterableContains.Builder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotCheckerBuilderImpl<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    containsBuilder
), IterableContainsCheckerBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>

