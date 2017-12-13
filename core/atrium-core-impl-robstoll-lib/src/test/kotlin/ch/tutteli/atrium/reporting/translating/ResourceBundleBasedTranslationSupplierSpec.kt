package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import java.util.*

class ResourceBundleBasedTranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec(
    AssertionVerbFactory,
    ReporterBuilder
        .withTranslator(ResourceBundleBasedTranslator(Locale("de", "CH"), Locale("fr")))
        .withDetailedObjectFormatter()
        .withDefaultAssertionFormatterController()
        .withDefaultAssertionFormatterFacade()
        .withSameLineTextAssertionFormatter()
        .buildOnlyFailureReporter()
)
