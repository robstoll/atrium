@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.impl.*
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.*

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [NoOpSearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, NoOpSearchBehaviour>._domain
    get(): BuilderNoOpDomain<E, T> = BuilderNoOpDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [NoOpSearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>._domain
    get(): BuilderInAnyOrderDomain<E, T> = BuilderInAnyOrderDomainImpl(this)


/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [InAnyOrderOnlySearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>._domain
    get(): BuilderInAnyOrderOnlyDomain<E, T> = BuilderInAnyOrderOnlyDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable]and a nullable element type
 * extending [Any] as well as where the builder is using an [InAnyOrderOnlySearchBehaviour].
 */
val <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InAnyOrderOnlySearchBehaviour>._domain
    get():  BuilderNullableInAnyOrderOnlyDomain<E, T> = BuilderNullableInAnyOrderOnlyDomainImpl(this)


/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [InOrderSearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderSearchBehaviour>._domain
    get(): BuilderInOrderDomain<E, T> = BuilderInOrderDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [InOrderOnlySearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>._domain
    get(): BuilderInOrderOnlyDomain<E, T> = BuilderInOrderOnlyDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable]and a nullable element type
 * extending [Any] as well as where the builder is using an [InOrderOnlySearchBehaviour].
 */
val <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlySearchBehaviour>._domain
    get():  BuilderNullableInOrderOnlyDomain<E, T> = BuilderNullableInOrderOnlyDomainImpl(this)


/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable] and an element type extending [Any]`?`
 * as well as where the builder is using an [InOrderOnlyGroupedSearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>._domain
    get(): BuilderInOrderOnlyGroupedDomain<E, T> = BuilderInOrderOnlyGroupedDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.Builder] is passed along,
 * scoping it to the domain of subjects whose type extends [Iterable]and a nullable element type
 * extending [Any] as well as where the builder is using an [InOrderOnlyGroupedSearchBehaviour].
 */
val <E : Any, T : Iterable<E?>> IterableContains.Builder<E?, T, InOrderOnlyGroupedSearchBehaviour>._domain
    get():  BuilderNullableInOrderOnlyGroupedDomain<E, T> = BuilderNullableInOrderOnlyGroupedDomainImpl(this)


/**
 * Represents the base interface for domains of [IterableContains.Builder].
 *
 * @since 0.9.0
 */
interface BuilderDomain<E, T : Iterable<E>, S : IterableContains.SearchBehaviour> {
    val builder: IterableContains.Builder<E, T, S>
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InAnyOrderSearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderNoOpDomain<E, T : Iterable<E>> : BuilderDomain<E, T, NoOpSearchBehaviour> {
    val inAnyOrder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
    val inOrder: IterableContains.Builder<E, T, InOrderSearchBehaviour>
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InAnyOrderSearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderInAnyOrderDomain<E, T : Iterable<E>> : BuilderDomain<E, T, InAnyOrderSearchBehaviour> {
    val only: IterableContains.Builder<E, T, InAnyOrderOnlySearchBehaviour>
}


/**
 * Represents the domains of [IterableContains.Builder] with a [InAnyOrderOnlySearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderInAnyOrderOnlyDomain<E, T : Iterable<E>> : BuilderDomain<E, T, InAnyOrderOnlySearchBehaviour> {
    fun values(expected: List<E>): Assertion
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InAnyOrderOnlySearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderNullableInAnyOrderOnlyDomain<E : Any, T : Iterable<E?>> :
    BuilderDomain<E?, T, InAnyOrderOnlySearchBehaviour> {

    fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InOrderSearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderInOrderDomain<E, T : Iterable<E>> : BuilderDomain<E, T, InOrderSearchBehaviour> {
    val only: IterableContains.Builder<E, T, InOrderOnlySearchBehaviour>
}


/**
 * Represents the domains of [IterableContains.Builder] with a [InOrderOnlySearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderInOrderOnlyDomain<E, T : Iterable<E>> : BuilderDomain<E, T, InOrderOnlySearchBehaviour> {
    val grouped: IterableContains.Builder<E, T, InOrderOnlyGroupedSearchBehaviour>

    fun values(expected: List<E>): Assertion
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InOrderOnlySearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderNullableInOrderOnlyDomain<E : Any, T : Iterable<E?>> :
    BuilderDomain<E?, T, InOrderOnlySearchBehaviour> {

    fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion
}


/**
 * Represents the domains of [IterableContains.Builder] with a [InOrderOnlyGroupedSearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderInOrderOnlyGroupedDomain<E, T : Iterable<E>> : BuilderDomain<E, T, InOrderOnlyGroupedSearchBehaviour> {
    val within: IterableContains.Builder<E, T, InOrderOnlyGroupedWithinSearchBehaviour>

    fun values(groups: List<List<E>>): Assertion
}

/**
 * Represents the domains of [IterableContains.Builder] with a [InOrderOnlyGroupedSearchBehaviour].
 *
 * @since 0.9.0
 */
interface BuilderNullableInOrderOnlyGroupedDomain<E : Any, T : Iterable<E?>> :
    BuilderDomain<E?, T, InOrderOnlyGroupedSearchBehaviour> {

    fun entries(groups: List<List<(Expect<E>.() -> Unit)?>>): Assertion
}
