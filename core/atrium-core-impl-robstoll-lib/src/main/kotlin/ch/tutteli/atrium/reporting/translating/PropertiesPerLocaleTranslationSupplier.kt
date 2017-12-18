package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslationSupplier] which is based on properties-files which are structured per [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `Atrium_de_CH.properties` in the folder `/ch/tutteli/atrium/reporting/translating`.
 *
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named .
 *
 * An entry in such a file would look like the following:
 * `ch.tutteli.atrium.DescriptionAnyAssertion-TO_BE = a translation for TO_BE`
 */
class PropertiesPerLocaleTranslationSupplier : PropertiesBasedTranslationSupplier<Locale>() {

    override fun getNotForRoot(translatable: ITranslatable, locale: Locale): String? {
        val fileName = getFileNameFor(this::class.java.`package`.name + ".Atrium", locale)
        val translations = getOrLoadProperties(locale, fileName, { it })
        return translations[translatable.id]
    }

}
