package ch.tutteli.atrium.domain.creating.transformers

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreator
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.domain._domain
import ch.tutteli.atrium.domain.changeSubject
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.reportables.ProofExplanation

/**
 * Defines the contract to change the subject of a [ProofContainer] by creating
 * a new [Expect] with the new subject whereas the new [Expect] delegates proof checking to the given original
 * [ProofContainer].
 */
interface SubjectChanger {

    /**
     * Changes to a new subject according to the given [transformation] without showing it
     * in reporting and returns a new [Expect] for the new subject.
     *
     * Explained a bit more in depth: it creates a new [Expect] incorporating the given [transformation]
     * whereas the new [Expect] delegates proof checking to the given [container] -
     * the change as such will not be reflected in reporting.
     *
     * This method is useful if you want to create proofs about a feature of the subject, but do not want that
     * the feature extraction shows up in reporting. For instance, if a class can behave as another class
     * (e.g. `Sequence::asIterable`) or you want to hide a conversion (e.g. `Int::toChar`) then you can use this
     * function.
     *
     * Notice, in case the change to the new subject is not always safe (you expect it to be safe, but it does not have
     * to be), then you should use [reported] instead, so that the expectation is reflected in reporting.
     *
     * @param container the proof container with the current subject (before the change).
     * @param transformation Provides the subject (most likely based on the current subject but is not a requirement).
     *
     * @param SubjectT The type of the current subject (before the change).
     * @param SubjectAfterChangeT the type of the new subject.
     *
     * @return The newly created [Expect] for the extracted feature.
     */
    fun <SubjectT, SubjectAfterChangeT> unreported(
        container: ProofContainer<SubjectT>,
        transformation: (SubjectT) -> SubjectAfterChangeT
    ): Expect<SubjectAfterChangeT>


    /**
     * Changes to a new subject according to the given [transformation] --
     * the change as such is reflected in reporting by the given [description] and [representation].
     *
     * Explained a bit more in depth: it creates a new [Expect] incorporating the given [transformation]
     * whereas the new [Expect] delegates proof checking to the given [container].
     * The [transformation] as such can either return the new subject wrapped in a [Some] or return [None] in case
     * the transformation cannot be carried out.
     *
     * This method is useful if you want to change the subject whereas the change as such is kind of a proof as well,
     * in the sense of "I expect the transformation xy to succeed", i.e. it should be included in reporting as well.
     * For instance, say you want to change the subject of type `Int?` to `Int`.
     * Since the subject could also be `null` it makes sense to report this proof instead of failing
     * with an exception.
     *
     * @param container the proof container with the current subject (before the change)
     * @param description Describes the kind of subject change (e.g. in case of a type change `to be an instance of).
     * @param representation Representation of the change (e.g. in case of a type transformation the KClass).
     * @param transformation Provides the subject wrapped into a [Some] if the extraction as such can be carried out
     *   otherwise [None].
     * @param failureHandler The [FailureHandler] which shall be used in case the subject cannot be transformed.
     *   A failure handler has the chance to augment the failing proof representing the failed transformation
     *   with further information (and hints).
     * @param maybeSubExpectationCreatorAndUsageHints Optionally, a pair where the first element represents subsequent
     *   expectations for the new subject. This is especially useful if the change fails since we can then
     *   already show to you (in error reporting) what you expect about the new subject (which gives you more
     *   context to the error). The second element are usage hints in case the [ExpectationCreator] does not create (
     *   or at least not append) any [Proof] to the new [Expect]. The usage hints shall indicate what other overload
     *   (or expectation function) should have been used if one really doesn't want to create additional expectations.
     *   Whenever a user creates an [ExpectationCreator] then it is best practice to fail if no [Proof] was appended
     *   to a corresponding [ProofContainer]. Provide an empty list in case you don't want that a usage hint is shown
     *   (or there is simply no alternative) in which case only the failing expectation is shown.
     *
     * @param SubjectT The type of the current subject (before the change).
     * @param SubjectAfterChangeT the type of the new subject.
     *
     * @return The newly created [Expect] for the new subject.
     */
    fun <SubjectT, SubjectAfterChangeT> reported(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        representation: Any,
        transformation: (SubjectT) -> Option<SubjectAfterChangeT>,
        failureHandler: FailureHandler<SubjectT, SubjectAfterChangeT>,
        maybeSubExpectationCreatorAndUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ): Expect<SubjectAfterChangeT>

    /**
     * Represents a handler which is responsible to create the [Proof] resulting from a failed subject change.
     *
     * A handler should augment the failing proof with a [ProofExplanation] in case the user supplied an
     * [ExpectationCreator]. Yet, a failure handler might also add additional information -- e.g. regarding the
     * current subject.
     *
     * @param SubjectT The type of the subject
     * @param SubjectAfterChangeT The type of the subject after the subject change (if it were possible).
     */
    interface FailureHandler<SubjectT, SubjectAfterChangeT> {
        /**
         * Creates the failing [Proof] most likely based on the given [proof] -- which in turn
         * is based on the previously specified description, representation etc. -- and should incorporate
         * the proofs the [Pair.first] element of [maybeExpectationCreatorWithUsageHints] would have created (if given
         * for the new subject) as well as the usage hints provided as [Pair.second] element.
         *
         * @return The newly created failing [Proof].
         */
        fun createProof(
            container: ProofContainer<SubjectT>,
            proof: Proof,
            maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
        ): Proof
    }
}

/**
 * Represents a [SubjectChanger.FailureHandler] which acts as an adapter for another failure handler by mapping first
 * the given subject to another type [SubjectIntermediateT] which is understood as input of the other failure handler.
 *
 * Effectively turning a `FailureHandler<SubjectIntermediateT, SubjectAfterChangeT>` into a
 * `FailureHandler<SubjectT, SubjectAfterChangeT>` with the help of a mapping
 * function `(SubjectT) -> SubjectIntermediateT`
 *
 * @param SubjectT The type of the subject
 * @param SubjectIntermediateT The type of the mapped subject
 * @param SubjectAfterChangeT The type of the subject after the subject change (if it were possible).
 */
class FailureHandlerAdapter<SubjectT, SubjectIntermediateT, SubjectAfterChangeT>(
    private val failureHandler: SubjectChanger.FailureHandler<SubjectIntermediateT, SubjectAfterChangeT>,
    private val map: (SubjectT) -> SubjectIntermediateT
) : SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {
    override fun createProof(
        container: ProofContainer<SubjectT>,
        proof: Proof,
        maybeExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ): Proof = container.changeSubject.unreported(map).let {
        failureHandler.createProof(it._domain, proof, maybeExpectationCreatorWithUsageHints)
    }
}



