package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass

/**
 * Collection of proof functions and builders which are applicable to any type (sometimes `Any?` sometimes `Any`).
 *
 * @since 1.3.0
 */
interface AnyProofs {

    /** @since 1.3.0 */
    fun <T> toEqual(container: ProofContainer<T>, expected: T): Proof

    /** @since 1.3.0 */
    fun <T> notToEqual(container: ProofContainer<T>, expected: T): Proof

    /** @since 1.3.0 */
    fun <T> toBeTheInstance(container: ProofContainer<T>, expected: T): Proof

    /** @since 1.3.0 */
    fun <T> notToBeTheInstance(container: ProofContainer<T>, expected: T): Proof

    /** @since 1.3.0 */
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    fun <T, SubTypeOfT> toBeAnInstanceOf(
        container: ProofContainer<T>,
        subType: KClass<SubTypeOfT>
    ): SubjectChangerBuilder.ExecutionStep<T, SubTypeOfT> where SubTypeOfT : Any, SubTypeOfT : T

    /** @since 1.3.0 */
    fun <T> notToBeAnInstanceOf(container: ProofContainer<T>, types: List<KClass<*>>): Proof

    /** @since 1.3.0 */
    fun <T : Any> toEqualNullIfNullGivenElse(
        container: ProofContainer<T?>,
        expectationCreatorOrNull: (Expect<T>.() -> Unit)?
    ): Proof

    /** @since 1.3.0 */
    fun <T : Any> notToEqualNullButToBeAnInstanceOf(
        container: ProofContainer<T?>,
        subType: KClass<T>
    ): SubjectChangerBuilder.ExecutionStep<T?, T>

    /** @since 1.3.0 */
    fun <T> notToEqualOneIn(container: ProofContainer<T>, expected: Iterable<T>): Proof
}
