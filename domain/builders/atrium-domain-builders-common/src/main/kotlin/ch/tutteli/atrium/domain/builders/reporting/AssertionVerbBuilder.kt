package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.FinalStepImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.ReporterOptionImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.ReporterFactory
import ch.tutteli.atrium.reporting.reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Defines the contract to create custom assertion verbs.
 *
 */
interface AssertionVerbBuilder {
    /**
     * Option step which allows to specify the assertion verb which shall be used.
     *
     * @param T the type of the subject.
     */
    interface AssertionVerbOption<T> {
        /**
         * The previously specified subject of the assertion.
         */
        val maybeSubject: Option<T>

        /**
         * Wraps the given [verb] into an [Untranslatable] and uses it as assertion verb.
         */
        fun withVerb(verb: String) = withVerb(Untranslatable(verb))

        /**
         * Uses the given [verb] as assertion verb.
         */
        fun withVerb(verb: Translatable): ReporterOption<T>
    }

    /**
     * Option step which allows to specify the [Reporter] which shall be used during reporting
     *
     * @param T the type of the subject.
     */
    interface ReporterOption<T> {
        /**
         * The previously specified subject of the assertion.
         */
        val maybeSubject: Option<T>

        /**
         * The previously defined assertion verb.
         */
        val assertionVerb: Translatable

        /**
         * Uses the [Reporter] returned by [reporter] for reporting.
         *
         * You can configure the default [Reporter] via [ReporterFactory.ATRIUM_PROPERTY_KEY],
         * see [reporter] for more details.
         */
        fun withDefaultReporter() = withCustomReporter(reporter)

        /**
         * Uses the given [reporter] for reporting.
         */
        fun withCustomReporter(reporter: Reporter): FinalStep<T>

        companion object {
            fun <T> create(
                maybeSubject: Option<T>,
                assertionVerb: Translatable
            ): ReporterOption<T> = ReporterOptionImpl(maybeSubject, assertionVerb)
        }
    }

    /**
     * Final step in the assertion verb building process, creates a new [Expect] based on the so far specified options.
     *
     * @param T the type of the subject.
     */
    interface FinalStep<T> {
        /**
         * The previously specified subject of the assertion.
         */
        val maybeSubject: Option<T>

        /**
         * The previously defined assertion verb.
         */
        val assertionVerb: Translatable

        /**
         * The previously specified [Reporter]
         */
        val reporter: Reporter

        /**
         * Creates a new [Expect] based on the previously defined options.
         */
        fun build(): Expect<T>

        companion object {
            fun <T> create(
                maybeSubject: Option<T>,
                assertionVerb: Translatable,
                reporter: Reporter
            ): FinalStep<T> = FinalStepImpl(maybeSubject, assertionVerb, reporter)
        }
    }
}

