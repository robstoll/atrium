package ch.tutteli.atrium

import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.creating.AssertImpl
import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

internal fun <T : Any> assert(subject: T)
    = AssertImpl.coreFactory.newReportingPlant(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun <T : Any> assert(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(ASSERT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

internal fun <T : Any?> assert(subject: T)
    = AssertImpl.coreFactory.newReportingPlantNullable(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun expect(act: () -> Unit)
    = AssertImpl.throwable.thrownBuilder(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)


internal enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        reporterBuilder
            .withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities()
            .buildOnlyFailureReporter()
    }
}
