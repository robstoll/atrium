@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import java.util.*

/**
 * Represents a [TranslationSupplier] which is based on properties-files which are structured per
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
