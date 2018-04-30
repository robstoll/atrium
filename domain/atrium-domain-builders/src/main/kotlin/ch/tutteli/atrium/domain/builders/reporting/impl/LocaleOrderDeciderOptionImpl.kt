package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.LocaleOrderDeciderOption
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.TranslationSupplier

internal class LocaleOrderDeciderOptionImpl(
    override val translationSupplier: TranslationSupplier
) : LocaleOrderDeciderOption {

    override fun withDefaultLocaleOrderDecider()
        = TranslatorOptionImpl(
        translationSupplier,
        coreFactory.newLocaleOrderDecider()
    )

    override fun withLocaleOrderDecider(localeOrderDecider: LocaleOrderDecider)
        = TranslatorOptionImpl(translationSupplier, localeOrderDecider)
}
