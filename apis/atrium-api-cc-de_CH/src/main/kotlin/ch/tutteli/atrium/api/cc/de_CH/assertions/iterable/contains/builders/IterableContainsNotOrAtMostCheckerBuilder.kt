package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.api.cc.de_CH.nichtOderHoechstens
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsNotOrAtMostCheckerBuilderBase
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the builder of a `contains not or at most` check within the fluent API of a
 * sophisticated `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *              sophisticated `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *              found in the [Iterable].
 * @param containsBuilder The previously used [IterableContainsBuilder].
 */
open class IterableContainsNotOrAtMostCheckerBuilder<E, T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : IterableContainsNotOrAtMostCheckerBuilderBase<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times,
    containsBuilder,
    containsBuilder.plant::enthaeltNicht.name,
    { "${containsBuilder::nichtOderHoechstens.name}($it)" }
)
