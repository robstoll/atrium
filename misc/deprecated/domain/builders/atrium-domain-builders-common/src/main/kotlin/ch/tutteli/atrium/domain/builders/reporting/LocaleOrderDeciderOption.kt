//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.LocaleOrderDeciderOptionImpl
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

/**
 * Provides options to create a [LocaleOrderDecider].
 */
@Deprecated("Configure components via withOptions when creating an expectation verb instead; will be removed with 0.17.0")
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

    companion object {
        fun create(translationSupplier: TranslationSupplier): LocaleOrderDeciderOption =
            LocaleOrderDeciderOptionImpl(translationSupplier)
    }
}
