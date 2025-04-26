// @formatter:off
//---------------------------------------------------
//  Generated content, modify:
//  gradle/build-logic/dev/src/main/kotlin/generation.kt
//  if necessary - enjoy the day 🙂
//---------------------------------------------------
package ch.tutteli.atrium.creating.proofs

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.transformers.SubjectChangerBuilder
import kotlin.reflect.KClass
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.proofs.impl.DefaultAnyProofs


    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.toEqual(expected: SubjectT): Proof =
    impl.toEqual(this, expected)

    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.notToEqual(expected: SubjectT): Proof =
    impl.notToEqual(this, expected)

    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.toBeTheInstance(expected: SubjectT): Proof =
    impl.toBeTheInstance(this, expected)

    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.notToBeTheInstance(expected: SubjectT): Proof =
    impl.notToBeTheInstance(this, expected)

    /** @since 1.3.0 */
    @Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
fun <SubjectT, SubTypeOfSubjectT> ProofContainer<SubjectT>.toBeAnInstanceOf(subType: KClass<SubTypeOfSubjectT>): SubjectChangerBuilder.ExecutionStep<SubjectT, SubTypeOfSubjectT> where SubTypeOfSubjectT : Any, SubTypeOfSubjectT : SubjectT =
    impl.toBeAnInstanceOf(this, subType)

    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.notToBeAnInstanceOf(types: List<KClass<*>>): Proof =
    impl.notToBeAnInstanceOf(this, types)

    /** @since 1.3.0 */
fun <SubjectT : Any> ProofContainer<SubjectT?>.toEqualNullIfNullGivenElse(expectationCreatorOrNull: (Expect<SubjectT>.() -> Unit)?): Proof =
    impl.toEqualNullIfNullGivenElse(this, expectationCreatorOrNull)

    /** @since 1.3.0 */
fun <SubjectT : Any> ProofContainer<SubjectT?>.notToEqualNullButToBeAnInstanceOf(subType: KClass<SubjectT>): SubjectChangerBuilder.ExecutionStep<SubjectT?, SubjectT> =
    impl.notToEqualNullButToBeAnInstanceOf(this, subType)

    /** @since 1.3.0 */
fun <SubjectT> ProofContainer<SubjectT>.notToEqualOneIn(expected: Iterable<SubjectT>): Proof =
    impl.notToEqualOneIn(this, expected)

@OptIn(ExperimentalNewExpectTypes::class)
private inline val <T> ProofContainer<T>.impl: AnyProofs
    get() = getImpl(AnyProofs::class) { DefaultAnyProofs() }
