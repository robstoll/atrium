package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory

/**
 * A builder to create an [IReporter] consisting of an [IObjectFormatter] which is used by an
 * [IAssertionMessageFormatter] which in turn is used by the created [IReporter].
 */
class ReporterBuilder(private val assertionMessageFormatter: IAssertionMessageFormatter) {

    /**
     * Uses [AtriumFactory.newOnlyFailureReporter] as [IReporter].
     */
    fun buildOnlyFailureReporting(): IReporter
        = AtriumFactory.newOnlyFailureReporter(assertionMessageFormatter)

    companion object {
        /**
         * Uses [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         */
        fun withDetailedObjectFormatter(): AssertionMessageBuilder
            = AssertionMessageBuilder(AtriumFactory.newDetailedObjectFormatter())
    }

    class AssertionMessageBuilder(private val objectFormatter: IObjectFormatter) {
        /**
         * Uses [AtriumFactory.newSameLineAssertionMessageFormatter] as [IAssertionMessageFormatter].
         */
        fun withSameLineAssertionMessageFormatter(): ReporterBuilder
            = ReporterBuilder(AtriumFactory.newSameLineAssertionMessageFormatter(objectFormatter))
    }
}
