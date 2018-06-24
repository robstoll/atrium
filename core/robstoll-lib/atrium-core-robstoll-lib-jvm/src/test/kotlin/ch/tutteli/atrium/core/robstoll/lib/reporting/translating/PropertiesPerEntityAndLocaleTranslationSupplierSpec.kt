package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder

object PropertiesPerEntityAndLocaleTranslationSupplierSpec  : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
    AssertionVerbFactory,
    { primaryLocale, fallbackLocales ->
        reporterBuilder
            .withTranslationSupplier(PropertiesPerEntityAndLocaleTranslationSupplier())
            .withDefaultLocaleOrderDecider()
            .withDefaultTranslator(primaryLocale, *fallbackLocales)
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities()
            .withOnlyFailureReporter()
            .build()
    },
    true,
    "[Atrium's TranslationIntSpec] "
)
