package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [Translator] which uses an [TranslationSupplier] to retrieve translations and [CoroutineBasedLocaleOrderDecider]
 * to determine in which order it should try to find translations for a given [Translatable].
 *
 * @property translationSupplier The [TranslationSupplier] which provides available translations.
 * @property localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *           given [ITranslatable].
 *
 * @constructor Represents an [Translator] which uses an [TranslationSupplier] to retrieve translations and
 *              [LocaleOrderDecider] to determine in which order it should try to find translations for a given
 *              [ITranslatable].
 * @param translationSupplier The [TranslationSupplier] as such.
 * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *        given [ITranslatable].
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *        which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in
 *        the resulting translation of [ITranslatableWithArgs.translatable] with the [ITranslatableWithArgs.arguments].
 * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *        [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 */
class TranslationSupplierBasedTranslator(
    private val translationSupplier: TranslationSupplier,
    private val localeOrderDecider: LocaleOrderDecider,
    primaryLocale: Locale,
    fallbackLocales: Array<out Locale>
) : ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {

    override fun translateWithoutArgs(translatable: Translatable)
        = localeOrderDecider.determineOrder(primaryLocale, fallbackLocales)
        .map { translationSupplier.get(translatable, it) }
        .firstOrNull { it != null }
        ?: translatable.getDefault()
}
