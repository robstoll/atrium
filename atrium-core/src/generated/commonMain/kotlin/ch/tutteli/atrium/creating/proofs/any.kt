// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultAnyProofs


    /**
     * @since 1.3.0
     */
fun <T> ProofContainer<T>.toEqual(expected: T): Proof = impl.toEqual(this, expected)

    /**
     * @since 1.3.0
     */
fun <T> ProofContainer<T>.notToEqual(expected: T): Proof = impl.notToEqual(this, expected)

    /**
     * @since 1.3.0
     */
fun <T> ProofContainer<T>.toBeTheInstance(expected: T): Proof = impl.toBeTheInstance(this, expected)

    /**
     * @since 1.3.0
     */
fun <T> ProofContainer<T>.notToBeTheInstance(expected: T): Proof = impl.notToBeTheInstance(this, expected)

    /**
     * @since 1.3.0
     */
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, SubTypeOfT> ProofContainer<T>.toBeAnInstanceOf(subType: KClass<SubTypeOfT>): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T = impl.toBeAnInstanceOf(this, subType)

    /**
     * @since 1.3.0
     */
fun <T> ProofContainer<T>.notToBeAnInstanceOf(types: List<KClass<*>>): Proof = impl.notToBeAnInstanceOf(this, types)

fun <T : Any> ProofContainer<T?>.toEqualNullIfNullGivenElse(expectationCreatorOrNull: (Expect<T>.() -> Unit)?): Proof = impl.toEqualNullIfNullGivenElse(this, expectationCreatorOrNull)

    /**
     * @since 1.3.0
     */
fun <T : Any> ProofContainer<T?>.notToEqualNullButToBeAnInstanceOf(subType: KClass<T>): SubjectChangerBuilder.ExecutionStep<T?, T> = impl.notToEqualNullButToBeAnInstanceOf(this, subType)

fun <T> ProofContainer<T>.notToEqualOneIn(expected: Iterable<T>): Proof = impl.notToEqualOneIn(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: AnyProofs
    get() = getImpl(AnyProofs::class) { DefaultAnyProofs() }
