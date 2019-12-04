package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.RootExpect
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.AssertionVerbStepImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.FinalStepImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.OptionsChooserImpl
import ch.tutteli.atrium.domain.builders.reporting.impl.verb.OptionsStepImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Defines the contract to create custom assertion verbs, `Expect<T>` respectively.
 */
interface ExpectBuilder {
    companion object {

        /**
         * Entry point to use the [ExpectBuilder] which helps in creating
         * an assertion verb for the given [subject] or in other words an [Expect] for the given [subject].
         */
        fun <T> forSubject(subject: T): AssertionVerbStep<T> = AssertionVerbStepImpl(Some(subject))
    }

    /**
     * Step which allows to specify the assertion verb which shall be used.
     *
     * @param T the type of the subject.
     */
    interface AssertionVerbStep<T> {
        /**
         * The previously specified subject of the assertion.
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
    interface OptionsStep<T> {
        /**
         * The previously specified subject of the assertion.
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
        fun withOptions(configuration: OptionsChooser.() -> Unit): FinalStep<T> =
            withOptions(ExpectOptions(configuration))

        /**
         * Uses the given [expectOptions].
         */
        fun withOptions(expectOptions: ExpectOptions): FinalStep<T>

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
    interface OptionsChooser {

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
         * Uses the given [representation] as representation of the subject instead of using the subject as such to
         * represent itself.
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withRepresentation(representation: Any)

        /**
         * Use the given [representation] as custom representation for `null`.
         *
         * Notice, if you want to use text (e.g. a [String]) as representation,
         * then wrap it into a [RawString] via [RawString.create] and pass the [RawString] instead.
         */
        fun withNullRepresentation(representation: Any)

        /**
         * Uses the given [reporter] instead of the default reporter.
         */
        fun withReporter(reporter: Reporter)

        companion object {
            fun createAndBuild(configuration: OptionsChooser.() -> Unit): ExpectOptions =
                OptionsChooserImpl().apply(configuration).build()
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
         * Either the previously specified [ExpectOptions] or `null`.
         */
        val options: ExpectOptions?

        /**
         * Creates a new [Expect] based on the previously defined maybeOptions.
         */
        fun build(): RootExpect<T>

        companion object {
            fun <T> create(
                maybeSubject: Option<T>,
                assertionVerb: Translatable,
                options: ExpectOptions?
            ): FinalStep<T> = FinalStepImpl(maybeSubject, assertionVerb, options)
        }
    }
}

/**
 * Additional (non-mandatory) options for the [ExpectBuilder] to create an [Expect].
 *
 * @property assertionVerb Defines a custom assertion verb if not null.
 * @property representation Defines a custom representation for the subject if not null.
 * @property nullRepresentation Defines a custom representation for `null` if not null.
 * @property reporter Defines a custom reporter if not null.
 */
data class ExpectOptions(
    val assertionVerb: Translatable? = null,
    val representation: Any? = null,
    val nullRepresentation: Any? = null,
    val reporter: Reporter? = null
) {
    /**
     * Merges the given [options] with this object creating a new [ExpectOptions]
     * where defined properties in [options] will have precedence over properties defined in this instance.
     *
     * For instance, this object has defined [representation] (meaning it is [Some]) and the given [options] as well,
     * then the resulting [ExpectOptions] will have the [representation] of [options].
     */
    fun merge(options: ExpectOptions): ExpectOptions =
        ExpectOptions(
            options.assertionVerb ?: assertionVerb,
            options.representation ?: representation,
            options.nullRepresentation ?: nullRepresentation,
            options.reporter ?: reporter
        )
}

@Suppress("FunctionName")
fun ExpectOptions(configuration: ExpectBuilder.OptionsChooser.() -> Unit): ExpectOptions =
    ExpectBuilder.OptionsChooser.createAndBuild(configuration)
