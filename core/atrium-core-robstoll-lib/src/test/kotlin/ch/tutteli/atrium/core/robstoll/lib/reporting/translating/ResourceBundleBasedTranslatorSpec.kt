package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.core.migration.toAtriumLocale
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

object ResourceBundleBasedTranslatorSpec : Spek({
    include(AtriumsTranslatorErrorCaseSpec)
    include(AtriumsTranslatorIntSpec)
}) {
    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory,
        { primaryLocale: java.util.Locale,
          fallbackLocales: List<java.util.Locale> ->
            ResourceBundleBasedTranslator(primaryLocale.toAtriumLocale(), fallbackLocales.map { it.toAtriumLocale() })
        }, "[Atrium's TranslatorErrorSpec] "
    )

    object AtriumsTranslatorIntSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            reporterBuilder
                .withTranslator(
                    ResourceBundleBasedTranslator.create(
                        primaryLocale.toAtriumLocale(),
                        *fallbackLocales.map { it.toAtriumLocale() }.toTypedArray()
                    )
                )
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withTextSameLineAssertionPairFormatter()
                .withDefaultTextCapabilities()
                .withOnlyFailureReporter()
                .build()
        },
        //TODO should be true as soon as http://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
        false,
        "[Atrium's TranslatorIntSpec] "
    )
}
