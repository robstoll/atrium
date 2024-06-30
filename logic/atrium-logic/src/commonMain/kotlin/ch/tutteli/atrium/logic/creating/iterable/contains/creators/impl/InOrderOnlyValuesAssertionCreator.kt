//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.logic.creating.iterablelike.contains.reporting.InOrderOnlyReportingOptions
import ch.tutteli.atrium.logic.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the subject of the expectation for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by an
 *   expected object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [ch.tutteli.atrium.reporting.translating.Translatable]) which is used for the [AssertionGroup].
 */
class InOrderOnlyValuesAssertionCreator<E, T : IterableLike>(
    converter: (T) -> Iterable<E>,
    searchBehaviour: InOrderOnlySearchBehaviour,
    reportingOptions: InOrderOnlyReportingOptions.() -> Unit
) : InOrderOnlyAssertionCreator<E, T, E>(converter, searchBehaviour, reportingOptions),
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher()
