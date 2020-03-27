package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
// import org.jetbrains.spek.api.Spek
// import org.jetbrains.spek.api.include
import org.spekframework.spek2.Spek

//cannot be easily migrated to specs-common/spek2 as it depends on JVM resources => need to find a solution first
object ResourceBundleBasedTranslatorSpec : Spek({
    include(AtriumsTranslatorErrorCaseSpec)
    include(AtriumsTranslatorIntSpec)
}) {
    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorErrorCaseSpec(
        ::ResourceBundleBasedTranslator, "[Atrium's TranslatorErrorSpec] "
    )

    object AtriumsTranslatorIntSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorIntSpec(
        { primaryLocale, fallbackLocales ->
            ReporterBuilder.create()
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
        // it could be true if https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
        false,
        "[Atrium's TranslatorIntSpec] "
    )
}
