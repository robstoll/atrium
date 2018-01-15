package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * A base class for properties based [TranslationSupplier]s which provides a loading
 * and caching mechanism of properties files.
 *
 * There is no way to purge the cache. This class is intended for a one run process where
 * translations do not change in between.
 *
 * @param T Translations are grouped by a certain aspect (for instance, by [Locale]). [T] defines the type of it.
 */
abstract class PropertiesBasedTranslationSupplier<in T> : TranslationSupplier {
    /**
     * The cached translations.
     */
    private val translations = ConcurrentHashMap<T, Map<String, String>>()

    override final fun get(translatable: Translatable, locale: Locale): String? {
        require(locale != Locale.ROOT) {
            "Locale.ROOT is not supported -- most likely a bug in the chosen implementation of ${LocaleOrderDecider::class.simpleName}"
        }
        return getNotForRoot(translatable, locale)
    }

    /**
     * Returns the translation for the given [translatable] and the given [locale] or `null` if it does not have
     * any translation.
     *
     * It does not have to check if [locale] is [Locale.ROOT] anymore, this is already done in [get].
     *
     * @return The translation or null if no translation was found.
     */
    protected abstract fun getNotForRoot(translatable: Translatable, locale: Locale): String?

    /**
     * Gets the cached [Properties] content as [Map] for the given [key] or
     * loads the properties file with the given [fileName] and creates a map out of it using the given [keyCreator]
     * function to create the keys of the map, based on a key of a property.
     *
     * @param key The key which identifies the [Properties]
     * @param fileName The fileName of the properties file without file extension, including the package in which it resides
     *   as absolute path (no '../' are allowed). In case it does not start with '/' (which would be a
     *   relative path), then a '/' is prepended. Hence it is always searched with an
     *   absolute path -- which is the same behaviour as for a properties based [ResourceBundle].
     * @param keyCreator The function used to create keys of the resulting [Map] (in case the properties file needs
     *   to be loaded). It is called passing in a key of a property of the properties file.
     *
     * @return A [Map] containing the resulting keys (based on the [Properties], see [keyCreator]) with its translations.
     */
    protected fun getOrLoadProperties(key: T, fileName: String, keyCreator: (String) -> String): Map<String, String> {
        require(!fileName.contains("../")) {
            "only absolute paths without any '../' are allowed"
        }
        return this.translations.getOrPut(key, {
            val absoluteFileName = if (fileName.startsWith('/')) fileName else "/$fileName"
            val file = this::class.java.getResourceAsStream("$absoluteFileName.properties")
            if (file != null) {
                val properties = Properties()
                file.use {
                    properties.load(it)
                }
                properties.stringPropertyNames().associate {
                    keyCreator(it) to properties.getProperty(it)
                }
            } else {
                emptyMap()
            }
        })
    }

    /**
     * Returns the name of the properties file without extension -- including the package (as prefixed relative path)
     * in which it resides if necessary -- in which we expect to find a translation in the given [locale] for
     * [baseName].
     *
     * The implementation is based on [ResourceBundle.Control.toBundleName].
     * @param baseName Usually the [Translatable] or another identifier for which we are searching translations. Has to
     *   contain the package name as well if necessary ('.' will be replaced with '/').
     * @param locale The [Locale] for which we are searching a translation.
     *
     * @return The name of the properties file.
     */
    protected fun getFileNameFor(baseName: String, locale: Locale): String {
        val sb = StringBuilder(baseName)
            //using _ as separator to be compatible with ResourceBundle
            .append('_').append(locale.language)

        if (locale.script.isNotEmpty()) {
            sb.append('_').append(locale.script)
        }
        if (locale.country.isNotEmpty()) {
            sb.append('_').append(locale.country)
        }
        if (locale.variant.isNotEmpty()) {
            sb.append('_').append(locale.variant)
        }
        return sb.toString().replace('.', '/')
    }
}
