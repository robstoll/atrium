package ch.tutteli.atrium.domain.creating.iterable.contains.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.*
import ch.tutteli.atrium.domain.creating.iterable.contains.creators.iterableContainsAssertions
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

internal class BuilderNoOpDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
) : BuilderNoOpDomain<E, T> {

    override val inAnyOrder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
        get() = searchBehaviourFactory.inAnyOrder(builder)

    override val inOrder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
        get() = searchBehaviourFactory.inOrder(builder)
}


internal class BuilderInAnyOrderDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
) : BuilderInAnyOrderDomain<E, T> {

    override val only: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>
        get() = searchBehaviourFactory.inAnyOrderOnly(builder)
}


internal class BuilderInAnyOrderOnlyDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>
) : BuilderInAnyOrderOnlyDomain<E, T> {

    override fun values(expected: List<E>): Assertion =
        iterableContainsAssertions.valuesInAnyOrderOnly(builder, expected)
}

internal class BuilderNullableInAnyOrderOnlyDomainImpl<E : Any, T : Iterable<E?>>(
    override val builder: IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>
) : BuilderNullableInAnyOrderOnlyDomain<E, T> {

    override fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion =
        iterableContainsAssertions.entriesInAnyOrderOnly(builder, assertionCreatorsOrNulls)
}


internal class BuilderInOrderDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
) : BuilderInOrderDomain<E, T> {

    override val only: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
        get() = searchBehaviourFactory.inOrderOnly(builder)
}


internal class BuilderInOrderOnlyDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
) : BuilderInOrderOnlyDomain<E, T> {

    override val grouped: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>
        get() = searchBehaviourFactory.inOrderOnlyGrouped(builder)

    override fun values(expected: List<E>): Assertion =
        iterableContainsAssertions.valuesInOrderOnly(builder, expected)
}

internal class BuilderNullableInOrderOnlyDomainImpl<E : Any, T : Iterable<E?>>(
    override val builder: IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>
) : BuilderNullableInOrderOnlyDomain<E, T> {

    override fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion =
        iterableContainsAssertions.entriesInOrderOnly(builder, assertionCreatorsOrNulls)
}


internal class BuilderInOrderOnlyGroupedDomainImpl<E, T : Iterable<E>>(
    override val builder: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>
) : BuilderInOrderOnlyGroupedDomain<E, T> {

    override val within: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>
        get() = searchBehaviourFactory.inOrderOnlyGroupedWithin(builder)

    override fun values(groups: List<List<E>>): Assertion =
        iterableContainsAssertions.valuesInOrderOnlyGrouped(builder, groups)
}

internal class BuilderNullableInOrderOnlyGroupedDomainImpl<E : Any, T : Iterable<E?>>(
    override val builder: IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>
) : BuilderNullableInOrderOnlyGroupedDomain<E, T> {

    override fun entries(groups: List<List<(Expect<E>.() -> Unit)?>>): Assertion =
        iterableContainsAssertions.entriesInOrderOnlyGrouped(builder, groups)
}
