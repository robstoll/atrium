package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.BulletPointIdentifier
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

/**
 * A builder to create a [Reporter] consisting of several components.
 */
class ReporterBuilder(private val assertionFormatterFacade: AssertionFormatterFacade) {

    /**
     * Uses [AtriumFactory.newOnlyFailureReporter] as [Reporter].
     */
    fun buildOnlyFailureReporter(): Reporter
        = AtriumFactory.newOnlyFailureReporter(assertionFormatterFacade)

    /**
     * Uses the given [factory] to build a custom [Reporter].
     */
    fun buildCustomReporter(factory: (AssertionFormatterFacade) -> Reporter): Reporter
        = factory(assertionFormatterFacade)

    /**
     * Provides options to create a [Translator] or [TranslationSupplier].
     */
    companion object {

        /**
         * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
         * of [TranslatableWithArgs].
         *
         * [UsingDefaultTranslator] does not require a [TranslationSupplier] nor a [LocaleOrderDecider] and thus
         * the options to specify implementations of them are skipped.
         *
         * Notice that [UsingDefaultTranslator] does not translate but uses what [Translatable.getDefault] returns.
         * Also notice, that if you omit the [primaryLocale] then [Locale.getDefault] is used.
         *
         * @param primaryLocale The [Locale] used to format arguments of [TranslatableWithArgs].
         */
        fun withoutTranslations(primaryLocale: Locale = Locale.getDefault())
            = ObjectFormatterOptions(UsingDefaultTranslator(primaryLocale))

        /**
         * Uses the given [translator] as [Translator] skipping the options for [TranslationSupplier] and
         * [LocaleOrderDecider] assuming the given [translator] is implemented differently -- use
         * [withDefaultTranslationSupplier] or [withTranslationSupplier] in case the given [translator] requires
         * a [TranslationSupplier] or a [LocaleOrderDecider].
         */
        fun withTranslator(translator: Translator)
            = ObjectFormatterOptions(translator)

        /**
         * Uses [AtriumFactory.newPropertiesBasedTranslationSupplier] as [TranslationSupplier].
         */
        fun withDefaultTranslationSupplier()
            = LocaleOrderDeciderOptions(AtriumFactory.newPropertiesBasedTranslationSupplier())

        /**
         * Uses the given [translationSupplier] as [TranslationSupplier].
         */
        fun withTranslationSupplier(translationSupplier: TranslationSupplier)
            = LocaleOrderDeciderOptions(translationSupplier)
    }

    class LocaleOrderDeciderOptions(private val translationSupplier: TranslationSupplier) {

        /**
         * Uses [AtriumFactory.newLocaleOrderDecider] as [LocaleOrderDecider].
         */
        fun withDefaultLocaleOrderDecider()
            = TranslatorOptions(translationSupplier, AtriumFactory.newLocaleOrderDecider())

        /**
         * Uses [localeOrderDecider] as [LocaleOrderDecider].
         */
        fun withLocaleOrderDecider(localeOrderDecider: LocaleOrderDecider)
            = TranslatorOptions(translationSupplier, localeOrderDecider)
    }

    class TranslatorOptions(private val translationSupplier: TranslationSupplier, private val localeOrderDecider: LocaleOrderDecider) {

        /**
         * Uses [AtriumFactory.newTranslator] as [Translator] where the specified [translationSupplier] is used to
         * retrieve translations, the specified [localeOrderDecider] to determine candidate [Locale]s and
         * [primaryLocale] is used as primary [Locale] and the optional [fallbackLocales] as fallback [Locale]s.
         *
         * @param primaryLocale The [Locale] for which the [Translator] will first search translations --
         *        it will also be used to format arguments of [TranslatableWithArgs].
         * @param fallbackLocales One [Locale] after another (in the given order) will be considered as primary Locale
         *        in case no translation was found the previous primary Locale.
         */
        fun withDefaultTranslator(primaryLocale: Locale, vararg fallbackLocales: Locale)
            = ObjectFormatterOptions(AtriumFactory.newTranslator(translationSupplier, localeOrderDecider, primaryLocale, *fallbackLocales))

        /**
         * Uses the given [factory] to build a [Translator].
         */
        fun withTranslator(factory: (TranslationSupplier, LocaleOrderDecider) -> Translator)
            = ObjectFormatterOptions(factory(translationSupplier, localeOrderDecider))
    }

