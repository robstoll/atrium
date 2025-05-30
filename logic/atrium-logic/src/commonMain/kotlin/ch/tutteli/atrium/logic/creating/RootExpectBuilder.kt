package ch.tutteli.atrium.logic.creating

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.creating.impl.*
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.Text
import kotlin.reflect.KClass

/**
 * Defines the contract to create custom expectation verbs, `RootExpect<T>` respectively.
 */
interface RootExpectBuilder {
    companion object {

        /**
         * Entry point to use the [RootExpectBuilder] which helps in creating
         * an expectation verb for the given [subject] or in other words a [RootExpect] for the given [subject].
         */
        fun <T> forSubject(subject: T): ExpectationVerbStep<T> = ExpectationVerbStepImpl(subject)
    }

    /**
     * Step which allows to specify the expectation verb which shall be used.
     *
     * @param T the type of the subject.
     */
    interface ExpectationVerbStep<T> {
        /**
         * The previously specified subject of the expectation.
         */
        val subject: T

        /**
         * Wraps the given [verb] into an [ch.tutteli.atrium.reporting.translating.Untranslatable] and uses it as expectation verb.
         */
        //TODO 1.3.0 remove suppress again
        @Suppress("DEPRECATION")
        fun withVerb(verb: String): OptionsStep<T> = withVerb(ch.tutteli.atrium.reporting.translating.Untranslatable(verb))

        /**
         * Uses the given [verb] as expectation verb.
         */
        //TODO 1.3.0 replace with InlineElement and remove suppression
        @Suppress("DEPRECATION")
        fun withVerb(verb: ch.tutteli.atrium.reporting.translating.Translatable): OptionsStep<T>
    }

    /**
     * Step which allows to override previously defined properties -- such as use a different expectation verb -- but
     * also allows to define options where usually a default value is used, such as use a custom [Reporter].
     *
     * @param T the type of the subject.
     */
    interface OptionsStep<T> {
        /**
         * The previously specified subject of the expectation.
         */
        val subject: T

        /**
         * The previously defined expectation verb.
         */
        //TODO 1.3.0 replace with InlineElement and remove suppression
        @Suppress("DEPRECATION")
        val expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable

        /**
         * Allows to define the [RootExpectOptions] via an [OptionsChooser]-lambda which provides convenience functions.
         *
         * The functions usually start with `set...` and are sometimes overloaded to ease the configuration.
         * Note that the last configuration
         */
        @ExperimentalNewExpectTypes
        fun withOptions(configuration: OptionsChooser<T>.() -> Unit): FinalStep<T> =
            withOptions(RootExpectOptions(configuration))

        /**
         * Uses the given [rootExpectOptions].
         */
        @ExperimentalNewExpectTypes
        fun withOptions(rootExpectOptions: RootExpectOptions<T>): FinalStep<T>

        /**
         * States explicitly that no optional [RootExpectOptions] are defined, which means, `build` will create
         * a new [Expect] based on the previously defined mandatory options but without any optional options or
         * in other words, the default values are used for the optional options.
         *
         * Use [withOptions] if you want to define optional [RootExpectOptions] such as, override the
         * verb, define an own representation or use an own [Reporter].
         */
        fun withoutOptions(): FinalStep<T>

        companion object {
            operator fun <T> invoke(
                subject: T,
                //TODO 1.3.0 replace with InlineElement and remove suppression
                @Suppress("DEPRECATION")
                expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable
            ): OptionsStep<T> = OptionsStepImpl(subject, expectationVerb)
        }
    }

    /**
     * Helper lambda to specify [RootExpectOptions] via convenience methods.
     *
     * Calling multiple times the same method overrides the previously defined value.
     */
    interface OptionsChooser<T> {

        /**
         * Wraps the given [verb] into an [ch.tutteli.atrium.reporting.translating.Untranslatable] and passes it to the overload
         * which expects a [ch.tutteli.atrium.reporting.translating.Translatable] -- this is then used as custom expectation verb
         * instead of the previously defined verb.
         *
         */
        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        fun withVerb(verb: String) {
            withVerb(ch.tutteli.atrium.reporting.translating.Untranslatable(verb))
        }

        /**
         * Uses the given [verb] as expectation verb instead of the previously defined verb.
         */
        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        fun withVerb(verb: ch.tutteli.atrium.reporting.translating.Translatable)

        /**
         * Wraps the given [textRepresentation] into a [Text] and uses it as representation of the subject
         * instead of the representation that has been defined so far (which defaults to the subject itself).
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
         */
        fun withRepresentation(representationProvider: (T) -> Any)

        /**
         * Expects a factory which creates the component of type [I] which is used instead of the currently specified
         * factory.
         */
        @ExperimentalComponentFactoryContainer
        fun <I : Any> withComponent(kClass: KClass<I>, factory: (ComponentFactoryContainer) -> I)

        /**
         * Expects a factory which creates the component of type [I] which is used instead of the currently specified
         * factory.
         */
        @ExperimentalComponentFactoryContainer
        fun <I : Any> withSingletonComponent(kClass: KClass<I>, factory: (ComponentFactoryContainer) -> I)


        @ExperimentalComponentFactoryContainer
        fun <I : Any> prependChainedComponents(kClass: KClass<I>, factories: Sequence<ComponentFactory>)

        companion object {
            @ExperimentalNewExpectTypes
            @OptIn(ExperimentalComponentFactoryContainer::class)
            operator fun <T> invoke(configuration: OptionsChooser<T>.() -> Unit): RootExpectOptions<T> =
                RootExpectOptionsChooserImpl<T>().apply(configuration).build()
        }
    }

    /**
     * Final step in the [RootExpect] building process which creates a new [RootExpect] based
     * on the so far specified options.
     *
     * @param T the type of the subject.
     */
    interface FinalStep<T> {
        /**
         * The previously specified subject of the expectation.
         */
        val subject: T

        /**
         * The previously defined expectation verb.
         */
        //TODO 1.3.0 remove suppress again, use InlineElement instead ?
        @Suppress("DEPRECATION")
        val expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable

        /**
         * Either the previously specified [RootExpectOptions] or `null`.
         */
        @OptIn(ExperimentalNewExpectTypes::class)
        val options: RootExpectOptions<T>?

        /**
         * Creates a new [Expect] based on the previously defined maybeOptions.
         */
        fun build(): RootExpect<T>

        companion object {
            @ExperimentalNewExpectTypes
            operator fun <T> invoke(
                subject: T,
                //TODO 1.3.0 remove suppress again, use InlineElement instead
                @Suppress("DEPRECATION")
                expectationVerb: ch.tutteli.atrium.reporting.translating.Translatable,
                options: RootExpectOptions<T>?
            ): FinalStep<T> = FinalStepImpl(subject, expectationVerb, options)
        }
    }
}
