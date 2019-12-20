package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionCollectionAssertion

internal class CollectionDomainImpl<E, T : Collection<E>>(
    collectionOnlyDomain: CollectionOnlyDomain<E, T>,
    iterableDomain: IterableDomain<E, T>
) : CollectionDomain<E, T>, CollectionOnlyDomain<E, T> by collectionOnlyDomain, IterableDomain<E, T> by iterableDomain {
    override val expect: Expect<T> = collectionOnlyDomain.expect
}

internal class CollectionOnlyDomainImpl<E, T : Collection<E>>(
    override val expect: Expect<T>
) : CollectionOnlyDomain<E, T> {

    override fun isEmpty(): Assertion = collectionAssertions.isEmpty(expect)
    override fun isNotEmpty(): Assertion = collectionAssertions.isNotEmpty(expect)

    override val size: ExtractedFeaturePostStep<T, Int>
        get() = expect._domain.manualFeature(DescriptionCollectionAssertion.SIZE) { size }
}