    /**
     * Provides options to create an [ObjectFormatter].
     */
    class ObjectFormatterOptions(private val translator: Translator) {
        /**
         * Uses [AtriumFactory.newDetailedObjectFormatter] as [ObjectFormatter].
         */
        fun withDetailedObjectFormatter()
            = AssertionFormatterControllerOptions(AtriumFactory.newDetailedObjectFormatter(translator), translator)

        /**
         * Uses the given [factory] to build a custom [ObjectFormatter].
         */
        fun withObjectFormatter(factory: (Translator) -> ObjectFormatter)
            = AssertionFormatterControllerOptions(factory(translator), translator)
    }

    /**
     * Provides options to create an [AssertionFormatterController].
     */
    class AssertionFormatterControllerOptions(private val objectFormatter: ObjectFormatter, private val translator: Translator) {
        /**
         * Uses [AtriumFactory.newAssertionFormatterController] as [AssertionFormatterController].
         */
        fun withDefaultAssertionFormatterController()
            = AssertionFormatterFacadeOptions(AtriumFactory.newAssertionFormatterController(), objectFormatter, translator)

        /**
         * Uses the given [assertionFormatterController] a custom [AssertionFormatterController].
         */
        fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController)
            = AssertionFormatterFacadeOptions(assertionFormatterController, objectFormatter, translator)
    }

    /**
     * Provides options to create an [AssertionFormatterFacade].
     */
    class AssertionFormatterFacadeOptions(private val assertionFormatterController: AssertionFormatterController, private val objectFormatter: ObjectFormatter, private val translator: Translator) {
        /**
         * Uses [AtriumFactory.newAssertionFormatterFacade] as [AssertionFormatterFacade].
         */
        fun withDefaultAssertionFormatterFacade()
            = AssertionPairFormatterOptions(AssertionFormatterChosenOptions(
            AtriumFactory.newAssertionFormatterFacade(assertionFormatterController), objectFormatter, translator))

        /**
         * Uses the given [factory] to build a custom [AssertionFormatterFacade].
         */
        fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade)
            = AssertionPairFormatterOptions(AssertionFormatterChosenOptions(
            factory(assertionFormatterController), objectFormatter, translator))
    }

    class AssertionFormatterChosenOptions(val assertionFormatterFacade: AssertionFormatterFacade, val objectFormatter: ObjectFormatter, val translator: Translator)

    /**
     * Provides options to create an [AssertionPairFormatter].
     */
    class AssertionPairFormatterOptions(private val options: AssertionFormatterChosenOptions) {

        /**
         * Uses [AtriumFactory.newTextSameLineAssertionPairFormatter] as [AssertionPairFormatter].
         */
        fun withTextSameLineAssertionPairFormatter()
            = TextAssertionFormatterOptions(options, AtriumFactory.newTextSameLineAssertionPairFormatter(options.objectFormatter, options.translator))

        /**
         * Uses the given [factory] to build a custom [AssertionPairFormatter].
         */
        fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter)
            = TextAssertionFormatterOptions(options, factory(options.objectFormatter, options.translator))

        @Deprecated("Will be removed in 0.7.0 -- choose an AssertionPairFormatter instead, use the suggestion replacement if you want to stick to same line formatting",
            ReplaceWith("withTextSameLineAssertionPairFormatter().withDefaultTextCapabilities(bulletPoints)"))
        fun withSameLineTextAssertionFormatter(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterBuilder
            = withTextSameLineAssertionPairFormatter()
            .withDefaultTextCapabilities(*bulletPoints)
    }

    /**
     * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
     *
     * @see AssertionFormatterFacadeOptions
     */
    class TextAssertionFormatterOptions(private val options: AssertionFormatterChosenOptions, private val assertionPairFormatter: AssertionPairFormatter) {

        /**
         * Uses [AtriumFactory.registerTextAssertionFormatterCapabilities] to register the default [AssertionFormatter]s
         * intended for text output -- using the defined [assertionPairFormatter],
         * [AssertionFormatterChosenOptions.objectFormatter] and [AssertionFormatterChosenOptions.translator]
         * -- to the specified [AssertionFormatterChosenOptions.assertionFormatterFacade] where the given [bulletPoints] can be used to customise
         * the predefined bullet points.
         *
         * Have a look at the sub types of [BulletPointIdentifier] to get a feel for what and how you can customise
         * bullet points.
         */
        fun withDefaultTextCapabilities(vararg bulletPoints: Pair<Class<out BulletPointIdentifier>, String>): ReporterBuilder {
            AtriumFactory.registerTextAssertionFormatterCapabilities(
                bulletPoints.toMap(), options.assertionFormatterFacade, assertionPairFormatter, options.objectFormatter, options.translator)
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
