//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.FeatureExpectImpl
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [Expect] which results due to a feature extraction from he subject of the expectation.
 */
interface FeatureExpect<T, R> : Expect<R> {

    companion object {

        @ExperimentalComponentFactoryContainer
        @ExperimentalNewExpectTypes
        operator fun <T, R> invoke(
            previousExpect: Expect<T>,
            maybeSubject: Option<R>,
            description: Translatable,
            assertions: List<Assertion>,
            featureExpectOptions: FeatureExpectOptions<R>
        ): FeatureExpect<T, R> =
            FeatureExpectImpl(previousExpect, maybeSubject, description, assertions, featureExpectOptions)

        /**
         * Use this overload if you want to modify the options of a [FeatureExpect].
         */
        @ExperimentalNewExpectTypes
        @ExperimentalComponentFactoryContainer
        operator fun <T, R> invoke(
            featureExpect: FeatureExpect<T, R>,
            featureExpectOptions: FeatureExpectOptions<R>
        ): FeatureExpect<T, R> = when (featureExpect) {
            is FeatureExpectImpl -> FeatureExpectImpl(featureExpect, featureExpectOptions)
            else -> throw UnsupportedOperationException("Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20FeatureExpect%20creation")
        }

    }
}
