@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.domain.creating.impl.*
import kotlin.js.JsName

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [List];
 * i.e. it returns a [ListDomain] for this [Expect].
 */
val <E, T : List<E>> Expect<T>._domain: ListDomain<E, T>
    get() = ListDomainImpl(
        ListSubDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        CollectionDomainImpl(
            CollectionSubDomainImpl(this),
            IterableDomainImpl(IterableSubDomainImpl(this), AnyDomainImpl(this, AnyInclNullableDomainImpl(this)))
        )
    )

/**
 * Access to the domain level of Atrium where this [Expect] is passed along,
 * scoping it to the domain of subjects whose type extends [List] with an element type extending [Comparable];
 * i.e. it returns a [ListDomain] for this [Expect].
 */
val <E : Comparable<E>, T : List<E>> Expect<T>._domain: ListElementComparableDomain<E, T>
    // note, the generated JS is wrong but it works due to dynamic typing in JS and
    // as long as this definition is defined after the one above (as it is a subtype)
    // TODO remove this comment as soon as https://youtrack.jetbrains.com/issue/KT-33294 is fixed
    @JsName("_domainListElementComparable")
    get() = ListElementComparableDomainImpl(
        ListElementComparableSubDomainImpl(this),
        ListSubDomainImpl(this),
        //TODO simplify once we have expect.config.impl in 0.10.0
        CollectionElementComparableDomainImpl(
            CollectionElementComparableSubDomainImpl(this),
            CollectionSubDomainImpl(this),
            IterableElementComparableDomainImpl(
                IterableElementComparableSubDomainImpl(this),
                IterableDomainImpl(IterableSubDomainImpl(this), AnyDomainImpl(this, AnyInclNullableDomainImpl(this)))
            )
        )
    )

/**
 * Represents the [ExpectDomain] whose type extends [List];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface ListDomain<E, T : List<E>> : ListSubDomain<E, T>, CollectionDomain<E, T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [List]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [List] (e.g. the functions of the [AnyDomain]).
 */
interface ListSubDomain<E, T : List<E>> : ExpectDomain<T> {

    fun get(index: Int): ExtractedFeaturePostStep<T, E>
}

/**
 * Represents the [ExpectDomain] whose type extends [List] with an element type extending [Comparable];
 * i.e. the subject of the underlying [expect] has such a type.
 */
interface ListElementComparableDomain<E : Comparable<E>, T : List<E>> :
    ListElementComparableSubDomain<E, T>,
    ListDomain<E, T>,
    CollectionElementComparableDomain<E, T>

/**
 * Represents a sub-[ExpectDomain] whose type extends [List] with an element type extending [Comparable]
 * -- i.e. the subject of the underlying [expect] has such a type --
 * where it does not include the sub domains of super types of [List] (e.g. the functions of the [AnyDomain]).
 */
interface ListElementComparableSubDomain<E : Comparable<E>, T : List<E>> : ExpectDomain<T>
