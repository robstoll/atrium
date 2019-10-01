package ch.tutteli.atrium.api.verbs.internal

import ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT
import ch.tutteli.atrium.api.verbs.internal.AssertionVerb.EXPECT_THROWN
import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.domain.builders.reporting.ReporterBuilder
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Creates an [Expect] for the given [subject].
 *
 * @return The newly created assertion container.
 */
fun <T> expect(subject: T, representation: String? = null, options: ExpectOptions = ExpectOptions()): Expect<T> =
    ExpectBuilder.forSubject(subject)
        .withVerb(EXPECT)
        .withOptions(options.merge(ExpectOptions(representation = representation?.let { RawString.create(it) })))
        .build()

/**
 * Creates an [Expect] for the given [subject] and [Expect.addAssertionsCreatedBy] the
 * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
 * the configured [Reporter]) reported as a whole.
 *
 * @return The newly created assertion container.
 */
fun <T> expect(
    subject: T,
    representation: String? = null,
    options: ExpectOptions = ExpectOptions(),
    assertionCreator: Expect<T>.() -> Unit
): Expect<T> = expect(subject, representation, options).addAssertionsCreatedBy(assertionCreator)

/**
 * Creates a [ThrowableThrown.Builder] for the given function [act] which catches a potentially thrown [Throwable]
 * and allows to define an assertion for it.
 *
 * @return The newly created [ThrowableThrown.Builder].
 */
fun expect(act: () -> Unit): ThrowableThrown.Builder = ExpectImpl.throwable.thrownBuilder(EXPECT_THROWN, act, reporter)

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
