@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NotSearchBehaviour

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Iterable].
 */
val <E, T : Iterable<E>> Expect<T>._domain: IterableDomain<E, T>
    get() = IterableDomainImpl(IterableOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Iterable].
 */
//TODO rename to _domain with 1.0.0 if https://youtrack.jetbrains.com/issue/KT-32451 is fixed
val <E : Comparable<E>, T : Iterable<E>> Expect<T>._domainComparable: IterableElementComparableDomain<E, T>
    get() = IterableElementComparableDomainImpl(IterableElementComparableOnlyDomainImpl(this), AnyDomainImpl(this))


/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects which have a type extending [Iterable].
 */
//TODO rename to _domain with 1.0.0 if https://youtrack.jetbrains.com/issue/KT-32451 is fixed
val <E : Any, T : Iterable<E?>> Expect<T>._domainNullable: IterableElementNullableDomain<E, T>
    get() = IterableElementNullableDomainImpl(IterableElementNullableOnlyDomainImpl(this), AnyDomainImpl(this))

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableDomain<E, T : Iterable<E>> : IterableOnlyDomain<E, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable]
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface IterableOnlyDomain<E, T : Iterable<E>> : ExpectDomain<T> {
    val containsBuilder: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    val containsNotBuilder: IterableContains.Builder<E, T, NotSearchBehaviour>

    /**
     * @since 0.9.0
     */
    fun hasNext(): Assertion

    /**
     * @since 0.9.0
     */
    fun hasNotNext(): Assertion
}


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable]
 * and having an element type extending [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableElementComparableDomain<E : Comparable<E>, T : Iterable<E>> :
    IterableElementComparableOnlyDomain<E, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable]
 * and having an element type extending [Comparable] --
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface IterableElementComparableOnlyDomain<E : Comparable<E>, T : Iterable<E>> : ExpectDomain<T> {
    /**
     * @since 0.9.0
     */
    fun min(): ExtractedFeaturePostStep<T, E>

    /**
     * @since 0.9.0
     */
    fun max(): ExtractedFeaturePostStep<T, E>
}


/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable]
 * and having an element type extending [Comparable],
 * which an implementation of the domain of Atrium has to provide.
 */
interface IterableElementNullableDomain<E : Any, T : Iterable<E?>> :
    IterableElementNullableOnlyDomain<E, T>, AnyDomain<T>

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Iterable]
 * and having a nullable element type extending [Any] --
 * excluding the assertion functions which are defined on domains of super types
 * (e.g. the functions of the [AnyDomain]), which an implementation of the domain of Atrium has to provide.
 */
interface IterableElementNullableOnlyDomain<E : Any, T : Iterable<E?>> : ExpectDomain<T> {
    fun all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion
}

