package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion

internal class CollectionDomainImpl<E, T : Collection<E>>(
    collectionSubDomain: CollectionSubDomain<E, T>,
    iterableDomain: IterableDomain<E, T>
) : CollectionDomain<E, T>, CollectionSubDomain<E, T> by collectionSubDomain, IterableDomain<E, T> by iterableDomain {
    override val expect: Expect<T> = collectionSubDomain.expect
}

internal class CollectionSubDomainImpl<E, T : Collection<E>>(
    override val expect: Expect<T>
) : CollectionSubDomain<E, T> {

    override fun isEmpty(): Assertion = collectionAssertions.isEmpty(expect)
    override fun isNotEmpty(): Assertion = collectionAssertions.isNotEmpty(expect)

    override val size: ExtractedFeaturePostStep<T, Int>
        get() = expect._domain.manualFeature(DescriptionCollectionAssertion.SIZE) { size }
}


internal class CollectionElementComparableDomainImpl<E : Comparable<E>, T : Collection<E>>(
    collectionElementComparableSubDomain: CollectionElementComparableSubDomain<E, T>,
    collectionSubDomain: CollectionSubDomain<E, T>,
    iterableElementComparableDomain: IterableElementComparableDomain<E, T>
) : CollectionElementComparableDomain<E, T>,
    CollectionElementComparableSubDomain<E, T> by collectionElementComparableSubDomain,
    CollectionSubDomain<E, T> by collectionSubDomain,
    IterableElementComparableDomain<E, T> by iterableElementComparableDomain {
    override val expect: Expect<T> = collectionElementComparableSubDomain.expect
}

internal class CollectionElementComparableSubDomainImpl<E : Comparable<E>, T : Collection<E>>(
    override val expect: Expect<T>
) : CollectionElementComparableSubDomain<E, T>
