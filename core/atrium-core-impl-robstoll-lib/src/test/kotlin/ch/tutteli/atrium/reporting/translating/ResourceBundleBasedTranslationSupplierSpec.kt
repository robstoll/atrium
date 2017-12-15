package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import java.util.*

class ResourceBundleBasedTranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierIntSpec(
    AssertionVerbFactory,
    ReporterBuilder
        .withTranslator(ResourceBundleBasedTranslator.create(Locale("de", "CH"), Locale("fr")))
        .withDetailedObjectFormatter()
        .withDefaultAssertionFormatterController()
        .withDefaultAssertionFormatterFacade()
        .withSameLineTextAssertionFormatter()
        .buildOnlyFailureReporter()
)
