package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.domain.builders.ExpectImpl
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include

//cannot be easily migrated to spek2 as it depends on JVM resources => need to find a solution first
object ResourceBundleBasedTranslatorSpec : Spek({
    include(AtriumsTranslatorErrorCaseSpec)
    include(AtriumsTranslatorIntSpec)
}) {
    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorErrorCaseSpec(
        AssertionVerbFactory, ::ResourceBundleBasedTranslator, "[Atrium's TranslatorErrorSpec] "
    )

    object AtriumsTranslatorIntSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec(
        AssertionVerbFactory,
        { primaryLocale, fallbackLocales ->
            ExpectImpl.reporterBuilder
                .withTranslator(ResourceBundleBasedTranslator.create(primaryLocale, *fallbackLocales))
                .withDetailedObjectFormatter()
                .withDefaultAssertionFormatterController()
                .withDefaultAssertionFormatterFacade()
                .withTextSameLineAssertionPairFormatter()
                .withTextCapabilities()
                .withDefaultAtriumErrorAdjusters()
                .withOnlyFailureReporter()
                .build()
        },
        // ResourceBundleBasedTranslator is only a reference to assure us that we have a similar implementation
        // it could be true if http://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
        false,
        "[Atrium's TranslatorIntSpec] "
    )
}
