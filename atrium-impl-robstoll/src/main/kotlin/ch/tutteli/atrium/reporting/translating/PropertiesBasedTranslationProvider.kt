package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class PropertiesBasedTranslationProvider<in T> : ITranslationProvider {
    private val translations = ConcurrentHashMap<T, Map<String, String>>()
    protected fun getOrLoadProperties(key: T, fileName: String, idCreator: (String) -> String) : Map<String, String> {
        val translations = translations.getOrPut(key, {
            val file = this::class.java.getResourceAsStream(fileName + FILE_ENDING)
            if (file != null) {
                val properties = Properties()
                file.use {
                    properties.load(it)
                }
                properties.stringPropertyNames().associate {
                    idCreator(it) to properties.getProperty(it)
                }
            } else {
                emptyMap()
            }
        })
        return translations
    }

    companion object {
        const val FILE_ENDING = ".properties"
    }
}
