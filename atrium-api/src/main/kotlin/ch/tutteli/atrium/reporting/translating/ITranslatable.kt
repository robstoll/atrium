package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Something which is translatable with a default representation given by [getDefault] and defined for [locale].
 */
interface ITranslatable {
    /**
     * @return The default representation of this [ITranslatable].
     */
    fun getDefault(): String

    /**
     * The [Locale] for which [getDefault] is defined.
     */
    val locale: Locale
}
