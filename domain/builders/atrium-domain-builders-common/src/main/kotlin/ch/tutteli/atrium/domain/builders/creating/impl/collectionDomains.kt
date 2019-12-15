package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.CollectionDomain
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.collectionAssertions

internal class CollectionDomainImpl<E, T : Collection<E>>(override val expect: Expect<T>) : CollectionDomain<E, T> {
    override fun isEmpty(): Assertion = collectionAssertions.isEmpty(expect)
    override fun isNotEmpty(): Assertion = collectionAssertions.isNotEmpty(expect)
    override val size: ExtractedFeaturePostStep<T, Int> = collectionAssertions.size(expect)
}
