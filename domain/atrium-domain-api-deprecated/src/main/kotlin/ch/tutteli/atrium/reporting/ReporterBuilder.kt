@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0*/)
package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.core.migration.toAtriumLocale
import ch.tutteli.atrium.reporting.translating.*

/**
 * The *deprecated* builder to create a [Reporter] consisting of several components.
 */
@Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
class ReporterBuilder(private val assertionFormatterFacade: AssertionFormatterFacade) {

    /**
     * Uses [CoreFactory.newOnlyFailureReporter] as [Reporter].
     */
    @Deprecated("Use`withOnlyFailureReporter.build()` from the builder from package domain.builders.reporting", ReplaceWith("this.withOnlyFailureReporter().build()"))
    fun buildOnlyFailureReporter(): Reporter
        = coreFactory.newOnlyFailureReporter(assertionFormatterFacade, coreFactory.newMultiAtriumErrorAdjuster(
            coreFactory.newRemoveAtriumFromAtriumErrorAdjuster(),
                coreFactory.newRemoveRunnerAtriumErrorAdjuster(),
                listOf()
            )
        )

    /**
     * Uses the given [factory] to build a custom [Reporter].
     */
    @Deprecated("Use`withCustomReporter` from the builder from package domain.builders.reporting", ReplaceWith("this.withCustomReporter(factory).build()"))
    fun buildCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): Reporter
        = factory(assertionFormatterFacade)

    /**
     * Provides options to create a [Translator] or [TranslationSupplier].
     */
    @Deprecated("Use reporterBuilder from package domain.builders.reporting; will be removed with 1.0.0")
    companion object {

        /**
         * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
         * of [TranslatableWithArgs].
         *
         * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
         * the options to specify implementations of them are skipped.
         *
         * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
         * Also notice, that if you omit the [primaryLocale] then [java.util.Locale.getDefault] is used.
         *
         * @param primaryLocale The [Locale] used to format arguments of [TranslatableWithArgs].
         */
        @Deprecated(
            "Use reporterBuilder from package domain.builders.reporting; will be removed with 1.0.0",
            ReplaceWith(
                "reporterBuilder.withoutTranslations(primaryLocale.toAtriumLocale())",
                "ch.tutteli.atrium.domain.builders.reporting.reporterBuilder",
                "ch.tutteli.atrium.core.migration.toAtriumLocale"
            )
        )
        fun withoutTranslations(primaryLocale: java.util.Locale = java.util.Locale.getDefault())
            = ObjectFormatterOptions(UsingDefaultTranslator(primaryLocale.toAtriumLocale()))

        /**
         * Uses the given [translator] as [Translator] skipping the options for [TranslationSupplier] and
         * [LocaleOrderDecider] assuming the given [translator] is implemented differently -- use
         * [withDefaultTranslationSupplier] or [withTranslationSupplier] in case the given [translator] requires
         * a [TranslationSupplier] or a [LocaleOrderDecider].
         */
        @Deprecated(
            "Use reporterBuilder from package domain.builders.reporting; will be removed with 1.0.0",
            ReplaceWith(
                "reporterBuilder.withTranslator(translator)",
                "ch.tutteli.atrium.domain.builders.reporting.reporterBuilder"
            )
        )
        fun withTranslator(translator: Translator)
            = ObjectFormatterOptions(translator)

        /**
         * Uses [CoreFactory.newPropertiesBasedTranslationSupplier] as [TranslationSupplier].
         */
        @Deprecated("Use reporterBuilder from package domain.builders.reporting", ReplaceWith(
            "reporterBuilder.withDefaultTranslationSupplier()",
            "ch.tutteli.atrium.domain.builders.reporting.reporterBuilder"
        ))
        fun withDefaultTranslationSupplier()
            = LocaleOrderDeciderOptions(coreFactory.newPropertiesBasedTranslationSupplier())

        /**
         * Uses the given [translationSupplier] as [TranslationSupplier].
         */
        @Deprecated(
            "Use reporterBuilder from package domain.builders.reporting; will be removed with 1.0.0",
            ReplaceWith(
                "reporterBuilder.withTranslationSupplier(translationSupplier)",
                "ch.tutteli.atrium.domain.builders.reporting.reporterBuilder"
            )
        )
        fun withTranslationSupplier(translationSupplier: TranslationSupplier)
            = LocaleOrderDeciderOptions(translationSupplier)
    }

    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class LocaleOrderDeciderOptions(private val translationSupplier: TranslationSupplier) {

        /**
         * Uses [CoreFactory.newLocaleOrderDecider] as [LocaleOrderDecider].
         */
        fun withDefaultLocaleOrderDecider()
            = TranslatorOptions(translationSupplier, coreFactory.newLocaleOrderDecider())

        /**
         * Uses [localeOrderDecider] as [LocaleOrderDecider].
         */
        fun withLocaleOrderDecider(localeOrderDecider: LocaleOrderDecider)
            = TranslatorOptions(translationSupplier, localeOrderDecider)
    }

    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class TranslatorOptions(private val translationSupplier: TranslationSupplier, private val localeOrderDecider: LocaleOrderDecider) {

        /**
         * Uses [CoreFactory.newTranslator] as [Translator] where the specified [translationSupplier] is used to
         * retrieve translations, the specified [localeOrderDecider] to determine candidate [Locale]s and
         * [primaryLocale] is used as primary [Locale] and the optional [fallbackLocales] as fallback [Locale]s.
         *
         * @param primaryLocale The [Locale] for which the [Translator] will first search translations --
         *   it will also be used to format arguments of [TranslatableWithArgs].
         * @param fallbackLocales One [Locale] after another (in the given order) will be considered as primary Locale
         *   in case no translation was found the previous primary Locale.
         */
        fun withDefaultTranslator(primaryLocale: java.util.Locale, vararg fallbackLocales: java.util.Locale)
            = ObjectFormatterOptions(coreFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale.toAtriumLocale(), fallbackLocales.map { it.toAtriumLocale() }))

        /**
         * Uses the given [factory] to build a [Translator].
         */
        fun withTranslator(factory: (TranslationSupplier, LocaleOrderDecider) -> Translator)
            = ObjectFormatterOptions(factory(translationSupplier, localeOrderDecider))
    }

    /**
     * Provides options to create an [ObjectFormatter].
     */
    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class ObjectFormatterOptions(private val translator: Translator) {
        /**
         * Uses [CoreFactory.newDetailedObjectFormatter] as [ObjectFormatter].
         */
        fun withDetailedObjectFormatter()
            = AssertionFormatterControllerOptions(coreFactory.newDetailedObjectFormatter(translator), translator)

        /**
         * Uses the given [factory] to build a custom [ObjectFormatter].
         */
        fun withObjectFormatter(factory: (Translator) -> ObjectFormatter)
            = AssertionFormatterControllerOptions(factory(translator), translator)
    }

    /**
     * Provides options to create an [AssertionFormatterController].
     */
    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class AssertionFormatterControllerOptions(private val objectFormatter: ObjectFormatter, private val translator: Translator) {
        /**
         * Uses [CoreFactory.newAssertionFormatterController] as [AssertionFormatterController].
         */
        fun withDefaultAssertionFormatterController()
            = AssertionFormatterFacadeOptions(coreFactory.newAssertionFormatterController(), objectFormatter, translator)

        /**
         * Uses the given [assertionFormatterController] a custom [AssertionFormatterController].
         */
        fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController)
            = AssertionFormatterFacadeOptions(assertionFormatterController, objectFormatter, translator)
    }

    /**
     * Provides options to create an [AssertionFormatterFacade].
     */
    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class AssertionFormatterFacadeOptions(private val assertionFormatterController: AssertionFormatterController, private val objectFormatter: ObjectFormatter, private val translator: Translator) {
        /**
         * Uses [CoreFactory.newAssertionFormatterFacade] as [AssertionFormatterFacade].
         */
        fun withDefaultAssertionFormatterFacade()
            = AssertionPairFormatterOptions(AssertionFormatterChosenOptions(
            coreFactory.newAssertionFormatterFacade(assertionFormatterController), objectFormatter, translator))

        /**
         * Uses the given [factory] to build a custom [AssertionFormatterFacade].
         */
        fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade)
            = AssertionPairFormatterOptions(AssertionFormatterChosenOptions(
            factory(assertionFormatterController), objectFormatter, translator))
    }

    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class AssertionFormatterChosenOptions(val assertionFormatterFacade: AssertionFormatterFacade, val objectFormatter: ObjectFormatter, val translator: Translator)

    /**
     * Provides options to create an [AssertionPairFormatter].
     */
    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class AssertionPairFormatterOptions(private val options: AssertionFormatterChosenOptions) {

        /**
         * Uses [CoreFactory.newTextSameLineAssertionPairFormatter] as [AssertionPairFormatter].
         */
        fun withTextSameLineAssertionPairFormatter()
            = TextAssertionFormatterOptions(options, coreFactory.newTextSameLineAssertionPairFormatter(options.objectFormatter, options.translator))

        /**
         * Uses the given [factory] to build a custom [AssertionPairFormatter].
         */
        fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter)
            = TextAssertionFormatterOptions(options, factory(options.objectFormatter, options.translator))

        fun withSameLineTextAssertionFormatter(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterBuilder
            = withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities(*bulletPoints)
    }

    /**
     * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
     *
     * @see AssertionFormatterFacadeOptions
     */
    @Deprecated("Use the builder from package domain.builders.reporting; will be removed with 1.0.0")
    class TextAssertionFormatterOptions(private val options: AssertionFormatterChosenOptions, private val assertionPairFormatter: AssertionPairFormatter) {

        /**
         * Uses [CoreFactory.registerTextAssertionFormatterCapabilities] to register the default [AssertionFormatter]s
         * intended for text output -- using the defined [assertionPairFormatter],
         * [AssertionFormatterChosenOptions.objectFormatter] and [AssertionFormatterChosenOptions.translator]
         * -- to the specified [AssertionFormatterChosenOptions.assertionFormatterFacade] where the given [bulletPoints] can be used to customise
         * the predefined bullet points.
         *
         * Have a look at the sub types of [BulletPointIdentifier] to get a feel for what and how you can customise
         * bullet points.
         */
        fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterBuilder {
            coreFactory.registerTextAssertionFormatterCapabilities(
                bulletPoints.associate { it.first.kotlin to it.second }, options.assertionFormatterFacade, assertionPairFormatter, options.objectFormatter, options.translator)
            return ReporterBuilder(options.assertionFormatterFacade)
        }

        /**
         * Uses the given [factory] and [otherFactories] to create and register [AssertionFormatter]s to
         * the specified [AssertionFormatterChosenOptions.assertionFormatterFacade].
         */
        fun withTextAssertionFormatter(
            factory: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter,
            vararg otherFactories: (AssertionFormatterChosenOptions) -> (AssertionFormatterController) -> AssertionFormatter
        ): ReporterBuilder {
            listOf(factory, *otherFactories).forEach {
                options.assertionFormatterFacade.register(it(options))
            }
            return ReporterBuilder(options.assertionFormatterFacade)
        }
    }
}
