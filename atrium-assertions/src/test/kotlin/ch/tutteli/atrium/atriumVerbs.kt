package ch.tutteli.atrium

import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.ReporterBuilder.Companion
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

internal fun <T : Any> assert(subject: T)
    = AtriumFactory.newReportingPlant(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun <T : Any> assert(subject: T, assertionCreator: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantAndAddAssertionsCreatedBy(ASSERT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

internal fun <T : Any?> assert(subject: T)
    = AtriumFactory.newReportingPlantNullable(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun expect(act: () -> Unit)
    = ThrowableThrownBuilder(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)


internal enum class AssertionVerb(override val value: String) : ISimpleTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    }
}
