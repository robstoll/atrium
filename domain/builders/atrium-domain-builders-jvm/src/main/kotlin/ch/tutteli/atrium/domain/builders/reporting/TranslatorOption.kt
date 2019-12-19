@file:Suppress("JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE" /* remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed */)

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create a [Translator].
 */
actual interface TranslatorOption : TranslatorOptionCommon {

    /**
     * Uses [CoreFactory.newTranslator] as [Translator] where the specified [translationSupplier] is used to
     * retrieve translations, the specified [localeOrderDecider] to determine candidate [java.util.Locale]s and
     * [primaryLocale] is used as primary [java.util.Locale] and the optional [fallbackLocales]
     * as fallback [java.util.Locale]s.
     *
     * @param primaryLocale The [java.util.Locale] for which the [Translator] will first search translations --
     *   it will also be used to format arguments of [TranslatableWithArgs].
     * @param fallbackLocales One [java.util.Locale] after another (in the given order) will be considered as primary
     *   Locale in case no translation was found the previous primary Locale.
     */
    @Deprecated(
        "Use the overload which uses Atrium's [Locale]; will be removed with 1.0.0",
        ReplaceWith(
            "this.withDefaultTranslator(primaryLocale.toAtriumLocale(), *fallbackLocales.map { it.toAtriumLocale() }.toTypedArray())",
            "ch.tutteli.atrium.core.migration.toAtriumLocale"
        )
    )
    fun withDefaultTranslator(
        primaryLocale: java.util.Locale,
        vararg fallbackLocales: java.util.Locale
    ): ObjectFormatterOption =
        @Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
        withDefaultTranslator(
            primaryLocale.toAtriumLocale(),
            *fallbackLocales.map { it.toAtriumLocale() }.toTypedArray()
        )
}
