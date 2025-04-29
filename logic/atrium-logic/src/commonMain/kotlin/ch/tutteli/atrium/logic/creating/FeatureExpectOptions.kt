//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.logic.creating.impl.FeatureExpectOptionsChooserImpl
import ch.tutteli.atrium.reporting.Text

/**
 * Define additional (non-mandatory) options to create a [FeatureExpect] based on a given
 * [FeatureExpectOptionsChooser]-lambda.
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.FeatureExpectOptions")
)
@ExperimentalNewExpectTypes
//using a function because overloading a constructor of a data class does not work well in Kotlin (type inference bugs)
fun <R> FeatureExpectOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
    FeatureExpectOptionsChooser(configuration)

/**
 * Helper lambda to specify [FeatureExpectOptions] via convenience methods.
 *
 * Calling multiple times the same method overrides the previously defined value.
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.FeatureExpectOptionsChooser")
)
@ExperimentalNewExpectTypes
interface FeatureExpectOptionsChooser<R> {

    /**
     * Wraps the given [description] into an [ch.tutteli.atrium.reporting.translating.Untranslatable] and passes it to the overload
     * which expects a [ch.tutteli.atrium.reporting.translating.Translatable] -- this is then used as custom description
     * instead of the previously defined description.
     *
     */
    @Deprecated(
        "Use the import from atrium-core and wrap into Text, atrium-logic will be removed with 2.0.0 at the latest",
        ReplaceWith("withDescription(Text(description))", "ch.tutteli.atrium.reporting.Text")
    )
    fun withDescription(description: String) {
        @Suppress("DEPRECATION")
        withDescription(ch.tutteli.atrium.reporting.translating.Untranslatable(description))
    }

    /**
     * Uses the given [description] as custom description instead of the previously defined description.
     */
    @Suppress("DEPRECATION")
    fun withDescription(description: ch.tutteli.atrium.reporting.translating.Translatable)

    /**
     * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
     * instead of the representation that has been defined so far (which defaults to the subject itself).
     *
     * In case [ProofContainer.maybeSubject] is not defined i.e. [None], then the previous representation is used.
     */
    @Deprecated(
        "Use the import from atrium-core and wrap into Text, atrium-logic will be removed with 2.0.0 at the latest",
        ReplaceWith("withRepresentationIfSubjectDefined { Text(textRepresentation) }", "ch.tutteli.atrium.reporting.Text")
    )
    fun withRepresentation(textRepresentation: String): Unit =
        withRepresentation { Text(textRepresentation) }

    /**
     * Uses the given [representationProvider] to retrieve a representation which can be based on the current
     * subject where this provided representation is used as new representation of the subject
     * instead of the representation that has been defined so far (which defaults to the subject itself).
     *
     * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
     * then wrap it into a [Text] and pass it instead.
     * If your text does not include the current subject, then we recommend to use the other overload which expects
     * a `String` and does the wrapping for you.
     *
     * In case [ProofContainer.maybeSubject] is not defined i.e. [None], then the previous representation is used.
     */
    @Deprecated(
        "Use the import from atrium-core and wrap into Text, atrium-logic will be removed with 2.0.0 at the latest",
        ReplaceWith("withRepresentationIfSubjectDefined(representationProvider)", "ch.tutteli.atrium.reporting.Text")
    )
    fun withRepresentation(representationProvider: (R) -> Any)

    companion object {
        @Deprecated(
            "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
            ReplaceWith("ch.tutteli.atrium.creating.FeatureExpectOptionsChooser(configuration)")
        )
        @ExperimentalNewExpectTypes
        operator fun <R> invoke(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
            FeatureExpectOptionsChooserImpl<R>().apply(configuration).build()
    }
}
