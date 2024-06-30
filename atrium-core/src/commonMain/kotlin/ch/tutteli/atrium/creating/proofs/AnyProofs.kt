package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of proof functions and builders which are applicable to any type (sometimes `Any?` sometimes `Any`).
 */
interface AnyProofs {

    fun <T> toEqual(container: ProofContainer<T>, expected: T): Proof
    fun <T> notToEqual(container: ProofContainer<T>, expected: T): Proof

    fun <T> toBeTheInstance(container: ProofContainer<T>, expected: T): Proof
    fun <T> notToBeTheInstance(container: ProofContainer<T>, expected: T): Proof

    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    fun <T, SubTypeOfT> toBeAnInstanceOf(
        container: ProofContainer<T>,
        subType: KClass<SubTypeOfT>
    ): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T
//
//    fun <T> notToBeAnInstanceOfAny(container: ProofContainer<T>, notExpectedTypes: List<KClass<*>>): Proof
//
    fun <T : Any> notToEqualNullButToBeAnInstanceOf(
        container: ProofContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T>

    fun <T> notToEqualOneIn(container: ProofContainer<T>, expected: Iterable<T>): Proof
}
