package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.reporting.ReporterBuilder

class ResourceBundleBasedTranslationSupplierSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
    AssertionVerbFactory,
    { primaryLocale, fallbackLocales ->
        ReporterBuilder
            .withTranslator(ResourceBundleBasedTranslator.create(primaryLocale, *fallbackLocales))
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    },
    //TODO should be true as soon as http://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
    false
)
