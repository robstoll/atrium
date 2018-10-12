@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the *deprecated* builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating; will be removed with 1.0.0",
    ReplaceWith(
        "AtMostCheckerOption",
        "ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtMostCheckerOption"
    )
)
open class IterableContainsAtMostCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.AtMostCheckerOptionImpl<E, T, InAnyOrderSearchBehaviour>(
    times, containsBuilder
), IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>
