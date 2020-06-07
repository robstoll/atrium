package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Additional (non-mandatory) options to create a [RootExpect].
 *
 * @property assertionVerb Defines a custom assertion verb if not null.
 * @property representationInsteadOfSubject Defines a custom representation based on a present subject if not null.
 * @property reporter Defines a custom reporter if not null.
 */
@ExperimentalNewExpectTypes
data class RootExpectOptions<T>(
    val assertionVerb: Translatable?,
    val representationInsteadOfSubject: ((T) -> Any)?,
    val reporter: Reporter?
) {
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
            options.assertionVerb ?: assertionVerb,
            options.representationInsteadOfSubject ?: representationInsteadOfSubject,
            options.reporter ?: reporter
        )
}
