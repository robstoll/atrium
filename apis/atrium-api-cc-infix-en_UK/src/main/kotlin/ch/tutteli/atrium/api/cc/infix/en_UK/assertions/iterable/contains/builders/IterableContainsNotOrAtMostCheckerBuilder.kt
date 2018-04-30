package ch.tutteli.atrium.api.cc.infix.en_UK.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the *deprecated* builder of a `contains not or at most` check within the fluent API of a
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
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith(
        "NotOrAtMostCheckerOption",
        "ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.NotOrAtMostCheckerOption"
    )
)
open class IterableContainsNotOrAtMostCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.infix.en_UK.creating.iterable.contains.builders.NotOrAtMostCheckerOptionImpl<E, T, InAnyOrderSearchBehaviour>(
    times, containsBuilder
), IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>
