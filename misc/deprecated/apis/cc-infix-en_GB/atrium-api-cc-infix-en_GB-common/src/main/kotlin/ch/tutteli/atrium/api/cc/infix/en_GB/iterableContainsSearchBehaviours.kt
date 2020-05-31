@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.entries
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.group
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Defines that the search behaviour "find entries `in any order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.inAny(ch.tutteli.atrium.api.infix.en_GB.order)",
        "ch.tutteli.atrium.api.infix.en_GB.inAny",
        "ch.tutteli.atrium.api.infix.en_GB.order"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inAny(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.but(ch.tutteli.atrium.api.infix.en_GB.only)",
        "ch.tutteli.atrium.api.infix.en_GB.but",
        "ch.tutteli.atrium.api.infix.en_GB.only"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>.but(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inAnyOrderOnly(this)


/**
 * Defines that the search behaviour "find entries `in order` in the [Iterable]" shall be applied to this
 * sophisticated `contains` in [Iterable] assertion.
 *
 * @param order Has to be `order`.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.inGiven(ch.tutteli.atrium.api.infix.en_GB.order)",
        "ch.tutteli.atrium.api.infix.en_GB.inGiven",
        "ch.tutteli.atrium.api.infix.en_GB.order"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>.inGiven(@Suppress("UNUSED_PARAMETER") order: order)
    = AssertImpl.iterable.contains.searchBehaviours.inOrder(this)

/**
 * Defines that the constraint "`only` the specified entries exist in the [Iterable]" shall be applied to this
 * sophisticated `contains in order` [Iterable] assertion.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.and(ch.tutteli.atrium.api.infix.en_GB.only)",
        "ch.tutteli.atrium.api.infix.en_GB.and",
        "ch.tutteli.atrium.api.infix.en_GB.only"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>.and(@Suppress("UNUSED_PARAMETER") only: only)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnly(this)

/**
 * Defines that the [Iterable] contains groups of entries `in order only` where the entries within the group can be in
 * any order.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.grouped(ch.tutteli.atrium.api.infix.en_GB.entries)",
        "ch.tutteli.atrium.api.infix.en_GB.grouped",
        "ch.tutteli.atrium.api.infix.en_GB.entries"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>.grouped(@Suppress("UNUSED_PARAMETER") entries: entries)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGrouped(this)

/**
 * Defines that the [Iterable] contains groups of entries `in order only` where the entries within the group can be in
 * any order.
 *
 * @return The newly created builder.
 */
@Deprecated(
    "Switch from api-cc-infix-en_GB to api-infix-en_GB; will be removed with 1.0.0 -- see https://github.com/robstoll/atrium/releases/tag/v0.12.0#migration for migration hints and scripts.",
    ReplaceWith(
        "this.within(ch.tutteli.atrium.api.infix.en_GB.group)",
        "ch.tutteli.atrium.api.infix.en_GB.within",
        "ch.tutteli.atrium.api.infix.en_GB.group"
    )
)
infix fun <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>.within(@Suppress("UNUSED_PARAMETER") group: group)
    = AssertImpl.iterable.contains.searchBehaviours.inOrderOnlyGroupedWithin(this)
