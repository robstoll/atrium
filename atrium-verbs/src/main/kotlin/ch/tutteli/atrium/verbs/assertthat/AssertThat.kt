package ch.tutteli.atrium.verbs.assertthat

import ch.tutteli.atrium.creating.AssertionPlantFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IAssertionPlantNullable
import ch.tutteli.atrium.creating.ThrowableFluent
import ch.tutteli.atrium.verbs.AtriumReporterSupplier

fun <T : Any> assertThat(subject: T): IAssertionPlant<T>
    = AssertionPlantFactory.newCheckImmediately("assert that", subject, AtriumReporterSupplier.REPORTER)

fun <T : Any?> assertThat(subject: T): IAssertionPlantNullable<T>
    = AssertionPlantFactory.newNullable("assert that", subject, AtriumReporterSupplier.REPORTER)

inline fun <T : Any> assertThat(subject: T, createAssertions: IAssertionPlant<T>.() -> Unit)
    = AssertionPlantFactory.newCheckLazilyAtTheEnd("assert that", subject, AtriumReporterSupplier.REPORTER, createAssertions)

fun assertThat(act: () -> Unit): ThrowableFluent
    = AssertionPlantFactory.throwableFluent("expect the thrown exception", act, AtriumReporterSupplier.REPORTER)
