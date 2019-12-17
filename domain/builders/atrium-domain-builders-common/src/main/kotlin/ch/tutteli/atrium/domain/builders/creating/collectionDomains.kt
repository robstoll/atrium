@file:Suppress("ObjectPropertyName")

package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.CollectionDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.CollectionOnlyDomainImpl
import ch.tutteli.atrium.domain.creating.CollectionDomain

val <E, T : Collection<E>> Expect<T>._domain: CollectionDomain<E, T>
    get() = CollectionDomainImpl(CollectionOnlyDomainImpl(this), AnyDomainImpl(this))
