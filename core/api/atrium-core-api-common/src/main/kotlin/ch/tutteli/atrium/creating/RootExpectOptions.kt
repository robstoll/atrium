package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Additional (non-mandatory) options to create a [RootExpect].
 *
 * See atrium-logic for helper functions to create an instance for this. Core is kept simple on purpose because
 * it is also used in other JVM languages than Kotlin.
 *
 * @property expectationVerb Defines a custom assertion verb if not null.
 * @property representationInsteadOfSubject Defines a custom representation based on a present subject if not null.
 * @property reporter Defines a custom reporter if not null.
 */
@ExperimentalNewExpectTypes
data class RootExpectOptions<T>(
    val expectationVerb: Translatable?,
    val representationInsteadOfSubject: ((T) -> Any)?,
    val reporter: Reporter?
) {
    @Deprecated("Use expectationVerb; will be removed latest with 1.0.0 (maybe earlier)")
    val assertionVerb = expectationVerb

    /**
     * Merges the given [options] with this object creating a new [RootExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfSubject] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [RootExpectOptions] will have the
     * [representationInsteadOfSubject] of [options].
     */
    fun merge(options: RootExpectOptions<T>): RootExpectOptions<T> =
        RootExpectOptions(
            options.expectationVerb ?: expectationVerb,
            options.representationInsteadOfSubject ?: representationInsteadOfSubject,
            options.reporter ?: reporter
        )
}
