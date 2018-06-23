package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterBuilderImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.*

/**
 * Entry point to build a [Reporter]
 * -- the first step provides options to create a [Translator] or a [TranslationSupplier].
 */
val reporterBuilder : ReporterBuilder = ReporterBuilderImpl


/**
 * Provides options to create a [Translator] or [TranslationSupplier].
 */
interface ReporterBuilder {

    /**
     * Uses [UsingDefaultTranslator] as [Translator] where [getDefaultLocale] is used to format arguments
     * of [TranslatableWithArgs].
     *
     * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
     * the options to specify implementations of them are skipped.
     *
     * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
     */
    fun withoutTranslationsUseDefaultLocale(): ObjectFormatterOption
        = withoutTranslations(getDefaultLocale())

    /**
     * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
     * of [TranslatableWithArgs].
     *
     * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
     * the options to specify implementations of them are skipped.
     *
     * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
     *
     * @param primaryLocale The [Locale] used to format arguments of [TranslatableWithArgs].
     */
    fun withoutTranslations(primaryLocale: Locale): ObjectFormatterOption

    /**
     * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
     * of [TranslatableWithArgs].
     *
     * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
     * the options to specify implementations of them are skipped.
     *
     * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
     * Also notice, that if you omit the [primaryLocale] then [java.util.Locale.getDefault] is used.
     *
     * @param primaryLocale The [Locale] used to format arguments of [TranslatableWithArgs].
     */
    @Deprecated(
        "Use the overload which uses Atrium's [Locale] or `withoutTranslationsUseDefaultLocale`; will be removed with 1.0.0",
        ReplaceWith(
            "this.withoutTranslations(primaryLocale.toAtriumLocale())",
            "ch.tutteli.atrium.core.migration.toAtriumLocale"
        )
    )
    fun withoutTranslations(primaryLocale: java.util.Locale = java.util.Locale.getDefault()): ObjectFormatterOption
        = withoutTranslations(primaryLocale.toAtriumLocale())


    /**
     * Uses the given [translator] as [Translator] skipping the options for [TranslationSupplier] and
     * [LocaleOrderDecider] assuming the given [translator] is implemented differently -- use
     * [withDefaultTranslationSupplier] or [withTranslationSupplier] in case the given [translator] requires
     * a [TranslationSupplier] or a [LocaleOrderDecider].
     */
    fun withTranslator(translator: Translator): ObjectFormatterOption

    /**
     * Uses [CoreFactory.newPropertiesBasedTranslationSupplier] as [TranslationSupplier].
     */
    fun withDefaultTranslationSupplier(): LocaleOrderDeciderOption

    /**
     * Uses the given [translationSupplier] as [TranslationSupplier].
     */
    fun withTranslationSupplier(translationSupplier: TranslationSupplier): LocaleOrderDeciderOption
}
