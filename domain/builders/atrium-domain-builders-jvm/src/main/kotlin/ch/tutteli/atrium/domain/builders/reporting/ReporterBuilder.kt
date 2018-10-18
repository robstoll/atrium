package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.reporting.translating.*

/**
 * Provides options to create a [Translator] or [TranslationSupplier].
 */
actual interface ReporterBuilder : ReporterBuilderCommon {

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
    fun withoutTranslations(primaryLocale: java.util.Locale = java.util.Locale.getDefault()): ObjectFormatterOption =
        @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
        withoutTranslations(primaryLocale.toAtriumLocale())
}
