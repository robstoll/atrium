package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import org.spekframework.spek2.Spek

@ExperimentalComponentFactoryContainer
object ResourceBundleBasedTranslatorSpec : Spek({
    include(AtriumsTranslatorErrorCaseSpec)
    include(AtriumsTranslatorIntSpec)
}) {
    object AtriumsTranslatorErrorCaseSpec : ch.tutteli.atrium.specs.reporting.translating.TranslatorErrorCaseSpec(
        ::ResourceBundleBasedTranslator, "[Atrium's TranslatorErrorSpec] "
    )


    object AtriumsTranslatorIntSpec : ch.tutteli.atrium.specs.reporting.translating.RealTranslatorIntSpec(
        { options, primaryLocale, fallbackLocales ->
            options.apply {
                withSingletonComponent(Translator::class) {
                    ResourceBundleBasedTranslator(primaryLocale, *fallbackLocales.toTypedArray())
                }
            }
        },
        // ResourceBundleBasedTranslator is only a reference to assure us that we have a similar implementation
        // it could be true if https://bugs.java.com/bugdatabase/view_bug.do?bug_id=JDK-8193496 is fixed in JDK8
        false,
        "[Atrium's TranslatorIntSpec] "
    )
}
