package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders

import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.enthaeltNicht
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsAtMostCheckerBuilderBase
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder
import ch.tutteli.atrium.assertions.iterable.contains.searchbehaviours.IterableContainsInAnyOrderSearchBehaviour

/**
 * Represents the builder of a `contains at least once but at most` check within the fluent API of a
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
open class IterableContainsAtMostCheckerBuilder<E, T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContainsBuilder<E, T, IterableContainsInAnyOrderSearchBehaviour>
) : IterableContainsAtMostCheckerBuilderBase<E, T, IterableContainsInAnyOrderSearchBehaviour>(
    times,
    containsBuilder,
    containsBuilder.plant::enthaeltNicht.name,
    containsBuilder::hoechstens.name,
    containsBuilder::zumindest.name,
    containsBuilder::genau.name
)
