package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.polyfills.fullName
import java.util.*

/**
 * Represents a [TranslationSupplier] which is based on properties-files which are structured per
 * entity (enum, object or class) and [Locale].
 *
 * For instance, the translations for `ch.tutteli.atrium.translations.DescriptionAnyAssertion`
 * and the [Locale] `de_CH` are searched in a properties file named `DescriptionAnyAssertion_de_CH.properties`
 * in the directory `/ch/tutteli/atrium/translations`.
 *
 * An entry in such a file would look like as follows:
 * `TO_BE = a translation for TO_BE`
 *
 * It is compatible with Java's [Properties] - thus properties files should also use ISO-8859-1 as encoding.
 */
@Suppress("DEPRECATION")
@Deprecated("will be removed with 2.0.0 at the latest without replacement")
class PropertiesPerEntityAndLocaleTranslationSupplier : PropertiesBasedTranslationSupplier<String>() {

    @Deprecated("will be removed with 2.0.0 at the latest without replacement")
    override fun get(translatable: Translatable, locale: Locale): String? {
        val qualifiedName = translatable::class.java.name
        val fileName = getFileNameFor(qualifiedName, locale)
        val fullName = translatable::class.fullName(translatable)
        val translations = getOrLoadProperties(fileName, fileName, keyCreator = {
            fullName + Translatable.ID_SEPARATOR + it
        })
        return translations[translatable.id]
    }
}
