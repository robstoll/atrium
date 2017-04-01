package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.creating.AssertionPlantFactory

class ReporterBuilder(private val assertionMessageFormatter: IAssertionMessageFormatter) {

    fun buildOnlyFailureReporting(): IReporter
        = AssertionPlantFactory.newOnlyFailureReporter(assertionMessageFormatter)

    companion object {
        fun withDetailedObjectFormatter(): AssertionMessageBuilder
            = AssertionMessageBuilder(AssertionPlantFactory.newDetailedObjectFormatter())
    }

    class AssertionMessageBuilder(private val objectFormatter: IObjectFormatter) {
        fun withSameLineAssertionMessageFormatter(): ReporterBuilder
            = ReporterBuilder(AssertionPlantFactory.newSameLineAssertionMessageFormatter(objectFormatter))
    }
}
