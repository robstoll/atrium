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

internal fun <T : Any> esGilt(subject: T)
    = AssertImpl.coreFactory.newReportingPlant(AssertionVerb.ASSERT, { subject }, reporter)

internal fun <T : Any> esGilt(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = AssertImpl.coreFactory.newReportingPlantAndAddAssertionsCreatedBy(AssertionVerb.ASSERT, { subject }, reporter, assertionCreator)

internal fun <T : Any?> esGilt(subject: T)
    = AssertImpl.coreFactory.newReportingPlantNullable(AssertionVerb.ASSERT, { subject }, reporter)

internal fun erwarte(act: () -> Unit)
    = AssertImpl.throwable.thrownBuilder(AssertionVerb.EXPECT_THROWN, act, reporter)

internal enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("es gilt"),
    EXPECT_THROWN("erwarte, die geworfene Exception"),
    ;

    init {
        // we specify the factory here because we only need to specify it once and
        // we do not want to specify it if it is not used. The verbs have to be loaded on their first usage
        // and thus this is a good place.
        ReporterFactory.specifyFactoryIfNotYetSet("ascii")
    }
}

class AsciiBulletPointReporterFactory : ReporterFactory {
    override val id = "ascii"

    override fun create(): Reporter {
        return reporterBuilder
            .withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities(
                RootAssertionGroupType::class.java to "* ",
                ListAssertionGroupType::class.java to "- ",
                FeatureAssertionGroupType::class.java to "=> ",
                //TODO remove with 1.0.0
                IndentAssertionGroupType::class.java to "| ",
                PrefixFeatureAssertionGroupHeader::class.java to ">> ",
                PrefixSuccessfulSummaryAssertion::class.java to "(/) ",
                PrefixFailingSummaryAssertion::class.java to "(x) ",
                WarningAssertionGroupType::class.java to "(!) "
            )
            .withOnlyFailureReporter()
            .build()
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
    override fun <T : Any> checkLazily(subject: T, assertionCreator: Assert<T>.() -> Unit)
        = esGilt(subject, assertionCreator)

    override fun <T> checkNullable(subject: T) = esGilt(subject)
    override fun checkException(act: () -> Unit) = erwarte(act)
}
