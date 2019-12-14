package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainNonNullableImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainOnlyNullableImpl
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.AnyDomainNonNullable
import ch.tutteli.atrium.domain.creating.AnyDomainOnlyNullable

val <T> Expect<T>._domain: AnyDomain<T> get() = AnyDomainImpl(this)
val <T : Any> Expect<T>._domain: AnyDomainNonNullable<T> get() = AnyDomainNonNullableImpl(this)

// we cannot use the same name since:
// - Kotlin has a bug in JS and the same mangled identifiert results as for <T: Any> above
// - isA would choose this overload instead of <T>
val <T : Any> Expect<T?>._domainNullable: AnyDomainOnlyNullable<T> get() = AnyDomainOnlyNullableImpl(this)

