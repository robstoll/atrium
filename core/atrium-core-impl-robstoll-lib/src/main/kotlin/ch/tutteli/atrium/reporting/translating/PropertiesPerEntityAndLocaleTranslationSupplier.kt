package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslationSupplier] which is based on properties-files which are structured per
 * entity (enum, object or class) and [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`
 * (compatible with the behaviour of [ResourceBundle] - thus properties files should also use ISO-8859-1 as encoding).
 *
 * An entry in such a file would look like as follows:
 * `TO_BE = a translation for TO_BE`
 */
class PropertiesPerEntityAndLocaleTranslationSupplier : PropertiesBasedTranslationSupplier<String>() {

    override fun getNotForRoot(translatable: ITranslatable, locale: Locale): String? {
        val qualifiedName = translatable::class.java.name
        //using _ here to be compatible with ResourceBundle
        val key = qualifiedName + "_" + locale.toString()
        val translations = getOrLoadProperties(key, key, { qualifiedName + ITranslatable.ID_SEPARATOR + it })
        return translations[translatable.id]
    }
}
