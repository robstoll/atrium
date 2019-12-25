package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.translations.DescriptionListAssertion

internal class ListDomainImpl<E, T : List<E>>(
    listSubDomain: ListSubDomain<E, T>,
    collectionDomain: CollectionDomain<E, T>
) : ListDomain<E, T>, ListSubDomain<E, T> by listSubDomain, CollectionDomain<E, T> by collectionDomain {
    override val expect: Expect<T> = listSubDomain.expect
}

internal class ListSubDomainImpl<E, T : List<E>>(
    override val expect: Expect<T>
) : ListSubDomain<E, T> {
    override fun get(index: Int): ExtractedFeaturePostStep<T, E> =
        expect._domain.featureExtractor
            .methodCall("get", index)
            .withRepresentationForFailure(DescriptionListAssertion.INDEX_OUT_OF_BOUNDS)
            .withFeatureExtraction {
                Option.someIf(index < it.size) { it[index] }
            }
            .withoutOptions()
            .build()
}

internal class ListElementComparableDomainImpl<E : Comparable<E>, T : List<E>>(
    listElementComparableSubDomain: ListElementComparableSubDomain<E, T>,
    listSubDomain: ListSubDomain<E, T>,
    collectionElementComparableDomain: CollectionElementComparableDomain<E, T>
) : ListElementComparableDomain<E, T>,
    ListElementComparableSubDomain<E, T> by listElementComparableSubDomain,
    ListSubDomain<E, T> by listSubDomain,
    CollectionElementComparableDomain<E, T> by collectionElementComparableDomain {
    override val expect: Expect<T> = listElementComparableSubDomain.expect
}

internal class ListElementComparableSubDomainImpl<E : Comparable<E>, T : List<E>>(
    override val expect: Expect<T>
) : ListElementComparableSubDomain<E, T>
