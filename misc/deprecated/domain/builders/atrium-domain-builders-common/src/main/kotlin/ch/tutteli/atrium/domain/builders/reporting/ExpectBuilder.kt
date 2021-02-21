//TODO remove latest with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.AssertionVerbStepImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.FinalStepImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.OptionsChooserImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.OptionsStepImpl
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Defines the contract to create custom assertion verbs, `Expect<T>` respectively.
 */
@Deprecated(
    "Use RootExpectBuilder from atrium-logic; will be removed latest with 1.0.0 (maybe earlier)",
    ReplaceWith("RootExpectBuilder", "ch.tutteli.atrium.logic.creating.RootExpectBuilder")
)
interface ExpectBuilder {
    companion object {

        /**
         * Entry point to use the [ExpectBuilder] which helps in creating
         * an assertion verb for the given [subject] or in other words an [Expect] for the given [subject].
         */
        @Deprecated(
            "Use RootExpectBuilder from atrium-logic; will be removed latest with 1.0.0 (maybe earlier)",
            ReplaceWith(
                "RootExpectBuilder.forSubject<T>(subject)",
                "ch.tutteli.atrium.logic.creating.RootExpectBuilder"
            )
        )
        fun <T> forSubject(subject: T): AssertionVerbStep<T> = AssertionVerbStepImpl(Some(subject))
    }

    /**
     * Step which allows to specify the assertion verb which shall be used.
     *
     * @param T the type of the subject.
     */
    @Deprecated("Use RootExpectBuilder.ExpectationVerbStep from atrium-logic;  will be removed latest with 1.0.0 (maybe earlier)")
    interface AssertionVerbStep<T> {
        /**
         * The previously specified subject of `this` expectation.
         */
        val maybeSubject: Option<T>

        /**
         * Wraps the given [verb] into an [Untranslatable] and uses it as assertion verb.
         */
        fun withVerb(verb: String): OptionsStep<T> = withVerb(Untranslatable(verb))

        /**
         * Uses the given [verb] as assertion verb.
         */
        fun withVerb(verb: Translatable): OptionsStep<T>
    }

    /**
     * Step which allows to override previously defined properties -- such as use a different assertion verb -- but
     * also allows to define options where usually a default value is used, such as use a customer [Reporter].
     *
     * @param T the type of the subject.
     */
    @Deprecated("Use RootExpectBuilder.OptionsStep from atrium-logic;  will be removed latest with 1.0.0 (maybe earlier)")
    interface OptionsStep<T> {
        /**
         * The previously specified subject of `this` expectation.
         */
        val maybeSubject: Option<T>

        /**
         * The previously defined assertion verb.
         */
        val assertionVerb: Translatable


        /**
         * Allows to define the [ExpectOptions] via an [OptionsChooser]-lambda which provides convenience functions.
         *
         * The function usually start with `with...` and are sometimes overloaded to ease the configuration.
         */
        fun withOptions(configuration: OptionsChooser<T>.() -> Unit): FinalStep<T> =
            withOptions(ExpectOptions(configuration))

        /**
         * Uses the given [expectOptions].
         */
        fun withOptions(expectOptions: ExpectOptions<T>): FinalStep<T>

        /**
         * States explicitly that no optional [ExpectOptions] are defined, which means, `build` will create
         * a new [Expect] based on the previously defined mandatory options but without any optional options or
         * in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [ExpectOptions] such as, override the
         * verb, define an own representation or use an own [Reporter].
         */
        fun withoutOptions(): FinalStep<T>

        companion object {
            @Deprecated(
                "Use OptionsStep from atrium-logic; will be removed with 0.17.0",
                ReplaceWith("ch.tutteli.atrium.logic.creating.ExpectBuilder.OptionsStep.create(maybeSubject, assertionVerb)")
            )
            fun <T> create(
                maybeSubject: Option<T>,
                assertionVerb: Translatable
            ): OptionsStep<T> = OptionsStepImpl(maybeSubject, assertionVerb)
        }
    }

    /**
     * Helper lambda to specify [ExpectOptions] via convenience methods.
     *
     * Calling multiple times the same method overrides the previously defined value.
     */
    @Deprecated(
        "Use RootExpectBuilder.OptionsChooser from atrium-logic; will be removed latest with 1.0.0 (maybe earlier)",
        ReplaceWith(" RootExpectBuilder.OptionsChooser", "ch.tutteli.atrium.logic.creating.RootExpectBuilder")
    )
    interface OptionsChooser<T> {

        /**
         * Wraps the given [verb] into an [Untranslatable] and passes it to the overload
         * which expects a [Translatable] -- this is then used as custom assertion verb
         * instead of the previously defined verb.
         *
         */
        fun withVerb(verb: String) {
            withVerb(Untranslatable(verb))
        }

        /**
         * Uses the given [verb] as assertion verb instead of the previously defined verb.
         */
        fun withVerb(verb: Translatable)

