package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslator] which reuses [ResourceBundle] properties based capabilities but uses an enhanced
 * fallback mechanism. Instead of falling back to [Locale.getDefault] one is able to specify fallback [Locale] oneself.
 * Whether this includes [Locale.getDefault] or not is up to the user.
 *
 * Creating a [ResourceBundleBasedTranslator] is done via the static [create] methods. Here one has the possibility to
 * specify the additional fallback [Locale]s.
 *
 * The translations are located in properties files structured per entity (enum, object or class).
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`.
 *
 * An entry in such a file would look like as follows:
 * `TO_BE = a translation for TO_BE`
 */
class ResourceBundleBasedTranslator private constructor(
    private val locale: Locale,
    private val resourceBundleControl: ResourceBundle.Control
) : ITranslator {

    override fun translate(translatable: ITranslatable): String {
        try {
            val bundle = ResourceBundle.getBundle(translatable::class.java.name, locale, resourceBundleControl)
            return bundle.getString(translatable.name)
        } catch(ex: MissingResourceException) {
            return translatable.getDefault()
        }
    }

    override fun translate(translatableWithArgs: ITranslatableWithArgs): String {
        val translation = translate(translatableWithArgs.translatable)
        return String.format(translation, translatableWithArgs.arguments)
    }

    companion object {
        fun create(locale: Locale, vararg fallbackLocales: Locale): ResourceBundleBasedTranslator {
            val control = if (fallbackLocales.isEmpty()) {
                ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES)
            } else {
                FallbackResourceBundleControl(listOf(locale, *fallbackLocales))
            }
            return ResourceBundleBasedTranslator(locale, control)
        }
    }

    private class FallbackResourceBundleControl(private val fallbackLocales: List<Locale>) : ResourceBundle.Control() {

        override fun getFormats(baseName: String?): List<String> = ResourceBundle.Control.FORMAT_PROPERTIES

        override fun getFallbackLocale(baseName: String?, locale: Locale?): Locale? {
            if (locale != null) {
                val index = fallbackLocales.indexOf(locale)
                if (index != -1 && index + 1 < fallbackLocales.size) {
                    return fallbackLocales[index + 1]
                }
            }
            return null
        }
    }

}
