package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Represents an [ITranslationProvider] which is based on properties-files which are structured per [Locale].
 * For instance, the translations for the [Locale] `de_CH` are stored in a properties file named `de_CH.properties`
 */
class PropertiesPerLocaleTranslationProvider : ITranslationProvider {
    private val translations = ConcurrentHashMap<Locale, Map<String, String>>()
    override fun get(translatable: ITranslatable, locale: Locale): String? {
        val translations = translations.getOrPut(locale, {
            val file = this::class.java.getResourceAsStream(locale.toString() + FILE_ENDING)
            if (file != null) {
                val properties = Properties()
                file.use {
                    properties.load(it)
                }
                properties.stringPropertyNames().associate {
                    it to properties.getProperty(it)
                }
            } else {
                emptyMap()
            }
        })
        return translations[translatable.id]
    }

    companion object {
        const val FILE_ENDING = ".properties"
    }

}
