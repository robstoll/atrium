package ch.tutteli.atrium.api.cc.en_UK.creating.iterable.contains.builders

import ch.tutteli.atrium.api.cc.en_UK.exactly
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.creating.iterable.contains.builders.IterableContainsExactlyCheckerBuilderBase
import ch.tutteli.atrium.creating.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
open class IterableContainsExactlyCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : IterableContainsExactlyCheckerBuilderBase<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::exactly.name}($it)" }
)
