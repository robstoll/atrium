package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.reporting.translating.EmptyTranslationProvider
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationProvider
import ch.tutteli.atrium.reporting.translating.ITranslator
import java.util.*

/**
 * A builder to create an [IReporter] consisting of an [IObjectFormatter] which is used by an
 * [IAssertionFormatter] which in turn is used by the created [IReporter].
 */
class ReporterBuilder(private val assertionFormatter: IAssertionFormatter) {

    /**
     * Uses [AtriumFactory.newOnlyFailureReporter] as [IReporter].
     */
    fun buildOnlyFailureReporting(): IReporter
        = AtriumFactory.newOnlyFailureReporter(assertionFormatter)

    companion object {

        /**
         * Uses an [ITranslationProvider] which returns an empty [Map].
         *
         * Or in other words, a [ITranslationProvider] which does not provide any translations.
         */
        fun withoutTranslationProvider(): TranslationBuilder
            = TranslationBuilder(EmptyTranslationProvider)

        /**
         * Uses [AtriumFactory.newTranslator] as [ITranslator]
         * and [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         */
        fun withDetailedObjectFormatter(): AssertionFormatterBuilder
            = withoutTranslationProvider().withEnTranslations().withDetailedObjectFormatter()
    }

    class TranslationBuilder(private val translationProvider: ITranslationProvider) {

        /**
         * Uses [AtriumFactory.newTranslator] as [ITranslator] with [Locale.ENGLISH] as default [Locale] and
         * without any fallback [Locale]s.
         */
        fun withEnTranslations(): ObjectFormatterBuilder
            = ObjectFormatterBuilder(AtriumFactory.newTranslator(translationProvider, Locale.UK!!))


    }

    class ObjectFormatterBuilder(private val translator: ITranslator) {
        /**
         * Uses [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         */
        fun withDetailedObjectFormatter(): AssertionFormatterBuilder
            = AssertionFormatterBuilder(AtriumFactory.newDetailedObjectFormatter(translator), translator)
    }

    class AssertionFormatterBuilder(private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {
        /**
         * Uses [AtriumFactory.newSameLineAssertionFormatter] as [IAssertionFormatter].
         */
        fun withSameLineAssertionMessageFormatter(): ReporterBuilder
            = ReporterBuilder(AtriumFactory.newSameLineAssertionFormatter(objectFormatter, translator))
    }
}
