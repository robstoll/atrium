package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

object ResourceBundleBasedTranslatorSpec : Spek({
    include(AtriumsTranslatorErrorCaseSpec)
    include(AtriumsTranslatorIntSpec)
}) {
    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory, ::ResourceBundleBasedTranslator, "[Atrium's TranslatorErrorSpec] "
    )

    object AtriumsTranslatorIntSpec : ch.tutteli.atrium.spec.reporting.translating.TranslatorIntSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            reporterBuilder
                .withTranslator(ResourceBundleBasedTranslator.create(primaryLocale, *fallbackLocales))
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withTextSameLineAssertionPairFormatter()
                .withTextCapabilities()
                .withOnlyFailureReporter()
                .build()
        },
        //TODO should be true as soon as http://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
        false,
        "[Atrium's TranslatorIntSpec] "
    )
}
