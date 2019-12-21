package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.CollectionDomain
import ch.tutteli.atrium.domain.creating.ListDomain
import ch.tutteli.atrium.domain.creating.ListSubDomain
import ch.tutteli.atrium.domain.creating._domain
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
