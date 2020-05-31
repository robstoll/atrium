@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators._containsEntriesInAnyOrder
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators._containsEntriesInAnyOrderOnly
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators._containsEntriesInOrderOnly
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators._containsEntriesInOrderOnlyGrouped


abstract class IterableContainsAssertionsDeprecatedImpl : IterableContainsAssertions {

    override fun <E : Any, T : Iterable<E?>> entriesInAnyOrderWithAssert(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInAnyOrder(checkerOption, assertionCreators)

    override fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInAnyOrderOnly(builder, assertionCreators)

    override fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(AssertionPlant<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInOrderOnly(builder, assertionCreators)

    override fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGroupedWithAssert(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(AssertionPlant<E>.() -> Unit)?>>
    ): Assertion = _containsEntriesInOrderOnlyGrouped(builder, groups)
}
