package ch.tutteli.atrium.domain.builders.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainNonNullableImpl
import ch.tutteli.atrium.domain.builders.creating.impl.AnyDomainOnlyNullableImpl
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.AnyDomainNonNullable
import ch.tutteli.atrium.domain.creating.AnyDomainOnlyNullable

//TODO inline functions and replace the following bodies with: (expect as DomainExpect<T>).config.impl()
fun <T> anyDomain(expect: Expect<T>): AnyDomain<T> = AnyDomainImpl(expect)
fun <T : Any> anyDomainNonNullable(expect: Expect<T>): AnyDomainNonNullable<T> = AnyDomainNonNullableImpl(expect)
fun <T : Any> anyDomainOnlyNullable(expect: Expect<T?>): AnyDomainOnlyNullable<T> = AnyDomainOnlyNullableImpl(expect)
