package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.creating.impl.CollectionDomainImpl
import ch.tutteli.atrium.domain.creating.impl.CollectionOnlyDomainImpl

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Collection].
 *
 * @since 0.9.0
 */
val <E, T : Collection<E>> Expect<T>._domain: CollectionDomain<E, T>
    get() = CollectionDomainImpl(CollectionOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection],
 * which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
 */
interface CollectionDomain<E, T : Collection<E>> : CollectionOnlyDomain<E, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 *
 * @since 0.9.0
 */
interface CollectionOnlyDomain<E, T : Collection<E>> : ExpectDomain<T> {
    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion

    val size: ExtractedFeaturePostStep<T, Int>
}
