package ch.tutteli.atrium.creating.transformers

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.reportables.InlineElement

/**
 * Defines the contract for sophisticated `safe feature extractions` including expectation creation for the feature.
 *
 * It is similar to [ch.tutteli.atrium.creating.proofs.FeatureExtractors] but differs in the intended usage.
 * [FeatureExtractors] are intended to state expectations about a return value of a method call or a property,
 * assuming that the call as such always succeeds (no exception is thrown).
 * The [FeatureExtractor] on the other hand should be used if it is already known,
 * that the call/access fails depending on given arguments.
 * For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 *
 * @since 1.3.0
 */
interface FeatureExtractor {

    /**
     * Extracts a feature according to the given [featureExtraction], creates an [Expect] for it (the
     * new subject) and applies the [ExpectationCreator] if defined in [maybeSubExpectationCreatorAndUsageHints].
     *
     * @param container the proof container with the current subject
     * @param description Describes the feature
     * @param representationForFailure Representation in case the extraction cannot be carried out.
     * @param featureExtraction Extracts the feature where it returns the feature wrapped into a [Some] if the
     *   extraction as such can be carried out, otherwise [None].
     * @param maybeSubExpectationCreatorAndUsageHints Optionally, a pair where the first element represents subsequent
     *   expectations for the feature (the new subject). This is especially useful if the change fails since we can then
     *   already show to you (in error reporting) what you expect about the feature (which gives you more
     *   context to the error). The second element are usage hints in case the [ExpectationCreator] does not create (
     *   or at least not append) any [Proof] to the new [Expect]. The usage hints shall indicate what other overload
     *   (or expectation function) should have been used if one really doesn't want to create additional expectations.
     *   Whenever a user creates an [ExpectationCreator] then it is best practice to fail if no [Proof] was appended
     *   to a corresponding [ProofContainer]. Provide an empty list in case you don't want that a usage hint is shown
     *   (or there is simply no alternative) in which case only the failing expectation is shown.
     * @param featureExpectOptions Additional options which shall apply to the feature extraction.
     *
     * @param SubjectT The type of the current subject.
     * @param FeatureT the type of the extracted feature.
     *
     * @return The newly created [FeatureExpect] for the extracted feature.
     *
     * @since 1.3.0
     */
    @OptIn(ExperimentalNewExpectTypes::class)
    fun <SubjectT, FeatureT> extract(
        container: ProofContainer<SubjectT>,
        description: InlineElement,
        representationForFailure: Any,
        featureExtraction: (SubjectT) -> Option<FeatureT>,
        maybeSubExpectationCreatorAndUsageHints: Option<ExpectationCreatorWithUsageHints<FeatureT>>,
        featureExpectOptions: FeatureExpectOptions<FeatureT>
    ): FeatureExpect<SubjectT, FeatureT>
}
