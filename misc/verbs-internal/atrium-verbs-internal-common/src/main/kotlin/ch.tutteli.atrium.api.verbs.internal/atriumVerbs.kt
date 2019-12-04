package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Creates an [Expect] for the given [subject].
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(subject: T, representation: String? = null, options: ExpectOptions? = null): RootExpect<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(EXPECT)
        .withMaybeRepresentationAndMaybeOptions(representation, options)
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator]-lambda where the created [Assertion]s are added as a group and reported as a whole.
 *
 * @param subject The subject for which we are going to postulate assertions.
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 * @param assertionCreator Assertion group block with a non-fail fast behaviour.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <T> expect(
    subject: T,
    representation: String? = null,
    options: ExpectOptions? = null,
    assertionCreator: Expect<T>.() -> Unit
): Expect<T> = expect(subject, representation, options).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates an [Expect] with the given [act]-lambda as subject.
 *
 * @param act the subject for which we are going to postulate assertions.
 * @param options Optional, use it in case you want to tweak the resulting [Expect], for instance, use another reporter.
 * @param representation Optional, use it in case you want to use a custom representation for the subject.
 *
 * @return The newly created assertion container.
 * @throws AssertionError in case an assertion does not hold.
 */
fun <R> expect(
    options: ExpectOptions? = null,
    representation: String? = null,
    act: () -> R
): RootExpect<() -> R> = expect(act, representation, options)

/**
 * Optimised version which only creates ExpectOptions if really required.
 */
fun <T> ExpectBuilder.OptionsStep<T>.withMaybeRepresentationAndMaybeOptions(
    representation: String?, options: ExpectOptions?
): ExpectBuilder.FinalStep<T> =
    if (representation == null) {
        if (options == null) this.withoutOptions()
        else this.withOptions(options)
    } else {
        val representationOption = ExpectOptions(representation = RawString.create(representation))
        if (options == null) this.withOptions(representationOption)
        else this.withOptions(options.merge(representationOption))
    }


enum class AssertionVerb(override val value: String) : StringBasedTranslatable {
    EXPECT("expect"),
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
