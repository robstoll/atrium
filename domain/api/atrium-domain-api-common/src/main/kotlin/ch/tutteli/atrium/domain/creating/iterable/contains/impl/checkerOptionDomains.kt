package ch.tutteli.atrium.domain.creating.iterable.contains.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.CheckerOptionInAnyOrderDomain
import ch.tutteli.atrium.domain.creating.iterable.contains.CheckerOptionNullableInAnyOrderDomain
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.iterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

internal class CheckerOptionInAnyOrderDomainImpl<E, T : Iterable<E>>(
    override val checkerOption: IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>
) : CheckerOptionInAnyOrderDomain<E, T> {

    override fun values(expected: List<E>): Assertion =
        iterableContainsAssertions.valuesInAnyOrder(checkerOption, expected)
}

internal class CheckerOptionNullableInAnyOrderDomainImpl<E : Any, T : Iterable<E?>>(
    override val checkerOption: IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>
) : CheckerOptionNullableInAnyOrderDomain<E, T> {

    override fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion =
        iterableContainsAssertions.entriesInAnyOrder(checkerOption, assertionCreatorsOrNulls)
}
