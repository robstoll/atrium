package ch.tutteli.atrium.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.FeatureExpectImpl
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents an [Expect] which results due to a change of the [Expect.maybeSubject] to a feature of the subject.
 *
 * A change can for instance be a feature extraction such as `expect(listOf(1)).get(0)`
 * but also just a feature assertion such as `expect(listOf(1)).feature { f(it::size) }`
 */
interface FeatureExpect<T, R> : Expect<R> {

    companion object {

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
        operator fun <T, R> invoke(
            featureExpect: FeatureExpect<T, R>,
            featureExpectOptions: FeatureExpectOptions<R>
        ): FeatureExpect<T, R> = when (featureExpect) {
            is FeatureExpectImpl -> FeatureExpectImpl(featureExpect, featureExpectOptions)
            else -> throw UnsupportedOperationException("Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20FeatureExpect%20creation")
        }

    }
}
