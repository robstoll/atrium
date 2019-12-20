@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating.iterable.contains

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.iterable.contains.impl.CheckerOptionInAnyOrderDomainImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.impl.CheckerOptionNullableInAnyOrderDomainImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Access to the domain level of Atrium where this [IterableContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects which have a type extending [Iterable] and an element type extending [Any]`?`
 * as well as where the checkerOption is using an [InAnyOrderSearchBehaviour].
 */
val <E, T : Iterable<E>> IterableContains.CheckerOption<E, T, InAnyOrderSearchBehaviour>._domain
    get(): CheckerOptionInAnyOrderDomain<E, T> = CheckerOptionInAnyOrderDomainImpl(this)

/**
 * Access to the domain level of Atrium where this [IterableContains.CheckerOption] is passed along,
 * scoping it to the domain of subjects which have a type extending [Iterable]and a nullable element type
 * extending [Any] as well as where the checkerOption is using an [InAnyOrderSearchBehaviour].
 */
val <E : Any, T : Iterable<E?>> IterableContains.CheckerOption<E?, T, InAnyOrderSearchBehaviour>._domain
    get():  CheckerOptionNullableInAnyOrderDomain<E, T> = CheckerOptionNullableInAnyOrderDomainImpl(this)


/**
 * Represents the base interface for domains of [IterableContains.CheckerOption].
 *
 * @since 0.9.0
 */
interface CheckerOptionDomain<E, T : Iterable<E>, S : IterableContains.SearchBehaviour> {
    val checkerOption: IterableContains.CheckerOption<E, T, S>
}

/**
 * Represents the domains of [IterableContains.CheckerOption] with a [InAnyOrderSearchBehaviour].
 *
 * @since 0.9.0
 */
interface CheckerOptionInAnyOrderDomain<E, T : Iterable<E>> : CheckerOptionDomain<E, T, InAnyOrderSearchBehaviour> {
    fun values(expected: List<E>): Assertion
}

/**
 * Represents the domains of [IterableContains.CheckerOption] with a [InAnyOrderSearchBehaviour].
 *
 * @since 0.9.0
 */
interface CheckerOptionNullableInAnyOrderDomain<E : Any, T : Iterable<E?>> :
    CheckerOptionDomain<E?, T, InAnyOrderSearchBehaviour> {

    fun entries(assertionCreatorsOrNulls: List<(Expect<E>.() -> Unit)?>): Assertion
}
