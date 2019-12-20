@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Collection].
 */
val <E, T : Collection<E>> Expect<T>._domain: CollectionDomain<E, T>
    get() = CollectionDomainImpl(
        CollectionOnlyDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        IterableDomainImpl(IterableOnlyDomainImpl(this), AnyDomainImpl(this))
    )

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CollectionDomain<E, T : Collection<E>> : CollectionOnlyDomain<E, T>, IterableDomain<E, T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface CollectionOnlyDomain<E, T : Collection<E>> : ExpectDomain<T> {
    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion
    /**
     * @since 0.9.0
     */
    val size: ExtractedFeaturePostStep<T, Int>
}
