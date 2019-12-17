package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection],
 * which an implementation of the domain of Atrium has to provide.
 */
interface CollectionDomain<E, T : Collection<E>> : CollectionOnlyDomain<E, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Collection]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface CollectionOnlyDomain<E, T : Collection<E>> : ExpectDomain<T> {
    fun isEmpty(): Assertion
    fun isNotEmpty(): Assertion

    val size: ExtractedFeaturePostStep<T, Int>
}
