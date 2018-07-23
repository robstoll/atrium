package ch.tutteli.atrium.verbs.internal

import ch.tutteli.atrium.core.newReportingPlantNullable
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

fun <T : Any> assert(subject: T)
    = AssertImpl.coreFactory.newReportingPlant(AssertionVerb.ASSERT, { subject }, reporter)

fun <T : Any> assert(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(AssertionVerb.ASSERT, { subject }, reporter, assertionCreator)

fun <T : Any?> assert(subject: T)
    = AssertImpl.coreFactory.newReportingPlantNullable(AssertionVerb.ASSERT, { subject }, reporter)

fun expect(act: () -> Unit)
    = AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, reporter)

enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
}
