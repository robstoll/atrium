@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterBuilderImpl
import ch.tutteli.atrium.reporting.translating.*

/**
 * Provides options to create a [Translator] or [TranslationSupplier].
 */
actual interface ReporterBuilder : ReporterBuilderCommon {
    actual companion object {
        actual fun create(): ReporterBuilder = ReporterBuilderImpl
    }

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
    //TODO only here to retain backward compatibility - remove with 1.0.0 and move companion object to common
    fun withoutTranslations(primaryLocale: java.util.Locale = java.util.Locale.getDefault()): ObjectFormatterOption =
        @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
        withoutTranslations(primaryLocale.toAtriumLocale())
}
