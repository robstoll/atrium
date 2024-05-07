package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents a [TranslationSupplier] which is based on properties-files which are structured per [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `Atrium_de_CH.properties` in the folder `/ch/tutteli/atrium/reporting/translating`.
 *
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named .
 *
 * An entry in such a file would look like the following:
 * `ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = a translation for TO_BE`
 *
 * It is compatible with Java's [Properties] - thus properties files should also use ISO-8859-1 as encoding.
 */
@Deprecated("will be removed with 2.0.0 at the latest without replacement")
@Suppress("DEPRECATION")
class PropertiesPerLocaleTranslationSupplier : PropertiesBasedTranslationSupplier<Locale>() {

    @Deprecated("will be removed with 2.0.0 at the latest without replacement")
    override fun get(translatable: Translatable, locale: Locale): String? {
        val fileName = getFileNameFor(this::class.java.`package`.name + ".Atrium", locale)
        val translations = getOrLoadProperties(locale, fileName, keyCreator = { it })
        return translations[translatable.id]
    }
}
