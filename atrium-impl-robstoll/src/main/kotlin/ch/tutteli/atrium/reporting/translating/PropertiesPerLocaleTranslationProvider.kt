package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslationProvider] which is based on properties-files which are structured per [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `Atrium_de_CH.properties` in the folder `/ch/tutteli/atrium/reporting/translating`.
 *
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named .
 *
 * An entry in such a file would look like the following:
 * `ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = a translation for TO_BE`
 */
class PropertiesPerLocaleTranslationProvider : PropertiesBasedTranslationProvider<Locale>() {

    override fun get(translatable: ITranslatable, locale: Locale): String? {
        val name = if (locale != Locale.ROOT) {
            this::class.java.`package`.name + ".Atrium_" + locale.toString()
        } else {
            this::class.java.`package`.name + ".Atrium"
        }
        val translations = getOrLoadProperties(locale, name, { it })
        return translations[translatable.id]
    }

}
