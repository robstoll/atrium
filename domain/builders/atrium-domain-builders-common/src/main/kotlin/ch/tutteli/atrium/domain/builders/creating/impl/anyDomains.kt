package ch.tutteli.atrium.domain.builders.creating.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.AnyDomain
import ch.tutteli.atrium.domain.creating.AnyDomainNonNullable
import ch.tutteli.atrium.domain.creating.AnyDomainOnlyNullable
import ch.tutteli.atrium.domain.creating.anyAssertions
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

internal class AnyDomainImpl<T>(override val expect: Expect<T>) : AnyDomain<T> {

    override fun notToBe(expected: T): Assertion = anyAssertions.notToBe(expect, expected)
    override fun isSame(expected: T): Assertion = anyAssertions.isSame(expect, expected)
    override fun isNotSame(expected: T): Assertion = anyAssertions.isNotSame(expect, expected)

    override fun toBeNull(): Assertion = anyAssertions.toBeNull(expect)

    override fun <TSub : Any> isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub> =
        anyAssertions.isA(expect, subType)
}

internal class AnyDomainNonNullableImpl<T : Any>(override val expect: Expect<T>) : AnyDomainNonNullable<T> {
    override fun toBe(expected: T): Assertion = anyAssertions.toBe(expect, expected)
}


internal class AnyDomainOnlyNullableImpl<T : Any>(override val expect: Expect<T?>) : AnyDomainOnlyNullable<T> {
    override fun toBeNullable(type: KClass<T>, expectedOrNull: T?) =
        anyAssertions.toBeNullable(expect, type, expectedOrNull)

    override fun toBeNullIfNullGivenElse(
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion = anyAssertions.toBeNullIfNullGivenElse(expect, type, assertionCreatorOrNull)
}
