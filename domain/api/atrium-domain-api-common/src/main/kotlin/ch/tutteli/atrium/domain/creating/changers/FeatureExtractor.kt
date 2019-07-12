package ch.tutteli.atrium.domain.creating.changers

import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.FeatureAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The access point to an implementation of [FeatureExtractor].
 *
 * It loads the implementation lazily via [loadSingleService].
 */
val featureExtractor by lazy { loadSingleService(FeatureExtractor::class) }

/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [FeatureAssertions] but differs in the intended usage. [FeatureAssertions] are intended to make
 * assertions about a return value of a method call or a property, regardless if this call/access fails or not.
 * The feature extractor on the other hand should be used if it is already known, that the call/access fails depending
 * on given arguments. For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
interface FeatureExtractor {

    /**
     * Extracts a feature according to the given [featureExtraction] if it [canBeExtracted], creates an [Expect] for the
     * new subject and applies [subAssertions] in case they are specified.
     *
     *
     * @param originalAssertionContainer the assertion container with the current subject (before the change) --
     *   if you use `ExpectImpl.changeSubject.reported(...)` within an assertion function (an extension function of
     *   [Expect]) then you usually pass `this` (so the instance of [Expect]) for this parameter.
     * @param description Describes the feature
     * @param representationForFailure Representation in case the extraction cannot be carried out.
     * @param canBeExtracted Indicates whether it is safe to extract the feature.
     * @param featureExtraction Extracts the feature.
     * @param subAssertions Optionally, subsequent assertions for the feature (the new subject).
     *   This is especially useful if the extraction cannot be carried out, because this way we can then already
     *   show to you (in error reporting) what you wanted to assert about the feature (which gives you more context to
     *   the error).
     *
     * @return the newly created [Expect].
     */
    fun <T, R> extract(
        originalAssertionContainer: Expect<T>,
        description: Translatable,
        representationForFailure: Any,
        canBeExtracted: (T) -> Boolean,
        featureExtraction: (T) -> R,
        subAssertions: (Expect<R>.() -> Unit)?
    ): Expect<R>
}
