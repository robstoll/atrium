package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslationProvider] which is based on properties-files which are structured per
 * entity (enum, object or class) and [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`
 * (same mechanism as in [ResourceBundle] applies here).
 *
 * An entry in such a file would look like as follows:
 * `TO_BE = a translation for TO_BE`
 */
class PropertiesPerEntityAndLocaleTranslationProvider : PropertiesBasedTranslationProvider<String>() {

    override fun get(translatable: ITranslatable, locale: Locale): String? {
        val qualifiedName = translatable::class.qualifiedName
        //using _ here to be compatible with ResourceBundle
        val key = qualifiedName + "_" + locale.toString()
        val translations = getOrLoadProperties(key, key, { qualifiedName + ITranslatable.ID_SEPARATOR + it })
        return translations[translatable.id]
    }
}
