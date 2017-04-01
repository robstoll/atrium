package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

fun <T : Any> assert(subject: T): IAssertionPlant<T>
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assert(subject: T): IAssertionPlantNullable<T>
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun assert(act: () -> Unit): ThrowableFluent
    = AtriumFactory.throwableFluent("assert the thrown exception", act, AtriumReporterSupplier.REPORTER)
