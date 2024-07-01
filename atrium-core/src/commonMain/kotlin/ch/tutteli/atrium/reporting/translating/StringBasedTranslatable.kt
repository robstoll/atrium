// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.translating

/**
 * Something which is [Translatable] and provides a default representation by [value].
 */
@Deprecated("Translation support was dropped with 1.2.0, switch to Reportable. Will be removed with 2.0.0 at the latest")
interface StringBasedTranslatable : Translatable {

    /**
     * The default representation of this [Translatable].
     */
    val value: String

    /**
     * Returns the default representation of this [Translatable] which is [value].
     *
     * @return [value].
     */
    override fun getDefault() = value
}
