package ch.tutteli.atrium.domain.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any]?,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomain<T> : Domain<T> {

    fun notToBe(expected: T): Assertion
    fun isSame(expected: T): Assertion
    fun isNotSame(expected: T): Assertion

    fun toBeNull(): Assertion

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
    fun <TSub : Any> isA(subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub>
}

/**
 * Defines the minimum set of assertion functions and builders applicable to types extending [Any],
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomainNonNullable<T : Any> : Domain<T> {

    fun toBe(expected: T): Assertion
}

/**
 * Defines the minimum set of assertion functions and builders applicable to nullable types extending [Any]?,
 * which an implementation of the domain of Atrium has to provide.
 */
interface AnyDomainOnlyNullable<T : Any> : Domain<T?> {

    fun toBeNullable(
        type: KClass<T>,
        expectedOrNull: T?
    ): Assertion

    fun toBeNullIfNullGivenElse(
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion
}
