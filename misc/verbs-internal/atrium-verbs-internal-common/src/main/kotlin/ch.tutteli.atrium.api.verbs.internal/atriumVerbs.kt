package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

fun <T> assert(subject: T) =
    ExpectImpl.assertionVerbBuilder(subject).withVerb(AssertionVerb.ASSERT).withDefaultReporter().build()

fun <T> assert(subject: T, assertionCreator: Expect<T>.() -> Unit) =
    assert(subject).addAssertionsCreatedBy(assertionCreator)

fun expect(act: () -> Unit) = ExpectImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, reporter)

enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
    ;

    init {
        // we specify the factory here because we only need to specify it once and
        // we do not want to specify it if it is not used. The verbs have to be loaded on their first usage
        // and thus this is a good place.
        ReporterFactory.specifyFactoryIfNotYetSet(NoAdjustingReporterFactory.ID)
    }
}


class NoAdjustingReporterFactory : ReporterFactory {
    override val id = ID

    override fun create(): Reporter {
        return ExpectImpl.reporterBuilder
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities()
            .withNoOpAtriumErrorAdjuster()
            .withOnlyFailureReporter()
            .build()
    }

    companion object {
        const val ID = "default-no-adjusting"
    }
}
