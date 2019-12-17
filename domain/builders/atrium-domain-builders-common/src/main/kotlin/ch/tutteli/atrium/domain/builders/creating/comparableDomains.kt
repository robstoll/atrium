@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.ComparableDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.ComparableOnlyDomainImpl
import ch.tutteli.atrium.domain.creating.ComparableDomain

val <E, T : Comparable<E>> Expect<T>._domain: ComparableDomain<E, T>
    get() = ComparableDomainImpl(ComparableOnlyDomainImpl(this), AnyDomainImpl(this))
