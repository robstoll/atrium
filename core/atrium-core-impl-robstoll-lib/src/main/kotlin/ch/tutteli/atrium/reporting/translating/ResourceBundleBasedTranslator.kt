package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslator] which reuses [ResourceBundle] properties based capabilities but uses an enhanced
 * fallback mechanism. Instead of falling back to [Locale.getDefault] one is able to specify fallback [Locale] oneself.
 * Whether this includes [Locale.getDefault] or not is up to the user.
 *
 * The translations are located in properties files structured per entity (enum, object or class).
 * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
 * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`.
 *
 * An entry in such a file would look like as follows:
 * `TO_BE = a translation for TO_BE`
 *
 * @constructor  Represents an [ITranslator] which reuses [ResourceBundle] properties based capabilities but uses
 *               an enhanced fallback mechanism. Instead of falling back to [Locale.getDefault] one is able to
 *               specify fallback [Locale] oneself. Whether this includes [Locale.getDefault] or not is up to the user.
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *        which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in the
 *        resulting translation of [ITranslatableWithArgs.translatable] with the [ITranslatableWithArgs.arguments].
 */
class ResourceBundleBasedTranslator(
    primaryLocale: Locale,
    fallbackLocales: Array<out Locale>
) : ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {

    override fun translateWithoutArgs(translatable: ITranslatable): String {
        val control = ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES)
        listOf(primaryLocale, *fallbackLocales).forEach { locale ->
            try {
                val bundle = ResourceBundle.getBundle(translatable::class.java.name, locale, control)
                return bundle.getString(translatable.name)
            } catch (ex: MissingResourceException) {
                //that's fine we'll return getDefault below if no translation was found
            }
        }
        return translatable.getDefault()
    }

    companion object {
        /**
         * Creates a [ResourceBundleBasedTranslator] and aggregates it with a [ResourceBundle.Control] which either
         * makes use of the given [fallbackLocales] if provided or uses only the given [primaryLocale].
         *
         * @param primaryLocale The primary [Locale] which will be used in [java.lang.String.format] to substitute the
         *        placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
         *        the [ITranslatableWithArgs.arguments].
         * @param fallbackLocales Used in case a translation for a given [ITranslatable] is not defined for
         *        [primaryLocale] or one of its secondary alternatives -- the [fallbackLocales] are used in the
         *        given order.
         */
        fun create(primaryLocale: Locale, vararg fallbackLocales: Locale)
            = ResourceBundleBasedTranslator(primaryLocale, fallbackLocales)
    }
}
