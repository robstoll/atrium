package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslationProvider] which is based on properties-files which are structured per [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `de_CH.properties` in the folder `/ch/tutteli/atrium/`
 * (same mechanism as in [ResourceBundle] applies here).
 *
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named .
 *
 * An entry in such a file would look like the following:
 * `ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = a translation for TO_BE`
 */
class PropertiesPerLocaleTranslationProvider : PropertiesBasedTranslationProvider<Locale>() {

    override fun get(translatable: ITranslatable, locale: Locale): String? {
        val translations = getOrLoadProperties(locale, this::class.java.`package`.name + "." + locale.toString(), { it })
        return translations[translatable.id]
    }

}
