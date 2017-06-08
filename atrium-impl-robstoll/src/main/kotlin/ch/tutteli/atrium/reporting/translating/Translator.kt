package ch.tutteli.atrium.reporting.translating

import java.util.*

class Translator(
    private val translationSupplier: ITranslationSupplier,
    primaryLocale: Locale,
    private val fallbackLocales: Array<out Locale>
) : ArgumentsSupportingTranslator(primaryLocale) {

    private val localeResolver = LocaleOrderDecider()

    override fun translateWithoutArgs(translatable: ITranslatable)
        = localeResolver.resolve(primaryLocale, fallbackLocales)
        .map { translationSupplier.get(translatable, it) }
        .firstOrNull { it != null }
        ?: translatable.getDefault()
}
