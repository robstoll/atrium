//TODO 1.3.0 remove again and switch to core
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.iterable.contains.IterableLikeContains.EntryPointStep
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.*
import ch.tutteli.atrium.logic.creating.iterable.contains.steps.*
import kotlin.jvm.JvmName

/**
 * Defines that the search behaviour "find entries `in any order` in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, NoOpSearchBehaviour>.inAnyOrder: EntryPointStep<E, T, InAnyOrderSearchBehaviour>
    get() = _logic.inAnyOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, InAnyOrderSearchBehaviour>.only: EntryPointStep<E, T, InAnyOrderOnlySearchBehaviour>
    @JvmName("butOnly")
    get() = _logic.butOnly

/**
 * Defines that the search behaviour "find entries `in order` in the [IterableLike]" shall be applied to this
 * sophisticated `to contain` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, NoOpSearchBehaviour>.inOrder: EntryPointStep<E, T, InOrderSearchBehaviour>
    get() = _logic.inOrder

/**
 * Defines that the constraint "`only` the specified entries exist in the [IterableLike]" shall be applied to this
 * sophisticated `to contain in order` in [IterableLike] expectation.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, InOrderSearchBehaviour>.only: EntryPointStep<E, T, InOrderOnlySearchBehaviour>
    @JvmName("andOnly")
    get() = _logic.andOnly

/**
 * Defines that the [IterableLike] contains `in order only` groups of entries
 * whereas the order within the group is specified as next step.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, InOrderOnlySearchBehaviour>.grouped: EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>
    get() = _logic.grouped

/**
 * A filler word to emphasise that the next step defines the order within expected groups of values.
 *
 * @return The newly created builder.
 *
 * @since 0.14.0 -- API existed for [Iterable] but not for [IterableLike].
 */
val <E, T: IterableLike> EntryPointStep<E, T, InOrderOnlyGroupedSearchBehaviour>.within: EntryPointStep<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
    get() = _logic.within
