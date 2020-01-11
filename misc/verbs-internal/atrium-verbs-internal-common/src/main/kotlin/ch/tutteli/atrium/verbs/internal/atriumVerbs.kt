//TODO remove with 1.0.0
package ch.tutteli.atrium.verbs.internal

import ch.tutteli.atrium.core.newReportingPlantNullable
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

// ------------------------------------------------------------
// @Jetbrains -> try to apply the replacement, the import is not added
fun <T : Any> assert(subject: T) = AssertImpl.coreFactory.newReportingPlant(AssertionVerb.ASSERT, { subject }, reporter)

@Suppress("DEPRECATION")
fun <T : Any> assert(subject: T, assertionCreator: Assert<T>.() -> Unit) =
    AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(
        AssertionVerb.ASSERT,
        { subject },
        reporter,
        assertionCreator
    )

@Suppress("DEPRECATION")
fun <T : Any?> assert(subject: T) =
    AssertImpl.coreFactory.newReportingPlantNullable(AssertionVerb.ASSERT, { subject }, reporter)

@Suppress("DEPRECATION")
fun expect(act: () -> Unit) = AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, reporter)

@Deprecated("Switch to api.verbs.internal.AssertionVerb; will be removed with 1.0.0")
@Suppress("DEPRECATION")
enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("assert"),
    EXPECT_THROWN("expect the thrown exception"),
    ;

    init {
        // we specify the factory here because we only need to specify it once and
        // we do not want to specify it if it is not used. The verbs have to be loaded on their first usage
        // and thus this is a good place.
        @Suppress("DEPRECATION")
        ReporterFactory.specifyFactoryIfNotYetSet(NoAdjustingReporterFactory.ID)
    }
}

@Deprecated(
    "Switch to api.verbs.internal.NoAdjustingReporterFactory; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.api.verbs.internal.NoAdjustingReporterFactory")
)
class NoAdjustingReporterFactory : ReporterFactory {
    override val id: String = ID

    override fun create(): Reporter {
        return ReporterBuilder.create()
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
        const val ID: String = "default-no-adjusting"
    }
}
