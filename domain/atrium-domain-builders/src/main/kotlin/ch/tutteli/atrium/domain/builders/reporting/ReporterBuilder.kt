package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.ReporterBuilderImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

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
     * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
     * of [TranslatableWithArgs].
     *
     * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
     * the options to specify implementations of them are skipped.
     *
     * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
     * Also notice, that if you omit the [primaryLocale] then [Locale.getDefault] is used.
     *
     * @param primaryLocale The [Locale] used to format arguments of [TranslatableWithArgs].
     */
    fun withoutTranslations(primaryLocale: Locale = Locale.getDefault()): ObjectFormatterOption

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
