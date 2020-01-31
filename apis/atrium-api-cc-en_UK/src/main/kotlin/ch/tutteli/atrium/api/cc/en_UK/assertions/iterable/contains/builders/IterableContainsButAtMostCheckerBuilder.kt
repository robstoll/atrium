@file:Suppress("DEPRECATION" /* TODO remove with 0.10.0 */)
package ch.tutteli.atrium.api.cc.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the *deprecated* builder of the second step of a `contains at least but at most` check within the
 * fluent API of a sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of the second step of a `contains at least but at most` check within the
 *   fluent API of a sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated(
    "Use the builder from the package creating; will be removed with 1.0.0",
    ReplaceWith(
        "ButAtMostCheckerOption",
        "ch.tutteli.atrium.api.cc.en_GB.creating.iterable.contains.builders.ButAtMostCheckerOption"
    )
)
open class IterableContainsButAtMostCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    atLeastBuilder: IterableContainsAtLeastCheckerBuilder<E, T>,
    containsBuilder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders.ButAtMostCheckerOptionImpl<E, T, InAnyOrderSearchBehaviour>(
    times, atLeastBuilder, containsBuilder
), IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>
