//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.creating.changers

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.FeatureExpect

/**
 * Option step which allows to decide what should be done with the extracted feature of type [R].
 *
 * Notice, this class does not add any functionality to [PostFinalStep] and is more like a marker.
 * The purpose of the marker is extensibility, this way you can write a post-final-step which only applies to
 * [ExtractedFeaturePostStep] and not to all kind of [PostFinalStep].
 *
 * @param expect The [Expect] which was involved in the building process
 *   and holds assertion for the initial subject.
 * @param extract The extraction of the feature which creates and returns a new [Expect] of type [R].
 * @param extractAndApply The extraction of the feature which not only creates and
 *   returns a new [Expect] of type [R] but also applies a given assertionCreator lambda.
 */
@Deprecated(
    "Use FeatureExtractorBuilder.ExecutionStep from atrium-logic; will be removed with 1.0.0",
    ReplaceWith(
        "ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder.ExecutionStep(expect._logic, extract, extractAndApply)",
        "ch.tutteli.atrium.logic._logic"
    )
)
class ExtractedFeaturePostStep<T, R>(
    expect: Expect<T>,
    extract: Expect<T>.() -> FeatureExpect<T, R>,
    extractAndApply: Expect<T>.(Expect<R>.() -> Unit) -> Expect<R>
) : PostFinalStep<T, R, FeatureExpect<T, R>>(expect, extract, extractAndApply)
