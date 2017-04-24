package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.reporting.translating.ITranslator

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
         * Uses [AtriumFactory.newTranslator] as [ITranslator].
         */
        fun withDefaultTranslator(): ObjectFormatterBuilder
            = ObjectFormatterBuilder(AtriumFactory.newTranslator())

        /**
         * Uses [AtriumFactory.newTranslator] as [ITranslator]
         * and [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         */
        fun withDetailedObjectFormatter(): AssertionFormatterBuilder
            = ObjectFormatterBuilder(AtriumFactory.newTranslator()).withDetailedObjectFormatter()
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
