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
 * scoping it to the domain of subjects whose type extends [Iterable];
 * i.e. it returns a [IterableDomain] for this [Expect].
 */
val <E, T : Iterable<E>> Expect<T>._domain: IterableDomain<E, T>
    get() = IterableDomainImpl(IterableSubDomainImpl(this), AnyDomainImpl(this))


/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] with an element type extending [Comparable];
 * i.e. it returns a [IterableElementComparableDomain] for this [Expect].
 */
//TODO rename to _domain with 1.0.0 if https://youtrack.jetbrains.com/issue/KT-32451 is fixed
val <E : Comparable<E>, T : Iterable<E>> Expect<T>._domainComparable: IterableElementComparableDomain<E, T>
    get() = IterableElementComparableDomainImpl(IterableElementComparableSubDomainImpl(this), AnyDomainImpl(this))


/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] with a nullable element type;
 * i.e. it returns a [IterableElementNullableDomain] for this [Expect].
 */
//TODO rename to _domain with 1.0.0 if https://youtrack.jetbrains.com/issue/KT-32451 is fixed
val <E : Any, T : Iterable<E?>> Expect<T>._domainNullable: IterableElementNullableDomain<E, T>
    get() = IterableElementNullableDomainImpl(IterableElementNullableSubDomainImpl(this), AnyDomainImpl(this))

/**
 * Represents the [ExpectDomain] whose type extends [Iterable];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface IterableDomain<E, T : Iterable<E>> : IterableSubDomain<E, T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Iterable]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Iterable] (e.g. the functions of the [AnyDomain]).
 */
interface IterableSubDomain<E, T : Iterable<E>> : ExpectDomain<T> {
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
 * Represents the [ExpectDomain] whose type extends [Iterable] with an element type extending [Comparable];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface IterableElementComparableDomain<E : Comparable<E>, T : Iterable<E>> :
    IterableElementComparableSubDomain<E, T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Iterable] with an element type extending [Comparable];
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Iterable] (e.g. the functions of the [AnyDomain]).
 */
interface IterableElementComparableSubDomain<E : Comparable<E>, T : Iterable<E>> : ExpectDomain<T> {
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
 * Represents the [ExpectDomain] whose type extends [Iterable] with a nullable element type;
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface IterableElementNullableDomain<E : Any, T : Iterable<E?>> :
    IterableElementNullableSubDomain<E, T>, AnyDomain<T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [Iterable] with a nullable element type;
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [Iterable] (e.g. the functions of the [AnyDomain]).
 */
interface IterableElementNullableSubDomain<E : Any, T : Iterable<E?>> : ExpectDomain<T> {
    fun all(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion
}

