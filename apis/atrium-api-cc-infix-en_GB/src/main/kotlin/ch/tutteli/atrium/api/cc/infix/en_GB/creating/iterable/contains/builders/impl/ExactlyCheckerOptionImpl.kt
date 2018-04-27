package ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.iterable.contains.builders.ExactlyCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.exactly
import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.ExactlyCheckerOptionBase
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

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
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
internal class ExactlyCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ExactlyCheckerOptionBase<E, T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "`${containsBuilder::exactly.name} $it`" }
), ExactlyCheckerOption<E, T, S>
