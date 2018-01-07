package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.throwable.thrown.builders.ThrowableThrownBuilder
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable
import ch.tutteli.atrium.spec.AssertionVerbFactory

internal fun <T : Any> esGilt(subject: T)
    = AtriumFactory.newReportingPlant(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun <T : Any> esGilt(subject: T, assertionCreator: Assert<T>.() -> Unit)
    = AtriumFactory.newReportingPlantAndAddAssertionsCreatedBy(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER, assertionCreator)

internal fun <T : Any?> esGilt(subject: T)
    = AtriumFactory.newReportingPlantNullable(AssertionVerb.ASSERT, subject, AtriumReporterSupplier.REPORTER)

internal fun erwarte(act: () -> Unit)
    = ThrowableThrownBuilder(AssertionVerb.EXPECT_THROWN, act, AtriumReporterSupplier.REPORTER)

internal enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    ASSERT("es gilt"),
    EXPECT_THROWN("erwarte, die geworfene Exception"),
}

internal object AtriumReporterSupplier {
    val REPORTER by lazy {
        ReporterBuilder
            .withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
            .withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities(
                RootAssertionGroupType::class.java to "* ",
                ListAssertionGroupType::class.java to "- ",
                FeatureAssertionGroupType::class.java to "=> ",
                IndentAssertionGroupType::class.java to "| ",
                PrefixFeatureAssertionGroupHeader::class.java to ">> ",
                PrefixSuccessfulSummaryAssertion::class.java to "(/) ",
                PrefixFailingSummaryAssertion::class.java to "(x) ",
                WarningAssertionGroupType::class.java to "(!) "
            )
            .buildOnlyFailureReporter()
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
