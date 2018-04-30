package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
import java.util.*

internal object ReporterBuilderImpl : ReporterBuilder {

    override fun withoutTranslations(primaryLocale: Locale)
        =
        ObjectFormatterOptionImpl(
            UsingDefaultTranslator(
                primaryLocale
            )
        )

    override fun withTranslator(translator: Translator)
        = ObjectFormatterOptionImpl(translator)

    override fun withDefaultTranslationSupplier()
        =
        LocaleOrderDeciderOptionImpl(coreFactory.newPropertiesBasedTranslationSupplier())

    override fun withTranslationSupplier(translationSupplier: TranslationSupplier)
        = LocaleOrderDeciderOptionImpl(translationSupplier)
}
