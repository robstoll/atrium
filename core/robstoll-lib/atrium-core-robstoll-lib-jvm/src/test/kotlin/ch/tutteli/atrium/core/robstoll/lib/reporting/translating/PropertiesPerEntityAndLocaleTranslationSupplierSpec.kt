package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec

//cannot be easily migrated to specs-common/spek2 as it depends on JVM resources => need to find a solution first
object PropertiesPerEntityAndLocaleTranslationSupplierSpec : TranslatorIntSpec(
    { primaryLocale, fallbackLocales ->
        ExpectImpl.reporterBuilder
            .withTranslationSupplier(PropertiesPerEntityAndLocaleTranslationSupplier())
            .withDefaultLocaleOrderDecider()
            .withDefaultTranslator(primaryLocale, *fallbackLocales)
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withDefaultAtriumErrorAdjusters()
            .withOnlyFailureReporter()
            .build()
    },
    true,
    "[Atrium's TranslationIntSpec] "
)
