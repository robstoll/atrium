package ch.tutteli.atrium.logic.creating.iterablelike.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.iterablelike.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by holding a group of assertions,
 * created by an assertion creator lambda.
 *
 * @param T The type of the subject of the assertion for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by holding a
 *   group of assertions, created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class InOrderOnlyEntriesAssertionCreator<E : Any, T : IterableLike>(
    converter: (T) -> Iterable<E?>,
    searchBehaviour: InOrderOnlySearchBehaviour
) : InOrderOnlyAssertionCreator<E?, T, (Expect<E>.() -> Unit)?>(converter, searchBehaviour),
    InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> by InOrderOnlyEntriesMatcher()
