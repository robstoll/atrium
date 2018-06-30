package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inBeliebigerReihenfolge
    : IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.nur
    : IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>

/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inGegebenerReihenfolge
    : IterableContains.Builder<E, T, InOrderSearchBehaviour>


/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.nur
    : IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>

/**
 * Defines that the [Iterable] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.gruppiert
    : IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>

/**
 * A filler word to emphasis that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 */
expect val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.innerhalb
    : IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
