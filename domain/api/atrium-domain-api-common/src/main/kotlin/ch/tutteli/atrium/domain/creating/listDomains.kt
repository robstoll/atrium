@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [List].
 */
val <E, T : List<E>> Expect<T>._domain: ListDomain<E, T>
    get() = ListDomainImpl(
        ListOnlyDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        CollectionDomainImpl(
            CollectionOnlyDomainImpl(this),
            IterableDomainImpl(IterableOnlyDomainImpl(this), AnyDomainImpl(this))
        )
    )

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [List],
 * which an implementation of the domain of Atrium has to provide.
 */
interface ListDomain<E, T : List<E>> : ListOnlyDomain<E, T>, CollectionDomain<E, T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [List]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface ListOnlyDomain<E, T : List<E>> : ExpectDomain<T> {

    fun get(index: Int): ExtractedFeaturePostStep<T, E>
}
