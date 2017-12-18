package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslator] which uses an [ITranslationSupplier] to retrieve translations and [LocaleOrderDecider]
 * to determine in which order it should try to find translations for a given [ITranslatable].
 *
 * @property translationSupplier The [ITranslationSupplier] which provides available translations.
 * @property localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *           given [ITranslatable].
 *
 * @constructor Represents an [ITranslator] which uses an [ITranslationSupplier] to retrieve translations and
 *              [LocaleOrderDecider] to determine in which order it should try to find translations for a given
 *              [ITranslatable].
 * @param translationSupplier The [ITranslationSupplier] as such.
 * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
 *        given [ITranslatable].
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *        which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in
 *        the resulting translation of [ITranslatableWithArgs.translatable] with the [ITranslatableWithArgs.arguments].
 * @param fallbackLocales Used in case a translation for a given [ITranslatable] is not defined for
 *        [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 */
class TranslationSupplierBasedTranslator(
    private val translationSupplier: ITranslationSupplier,
    private val localeOrderDecider: ILocaleOrderDecider,
    primaryLocale: Locale,
    fallbackLocales: Array<out Locale>
) : ArgumentsSupportingTranslator(primaryLocale, fallbackLocales) {

    override fun translateWithoutArgs(translatable: ITranslatable)
        = localeOrderDecider.determineOrder(primaryLocale, fallbackLocales)
        .map { translationSupplier.get(translatable, it) }
        .firstOrNull { it != null }
        ?: translatable.getDefault()
}
