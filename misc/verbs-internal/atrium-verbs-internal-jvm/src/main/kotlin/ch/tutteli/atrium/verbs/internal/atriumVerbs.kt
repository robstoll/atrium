package ch.tutteli.atrium.verbs.internal

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
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

/**
 * Only required if you implement a custom component (for instance an own [Reporter], [ObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-api-cc-en_GB in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
object AssertionVerbFactory : ch.tutteli.atrium.spec.AssertionVerbFactory {

    override fun <T : Any> checkImmediately(subject: T) = assert(subject)
    override fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit): AssertionPlant<T>
        = assert(subject, assertionCreator)

    override fun <T> checkNullable(subject: T) = assert(subject)
    override fun checkException(act: () -> Unit) = expect(act)
}
