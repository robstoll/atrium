package ch.tutteli.atrium

import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.test.IAssertionVerbFactory

internal fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

internal inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit): IAssertionPlant<T>
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

internal fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

internal fun expect(act: () -> Unit): ThrowableFluent
    = AtriumFactory.newThrowableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}

/**
 * Only necessary if you want to reuse tests from atrium-test
 */
internal object AssertionVerbFactory : IAssertionVerbFactory {
    override fun <T : Any> checkImmediately(subject: T) = assert(subject)
    override fun <T : Any> checkLazily(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
        = assert(subject, createAssertions)
    override fun <T> checkNullable(subject: T) = assert(subject)
    override fun checkException(act: () -> Unit) = expect(act)
}
