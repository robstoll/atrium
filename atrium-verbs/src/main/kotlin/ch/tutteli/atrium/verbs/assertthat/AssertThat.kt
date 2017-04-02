package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

fun <T : Any> assertThat(subject: T): IAssertionPlant<T>
    = AtriumFactory.newCheckImmediately("assert that", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assertThat(subject: T): IAssertionPlantNullable<T>
    = AtriumFactory.newNullable("assert that", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AtriumFactory.newCheckLazilyAtTheEnd("assert that", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun assertThat(act: () -> Unit): ThrowableFluent
    = AtriumFactory.newThrowableFluent("assert that the thrown exception", act, AtriumReporterSupplier.REPORTER)
