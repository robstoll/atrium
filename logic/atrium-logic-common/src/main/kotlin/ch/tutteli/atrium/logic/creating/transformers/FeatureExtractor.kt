@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.FeatureAssertions
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Defines the contract for sophisticated `safe feature extractions` including assertion creation for the feature.
 *
 * It is similar to [FeatureAssertions] but differs in the intended usage.
 * [FeatureAssertions] are intended to make assertions about a return value of a method call or a property,
 * assuming that the call as such always succeeds (no exception is thrown).
 * The [FeatureExtractor] on the other hand should be used if it is already known,
 * that the call/access fails depending on given arguments.
 * For instance, [List.get] is a good example where it fails if the given index is out of bounds.
 */
interface FeatureExtractor {

    /**
     * Extracts a feature according to the given [featureExtraction], creates an [Expect] for the
     * new subject and applies [maybeSubAssertions] in case they are specified.
     *
     *
     * @param container the assertion container with the current subject (before the change)
     * @param description Describes the feature
     * @param representationForFailure Representation in case the extraction cannot be carried out.
     * @param featureExtraction Extracts the feature where it returns the feature wrapped into a [Some] if the
     *   extraction as such can be carried out, otherwise [None].
     * @param maybeSubAssertions Optionally, subsequent assertions for the feature (the new subject).
     *   This is especially useful if the extraction cannot be carried out, because this way we can then already
     *   show to you (in error reporting) what you wanted to assert about the feature (which gives you more context to
     *   the error).
     * @param representationInsteadOfFeature Per default the feature as such is used to represent itself. However,
     *   if you want a different representation, then use this parameter where passing `null` still means use the
     *   feature.
     *
     * @return The newly created [Expect] for the extracted feature.
     */
    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
    fun <T, R> extract(
        container: AssertionContainer<T>,
        description: Translatable,
        representationForFailure: Any,
        featureExtraction: (T) -> Option<R>,
        maybeSubAssertions: Option<Expect<R>.() -> Unit>,
        featureExpectOptions: FeatureExpectOptions<R>
    ): FeatureExpect<T, R>
}
