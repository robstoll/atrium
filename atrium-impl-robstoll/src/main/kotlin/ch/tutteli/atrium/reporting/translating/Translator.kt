package ch.tutteli.atrium.reporting.translating

import java.util.*

class Translator(
    private val translationSupplier: ITranslationSupplier,
    primaryLocale: Locale,
    private val fallbackLocales: Array<out Locale>
) : ArgumentsSupportingTranslator(primaryLocale) {

    private val localeOrderDecider = LocaleOrderDecider()

    override fun translateWithoutArgs(translatable: ITranslatable)
        = localeOrderDecider.determineOrder(primaryLocale, fallbackLocales)
        .map { translationSupplier.get(translatable, it) }
        .firstOrNull { it != null }
        ?: translatable.getDefault()
}
