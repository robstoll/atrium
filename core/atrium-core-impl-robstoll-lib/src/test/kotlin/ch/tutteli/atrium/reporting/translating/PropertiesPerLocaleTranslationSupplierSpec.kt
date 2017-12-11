package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.spec.reporting.translating.TranslationSupplierSpec
import java.util.*

object PropertiesPerLocaleTranslationSupplierSpec : TranslationSupplierSpec(
    AssertionVerbFactory,
    ReporterBuilder
        .withDefaultTranslator(PropertiesPerLocaleTranslationSupplier(), Locale("de", "CH"), Locale("fr"))
        .withDetailedObjectFormatter()
        .withDefaultAssertionFormatterController()
        .withDefaultAssertionFormatterFacade()
        .withSameLineTextAssertionFormatter()
        .buildOnlyFailureReporter()
)
