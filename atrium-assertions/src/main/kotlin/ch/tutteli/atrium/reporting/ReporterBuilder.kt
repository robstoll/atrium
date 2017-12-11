package ch.tutteli.atrium.reporting


import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.assertions.IBulletPointIdentifier
import ch.tutteli.atrium.reporting.translating.*
import java.util.*

/**
 * A builder to create an [IReporter] consisting of several components.
 */
class ReporterBuilder(private val assertionFormatterFacade: IAssertionFormatterFacade) {

    /**
     * Uses [AtriumFactory.newOnlyFailureReporter] as [IReporter].
     */
    fun buildOnlyFailureReporter(): IReporter
        = AtriumFactory.newOnlyFailureReporter(assertionFormatterFacade)

    /**
     * Uses the given [factory] to build a custom [IReporter].
     */
    fun buildCustomReporter(factory: (IAssertionFormatterFacade) -> IReporter): IReporter
        = factory(assertionFormatterFacade)

    /**
     * Provides options to create an [ITranslator].
     */
    companion object {

        /**
         * Uses [UsingDefaultTranslator] as [ITranslator] where the given [primaryLocale] is used to format arguments
         * of [ITranslatableWithArgs].
         *
         * Notice that [UsingDefaultTranslator] does not translate but uses what [ITranslatable.getDefault] returns.
         * Also notice, that if you omit the [primaryLocale] then [Locale.getDefault] is used.
         *
         * @param primaryLocale The [Locale] used to format arguments of [ITranslatableWithArgs].
         */
        fun withoutTranslations(primaryLocale: Locale = Locale.getDefault())
            = ObjectFormatterOptions(UsingDefaultTranslator(primaryLocale))

        /**
         * Uses [AtriumFactory.newTranslator] as [ITranslator] and [AtriumFactory.newPropertiesBasedTranslationSupplier]
         * as its [ITranslationSupplier]. It uses the [primaryLocale] as primary [Locale] and the optional
         * [fallbackLocales] as fallback [Locale]s.
         *
         * @param primaryLocale The [Locale] for which the [ITranslator] will first search translations --
         *        it will also be used to format arguments of [ITranslatableWithArgs].
         * @param fallbackLocales One [Locale] after another (in the given order) will be considered as primary Locale
         *        in case no translation was found the previous primary Locale.
         */
        fun withDefaultTranslatorAndSupplier(primaryLocale: Locale, vararg fallbackLocales: Locale)
            = withDefaultTranslator(AtriumFactory.newPropertiesBasedTranslationSupplier(), primaryLocale, *fallbackLocales)

        /**
         * Uses [AtriumFactory.newTranslator] as [ITranslator] where the given [translationSupplier] is used retrieve
         * translations, [primaryLocale] is used as primary [Locale] and the optional [fallbackLocales] as fallback [Locale]s.
         *
         * @param translationSupplier The supplier which provides translations for [ITranslatable].
         * @param primaryLocale The [Locale] for which the [ITranslator] will first search translations --
         *        it will also be used to format arguments of [ITranslatableWithArgs].
         * @param fallbackLocales One [Locale] after another (in the given order) will be considered as primary Locale
         *        in case no translation was found the previous primary Locale.
         */
        fun withDefaultTranslator(translationSupplier: ITranslationSupplier, primaryLocale: Locale, vararg fallbackLocales: Locale)
            = ObjectFormatterOptions(AtriumFactory.newTranslator(translationSupplier, primaryLocale, *fallbackLocales))

        /**
         * Uses the given [translator] as [ITranslator].
         */
        fun withTranslator(translator: ITranslator)
            = ObjectFormatterOptions(translator)

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

    /**
     * Provides options to create an [IObjectFormatter].
     */
    class ObjectFormatterOptions(private val translator: ITranslator) {
        /**
         * Uses [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         */
        fun withDetailedObjectFormatter()
            = AssertionFormatterControllerOptions(AtriumFactory.newDetailedObjectFormatter(translator), translator)

        /**
         * Uses the given [factory] to build a custom [IObjectFormatter].
         */
        fun withObjectFormatter(factory: (ITranslator) -> IObjectFormatter)
            = AssertionFormatterControllerOptions(factory(translator), translator)
    }

    /**
     * Provides options to create an [IAssertionFormatterController].
     */
    class AssertionFormatterControllerOptions(private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {
        /**
         * Uses [AtriumFactory.newAssertionFormatterController] as [IAssertionFormatterController].
         */
        fun withDefaultAssertionFormatterController()
            = AssertionFormatterFacadeOptions(AtriumFactory.newAssertionFormatterController(), objectFormatter, translator)

        /**
         * Uses the given [assertionFormatterController] a custom [IAssertionFormatterController].
         */
        fun withAssertionFormatterController(assertionFormatterController: IAssertionFormatterController)
            = AssertionFormatterFacadeOptions(assertionFormatterController, objectFormatter, translator)
    }

    /**
     * Provides options to create an [IAssertionFormatterFacade].
     */
    class AssertionFormatterFacadeOptions(private val assertionFormatterController: IAssertionFormatterController, private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {
        /**
         * Uses [AtriumFactory.newAssertionFormatterFacade] as [IAssertionFormatterFacade].
         */
        fun withDefaultAssertionFormatterFacade()
            = AssertionFormatterOptions(AtriumFactory.newAssertionFormatterFacade(assertionFormatterController), objectFormatter, translator)

        /**
         * Uses the given [factory] to build a custom [IAssertionFormatterFacade].
         */
        fun withAssertionFormatterFacade(factory: (IAssertionFormatterController) -> IAssertionFormatterFacade)
            = AssertionFormatterOptions(factory(assertionFormatterController), objectFormatter, translator)
    }

    /**
     * Provides options to register [IAssertionFormatter]s to the chosen [IAssertionFormatterFacade].
     *
     * @see AssertionFormatterFacadeOptions
     */
    class AssertionFormatterOptions(private val assertionFormatterFacade: IAssertionFormatterFacade, private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {

        /**
         * Uses [AtriumFactory.registerSameLineTextAssertionFormatterCapabilities] to register [IAssertionFormatter] to
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
         * Uses the given [assertionFormatterFactory] to create and register an [IAssertionFormatter] to the
         * [assertionFormatterFacade].
         */
        fun withAssertionFormatter(assertionFormatterFactory: (IAssertionFormatterController) -> IAssertionFormatter): ReporterBuilder {
            assertionFormatterFacade.register(assertionFormatterFactory)
            return ReporterBuilder(assertionFormatterFacade)
        }
    }
}
