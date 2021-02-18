package ch.tutteli.atrium.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Additional (non-mandatory) options to create a [FeatureExpect].
 *
 * @property description Defines a custom description if not null.
 * @property representationInsteadOfFeature Defines a custom representation based on a present subject if not null.
 */
@ExperimentalNewExpectTypes
data class FeatureExpectOptions<R>(
    val description: Translatable? = null,
    val representationInsteadOfFeature: ((R) -> Any)? = null
) {
    /**
     * Merges the given [options] with this object creating a new [FeatureExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfFeature] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [FeatureExpectOptions] will have the
     * [representationInsteadOfFeature] of [options].
     */
    fun merge(options: FeatureExpectOptions<R>): FeatureExpectOptions<R> =
        FeatureExpectOptions(
            options.description ?: description,
            options.representationInsteadOfFeature ?: representationInsteadOfFeature
        )
}

/**
 * Define additional (non-mandatory) options to create a [FeatureExpect] based on a given
 * [FeatureExpectOptionsChooser]-lambda.
 */
@Suppress("FunctionName", "DEPRECATION")
@ExperimentalNewExpectTypes
@Deprecated(
    "Use the helper function FeatureExpectOptions from atrium-logic; will be removed with 0.17.0",
    ReplaceWith("ch.tutteli.atrium.logic.creating.FeatureExpectOptions(configuration)")
)
//using a function because overloading a constructor of a data class does not work well in Kotlin (type inference bugs)
fun <R> FeatureExpectOptions(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
    FeatureExpectOptionsChooser(configuration)

//TODO 0.16.0 deprecate and move to logic
/**
 * Helper lambda to specify [FeatureExpectOptions] via convenience methods.
 *
 * Calling multiple times the same method overrides the previously defined value.
 */
@Suppress("DeprecatedCallableAddReplaceWith", "DEPRECATION")
@ExperimentalNewExpectTypes
@Deprecated("Use FeatureExpectOptionsChooser from atrium-logic; will be removed with 0.17.0")
interface FeatureExpectOptionsChooser<R> {

    /**
     * Wraps the given [description] into an [Untranslatable] and passes it to the overload
     * which expects a [Translatable] -- this is then used as custom description
     * instead of the previously defined description.
     *
     */
    @Deprecated("Use FeatureExpectOptionsChooser from atrium-logic; will be removed with 0.17.0")
    fun withDescription(description: String) {
        withDescription(Untranslatable(description))
    }

    /**
     * Uses the given [description] as custom description instead of the previously defined description.
     */
    @Deprecated("Use FeatureExpectOptionsChooser from atrium-logic; will be removed with 0.17.0")
    fun withDescription(description: Translatable)

    /**
     * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
     * instead of the representation that has been defined so far (which defaults to the subject itself).
     *
     * In case [AssertionContainer.maybeSubject] is not defined i.e. [None], then the previous representation is used.
     */
    @Deprecated("Use FeatureExpectOptionsChooser from atrium-logic; will be removed with 0.17.0")
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
     * In case [AssertionContainer.maybeSubject] is not defined i.e. [None], then the previous representation is used.
     */
    @Deprecated("Use FeatureExpectOptionsChooser from atrium-logic; will be removed with 0.17.0")
    fun withRepresentation(representationProvider: (R) -> Any)

    companion object {
        @Suppress("DEPRECATION")
        @ExperimentalNewExpectTypes
        @Deprecated("Use the helper function FeatureExpectOptions from atrium-logic; will be removed with 0.17.0", ReplaceWith("ch.tutteli.atrium.logic.creating.FeatureExpectOptions(configuration)"))
        operator fun <R> invoke(configuration: FeatureExpectOptionsChooser<R>.() -> Unit): FeatureExpectOptions<R> =
            ch.tutteli.atrium.creating.impl.FeatureExpectOptionsChooserImpl<R>().apply(configuration).build()
    }
}
