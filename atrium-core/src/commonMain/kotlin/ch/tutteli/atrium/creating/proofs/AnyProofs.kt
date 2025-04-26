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
    fun <SubjectT> toEqual(container: ProofContainer<SubjectT>, expected: SubjectT): Proof

    /** @since 1.3.0 */
    fun <SubjectT> notToEqual(container: ProofContainer<SubjectT>, expected: SubjectT): Proof

    /** @since 1.3.0 */
    fun <SubjectT> toBeTheInstance(container: ProofContainer<SubjectT>, expected: SubjectT): Proof

    /** @since 1.3.0 */
    fun <SubjectT> notToBeTheInstance(container: ProofContainer<SubjectT>, expected: SubjectT): Proof

    /** @since 1.3.0 */
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
    fun <SubjectT, SubTypeOfSubjectT> toBeAnInstanceOf(
        container: ProofContainer<SubjectT>,
        subType: KClass<SubTypeOfSubjectT>
    ): SubjectChangerBuilder.ExecutionStep<SubjectT, SubTypeOfSubjectT> where SubTypeOfSubjectT : Any, SubTypeOfSubjectT : SubjectT

    /** @since 1.3.0 */
    fun <SubjectT> notToBeAnInstanceOf(container: ProofContainer<SubjectT>, types: List<KClass<*>>): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Any> toEqualNullIfNullGivenElse(
        container: ProofContainer<SubjectT?>,
        expectationCreatorOrNull: (Expect<SubjectT>.() -> Unit)?
    ): Proof

    /** @since 1.3.0 */
    fun <SubjectT : Any> notToEqualNullButToBeAnInstanceOf(
        container: ProofContainer<SubjectT?>,
        subType: KClass<SubjectT>
    ): SubjectChangerBuilder.ExecutionStep<SubjectT?, SubjectT>

    /** @since 1.3.0 */
    fun <SubjectT> notToEqualOneIn(container: ProofContainer<SubjectT>, expected: Iterable<SubjectT>): Proof
}
