package ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.IterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlyGroupedSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InOrderOnlySearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.iterable.contains.creators.*


class IterableContainsAssertionsImpl : IterableContainsAssertions, IterableContainsAssertionsDeprecatedImpl() {

    override fun <E, T : Iterable<E>> valuesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>,
        expected: List<E>
    ): Assertion = _containsValuesInAnyOrder(checkerOption, expected)

    override fun <E : Any, T : Iterable<E?>> entriesInAnyOrder(
        checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInAnyOrder(checkerOption, assertionCreators)

    override fun <E, T : Iterable<E>> valuesInAnyOrderOnly(
        builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion = _containsValuesInAnyOrderOnly(builder, expected)

    override fun <E : Any, T : Iterable<E?>> entriesInAnyOrderOnly(
        builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInAnyOrderOnly(builder, assertionCreators)

    override fun <E, T : Iterable<E>> valuesInOrderOnly(
        builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>,
        expected: List<E>
    ): Assertion = _containsValuesInOrderOnly(builder, expected)

    override fun <E : Any, T : Iterable<E?>> entriesInOrderOnly(
        builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>,
        assertionCreators: List<(Expect<E>.() -> Unit)?>
    ): Assertion = _containsEntriesInOrderOnly(builder, assertionCreators)

    override fun <E, T : Iterable<E>> valuesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<E>>
    ): Assertion = _containsValuesInOrderOnlyGrouped(builder, groups)

    override fun <E : Any, T : Iterable<E?>> entriesInOrderOnlyGrouped(
        builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>,
        groups: List<List<(Expect<E>.() -> Unit)?>>
    ): Assertion = _containsEntriesInOrderOnlyGrouped(builder, groups)
}
