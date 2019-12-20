package ch.tutteli.atrium.domain.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.*
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.utils.subExpect
import ch.tutteli.atrium.reporting.RawString
import kotlin.reflect.KClass

internal class AnyDomainImpl<T>(
    override val expect: Expect<T>
) : AnyDomain<T> {

    override fun notToBe(expected: T): Assertion = anyAssertions.notToBe(expect, expected)
    override fun isSame(expected: T): Assertion = anyAssertions.isSame(expect, expected)
    override fun isNotSame(expected: T): Assertion = anyAssertions.isNotSame(expect, expected)

    override fun <TSub : Any> isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub> =
        changeSubject.reportBuilder()
            .downCastTo(subType)
            .build()

    @Suppress("DEPRECATION" /* TODO implement here directly and remove annotation with 0.10.0 */)
    override fun <R> genericFeature(metaFeature: MetaFeature<R>): ExtractedFeaturePostStep<T, R> {
        val representation: Any = metaFeature.representation ?: RawString.NULL
        return expect._domain.featureExtractor
            .withDescription(metaFeature.description)
            .withRepresentationForFailure(representation)
            .withFeatureExtraction { metaFeature.maybeSubject }
            .withOptions { withRepresentation(representation) }
            .build()
    }
}


internal class AnyDomainNonNullableImpl<T : Any>(
    override val expect: Expect<T>,
    anyDomain: AnyDomain<T>
) : AnyDomainNonNullable<T>, AnyDomain<T> by anyDomain {

    override fun toBe(expected: T): Assertion = anyAssertions.toBe(expect, expected)
}


internal class AnyDomainOnlyNullableImpl<T : Any>(override val expect: Expect<T?>) : AnyDomainOnlyNullable<T> {

    override fun toBeNull(): Assertion = anyAssertions.toBeNull(expect)

    override fun toBeNullable(type: KClass<T>, expectedOrNull: T?): Assertion =
        toBeNullIfNullGivenElse(type, expectedOrNull?.let { subExpect<T> { addAssertion(_domain.toBe(it)) } })

    override fun toBeNullIfNullGivenElse(type: KClass<T>, assertionCreatorOrNull: (Expect<T>.() -> Unit)?): Assertion =
        when (assertionCreatorOrNull) {
            null -> toBeNull()
            else -> notToBeNull(type).collect(assertionCreatorOrNull)
        }
}
