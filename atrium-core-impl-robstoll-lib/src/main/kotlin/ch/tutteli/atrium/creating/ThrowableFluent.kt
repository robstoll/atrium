package ch.tutteli.atrium.creating

import ch.tutteli.atrium.AtriumFactory
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.reporting.translating.ITranslatable
import kotlin.reflect.KClass

/**
 * Provides [toThrow] methods for making assertions about a [Throwable]
 * which one expects was thrown.
 *
 * @property commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] is used for failure reporting etc.
 *
 * @constructor Use [AtriumFactory.newThrowableFluent] to create an instance.
 * @param commonFields The [commonFields]'s [subject][IAssertionPlantWithCommonFields.CommonFields.subject]
 *        represents the thrown [Throwable] and will be used in [ThrowableFluent.toThrow]. Its method
 *        [IAssertionPlantWithCommonFields.CommonFields.fail] could be used for failure reporting etc.
 */
class ThrowableFluent internal constructor(
    override val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>
) : IThrowableFluent {

    private constructor(assertionVerb: ITranslatable, throwable: Throwable?, assertionChecker: IAssertionChecker)
        : this(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, throwable, assertionChecker))

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, description: ITranslatable, nullRepresentation: ITranslatable): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder(description, expectedType, commonFields)
        .withNullRepresentation(nullRepresentation)
        .cast()

    override fun <TExpected : Throwable> toThrow(expectedType: KClass<TExpected>, description: ITranslatable, nullRepresentation: ITranslatable, createAssertions: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = AtriumFactory.newDownCastBuilder(description, expectedType, commonFields)
        .withNullRepresentation(nullRepresentation)
        .withLazyAssertions(createAssertions)
        .cast()

    companion object {
        /**
         * Creates a [ThrowableFluent] where executing [act] will determine the
         * [subject](IAssertionPlantWithCommonFields.CommonFields.subject) of [commonFields].
         *
         * @return The newly created [ThrowableFluent].
         */
        fun create(assertionVerb: ITranslatable, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch (t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
