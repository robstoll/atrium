package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.reporting.translating.*

/**
 * Provides options to create a [Translator] -- the platform specific interface might provide further options.
 */
expect interface TranslatorOption : TranslatorOptionCommon

/**
 * Provides options to create a [Translator] -- those options have to be provided on all platforms.
 */
interface TranslatorOptionCommon {

    /**
     * The previously chosen [TranslationSupplier].
     */
    val translationSupplier: TranslationSupplier

    /**
     * The previously chosen [LocaleOrderDecider].
     */
    val localeOrderDecider: LocaleOrderDecider

    /**
     * Uses [CoreFactory.newTranslator] as [Translator] where the specified [translationSupplier] is used to
     * retrieve translations, the specified [localeOrderDecider] to determine candidate [Locale]s and
     * [primaryLocale] is used as primary [Locale] and the optional [fallbackLocales] as fallback [Locale]s.
     *
     * @param primaryLocale The [Locale] for which the [Translator] will first search translations --
     *   it will also be used to format arguments of [TranslatableWithArgs].
     * @param fallbackLocales One [Locale] after another (in the given order) will be considered as primary Locale
     *   in case no translation was found the previous primary Locale.
     */
    fun withDefaultTranslator(primaryLocale: Locale, vararg fallbackLocales: Locale): ObjectFormatterOption

    /**
     * Uses the given [factory] to build a [Translator].
     */
    fun withTranslator(factory: (TranslationSupplier, LocaleOrderDecider) -> Translator): ObjectFormatterOption
}
