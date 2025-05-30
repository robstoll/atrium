package ch.tutteli.atrium.reporting.translating.impl

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.reporting.translating.*

/**
 * Represents a [Translator] which uses a given [TranslationSupplier] to retrieve translations and
 * a given [LocaleOrderDecider] to determine in which order it should try to find translations
 * for a given [Translatable].
 *
 * @property translationSupplier The [TranslationSupplier] which provides available translations.
 * @property localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *   given [Translatable].
 *
 * @constructor Represents a [Translator] which uses a [TranslationSupplier] to retrieve translations and
 *   [LocaleOrderDecider] to determine in which order it should try to find translations for a given
 *   [Translatable].
 * @param translationSupplier The [TranslationSupplier] as such.
 * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *   given [Translatable].
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *   which will be used in [String.format], which in turn is used to substitute the placeholders in
 *   the resulting translation of [TranslatableWithArgs.translatable] with the [TranslatableWithArgs.arguments].
 * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *   [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 */
@Suppress("DEPRECATION")
@Deprecated("will be removed with 2.0.0 at the latest without replacement")
class TranslationSupplierBasedTranslator(
    private val translationSupplier: TranslationSupplier,
    private val localeOrderDecider: LocaleOrderDecider,
    primaryLocale: Locale,
    fallbackLocales: List<Locale>
) : ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {

    @Deprecated("class will be removed with 2.0.0 at the latest without replacement")
    override fun translateWithoutArgs(translatable: Translatable): String {
        return localeOrderDecider.determineOrder(primaryLocale, fallbackLocales)
            .map { translationSupplier.get(translatable, it) }
            .firstOrNull { it != null }
            ?: translatable.getDefault()
    }
}