        /**
         * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
         * instead of the representation that has been defined so far (which defaults to the subject itself).
         *
         * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
         */
        fun withRepresentation(textRepresentation: String): Unit =
            withRepresentation { Text(textRepresentation) }

        /**
         * Uses the given [representationProvider] to retrieve a representation which can be based on the current
         * subject where it is used as new representation of the subject
         * instead of the representation that has been defined so far (which defaults to the subject itself).
         *
         * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
         * then wrap it into a [Text] and pass it instead.
         * If your text does not include the current subject, then we recommend to use the other overload which expects
         * a `String` and does the wrapping for you.
         *
         * In case [Expect.maybeSubject] is not defined i.e. [None], then the previous representation is used.
         */
        fun withRepresentation(representationProvider: (T) -> Any)

        /**
         * Uses the given [reporter] instead of the default reporter.
         */
        fun withReporter(reporter: Reporter)

        companion object {
            @Deprecated(
                "Use the helper function OptionsChooser from atrium-logic",
                ReplaceWith("RootExpectOptions(configuration)", "ch.tutteli.atrium.logic.creating.RootExpectOptions")
            )
            fun <T> createAndBuild(configuration: OptionsChooser<T>.() -> Unit): ExpectOptions<T> =
                OptionsChooserImpl<T>().apply(configuration).build()
        }
    }

    /**
     * Final step in the assertion verb building process, creates a new [Expect] based on the so far specified options.
     *
     * @param T the type of the subject.
     */
    @Deprecated("Use RootExpectBuilder.FinalStep from atrium-logic;  will be removed latest with 1.0.0 (maybe earlier)")
    interface FinalStep<T> {
        /**
         * The previously specified subject of `this` expectation.
         */
        val maybeSubject: Option<T>

        /**
         * The previously defined assertion verb.
         */
        val assertionVerb: Translatable

        /**
         * Either the previously specified [ExpectOptions] or `null`.
         */
        val options: ExpectOptions<T>?

        /**
         * Creates a new [Expect] based on the previously defined maybeOptions.
         */
        fun build(): RootExpect<T>

        companion object {
            @Deprecated(
                "Use OptionsStep from atrium-logic; will be removed with 0.17.0",
                ReplaceWith("ch.tutteli.atrium.logic.creating.ExpectBuilder.FinalStep.create(maybeSubject, assertionVerb, options)")
            )
            fun <T> create(
                maybeSubject: Option<T>,
                assertionVerb: Translatable,
                options: ExpectOptions<T>?
            ): FinalStep<T> = FinalStepImpl(maybeSubject, assertionVerb, options)
        }
    }
}

/**
 * Additional (non-mandatory) options for the [ExpectBuilder] to create an [Expect].
 *
 * @property assertionVerb Defines a custom assertion verb if not null.
 * @property representationInsteadOfSubject Defines a custom representation based on a present subject if not null.
 * @property reporter Defines a custom reporter if not null.
 */
@Deprecated(
    "Use RootExpectOptions instead; will be removed latest with 1.0.0 (maybe earlier)",
    ReplaceWith(
        "RootExpectOptions(assertionVerb, representationInsteadOfSubject, reporter)",
        "ch.tutteli.atrium.logic.creating.RootExpectOptions"
    )
)
data class ExpectOptions<T>(
    val assertionVerb: Translatable? = null,
    val representationInsteadOfSubject: ((T) -> Any)? = null,
    val reporter: Reporter? = null
) {
    /**
     * Merges the given [options] with this object creating a new [ExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representationInsteadOfSubject] (meaning it is not `null`) and
     * the given [options] as well, then the resulting [ExpectOptions] will have the
     * [representationInsteadOfSubject] of [options].
     */
    fun merge(options: ExpectOptions<T>): ExpectOptions<T> =
        ExpectOptions(
            options.assertionVerb ?: assertionVerb,
            options.representationInsteadOfSubject ?: representationInsteadOfSubject,
            options.reporter ?: reporter
        )

    @ExperimentalNewExpectTypes
    @Suppress("DEPRECATION" /* RequiresOptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalComponentFactoryContainer::class)
    fun toRootExpectOptions(): RootExpectOptions<T> =
        RootExpectOptions(
            assertionVerb,
            representationInsteadOfSubject,
            reporter?.let { r ->
                ComponentFactoryContainer.createIfNotEmpty(
                    mapOf(Reporter::class to ComponentFactory({ r }, producesSingleton = true)),
                    emptyMap()
                )
            })
}

@Suppress("FunctionName")
@Deprecated(
    "Use RootExpectOptions instead; will be removed latest with 1.0.0 (maybe earlier)",
    ReplaceWith("RootExpectOptions(configuration)", "ch.tutteli.atrium.logic.creating.RootExpectOptions")
)
fun <T> ExpectOptions(configuration: ExpectBuilder.OptionsChooser<T>.() -> Unit): ExpectOptions<T> =
    ExpectBuilder.OptionsChooser.createAndBuild(configuration)
