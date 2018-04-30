package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

/**
 * Provides options to create a [LocaleOrderDecider].
 */
interface LocaleOrderDeciderOption {

    /**
     * The previously chosen [TranslationSupplier].
     */
    val translationSupplier: TranslationSupplier

    /**
     * Uses [CoreFactory.newLocaleOrderDecider] as [LocaleOrderDecider].
     */
    fun withDefaultLocaleOrderDecider(): TranslatorOption

    /**
     * Uses [localeOrderDecider] as [LocaleOrderDecider].
     */
    fun withLocaleOrderDecider(localeOrderDecider: LocaleOrderDecider): TranslatorOption
}
