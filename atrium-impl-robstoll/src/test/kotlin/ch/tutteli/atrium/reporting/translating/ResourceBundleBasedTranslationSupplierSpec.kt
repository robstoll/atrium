package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec
import java.util.*

object ResourceBundleBasedTranslationSupplierSpec : TranslationSupplierSpec(
    AssertionVerbFactory,
    ReporterBuilder
        .withTranslator(ResourceBundleBasedTranslator.create(Locale("de", "CH"), Locale("fr")))
        .withDetailedObjectFormatter()
        .withSameLineAssertionFormatter()
        .buildOnlyFailureReporter()
)
