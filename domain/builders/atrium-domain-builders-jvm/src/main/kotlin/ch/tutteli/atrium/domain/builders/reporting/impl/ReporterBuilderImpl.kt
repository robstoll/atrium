package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.LocaleOrderDeciderOption
import ch.tutteli.atrium.domain.builders.reporting.ObjectFormatterOption
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator

internal actual object ReporterBuilderImpl : ReporterBuilder {

    override fun withoutTranslations(primaryLocale: Locale)
        = withTranslator(UsingDefaultTranslator(primaryLocale))

    override fun withTranslator(translator: Translator)
        = ObjectFormatterOption.create(translator)

    override fun withDefaultTranslationSupplier()
        = withTranslationSupplier(coreFactory.newPropertiesBasedTranslationSupplier())

    override fun withTranslationSupplier(translationSupplier: TranslationSupplier)
        = LocaleOrderDeciderOption.create(translationSupplier)
}
