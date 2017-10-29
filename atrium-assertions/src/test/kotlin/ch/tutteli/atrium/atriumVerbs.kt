package ch.tutteli.atrium

import ch.tutteli.atrium.AssertionVerb.ASSERT
import ch.tutteli.atrium.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable

internal fun <T : Any> assert(subject: T)
    = AtriumFactory.newReportingPlantCheckImmediately(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newReportingPlantCheckLazilyAtTheEnd(ASSERT, subject, AtriumReporterSupplier.REPORTER, createAssertions)

internal fun <T : Any?> assert(subject: T)
    = AtriumFactory.newReportingPlantNullable(ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun expect(act: () -> Unit)
    = AtriumFactory.newThrowableFluent(EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)


internal enum class AssertionVerb(override val value: String) : ISimpleTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineTextAssertionFormatter()
            .buildOnlyFailureReporter()
    }
}
