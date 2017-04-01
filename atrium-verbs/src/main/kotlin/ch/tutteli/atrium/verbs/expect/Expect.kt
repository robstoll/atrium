package ch.tutteli.atrium.verbs.expect

import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

fun <T : Any> expect(subject: T): IAssertionPlant<T>
    = AtriumFactory.newCheckImmediately("expect", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> expect(subject: T): IAssertionPlantNullable<T>
    = AtriumFactory.newNullable("expect", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> expect(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("expect", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun expect(act: () -> Unit): ThrowableFluent
    = AtriumFactory.throwableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)
