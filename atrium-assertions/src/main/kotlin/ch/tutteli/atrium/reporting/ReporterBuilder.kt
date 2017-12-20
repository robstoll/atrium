package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

/**
 * A builder to create an [Reporter] consisting of several components.
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
     * Provides options to create an [Translator] or [TranslationSupplier].
     */
    companion object {

        /**
         * Uses [UsingDefaultTranslator] as [Translator] where the given [primaryLocale] is used to format arguments
         * of [TranslatableWithArgs].
         *
         * [UsingDefaultTranslator] does not require an [TranslationSupplier] nor an [LocaleOrderDecider] and thus
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
         * an [TranslationSupplier] or an [LocaleOrderDecider].
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

        /**
         * Deprecated do not use it any longer and replace it with suggestion instead.
         */
        @Deprecated("will be removed in 0.6.0", ReplaceWith("ReporterBuilder\n" +
            "    .withoutTranslations()\n" +
            "    .withDetailedObjectFormatter()\n" +
            "    .withDefaultAssertionFormatterController()\n" +
            "    .withDefaultAssertionFormatterFacade()"))
        fun withDetailedObjectFormatter()
            = withoutTranslations()
            .withDetailedObjectFormatter()
            .withDefaultAssertionFormatterController()
            .withDefaultAssertionFormatterFacade()
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
         *        it will also be used to format arguments of [ITranslatableWithArgs].
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
            = AssertionFormatterOptions(AtriumFactory.newAssertionFormatterFacade(assertionFormatterController), objectFormatter, translator)

        /**
         * Uses the given [factory] to build a custom [AssertionFormatterFacade].
         */
        fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade)
            = AssertionFormatterOptions(factory(assertionFormatterController), objectFormatter, translator)
    }

    /**
     * Provides options to register [AssertionFormatter]s to the chosen [AssertionFormatterFacade].
     *
     * @see AssertionFormatterFacadeOptions
     */
    class AssertionFormatterOptions(private val assertionFormatterFacade: AssertionFormatterFacade, private val objectFormatter: ObjectFormatter, private val translator: Translator) {

        /**
         * Uses [AtriumFactory.registerSameLineTextAssertionFormatterCapabilities] to register [AssertionFormatter] to
         * the [assertionFormatterFacade] where the given [bulletPoints] can be used to customise the predefined bullet
         * points.
         *
         * Have a look at the sub types of [IBulletPointIdentifier] to get a feel for what and how you can customise
         * bullet points.
         */
        fun withSameLineTextAssertionFormatter(vararg bulletPoints: Pair<Class<out IBulletPointIdentifier>, String>): ReporterBuilder {
            AtriumFactory.registerSameLineTextAssertionFormatterCapabilities(
                bulletPoints.toMap(), assertionFormatterFacade, objectFormatter, translator)
            return ReporterBuilder(assertionFormatterFacade)
        }

        /**
         * Uses the given [assertionFormatterFactory] to create and register an [AssertionFormatter] to the
         * [assertionFormatterFacade].
         */
        fun withAssertionFormatter(assertionFormatterFactory: (AssertionFormatterController) -> AssertionFormatter): ReporterBuilder {
            assertionFormatterFacade.register(assertionFormatterFactory)
            return ReporterBuilder(assertionFormatterFacade)
        }
    }
}
