package ch.tutteli.atrium

import ch.tutteli.atrium.creating.AssertionPlantFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.reporting.ReporterBuilder

fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)

object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withDetailedObjectFormatter()
            .withSameLineAssertionMessageFormatter()
            .buildOnlyFailureReporting()
    }
}
