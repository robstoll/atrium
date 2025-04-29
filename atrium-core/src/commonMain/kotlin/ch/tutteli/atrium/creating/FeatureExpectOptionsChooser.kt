package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.impl.FeatureExpectOptionsChooserImpl
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.reportables.InlineElement

/**
 * Define additional (non-mandatory) options to create a [FeatureExpect] based on a given
 * [FeatureExpectOptionsChooser]-lambda.
 */
@ExperimentalNewExpectTypes
//using a function because overloading a constructor of a data class does not work well in Kotlin (type inference bugs)
fun <R> FeatureExpectOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
    FeatureExpectOptionsChooser(configuration)

/**
 * Helper lambda to specify [FeatureExpectOptions] via convenience methods.
 *
 * Calling multiple times the same method overrides the previously defined value.
 *
 * @since 1.3.0
 */
@ExperimentalNewExpectTypes
interface FeatureExpectOptionsChooser<R> {

    /**
     * Uses the given [description] as custom description instead of the previously defined description.
     *
     * @since 1.3.0
     */
    fun withDescription(description: InlineElement)

    /**
     * Uses the given [representationProvider] to retrieve a representation which can be based on the current
     * subject where this provided representation is used as new representation of the subject
     * instead of the representation that has been defined so far (which defaults to the subject itself).
     *
     * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
     * then wrap it into a [Text] and pass it instead.
     *
     * In case [ProofContainer.maybeSubject] is not defined i.e. [None], then the previous representation is used.
     *
     * @since 1.3.0
     */
    fun withRepresentationIfSubjectDefined(representationProvider: (R) -> Any)

    companion object {
        //TODO 1.3.0 use invoke or create or the like?
        @ExperimentalNewExpectTypes
        operator fun <R> invoke(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
            FeatureExpectOptionsChooserImpl<R>().apply(configuration).build()
    }
}
