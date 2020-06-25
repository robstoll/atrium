package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import kotlin.reflect.KClass

interface AnyAssertions {
    fun <T> toBe(container: AssertionContainer<T>, expected: T): Assertion
    fun <T> notToBe(container: AssertionContainer<T>, expected: T): Assertion
    fun <T> isSameAs(container: AssertionContainer<T>, expected: T): Assertion
    fun <T> isNotSameAs(container: AssertionContainer<T>, expected: T): Assertion

    fun <T : Any?> toBeNull(container: AssertionContainer<T>): Assertion

    fun <T : Any> toBeNullIfNullGivenElse(
        container: AssertionContainer<T?>,
        type: KClass<T>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion

    fun <T : Any> notToBeNull(container: AssertionContainer<T?>, subType: KClass<T>): ChangedSubjectPostStep<T?, T>

    //TODO restrict TSub with T once type parameter for upper bounds are supported:
    // https://youtrack.jetbrains.com/issue/KT-33262 is implemented
    fun <T, TSub : Any> isA(container: AssertionContainer<T>, subType: KClass<TSub>): ChangedSubjectPostStep<T, TSub>
}