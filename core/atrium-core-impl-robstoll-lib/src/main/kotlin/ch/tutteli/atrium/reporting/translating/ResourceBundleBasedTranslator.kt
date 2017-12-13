package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.reporting.translating.ResourceBundleBasedTranslator.Companion.create
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
 *
 * @property resourceBundleControl The [ResourceBundle.Control] which inter alia defines the order in which
 *           fallback [Locale]s are processed.
 *
 * @constructor  Represents an [ITranslator] which reuses [ResourceBundle] properties based capabilities but uses
 *               an enhanced fallback mechanism. Instead of falling back to [Locale.getDefault] one is able to
 *               specify fallback [Locale] oneself. Whether this includes [Locale.getDefault] or not is up to the user.
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *        which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in the
 *        resulting translation of [ITranslatableWithArgs.translatable] with the [ITranslatableWithArgs.arguments].
 * @param resourceBundleControl The [ResourceBundle.Control] which inter alia defines the order in which
 *        fallback [Locale]s are processed.
 */
class ResourceBundleBasedTranslator private constructor(
    primaryLocale: Locale,
    fallbackLocales: Array<out Locale>,
    private val resourceBundleControl: ResourceBundle.Control
) : ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {

    override fun translateWithoutArgs(translatable: ITranslatable): String {
        return try {
            val bundle = ResourceBundle.getBundle(translatable::class.java.name, primaryLocale, resourceBundleControl)
            bundle.getString(translatable.name)
        } catch(ex: MissingResourceException) {
            translatable.getDefault()
        }
    }

    companion object {
        /**
         * Creates a [ResourceBundleBasedTranslator] and aggregates it with a [ResourceBundle.Control] which either
         * makes use of the given [fallbackLocales] if provided or uses only the given [primaryLocale].
         *
         * @param primaryLocale The primary [Locale] which will be used in [java.lang.String.format] to substitute the
         *                      placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
         *                      the [ITranslatableWithArgs.arguments].
         * @param fallbackLocales Used in case a translation for a given [ITranslatable] is not defined for
         *                        [primaryLocale] or one of its secondary alternatives -- the [fallbackLocales] are
         *                        used in the given order.
         */
        fun create(primaryLocale: Locale, vararg fallbackLocales: Locale): ResourceBundleBasedTranslator {
            val control = if (fallbackLocales.isEmpty()) {
                ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES)
            } else {
                FallbackResourceBundleControl(listOf(primaryLocale, *fallbackLocales))
            }
            return ResourceBundleBasedTranslator(primaryLocale, fallbackLocales, control)
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
