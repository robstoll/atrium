package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

/**
 * Provides options to create a [Translator].
 */
interface TranslatorOption {

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

internal class TranslatorOptionImpl(
    override val translationSupplier: TranslationSupplier,
    override val localeOrderDecider: LocaleOrderDecider
) : TranslatorOption {

    override fun withDefaultTranslator(primaryLocale: Locale, vararg fallbackLocales: Locale)
        = ObjectFormatterOptionImpl(coreFactory.newTranslator(
            translationSupplier,
            localeOrderDecider,
            primaryLocale,
            fallbackLocales.toList()
        ))

    override fun withTranslator(factory: (TranslationSupplier, LocaleOrderDecider) -> Translator)
        = ObjectFormatterOptionImpl(factory(
            translationSupplier,
            localeOrderDecider
        ))
}
