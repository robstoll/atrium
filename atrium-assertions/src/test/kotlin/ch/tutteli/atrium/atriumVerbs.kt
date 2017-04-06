package ch.tutteli.atrium

import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.reporting.ReporterBuilder

fun <T : Any> assert(subject: T)
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assert(subject: T)
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun expect(act: () -> Unit)
    = AtriumFactory.newThrowableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)

object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}
