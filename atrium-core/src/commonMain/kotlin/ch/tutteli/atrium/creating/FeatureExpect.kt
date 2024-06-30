//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.FeatureExpectImpl
import ch.tutteli.atrium.creating.proofs.Proof
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [Expect] which results due to a feature extraction from the [SubjectT] of the expectation.
 */
interface FeatureExpect<SubjectT, FeatureT> : Expect<FeatureT> {

    companion object {

        @Deprecated(
            "Use the overload which expects an InlineElement as description and proofs instead of assertions",
            ReplaceWith(
                "FeatureExpect(previousExpect, maybeSubject, description as InlineElement, assertions, featureExpectOptions)",
                "ch.tutteli.atrium.reporting.reportables.InlineElement"
            )
        )
        @ExperimentalComponentFactoryContainer
        @ExperimentalNewExpectTypes
        operator fun <SubjectT, FeatureT> invoke(
            previousExpect: Expect<SubjectT>,
            maybeSubject: Option<FeatureT>,
            description: Translatable,
            assertions: List<Assertion>,
            featureExpectOptions: FeatureExpectOptions<FeatureT>
        ): FeatureExpect<SubjectT, FeatureT> =
            FeatureExpect(previousExpect, maybeSubject, description as InlineElement, assertions, featureExpectOptions)

        @ExperimentalComponentFactoryContainer
        @ExperimentalNewExpectTypes
        operator fun <SubjectT, FeatureT> invoke(
            previousExpect: Expect<SubjectT>,
            maybeSubject: Option<FeatureT>,
            description: InlineElement,
            proofs: List<Proof>,
            featureExpectOptions: FeatureExpectOptions<FeatureT>
        ): FeatureExpect<SubjectT, FeatureT> =
            FeatureExpectImpl(previousExpect, maybeSubject, description, proofs, featureExpectOptions)

        /**
         * Use this overload if you want to modify the options of a [FeatureExpect].
         */
        @ExperimentalNewExpectTypes
        @ExperimentalComponentFactoryContainer
        operator fun <SubjectT, FeatureT> invoke(
            featureExpect: FeatureExpect<SubjectT, FeatureT>,
            featureExpectOptions: FeatureExpectOptions<FeatureT>
        ): FeatureExpect<SubjectT, FeatureT> = when (featureExpect) {
            is FeatureExpectImpl -> FeatureExpectImpl(featureExpect, featureExpectOptions)
            else -> throw UnsupportedOperationException("Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20FeatureExpect%20creation")
        }

    }
}
