@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by holding a group of assertions,
 * created by an assertion creator lambda.
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by holding a
 *   group of assertions, created by an assertion creator lambda.
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
@Suppress("DEPRECATION")
@Deprecated("Switch from Assert to Expect and use InOrderOnlyEntriesAssertionCreator; will be removed with 1.0.0")
class InOrderOnlyEntriesDeprecatedAssertionCreator<E : Any, in T : Iterable<E?>>(
    searchBehaviour: InOrderOnlySearchBehaviour
) : InOrderOnlyDeprecatedAssertionCreator<E?, T, (AssertionPlant<E>.() -> Unit)?>(searchBehaviour),
    InOrderOnlyDeprecatedMatcher<E?, (AssertionPlant<E>.() -> Unit)?> by InOrderOnlyEntriesDeprecatedMatcher()
