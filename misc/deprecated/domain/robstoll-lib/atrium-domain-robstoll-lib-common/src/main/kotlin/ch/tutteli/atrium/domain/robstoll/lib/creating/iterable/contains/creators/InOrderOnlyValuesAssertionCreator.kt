package ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the expected entries
 * have to appear in the specified order and where an entry is identified by an expected object (equality comparison).
 *
 * @param T The type of the [AssertionPlant.subject][SubjectProvider.subject] for which the `contains` assertion is be build.
 *
 * @constructor Represents a creator of a sophisticated `contains` assertions for [Iterable] where exactly the
 *   expected entries have to appear in the specified order and where an entry is identified by an
 *   expected object (equality comparison).
 * @param searchBehaviour The search behaviour -- in this case representing `in any order only` which is used to
 *   decorate the description (a [Translatable]) which is used for the [AssertionGroup].
 */
class InOrderOnlyValuesAssertionCreator<E, in T : Iterable<E>>(
    searchBehaviour: InOrderOnlySearchBehaviour
) : InOrderOnlyAssertionCreator<E, T, E>(searchBehaviour),
    //TODO use protected visibility once https://youtrack.jetbrains.com/issue/KT-24328 is implemented
    InOrderOnlyMatcher<E, E> by InOrderOnlyValueMatcher()
