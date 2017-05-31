package ch.tutteli.atrium.reporting.translating

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class PropertiesBasedTranslationProvider<in T> : ITranslationProvider {
    private val translations = ConcurrentHashMap<T, Map<String, String>>()

    /**
     * Gets the cached [Properties] content as [Map] for the given [key] or
     * loads the properties file with the given [name] and creates a map out of it using the given [keyCreator]
     * function to create the keys of the map, based on a key of a property.
     *
     * @param key The key which identifies the [Properties]
     * @param name The name of the properties file, including the package in which it resides if necessary
     *             but without file extension. It is always searched with an absolute path (/ is prepended)
     *             -- the same behaviour as for a properties based [ResourceBundle]
     * @param keyCreator The function used to create keys of the resulting [Map] (in case the properties file needs
     *                   to be loaded). It is called passing in a key of a property of the properties file.
     */
    protected fun getOrLoadProperties(key: T, name: String, keyCreator: (String) -> String): Map<String, String> {
        val translations = translations.getOrPut(key, {
            val file = this::class.java.getResourceAsStream("/${name.replace('.', '/')}.properties")
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
        return translations
    }
}
