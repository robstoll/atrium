package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.reporting.reporterBuilder
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.spec.AssertionVerbFactory

internal fun <T : Any> esGilt(subject: T) =
    AssertImpl.coreFactory.newReportingPlant(AssertionVerb.ASSERT, { subject }, reporter)

internal fun <T : Any> esGilt(subject: T, assertionCreator: Assert<T>.() -> Unit) =
    AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(
        AssertionVerb.ASSERT,
        { subject },
        reporter,
        assertionCreator
    )

internal fun <T : Any?> esGilt(subject: T) =
    AssertImpl.coreFactory.newReportingPlantNullable(AssertionVerb.ASSERT, { subject }, reporter)

internal fun erwarte(act: () -> Unit) = AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, reporter)

internal enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("es gilt"),
    EXPECT_THROWN("erwarte, die geworfene Exception"),
    ;

    init {
        // we specify the factory here because we only need to specify it once and
        // we do not want to specify it if it is not used. The verbs have to be loaded on their first usage
        // and thus this is a good place.
        ReporterFactory.specifyFactoryIfNotYetSet(AsciiBulletPointReporterFactory.ID)
    }
}

class AsciiBulletPointReporterFactory : ReporterFactory {
    override val id = ID

    override fun create(): Reporter {
        return reporterBuilder
            .withoutTranslationsUseDefaultLocale()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withTextCapabilities(
                RootAssertionGroupType::class to "* ",
                ListAssertionGroupType::class to "- ",
                FeatureAssertionGroupType::class to "=> ",
                @Suppress("DEPRECATION" /* TODO remove together with entry with 1.0.0 */)
                IndentAssertionGroupType::class to "| ",
                PrefixFeatureAssertionGroupHeader::class to ">> ",
                PrefixSuccessfulSummaryAssertion::class to "(/) ",
                PrefixFailingSummaryAssertion::class to "(x) ",
                WarningAssertionGroupType::class to "(!) "
            )
            .withNoOpAtriumErrorAdjuster()
            .withOnlyFailureReporter()
            .build()
    }
    companion object {
        const val ID = "ascii"
    }
}

/**
 * You need to add atrium-spec to your dependencies in order to be able to reuse the VerbSpec.
 */
internal object VerbSpec : ch.tutteli.atrium.spec.verbs.VerbSpec(
    "es gilt" to { subject -> esGilt(subject) },
    "es gilt" to { subject, assertionCreator -> esGilt(subject, assertionCreator) },
    "es gilt" to { subject -> esGilt(subject) },
    "erwarte" to { act -> erwarte { act() } })

/**
 * Only required if you implement a custom component (for instance an own [Reporter], [ObjectFormatter] etc.)
 * or an own assertion function API (e.g., atrium-api-cc-de_CH in a different language)
 * and you want to reuse a specification from atrium-spec to test your custom component against it.
 */
internal object AssertionVerbFactory : AssertionVerbFactory {
    override fun <T : Any> checkImmediately(subject: T) = esGilt(subject)
    override fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit) =
        esGilt(subject, assertionCreator)

    override fun <T> checkNullable(subject: T) = esGilt(subject)
    override fun checkException(act: () -> Unit) = erwarte(act)
}
