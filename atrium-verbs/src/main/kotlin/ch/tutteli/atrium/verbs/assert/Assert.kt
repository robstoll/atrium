package ch.tutteli.atrium.verbs.assert

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.newCheckLazilyAtTheEnd
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

fun <T : Any> assert(subject: T)
    = AtriumFactory.newCheckImmediately("assert", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assert(subject: T)
    = AtriumFactory.newNullable("assert", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assert(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun assert(act: () -> Unit)
    = AtriumFactory.newThrowableFluent("assert the thrown exception", act, AtriumReporterSupplier.REPORTER)
