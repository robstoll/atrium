package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslationSupplier
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.reporting.translating.UsingDefaultTranslator
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
         * Uses [UsingDefaultTranslator] as [ITranslator] (which does not translate
         * but uses the [ITranslatable]'s [getDefault][ITranslatable.getDefault])
         * and the given [primaryLocale] which falls back to [Locale.getDefault] if not given.
         */
        fun withoutTranslations(primaryLocale: Locale = Locale.getDefault())
            = ObjectFormatterOptions(UsingDefaultTranslator(primaryLocale))

        /**
         * Uses [AtriumFactory.newTranslator] with the given [translationSupplier] as [ITranslator], uses [locale] as primary
         * [Locale] and the optional [fallbackLocales] as fallback [Locale]s.
         */
        fun withTranslations(translationSupplier: ITranslationSupplier, locale: Locale, vararg fallbackLocales: Locale)
            = ObjectFormatterOptions(AtriumFactory.newTranslator(translationSupplier, locale, *fallbackLocales))

        /**
         * Uses the given [translator] as [ITranslator].
         */
        fun withTranslator(translator: ITranslator)
            = ObjectFormatterOptions(translator)

        /**
         * Shortcut for [withoutTranslations].[ObjectFormatterOptions.withDetailedObjectFormatter].
         * [AssertionFormatterControllerOptions.withDefaultAssertionFormatterController].
         * [AssertionFormatterFacadeOptions.withDefaultAssertionFormatterFacade]
         * -- uses [UsingDefaultTranslator] as [ITranslator]
         * and [AtriumFactory.newDetailedObjectFormatter] as [IObjectFormatter].
         *
         * (!) Might be removed in the future
         */
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
         * Uses the given [objectFormatter] as [IObjectFormatter]
         */
        fun withObjectFormatter(objectFormatter: IObjectFormatter)
            = AssertionFormatterControllerOptions(objectFormatter, translator)
    }

    /**
     * Provides options to create an [IAssertionFormatterController].
     */
    class AssertionFormatterControllerOptions(private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {
        fun withDefaultAssertionFormatterController()
            = AssertionFormatterFacadeOptions(AtriumFactory.newAssertionFormatterController(), objectFormatter, translator)
    }

    /**
     * Provides options to create an [IAssertionFormatterFacade].
     */
    class AssertionFormatterFacadeOptions(private val assertionFormatterController: IAssertionFormatterController, private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {
        fun withDefaultAssertionFormatterFacade()
            = AssertionFormatterOptions(AtriumFactory.newAssertionFormatterFacade(assertionFormatterController), objectFormatter, translator)
    }

    /**
     * Provides options to register [IAssertionFormatter]s to the chosen [IAssertionFormatterFacade].
     *
     * @see AssertionFormatterFacadeOptions
     */
    class AssertionFormatterOptions(private val assertionFormatterFacade: IAssertionFormatterFacade, private val objectFormatter: IObjectFormatter, private val translator: ITranslator) {

        /**
         * Uses [AtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
         */
        fun withSameLineTextAssertionFormatter(
            bulletPoint: String = "▪",
            arrow: String = "➤",
            featureBulletPoint: String = "◾",
            listBulletPoint: String = "•",
            indentedListBulletPoint: String = "⋄",
            explanatoryBulletPoint: String = "»"
        ): ReporterBuilder {
            AtriumFactory.registerSameLineTextAssertionFormatterCapabilities(
                bulletPoint,
                arrow,
                featureBulletPoint,
                listBulletPoint,
                indentedListBulletPoint,
                explanatoryBulletPoint,
                assertionFormatterFacade, objectFormatter, translator)
            return ReporterBuilder(assertionFormatterFacade)
        }

        /**
         * Uses [AtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
         */
        @Deprecated("Use withSameLineTextAssertionFormatter instead", ReplaceWith("withSameLineTextAssertionFormatter()"))
        fun withSameLineAssertionFormatter() = withSameLineTextAssertionFormatter()

        /**
         * Uses the given [assertionFormatterFactory] to create a [IAssertionFormatter].
         */
        fun withAssertionFormatter(assertionFormatterFactory: (IAssertionFormatterController) -> IAssertionFormatter): ReporterBuilder {
            assertionFormatterFacade.register(assertionFormatterFactory)
            return ReporterBuilder(assertionFormatterFacade)
        }
    }
}
