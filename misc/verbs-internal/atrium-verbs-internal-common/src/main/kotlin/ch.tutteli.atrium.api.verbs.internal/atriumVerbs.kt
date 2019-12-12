package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate assertions.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T): RootExpect<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(EXPECT)
        .withoutOptions()
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator]-lambda where the created [Assertion]s are added as a group and reported as a whole.
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param assertionCreator Assertion group block with a non-fail fast behaviour.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(subject).addAssertionsCreatedBy(assertionCreator)

enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    EXPECT("expect"),
    ;

    init {
        // we specify the factory here because we only need to specify it once and
        // we do not want to specify it if it is not used. The verbs have to be loaded on their first usage
        // and thus this is a good place.
        ReporterFactory.specifyFactoryIfNotYetSet(NoAdjustingReporterFactory.ID)
    }
}

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
