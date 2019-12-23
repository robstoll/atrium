package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NEXT_ELEMENT
import ch.tutteli.atrium.translations.DescriptionIterableAssertion.NO_ELEMENTS

internal class IterableDomainImpl<E, T : Iterable<E>>(
    iterableDomainSubImpl: IterableSubDomain<E, T>,
    anyDomain: AnyDomain<T>
) : IterableDomain<E, T>, IterableSubDomain<E, T> by iterableDomainSubImpl, AnyDomain<T> by anyDomain {
    override val expect: Expect<T> = iterableDomainSubImpl.expect
}

internal class IterableSubDomainImpl<E, T : Iterable<E>>(
    override val expect: Expect<T>
) : IterableSubDomain<E, T> {
    override val containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour> =
        iterableAssertions.containsBuilder(expect)

    override val containsNotBuilder: IterableContains.Builder<E, T, NotSearchBehaviour> =
        iterableAssertions.containsNotBuilder(expect)

    override fun hasNext(): Assertion =
        assertionBuilder.createDescriptive(expect, DescriptionBasic.HAS, RawString.create(NEXT_ELEMENT)) {
            it.iterator().hasNext()
        }

    override fun hasNotNext(): Assertion =
        assertionBuilder.createDescriptive(expect, DescriptionBasic.HAS_NOT, RawString.create(NEXT_ELEMENT)) {
            !it.iterator().hasNext()
        }
}

internal class IterableElementComparableDomainImpl<E : Comparable<E>, T : Iterable<E>>(
    iterableElementComparableSubDomain: IterableElementComparableSubDomain<E, T>,
    iterableDomain: IterableDomain<E, T>
) : IterableElementComparableDomain<E, T>,
    IterableElementComparableSubDomain<E, T> by iterableElementComparableSubDomain,
    IterableDomain<E, T> by iterableDomain {

    override val expect: Expect<T> = iterableElementComparableSubDomain.expect
}

internal class IterableElementComparableSubDomainImpl<E : Comparable<E>, T : Iterable<E>>(
    override val expect: Expect<T>
) : IterableElementComparableSubDomain<E, T> {
    override fun min(): ExtractedFeaturePostStep<T, E> = collect("min", Iterable<E>::min)

    override fun max(): ExtractedFeaturePostStep<T, E> = collect("max", Iterable<E>::max)

    private fun collect(
        method: String,
        collect: T.() -> E?
    ): ExtractedFeaturePostStep<T, E> {
        return expect._domain.featureExtractor
            .methodCall(method)
            .withRepresentationForFailure(NO_ELEMENTS)
            .withFeatureExtraction {
                Option.someIf(it.iterator().hasNext()) {
                    it.collect() ?: throw IllegalStateException(
                        "Iterable does not haveNext() even though checked before! Concurrent access?"
                    )
                }
            }
            .withoutOptions()
            .build()
    }

}

