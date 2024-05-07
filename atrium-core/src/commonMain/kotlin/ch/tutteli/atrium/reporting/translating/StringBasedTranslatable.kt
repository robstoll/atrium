// TODO remove with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting.translating

/**
 * Something which is [Translatable] and provides a default representation by [value].
 */
//TOOD 1.3.0 deprecate with the introduction of Representable
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
