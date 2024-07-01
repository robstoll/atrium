//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of assertion functions and builders which are applicable to any type (sometimes `Any?` sometimes `Any`).
 */
@Deprecated("Switch to AnyProofs, will be removed with 2.0.0 at the latest")
interface AnyAssertions {
    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toEqual, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toEqual(expected)", "ch.tutteli.atrium.creating.proofs.toEqual")
    )
    fun <T> toBe(container: AssertionContainer<T>, expected: T): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToEqual, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToEqual(expected)", "ch.tutteli.atrium.creating.proofs.notToEqual")
    )
    fun <T> notToBe(container: AssertionContainer<T>, expected: T): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeTheInstance, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeTheInstance(expected)", "ch.tutteli.atrium.creating.proofs.toBeTheInstance")
    )
    fun <T> isSameAs(container: AssertionContainer<T>, expected: T): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use notToBeTheInstance, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.notToBeTheInstance(expected)", "ch.tutteli.atrium.creating.proofs.notToBeTheInstance")
    )
    fun <T> isNotSameAs(container: AssertionContainer<T>, expected: T): Assertion

    @Deprecated(
        "Migrate from AssertionContainer to ProofContainer and use toBeAnInstanceOf, will be removed with 2.0.0 at the latest",
        ReplaceWith("this.toBeAnInstanceOf(subType)", "ch.tutteli.atrium.creating.proofs.toBeAnInstanceOf")
    )
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    fun <T, SubTypeOfT> isA(
        container: AssertionContainer<T>,
        subType: KClass<SubTypeOfT>
    ): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T


    fun <T> notToBeAnInstanceOf(container: AssertionContainer<T>, notExpectedTypes: List<KClass<*>>): Assertion

    fun <T : Any> toBeNullIfNullGivenElse(
        container: AssertionContainer<T?>,
        assertionCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Assertion

    fun <T : Any> notToBeNullButOfType(
        container: AssertionContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T>


    fun <T> isNotIn(container: AssertionContainer<T>, expected: Iterable<T>): Assertion

    fun <T> because(
        container: AssertionContainer<T>,
        reason: String,
        assertionCreator: (Expect<T>.() -> Unit)
    ): Assertion
}
