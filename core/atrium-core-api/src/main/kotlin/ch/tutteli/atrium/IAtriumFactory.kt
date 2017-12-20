@file:JvmName("IAtriumFactoryExtensions")

package ch.tutteli.atrium

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.checking.IAssertionChecker
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
 * - [IAssertionPlant]
 * - [IAssertionChecker]
 * - [MethodCallFormatter]
 * - [Translator]
 * - [TranslationSupplier]
 * - [LocaleOrderDecider]
 * - [ObjectFormatter]
 * - [AssertionFormatterFacade]
 * - [AssertionFormatterController]
 * - [AssertionFormatter]
 * - [Reporter]
 */
interface IAtriumFactory {

    /**
     * Creates an [IReportingAssertionPlant] which checks and reports added [IAssertion]s.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(assertionVerb: Translatable, subject: T, reporter: Reporter): IReportingAssertionPlant<T>
        = newReportingPlant(assertionVerb, subject, newThrowingAssertionChecker(reporter))

    /**
     * Creates an [IReportingAssertionPlant] which checks and reports added [IAssertion]s.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(assertionVerb: Translatable, subject: T, assertionChecker: IAssertionChecker): IReportingAssertionPlant<T>
        = newReportingPlant(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker, RawString.NULL))

    /**
     * Creates an [IReportingAssertionPlant] which checks and reports added [IAssertion]s.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newReportingPlant(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlant<T>


    /**
     * Creates an [IReportingAssertionPlant] which [IAssertionPlant.addAssertionsCreatedBy] the
     * given [assertionCreator] lambda where the created [IAssertion]s are added as a group and usually (depending on
     * the configured [Reporter]) reported as a whole.
     *
     * It creates a [IAtriumFactory.newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     * @param assertionCreator The
     *
     * @return The newly created [IAssertionPlant] which can be used to postulate further assertions.
     *
     * @throws AssertionError The newly created [IAssertionPlant] might throw an [AssertionError] in case a
     *         created [IAssertion] does not hold.
     */
    fun <T : Any> newReportingPlantAndAddAssertionsCreatedBy(assertionVerb: Translatable, subject: T, reporter: Reporter, assertionCreator: IAssertionPlant<T>.() -> Unit)
        = newReportingPlant(assertionVerb, subject, reporter)
        .addAssertionsCreatedBy(assertionCreator)


    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It creates a [newThrowingAssertionChecker] based on the given [reporter] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param reporter The reporter which will be use for a [newThrowingAssertionChecker].
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: Translatable, subject: T, reporter: Reporter, nullRepresentation: Any = RawString.NULL): IReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(assertionVerb, subject, newThrowingAssertionChecker(reporter), nullRepresentation)

    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the given [assertionChecker] for assertion checking.
     *
     * @param assertionVerb The assertion verb which will be used inter alia in reporting
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionVerb]).
     * @param subject The subject for which this plant will create/check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.subject]).
     * @param assertionChecker The checker which will be used to check [IAssertion]s.
     *        (see [IAssertionPlantWithCommonFields.CommonFields.assertionChecker]).
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(assertionVerb: Translatable, subject: T, assertionChecker: IAssertionChecker, nullRepresentation: Any): IReportingAssertionPlantNullable<T>
        = newReportingPlantNullable(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, subject, assertionChecker, nullRepresentation))

    /**
     * Creates an [IReportingAssertionPlantNullable] which is the entry point for assertions about nullable types.
     *
     * It uses the [IAssertionPlantWithCommonFields.CommonFields.assertionChecker] of the given [commonFields] for
     * assertion checking.
     *
     * @param commonFields The commonFields for the new assertion plant.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any?> newReportingPlantNullable(commonFields: IAssertionPlantWithCommonFields.CommonFields<T>): IReportingAssertionPlantNullable<T>

    /**
     * Creates an [ICheckingAssertionPlant] which provides a method to check whether
     * [allAssertionsHold][ICheckingAssertionPlant.allAssertionsHold].
     *
     * @param subject The subject for which this plant will create [IAssertion]s.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCheckingPlant(subject: T): ICheckingAssertionPlant<T>

    /**
     * Creates an [ICollectingAssertionPlant] which is intended to be used as receiver object in lambdas to collect
     * created [IAssertion]s inside the lambda.
     *
     * Notice, that this [IAssertionPlant] might not even provide a [IAssertionPlant.subject] in which case it
     * throws an [PlantHasNoSubjectException] if [subject][IAssertionPlant.subject] is accessed.
     * Use [newCheckingPlant] instead if you want to know whether the assertions hold.
     *
     * @param subjectProvider The function which will either provide the subject for this plant or throw an
     * [PlantHasNoSubjectException] in case it cannot be provided.
     *
     * @return The newly created assertion plant.
     */
    fun <T : Any> newCollectingPlant(subjectProvider: () -> T): ICollectingAssertionPlant<T>

    /**
     * Creates an [IAssertionChecker] which throws [AssertionError]s in case an assertion fails
     * and uses the given [reporter] for reporting.
     *
     * @param reporter The reporter which is used to report [IAssertion]s.
     *
     * @return The newly created assertion checker.
     */
    fun newThrowingAssertionChecker(reporter: Reporter): IAssertionChecker

    /**
     * Creates an [IAssertionChecker] which creates an [IAssertionGroup] of [type][IAssertionGroup.type]
     * [IFeatureAssertionGroupType] instead of checking assertions and delegates this task to the given
     * [subjectPlant] by adding (see [IAssertionPlant.addAssertion]) the created assertion group to it.
     *
     * @param subjectPlant The assertion plant to which the created [IAssertionGroup] of [type][IAssertionGroup.type]
     *        [IFeatureAssertionGroupType] will be [added][IAssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any> newFeatureAssertionChecker(subjectPlant: IAssertionPlant<T>): IAssertionChecker


    /**
     * Creates an [IAssertionChecker] which delegates the checking of [IAssertion]s to the given [subjectPlant]
     * by adding (see [IAssertionPlant.addAssertion]) the assertions to the given [subjectPlant].
     *
     * @param subjectPlant The assertion plant to which the [IAssertion]s will be [added][IAssertionPlant.addAssertion].
     *
     * @return The newly created assertion checker.
     */
    fun <T : Any?> newDelegatingAssertionChecker(subjectPlant: IBaseAssertionPlant<T, *>): IAssertionChecker


    /**
     * Creates an [MethodCallFormatter] which represents arguments of a method call by using their [Object.toString]
     * representation with the exception of:
     * - [CharSequence], is wrapped in quotes (`"`) and line breaks (CR or/and LF) are escaped so that the
     *   whole representation remains on one line.
     * - [Char] is wrapped in apostrophes (`'`)
     *
     * @return The newly created method call formatter.
     */
    fun newMethodCallFormatter(): MethodCallFormatter


    /**
     * Creates an [Translator] which translates [Translatable]s to [primaryLocale] and falls back
     * to [fallbackLocales] (in the given order) in case no translation exists for [primaryLocale].
     *
     * It uses the given [translationSupplier] to retrieve all available translations.
     * In case no translation exists for a given property (neither for the [primaryLocale] nor for
     * any [fallbackLocales]) then it uses [Translatable]'s [getDefault][Translatable.getDefault].
     * As consequence an [Translator] does not or rather should not support [Locale.ROOT] -- users are discouraged
     * to define properties files for Locale.ROOT.
     * An implementation based on [ResourceBundle] would still take Locale.ROOT into account but apply it before the
     * defined [fallbackLocales] have been considered.
     *
     * Please refer to the documentation of [Translator] to see to which extend a translator has to be compatible
     * with [ResourceBundle].
     *
     * @param translationSupplier Provides the translations for a desired [Locale].
     * @param localeOrderDecider Decides in which order [Locale]s are processed to find a translation for a
     *        given [ITranslatable].
     * @param primaryLocale The [Locale] to which the translator translates per default.
     * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for [primaryLocale]
     *        or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
     *
     * @return The newly created translator.
     *
     * @throws IllegalArgumentException in case [primaryLocale] or [fallbackLocales] have as language `no` or if they
     *         have: as language `zh`, country is not set and script is either `Hant` or `Hans`.
     */
    fun newTranslator(translationSupplier: TranslationSupplier, localeOrderDecider: LocaleOrderDecider, primaryLocale: Locale, vararg fallbackLocales: Locale): Translator

    /**
     * Creates an [TranslationSupplier] which is based on properties and is compatible with [ResourceBundle] concerning
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
     * Creates an [LocaleOrderDecider] which decides in which order [Locale]s are processed to find a translation for a
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
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and serves as
     * fallback if no other formatter is able to format a given [IAssertion].
     *
     * Typically this includes the formatting of the [IAssertionGroup] with a [RootAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [RootAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFallbackAssertionFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IFeatureAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [PrefixFeatureAssertionGroupHeader] as prefix of the group header and [IFeatureAssertionGroupType] as prefix
     * for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextFeatureAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IListAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [IListAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [IBasicAssertion.description].
     *
     * @return The newly created assertion formatter.
     */
    fun newTextListAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController, objectFormatter: ObjectFormatter, translator: Translator): AssertionFormatter

    /**
     * Creates an [AssertionFormatter] which is intended for text output (e.g. for the console) and
     * formats [IAssertionGroup]s of type [IExplanatoryAssertionGroupType] by creating an
     * [AssertionFormatterMethodObject] which indicates that formatting its [IAssertionGroup.assertions] happens within
     * an explanatory assertion group.
     *
     * @param bulletPoints The bullet points used in reporting; will typically use the bullet point registered
     * for [IExplanatoryAssertionGroupType] as prefix for each [IAssertion] in [IAssertionGroup.assertions].
     * @param assertionFormatterController The controller used to steer the flow of the reporting.
     *
     * @return The newly created assertion formatter.
     */
    fun newTextExplanatoryAssertionGroupFormatter(bulletPoints: Map<Class<out IBulletPointIdentifier>, String>, assertionFormatterController: AssertionFormatterController): AssertionFormatter

    /**
     * Registers all available [AssertionFormatter]s -- which put assertion pairs on the same line and report in
     * text format (e.g. for the console) -- to the given [assertionFormatterFacade].
     *
     * Should at least support [RootAssertionGroupType], [IFeatureAssertionGroupType], [IListAssertionGroupType],
     * [ISummaryAssertionGroupType] and [IExplanatoryAssertionGroupType].
     *
     * @param bulletPoints The bullet points used in reporting to prefix each [IAssertion] in
     * [IAssertionGroup.assertions].
     * @param assertionFormatterFacade The [AssertionFormatterFacade] to which all [AssertionFormatter]s with
     *        same line capabilities and text reporting should be registered.
     * @param objectFormatter The formatter which is used to format objects other than [IAssertion]s.
     * @param translator The translator which is used to translate [Translatable] such as [IBasicAssertion.description].
     */
    fun registerSameLineTextAssertionFormatterCapabilities(
        bulletPoints: Map<Class<out IBulletPointIdentifier>, String>,
        assertionFormatterFacade: AssertionFormatterFacade,
        objectFormatter: ObjectFormatter,
        translator: Translator)

    /**
     * Creates an [Reporter] which reports only failing assertions
     * and uses the given [assertionFormatterFacade] to format assertions and messages.
     *
     * @param assertionFormatterFacade The formatter which is used to format [IAssertion]s.
     *
     * @return The newly created reporter.
     */
    fun newOnlyFailureReporter(assertionFormatterFacade: AssertionFormatterFacade): Reporter
}
