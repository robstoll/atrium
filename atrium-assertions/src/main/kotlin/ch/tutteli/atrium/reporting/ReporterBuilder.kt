package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory

class ReporterBuilder(private val assertionMessageFormatter: IAssertionMessageFormatter) {

    fun buildOnlyFailureReporting(): IReporter
        = AtriumFactory.newOnlyFailureReporter(assertionMessageFormatter)

    companion object {
        fun withDetailedObjectFormatter(): AssertionMessageBuilder
            = AssertionMessageBuilder(AtriumFactory.newDetailedObjectFormatter())
    }

    class AssertionMessageBuilder(private val objectFormatter: IObjectFormatter) {
        fun withSameLineAssertionMessageFormatter(): ReporterBuilder
            = ReporterBuilder(AtriumFactory.newSameLineAssertionMessageFormatter(objectFormatter))
    }
}
