package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.reporting.*
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslationSupplier
import ch.tutteli.atrium.reporting.translating.Translator
import java.util.*

/**
 * The minimum contract of the 'abstract factory' of atrium.
 *
 * It provides factory methods to create:
 * - [AssertionPlant]
 * - [AssertionPlantNullable]
 * - [CheckingAssertionPlant]
 * - [CollectingAssertionPlant]
 * - [AssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [TranslationSupplier]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [AssertionPairFormatter]
 * - [Reporter]
 */
interface ICoreFactory {

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(assertionVerb: Translatable, subject: T, reporter: Reporter): ReportingAssertionPlant<T>
        = newReportingPlant(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(assertionVerb: Translatable, subject: T, assertionChecker: AssertionChecker): ReportingAssertionPlant<T>
        = newReportingPlant(AssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker, RawString.NULL))

    /**
     * Creates a [ReportingAssertionPlant] which checks and reports added [Assertion]s.
     *
     * It uses the [AssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlant<T>


    /**
     * Creates a [ReportingAssertionPlant] which [AssertionPlant.addAssertionsCreatedBy] the
     * given [assertionCreator] lambda where the created [Assertion]s are added as a group and usually (depending on
     * the configured [Reporter]) reported as a whole.
     *
     * It creates a [ICoreFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     * @param assertionCreator The
     *
     * @return The newly created [AssertionPlant] which can be used to postulate further assertions.
     *
     * @throws AssertionError The newly created [AssertionPlant] might throw an [AssertionError] in case a
     *   created [Assertion] does not hold.
     */
    fun <T : Any> newReportingPlantAndAddAssertionsCreatedBy(assertionVerb: Translatable, subject: T, reporter: Reporter, assertionCreator: AssertionPlant<T>.() -> Unit)
        = newReportingPlant(assertionVerb, subject, reporter)
        .addAssertionsCreatedBy(assertionCreator)


    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: Translatable, subject: T, reporter: Reporter, nullRepresentation: Any = RawString.NULL): ReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter), nullRepresentation)

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [Assertion]s.
     *   (see [AssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: Translatable, subject: T, assertionChecker: AssertionChecker, nullRepresentation: Any): ReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(AssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker, nullRepresentation))

    /**
     * Creates a [ReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the [AssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(commonFields: AssertionPlantWithCommonFields.CommonFields<T>): ReportingAssertionPlantNullable<T>

    /**
     * Creates a [CheckingAssertionPlant] which provides a method to check whether
     * [allAssertionsHold][CheckingAssertionPlant.allAssertionsHold].
     *
     * @param subject The subject for which this plant will create [Assertion]s.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCheckingPlant(subject: T): CheckingAssertionPlant<T>

    /**
     * Creates a [CollectingAssertionPlant] which is intended to be used as receiver object in lambdas to collect
     * created [Assertion]s inside the lambda.
     *
     * Notice, that this [AssertionPlant] might not even provide a [AssertionPlant.subject] in which case it
     * throws a [PlantHasNoSubjectException] if [subject][AssertionPlant.subject] is accessed.
     * Use [newCheckingPlant] instead if you want to know whether the assertions hold.
     *
     * @param subjectProvider The function which will either provide the subject for this plant or throw an
     *   [PlantHasNoSubjectException] in case it cannot be provided.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCollectingPlant(subjectProvider: () -> T): CollectingAssertionPlant<T>

    /**
     * Creates an [AssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @param reporter The reporter which is used to report [Assertion]s.
     *
     * @return The newly created assertion checker.
     */
    fun newThrowingAssertionChecker(reporter: Reporter): AssertionChecker

    /**
     * Creates an [AssertionChecker] which creates an [AssertionGroup] of [type][AssertionGroup.type]
     * [FeatureAssertionGroupType] instead of checking assertions and delegates this task to the given
     * [subjectPlant] by adding (see [AssertionPlant.addAssertion]) the created assertion group to it.
     *
     * @param subjectPlant The assertion plant to which the created [AssertionGroup] of [type][AssertionGroup.type]
     *   [FeatureAssertionGroupType] will be [added][AssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any> newFeatureAssertionChecker(subjectPlant: AssertionPlant<T>): AssertionChecker


    /**
     * Creates an [AssertionChecker] which delegates the checking of [Assertion]s to the given [subjectPlant]
     * by adding (see [AssertionPlant.addAssertion]) the assertions to the given [subjectPlant].
     *
     * @param subjectPlant The assertion plant to which the [Assertion]s will be [added][AssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: BaseAssertionPlant<T, *>): AssertionChecker


    /**
     * Creates a [MethodCallFormatter] which represents arguments of a method call by using their [Object.toString]
     * representation with the exception of:
     * - [CharSequence], is wrapped in quotes (`"`) and line breaks (CR or/and LF) are escaped so that the
     *   whole representation remains on one line.
     * - [Char] is wrapped in apostrophes (`'`)
     *
     * @return The newly created method call formatter.
     */
    fun newMethodCallFormatter(): MethodCallFormatter


    /**
     * Creates a [Translator] which translates [Translatable]s to [primaryLocale] and falls back
     * to [fallbackLocales] (in the given order) in case no translation exists for [primaryLocale].
     *
     * It uses the given [translationSupplier] to retrieve all available translations.
     * In case no translation exists for a given property (neither for the [primaryLocale] nor for
     * any [fallbackLocales]) then it uses [Translatable]'s [getDefault][Translatable.getDefault].
     * As consequence a [Translator] does not or rather should not support [Locale.ROOT] -- users are discouraged
     * to define properties files for Locale.ROOT.
     * An implementation based on [ResourceBundle] would still take Locale.ROOT into account but apply it before the
     * defined [fallbackLocales] have been considered.
     *
     * Please refer to the documentation of [Translator] to see to which extend a translator has to be compatible
     * with [ResourceBundle].
     *
     * @param translationSupplier Provides the translations for a desired [Locale].
     * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
     *   given [Translatable].
     * @param primaryLocale The [Locale] to which the translator translates per default.
     * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for [primaryLocale]
     *   or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
     *
     * @return The newly created translator.
     *
     * @throws IllegalArgumentException in case [primaryLocale] or [fallbackLocales] have as language `no` or if they
     *   have: as language `zh`, country is not set and script is either `Hant` or `Hans`.
     */
    fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator

    /**
     * Creates a [TranslationSupplier] which is based on properties and is compatible with [ResourceBundle] concerning
     * the structure of the properties files.
     *
     * For instance, the translations for `ch.tutteli.atrium.DescriptionAnyAssertion` and the [Locale] `de_CH` are
     * stored in a properties file named `DescriptionAnyAssertion_de_CH.properties` in the folder `/ch/tutteli/atrium/`.
     * Moreover the files need to be encoded in ISO-8859-1 (restriction to be compatible with [ResourceBundle]).
     *
     * An entry in such a file would look like as follows:
     * `TO_BE = a translation for TO_BE`
     *
     * @return The newly created translation supplier.
     */
    fun newPropertiesBasedTranslationSupplier(): TranslationSupplier

    /**
     * Creates a [LocaleOrderDecider] which decides in which order [Locale]s are processed to find a translation for a
     * given [Translatable].
     *
     * Please refer to the documentation of [LocaleOrderDecider] to see to which extend a translator has to be
     * compatible with [ResourceBundle].
     *
     * @return The newly created [Locale] order decider.
     */
    fun newLocaleOrderDecider(): LocaleOrderDecider


    /**
     * Creates an [ObjectFormatter] which represents objects by using their [Object.toString] representation
     * including [Class.name] and their [System.identityHashCode].
     *
     * @return The newly created object formatter.
     */
    fun newDetailedObjectFormatter(translator: Translator): ObjectFormatter

    /**
     * Creates an [AssertionFormatterController] which all be used per default for [newAssertionFormatterFacade].
     *
     * @return The newly created assertion formatter controller.
     */
    fun newAssertionFormatterController(): AssertionFormatterController

    /**
     * Creates an [AssertionFormatterFacade] which shall be used per default for [newOnlyFailureReporter].
     *
     * @param assertionFormatterController The [AssertionFormatterController] which shall be used for formatting.
     *
     * @return The newly created assertion formatter facade.
     */
    fun newAssertionFormatterFacade(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacade

    /**
     * Creates an [AssertionPairFormatter] which is intended for text output (e.g. for the console) and puts assertion
     * pairs on the same line.
     *
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextSameLineAssertionPairFormatter(objectFormatter: ObjectFormatter, translator: Translator): AssertionPairFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and serves as
     * fallback if no other formatter is able to format a given [Assertion].
     *
     * Typically this includes the formatting of the [AssertionGroup] with a [RootAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [RootAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [FeatureAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [PrefixFeatureAssertionGroupHeader] as prefix of the group header and [FeatureAssertionGroupType] as prefix
     * for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [ListAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [ListAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [AssertionGroup]s of type [ExplanatoryAssertionGroupType] by creating an
     * [AssertionFormatterParameterObject] which indicates that formatting its [AssertionGroup.assertions] happens within
     * an explanatory assertion group.
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     *   for [ExplanatoryAssertionGroupType] as prefix for each [Assertion] in [AssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     *
     * @return The newly created assertion formatter.
     */
    fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out BulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter

    /**
     * Registers all available [AssertionFormatter]s  -- which are intended for text format (e.g. for the console)
     * -- to the given [assertionFormatterFacade] using the given [textAssertionPairFormatter].
     *
     * Should at least support [RootAssertionGroupType], [FeatureAssertionGroupType], [ListAssertionGroupType],
     * [SummaryAssertionGroupType] and [ExplanatoryAssertionGroupType] (see [AssertionGroupBuilder]).
     *
     * @param bulletPoints The bullet points used in reporting to prefix each [Assertion] in
     *   [AssertionGroup.assertions].
     * @param assertionFormatterFacade The [AssertionFormatterFacade] to which all [AssertionFormatter]s with text
     *   reporting capabilities should be registered.
     * @param textAssertionPairFormatter An [AssertionPairFormatter] which is intended for text format.
     * @param objectFormatter The formatter which is used to format objects other than [Assertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [DescriptiveAssertion.description].
     */
    fun registerTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out BulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        textAssertionPairFormatter: AssertionPairFormatter,
        objectFormatter: ObjectFormatter,
        translator: Translator)

    /**
     * Creates a [Reporter] which reports only failing assertions
     * and uses the given [assertionFormatterFacade] to format assertions and messages.
     *
     * @param assertionFormatterFacade The formatter which is used to format [Assertion]s.
     *
     * @return The newly created reporter.
     */
    fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
}
