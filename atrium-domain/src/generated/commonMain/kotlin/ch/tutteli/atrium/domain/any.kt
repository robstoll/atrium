// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.domain

import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.domain.impl.DefaultAnyProofs


fun <T> ProofContainer<T>.toEqual(expected: T): Proof = impl.toEqual(this, expected)
fun <T> ProofContainer<T>.notToEqual(expected: T): Proof = impl.notToEqual(this, expected)

fun <T> ProofContainer<T>.toBeTheInstance(expected: T): Proof = impl.toBeTheInstance(this, expected)
fun <T> ProofContainer<T>.notToBeTheInstance(expected: T): Proof = impl.notToBeTheInstance(this, expected)

    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <T, SubTypeOfT> ProofContainer<T>.toBeAnInstanceOf(subType: KClass<SubTypeOfT>): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T = impl.toBeAnInstanceOf(this, subType)
//
//fun <T> ProofContainer<T>.notToBeAnInstanceOfAny(notExpectedTypes: List<KClass<*>>): Proof = impl.notToBeAnInstanceOfAny(this, notExpectedTypes)
//
//    fun <T : Any> notToEqualNullButToBeAnInstanceOf(
//        container: ProofContainer<T?>,
//        subType: KClass<T>
//    ): SubjectChangerBuilder.ExecutionStep<T?, T>
//
//    fun <T : Any> toEqualNullIfNullGivenElse(
//        container: ProofContainer<T?>,
//        expectationCreatorOrNull: (Expect<T>.() -> Unit)?
//    ): Proof

fun <T> ProofContainer<T>.notToEqualOneIn(expected: Iterable<T>): Proof = impl.notToEqualOneIn(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: AnyProofs
    get() = getImpl(AnyProofs::class) { DefaultAnyProofs() }
