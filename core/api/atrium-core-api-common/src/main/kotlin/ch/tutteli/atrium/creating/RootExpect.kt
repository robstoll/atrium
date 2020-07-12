package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.impl.RootExpectImpl
import ch.tutteli.atrium.reporting.BUG_REPORT_URL
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Represents the root of an [Expect] chain, intended as extension point for functionality
 * which should only be available on the root.
 */
interface RootExpect<T> : Expect<T> {

    companion object {
        @ExperimentalNewExpectTypes
        operator fun <T> invoke(
            maybeSubject: Option<T>,
            assertionVerb: Translatable,
            expectOptions: RootExpectOptions<T>?
        ): RootExpect<T> = RootExpectImpl(maybeSubject, assertionVerb, expectOptions)

        @ExperimentalNewExpectTypes
        /**
         * Use this overload if you want to modify the options of a [RootExpect].
         */
        operator fun <T> invoke(
            rootExpect: RootExpect<T>,
            expectOptions: RootExpectOptions<T>
        ): RootExpect<T> = when (rootExpect) {
            is RootExpectImpl -> RootExpectImpl(rootExpect, expectOptions)
            else -> throw UnsupportedOperationException("Please open an issue that a hook shall be implemented: $BUG_REPORT_URL?template=feature_request&title=Hook%20for%20RootExpect%20creation")
        }
    }
}
